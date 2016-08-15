package mamFiles;

import Game.GlobalSettings;
import Game.Map.MaMWorld;
import Game.Monsters.MaMMonster;
import Toolbox.BinaryHelpers;
import Toolbox.FileHelpers;
import Toolbox.IValidatable;
//import mamFiles.WOX.WOXSpriteFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static Toolbox.BinaryHelpers.BYTES2INT_lsb;

/**
 * Created by duckman on 3/05/2016.
 */
public abstract class MaMCCFileReader extends MAMFile implements AutoCloseable
{

    protected static class CCFileTocEntry implements IValidatable
    {
        public int ID = 0; //unit16
        public int offset =0; //24bytes
        public int length =0; //uint16
        public int padding = 0; //ubyte

        public CCFileTocEntry() {
        }

        public boolean isPartOfSaveGame()
        {
            return ((ID == 0x2a0c)||(ID == 0x2a1c)||(ID == 0x2a2c)||(ID == 0x2a3c)||(ID == 0x2a4c)||(ID == 0x2a5c));
        }


        @Override
        public boolean isValid()
        {
            return ((padding == 0) || (padding == 41))
                    && (offset > 0)
                    && (offset < 0xffffff);
        }

        @Override
        public String toString() {
            return "CCFileTocEntry{" +
                    "ID=" + ID +
                    ", offset=" + offset +
                    ", length=" + length +
                    ", padding=" + padding +
                    '}';
        }

        public byte[] readAndDecryptFile(MaMCCFileReader ccFile) throws CCFileFormatException
        {
            try
            {
                byte[] data = new byte[length];
                ccFile.fileStream.seek(offset);
                ccFile.fileStream.read(data, 0, length);
                data = ccFile.extractFileFromRawCCData(data);
                return data;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                throw CCFileFormatException.fromIOError(e.getMessage());
            }

        }
    }

    //-------------------------------------------------------------------------------------------------
    // Instance data
    //-------------------------------------------------------------------------------------------------
    protected int numberOfFiles = 0;
    //protected FileInputStream fileStream = null;
    protected RandomAccessFile fileStream = null;
    protected CCFileTocEntry[] tocEntries = null;
    private Map<Integer, Integer> tocEntriesLut = null;
    public int seekDataBegin;

    protected Map<Integer, String> knownFileNames;
    String filePath;
    //protected int fileSize;

    //-------------------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------------------
    protected MaMCCFileReader(String fileName)
    {
        super(FileHelpers.getFileNameNoExtension(fileName), "root@" + fileName);
        filePath = fileName;
    }

    //-------------------------------------------------------------------------------------------------
    // Abstract members
    //-------------------------------------------------------------------------------------------------

    /**
     * This method takes the raw bytes from the CC file entry and returns bytes
     * that represent the file to be loaded.
     * Typical operations:
     *   - Decrypt data
     *   - Decompress data
     *   - Do nothing
     * @param data byte[] as loaded from the cc file, of the exact length of the stored data.
     */
    protected abstract byte[] extractFileFromRawCCData(byte[] data) throws CCFileFormatException;

    /**
     * Keep ing track of the correct palate is a pinta.
     * All resolution re correct palate comes back to this method.
     */
    public abstract MaMPallet getPalletForFile(int id) throws CCFileFormatException;

    // -----------------------------------------------
    // Decoding of files into game assets.
    //

    protected abstract MaMSprite   decodeSprite(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException;
    protected abstract MaMPallet   decodePallet(String name, String key, byte[] data) throws CCFileFormatException;
    protected abstract MaMSurface  decodeSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException;
    protected abstract MaMThing    decodeThing(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException;
    protected abstract MaMMazeFile decodeMapFile(String name, String key, byte[] data, MaMWorld world, int mazeID) throws CCFileFormatException;

    protected MaMRawImage decodeRawImage(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new MaMRawImage(name, key, data, pal);
    }

    protected MaMTextFile decodeText(String name, String key, byte[] data) throws CCFileFormatException {
        return new MaMTextFile(name, key, data);
    }

    protected MAMVocFile decodeVoc(String name, String key, byte[] data) throws CCFileFormatException {
        return new MAMVocFile(name, key, data);
    }

    protected MaMBinaryFile decodeBinaryFile(String name, String key, byte[] data) throws CCFileFormatException {
        return new MaMBinaryFile(name, key, data);
    }

    //-------------------------------------------------------------------------------------------------
    // hash and decrypt
    //-------------------------------------------------------------------------------------------------
    protected int hashFileName(String name)
    {
        char[] _name = name.toCharArray();
        int i, h;

        if( _name.length < 1 ) return( -1 );

        for( i = 1, h = _name[0] ; i < _name.length ; h += _name[i++] )
        {
            // Rotate the bits in 'h' right 7 places
            // In assembly it would be: ror h, 7
            // 01234567 89ABCDEF -> 9ABCDEF0 12345678
            // 0x007F = 00000000 01111111
            // 0xFF80 = 11111111 10000000
            h = (( h & 0x007F ) << 9) | (( h & 0xFF80 ) >> 7);
        }

        return( h );
    }

    //-------------------------------------------------------------------------------------------------
    // TOC and files
    //-------------------------------------------------------------------------------------------------
    protected void parseTocAndLoadFiles()
    {
        try
        {
            //Open the file stream
            fileStream = new RandomAccessFile(filePath, "r");
            RandomAccessFile fs = fileStream;
            int fileSize = (int)fs.getChannel().size();

            //read and validate number of files
            numberOfFiles = fs.read() | (fs.read() << 8);
            if(numberOfFiles < 0)
            {
                throw CCFileFormatException.fromBadHeader(this, "Invalid number of files in cc file (" + numberOfFiles + ")");
            }

            //read and decrypt file header
            int tocLen = numberOfFiles * 8;
            byte[] rawToc = new byte[tocLen];
            int readLen = fs.read(rawToc, 0, tocLen);
            if(readLen != tocLen)
            {
                throw CCFileFormatException.fromBadReadLength(this, readLen, tocLen);
            }
            seekDataBegin = tocLen + 2;
            createTocFromEncryptedHeader(rawToc, this.numberOfFiles, fileSize);

            //parse known files
            loadKnownFiles(filePath);

            if (GlobalSettings.INSTANCE.discoverFileNames())
            {
                doFileDiscovery(this);
            }

            //generate local copies
            if(GlobalSettings.INSTANCE.rebuildProxies())
            {
                makeProxiesOfAllEntries();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            close();
        }
        catch (IOException e) {
            e.printStackTrace();
            close();
        } catch (CCFileFormatException e) {
            e.printStackTrace();
            close();
        }
    }

    protected static void doFileDiscovery(MaMCCFileReader ccFile) {
        //unknown id's
        int[] unknownIDs = Arrays.stream(ccFile.tocEntries)
                .filter(E -> !ccFile.knownFileNames.containsKey(E.ID))
                .mapToInt(E -> E.ID)
                .toArray();

        //find possible file names
        Map<Integer, List<String>> names = discoverNames(unknownIDs, ccFile::hashFileName);

        //display and store results
        for (Map.Entry<Integer, List<String>> entry : names.entrySet()) {
            System.out.println("Matched id: " + entry.getKey() +", " + entry.getValue().size() + " match(es).");
            for (String possibleName : entry.getValue()) {
                System.out.println("\t" + entry.getKey() + ", " + possibleName);
            }

            //put most likely name in known file list
            ccFile.knownFileNames.put(entry.getKey(), entry.getValue().get(0));
        }
    }

    protected void createTocFromEncryptedHeader(byte[] rawToc, int numberOfFiles, int fileSize) throws CCFileFormatException
    {
        tocEntries = new CCFileTocEntry[numberOfFiles];

        //decrypt
        int ah = 0xac;
        for (int i = 0; i < rawToc.length; i++)
        {
            int r = rawToc[i] & 0xff;

            //rawToc[i] = UnsignedBytes.saturatedCast(((r << 2 | r >> 6) + ah) & 0xff);
            //rawToc[i] = (byte)(((rawToc[i] << 2 | rawToc[i] >> 6) + ah) & 0xff);
            int lsl2 = (r << 2) & 0xff;

            rawToc[i] = (byte)(((lsl2 | (r >> 6)) + ah) & 0xff);
            ah += 0x67;
        }


        BinaryHelpers.DebugDumpBinary(rawToc, "Headers.last");

        //parse
        for(int i=0; i<numberOfFiles; i++)
        {
            CCFileTocEntry toc = new CCFileTocEntry();
            int offset = i * 8;
            toc.ID = BYTES2INT_lsb(rawToc[offset], rawToc[offset + 1]);
            toc.offset = BYTES2INT_lsb(rawToc[offset + 2], rawToc[offset + 3], rawToc[offset + 4]);
            toc.length = BYTES2INT_lsb(rawToc[offset+5], rawToc[offset + 6]);
            toc.padding = rawToc[offset+7];

            if(!toc.isValid())
            {
                throw CCFileFormatException.fromBadHeader(this, "Toc entry " + i + " is invalid (" + toc.toString() + ")");
            }
            if(toc.offset > fileSize)
            {
                throw CCFileFormatException.fromBadHeader(this, "Toc entry " + i + " points to position beyond end of cc file.");
            }
            if((toc.offset+toc.length) > fileSize)
            {
                throw CCFileFormatException.fromBadHeader(this, "Toc entry " + i + " goes beyond end of cc file.");
            }

            tocEntries[i] = toc;
        }
    }

    //-------------------------------------------------------------------------------------------------
    // Cached file objects
    //-------------------------------------------------------------------------------------------------
    public MaMSprite getSprite(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::decodeSprite);
    }

    public MaMPallet getPallet(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::decodePallet);
    }

    public MaMSurface getSurface(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::decodeSurface);
    }

    public MaMThing getThing(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::decodeThing);
    }

    public MaMRawImage getRawImage(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::decodeRawImage);
    }

    public MaMMazeFile getMapFile(int id, MaMWorld world, int mazeID) throws CCFileFormatException
    {
        //return CCFileCache.INSTANCE.cachedGetFile(this, id, (I) -> this.decodeMapFile(I, world, mazeID));
        return CCFileCache.INSTANCE.cachedGetFile(this, id, world, mazeID, this::decodeMapFile);
    }

    public MaMTextFile getText(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::decodeText);
    }

    public MAMVocFile getVoc(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::decodeVoc);
    }

    public MaMBinaryFile getMaMBinaryFile(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::decodeBinaryFile);
    }



    //-------------------------------------------------------------------------------------------------
    // Auto file open
    //-------------------------------------------------------------------------------------------------
    public MAMFile getFile(int id) throws CCFileFormatException
    {
        String fileName = getNameForID(id);
        String fileExt = FileHelpers.getFileExtension(fileName).toUpperCase();
        String fileNameNoExt = FileHelpers.getFileNameNoExtension(fileName).toUpperCase();

        try
        {
            switch (fileExt)
            {
                case "MON":
                case "ATT":
                case "EG2":
                case "END":
                case "INT":
                case "TWN":
                case "SWL":
                case "PIC":
                case "ICN":
                case "FWL":
                case "FAC":
                case "VGA":
                case "0BJ":
                    return getSprite(id);
                case "GND":
                case "SKY":
                    return getSprite(id);
                case "PAL":
                    return getPallet(id);
                case "SRF":
                    return getSurface(id);
                case "TXT":
                    return getText(id);
                case "VOC":
                    return getVoc(id);
                case "RAW":
                    return getRawImage(id);
                case "OBJ":
                    return getThing(id);
                case"":
                    switch (fileNameNoExt)
                    {
                        case "FNT":
                            return getSprite(id);
                        case "NULLSND":
                            return getVoc(id);
                    }
                    //DELIBERATE flow through to default
                default:
                    return getMaMBinaryFile(id);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("ERROR: loading " + fileName);
            throw new CCFileFormatException(ex);
        }
    }



    //-------------------------------------------------------------------------------------------------
    // Access to graphics files, without specifying pallets
    //-------------------------------------------------------------------------------------------------
    public MaMSprite getSprite(int id) throws CCFileFormatException
    {
        return getSprite(id, getPalletForFile(id));
    }

    public MaMSurface getSurface(int id) throws CCFileFormatException
    {
        return getSurface(id, getPalletForFile(id));
    }

    public MaMThing getThing(int id) throws CCFileFormatException
    {
        return getThing(id, getPalletForFile(id));
    }

    public MaMRawImage getRawImage(int id) throws CCFileFormatException
    {
        return getRawImage(id, getPalletForFile(id));
    }

    public MaMSprite getSprite(String fileName) throws CCFileFormatException
    {
        try
        {
            return getSprite(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }


    public final MaMSprite getSpriteOrNull(String fileName)
    {
        int id = hashFileName(fileName);
        try {
            if(fileExists(id))
            {
                return getSprite(id);
            }
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MaMSurface getSurface(String fileName) throws CCFileFormatException
    {
        try
        {
            return getSurface(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMThing getThing(String fileName) throws CCFileFormatException
    {
        try
        {
            return getThing(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMRawImage getRawImage(String fileName) throws CCFileFormatException
    {
        try
        {
            return getRawImage(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    //-------------------------------------------------------------------------------------------------
    // Access to files, by using file name
    //-------------------------------------------------------------------------------------------------

    public MaMSprite getSprite(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        try
        {
            return getSprite(hashFileName(fileName), pal);
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }


    public MaMPallet getPallet(String fileName) throws CCFileFormatException
    {
        try
        {
            return getPallet(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMSurface getSurface(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        try
        {
            return getSurface(hashFileName(fileName), pal);
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMThing getThing(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        try
        {
            return getThing(hashFileName(fileName), pal);
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMRawImage getRawImage(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        try
        {
            return getRawImage(hashFileName(fileName), pal);
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMTextFile getText(String fileName) throws CCFileFormatException
    {
        try
        {
            return getText(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }


    public MAMVocFile getVoc(String fileName) throws CCFileFormatException
    {
        try
        {
            return getVoc(hashFileName(fileName));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public MaMMazeFile getMapFile(String fileName, MaMWorld world, int mazeID) throws CCFileFormatException {
        try {
            return getMapFile(hashFileName(fileName), world, mazeID);
        }
        catch (CCFileFormatException ex) {
            throw CCFileFormatException.wrapWithFilename(ex, fileName);
        }
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public byte[] getFileRaw(String fileName) throws CCFileFormatException
    {
        if(fileExists(fileName)) {
            return getFileRaw(hashFileName(fileName));
        }
        else {
            throw CCFileFormatException.fromMissingFile(fileName, this);
        }
    }

    public byte[] getFileRaw(int id) throws CCFileFormatException
    {
        ensureTocEntriesLut();

        if(tocEntriesLut.containsKey(id))
        {
            CCFileTocEntry toc = tocEntries[tocEntriesLut.get(id)];
            return toc.readAndDecryptFile(this);
        }
        else
        {
            throw CCFileFormatException.fromMissingFile(getNameForID(id), this);
        }
    }

    public boolean fileExists(String fileName) throws CCFileFormatException
    {
        return fileExists(hashFileName(fileName));
    }

    public boolean fileExists(int id) throws CCFileFormatException
    {
        ensureTocEntriesLut();
        return tocEntriesLut.containsKey(id);
    }

    //-------------------------------------------------------------------------------------------------
    // CC file specific loading logic
    //-------------------------------------------------------------------------------------------------
    public MaMMonster[] loadMonsters(MaMWorld world) throws CCFileFormatException
    {
        List<MaMMonster> monList = new ArrayList<>();
        int errors = 0;
        for(int id = 0; (id<999)&&(errors<20); id++)
        {
            //check for monster file
            if(fileExists(getMonsterIdleSpriteFileName(id)))
            {
                try
                {
                    MaMMonster mon = new MaMMonster("foo", id, world);
                    monList.add(mon);
                }
                catch (Exception ex)
                {
                    System.out.println("Error loading monster: #" + id);
                    ex.printStackTrace();
                }
                //System.out.println("Loaded monster: " + mon.toString());
            }
            else
            {
                errors++;
                //System.out.println("Loaded " + id +  " monsters.");
                //break;
            }
        }

        MaMMonster[] monsters = new MaMMonster[monList.size()];
        monsters = monList.toArray(monsters);

        return monsters;
    }

    public String getMonsterIdleSpriteFileName(int monsterID)
    {
        return String.format("%03d", monsterID) + ".MON";
    }

    public String getMonsterAttackSpriteFileName(int monsterID)
    {
        return String.format("%03d", monsterID) + ".ATT";
    }

    //-------------------------------------------------------------------------------------------------
    // Misc
    //-------------------------------------------------------------------------------------------------
    public String getNameForID(int id)
    {
        if(knownFileNames == null)
        {
            return null;
        }
        return knownFileNames.containsKey(id) ?
                knownFileNames.get(id) :
                ("FILE_" + id + ".BIN");
    }

    protected void ensureTocEntriesLut() throws  CCFileFormatException
    {
        if(tocEntriesLut == null)
        {
            tocEntriesLut = new HashMap<>();
            for (int i = 0; i < tocEntries.length; i++)
            {
                CCFileTocEntry toc = tocEntries[i];
                int hash = toc.ID;
                if(!tocEntriesLut.containsKey(hash))
                {
                    tocEntriesLut.put(hash, i);
                }
                else
                {
                    throw CCFileFormatException.fromBadHeader(this, "Duplicate ID (hash = " + hash + ")");
                }
            }
        }
    }

    public void loadKnownFiles(String filePath)
    {
        knownFileNames = new HashMap<>();

        //find a matcching csv and pars out the file names
        Path path = Paths.get(filePath + ".csv");
        if(FileHelpers.fileExists(path))
        {
            String[] lines = FileHelpers.readAllLines(path);
            String[] fileNameList = Arrays.stream(lines)
                                    .map(s -> getFileNameFromCSVLine(s))
                                    .filter(s -> s != null)
                                    .toArray(size -> new String[size]);

            Arrays.stream(fileNameList).forEach(f -> knownFileNames.put(hashFileName(f), f));


        }
    }

    public static String getFileNameFromCSVLine(String line)
    {
        if(line != null)
        {
            String name = line.trim();
            //check the line is not a comment
            if((name.length() > 0) && (!name.startsWith("#")))
            {
                int pos = name.indexOf(',');
                if( pos > 0)
                {
                    name = name.substring(0, pos);
                    return  name.trim();
                }
            }
        }

        return null;
    }

    @Override
    public void close()
    {
        if(fileStream != null)
        {
            try {
                fileStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileStream = null;
        }

    }

    public void makeProxiesOfAllEntries()
    {
        for (CCFileTocEntry tocEntry : tocEntries)
        {
            int id = tocEntry.ID;
            String fileName = getNameForID(id);

            MAMFile mamFile = null;
            try {
                //get MaM file
                mamFile = getFile(id);

                //-------------------- DEBUG CODE
                //unknown binary file, lets fore parse it.
                if(mamFile instanceof MaMBinaryFile)
                {
                    mamFile = forceDiscovery(id, fileName, mamFile);
                }

                //-------------------------------
                //get file name for proxy
                String proxyFileName = getProxyFilePath(fileName, mamFile);

                //create proxy if not found
                if(!FileHelpers.fileExists(proxyFileName))
                {
                    //save proxy
                    String path = FileHelpers.getParentDirectory(proxyFileName);
                    if(FileHelpers.ensurePathExists(path))
                    {
                        mamFile.saveProxy(proxyFileName);
                    }
                    else
                    {
                        System.out.println("Error creating path: " + path);
                    }
                }
            }
            catch (CCFileFormatException e)
            {
                e.printStackTrace();
                System.out.println("ERROR: proxy not created " + fileName);
            }
            catch (Exception ex)
            {
                System.out.println("ERROR: (General exception) proxy not created " + fileName);
                ex.printStackTrace();
            }

        } //end for

        //others
    }

    protected MAMFile forceDiscovery(int id, String fileName, MAMFile mamFile) {
        if(mamFile instanceof MaMBinaryFile)
        {
            //sprite
            try
            {
                MaMSprite sprite = decodeSprite("SPRITE_FOR_"+fileName,
                                            MAMFile.generateKeyFromCCFile(id, this),
                                            getFileRaw(id),
                                            getPalletForFile(id));

//                MaMSprite sprite = new WOXSpriteFile("SPRITE_FOR_"+fileName,
//                                                    MAMFile.generateKeyFromCCFile(id, this),
//                                                    getFileRaw(id),
//                                                    getPalletForFile(id));
                //if we are here, we parsed a sprite
                System.out.println(describeFile(fileName) + " was a computer sprite alright.");
                return sprite;
            }
            catch (Exception ex)
            {
            }

            //pal file
            try
            {
                byte[] rawData = getFileRaw(id);
                if(rawData.length == ((256 * 3)+4))
                {
                    MaMPallet pal = new MaMPallet("PAL_FOR_"+fileName,
                            MAMFile.generateKeyFromCCFile(id, this),
                            rawData,
                            256,
                            2);

                    //if we are here, we parsed a sprite
                    System.out.println(describeFile(fileName) + " was a pallate.");
                    return pal;
                }
            }
            catch (Exception ex)
            {
            }

            //raw image
            try
            {
                MaMRawImage img = new MaMRawImage("IMAGE_FOR_"+fileName,
                                                MAMFile.generateKeyFromCCFile(id, this),
                                                getFileRaw(id),
                                                getPalletForFile(id));
                //if we are here, we parsed a sprite
                System.out.println(describeFile(fileName) + " was a raw image.");
                return img;
            }
            catch (Exception ex)
            {
            }


            //text file?
            try
            {
                if(looksLikeText(((MaMBinaryFile) mamFile).data))
                {
                    MaMTextFile txt = new MaMTextFile("TEXT_FOR_"+fileName,
                                                        MAMFile.generateKeyFromCCFile(id, this),
                                                        getFileRaw(id));
                    System.out.println(describeFile(fileName) + " was a text file.");
                    return txt;
                }
            }
            catch (Exception ex)
            {
            }
        }
        return mamFile;
    }

    protected static boolean looksLikeText(byte[] data)
    {
        int nonTextLikeBytes = 0;
        int len = data.length;
        for(byte b : data)
        {
            // > 128 is probably not text.
            if(b < 0) {
                nonTextLikeBytes++;
            }
        }

        //does this look text like
        return (((len < 20) && (nonTextLikeBytes <= 1)) ||
                ((len <= 1024) && (nonTextLikeBytes <= 12)) ||
                ((len > 1024) && (nonTextLikeBytes / (double)len) < 0.001));
    }

    private String getProxyFilePath(String fileName, MAMFile mamFile) {
        String fileNameOnly =FileHelpers.getFileNameNoExtension(fileName);
        String fileExt = FileHelpers.getFileExtension(fileName);

        String ccPath = FileHelpers.getParentDirectory(FileHelpers.getAbsolutePath(this.filePath));

        String proxyDir = FileHelpers.getFileName(this.filePath.toLowerCase()).replace('.', '_');
        String proxyPath = FileHelpers.join(ccPath, proxyDir+"_proxy");

        //add catagory directory
        switch(fileExt.toUpperCase())
        {
            case "MON":
            case "ATT":
                proxyPath = FileHelpers.join(proxyPath, "Monsters");
                break;
            default:
                proxyPath = FileHelpers.join(proxyPath, fileExt.toUpperCase()+"_files");
        }

        //add name and extension
        //proxyPath = FileHelpers.join(proxyPath, fileNameOnly + "." + "proxy");
        String desiredExt = FileHelpers.getFileExtension(mamFile.suggestProxyFileName());

        //as in LAVA.TIl.GIF - done this way to avoid name space conflicts
        proxyPath = FileHelpers.join(proxyPath, fileName + "." + desiredExt);

        return proxyPath;
    }

    /**
     * The path of the .cc file itself.
     */
    public String getFilePath() {
        return filePath;
    }

    public String describeFile(String fileName)
    {
        try {
            if(fileExists(fileName))
            {
                return fileName + "@" + this.name;
            }
            return "!!!" + fileName + "@" + this.name + "!!![missing file]";
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }
        return "!!!" + fileName + "@" + this.name + "!!![error reading file]";
    }

    /**
     * Returns true if we are in forceDiscoveryMode
     * Used to supress un-necessary warnings etc.
     */
    protected boolean inForceDiscoveryMode()
    {
        //TODO: Nasty hack
        return Arrays.stream(Thread.currentThread()
                .getStackTrace())
                .map(StackTraceElement::getMethodName)
                .anyMatch(S -> S.contains("forceDiscovery"));
    }

    /**
     * find a workable name for a given file.
     */
    public static Map<Integer, List<String>> discoverNames(int[] idList, Function<String, Integer> hash) {
        //setup dictionaries
        final String[] extensions = new String[] {"", "0BJ", "AD", "ADM", "ADS", "MUS", "SND", "ATT", "BIN", "BRD", "BUF", "DAT", "DRV", "EG2", "END", "FAC", "FNT", "FRM", "FWL", "GND", "HED", "ICN", "INT", "M", "MID", "MON", "NUL", "OBJ", "OUT", "PAL", "PIC", "RAW", "SKY", "SRF", "SWL", "TIL", "TWN", "TXT", "VGA", "VOC", "WAL", "XEN", "ZOM"};
        final String[] names = new String[] {"", "CREDITS", "FILE", "NOTES", "K", "CLOUDS", "DARKPIC", "XEENPIC", "TIMER", "FSCFI", "MAZE", "MAZEX", "CHOOSE", "DETECTMN", "KLUDGE", "TITLEA", "TITLEC", "TITLED", "TITLEE", "TITLEF", "TITLEG", "TITLEH", "TITLEI", "CAVERNA", "CSTLREV", "DAYDESRT", "DNGON", "NEWBRIGH", "OUTNGHT", "TOWNDAY", "TWNNITEA", "TWNNITEB", "TWNWLK", "TITLEB", "WORLD", "CLOUD", "CROAD", "DESERT", "DIRT", "DWATER", "LAVA", "ROAD", "SCORTCH", "SEWER", "SNOW", "SPACE", "SWAMP", "TFLR", "SSCFI", "SCFI", "AAZEX", "DARKMIRR", "DARKX", "XEENMIRR", "AIRMON", "ALIEN", "BARBARCH", "BRANCH", "CENTI", "CLERIC", "COME", "DISINT", "DRAGMUM", "EARTHMON", "ELECT", "ENTER", "EYEBALL", "GDLUCK", "GIANT", "GO", "GOBLIN", "GOIN", "GREMLIN", "GREMLINK", "GTLOST", "HELP", "HOWDID", "HYDRA", "IGUANA", "INSECT", "KEY", "KNIGHT", "LAFF", "LICH", "MUMMY", "NEEDKEY", "OK", "PARROT", "PASS", "PRTYGD", "RAT", "SABER", "SAND", "SCREECH", "SEE", "SKULL", "SPIT", "THANKS", "THIEF", "TROLL", "VAMP", "VULTURE", "WOLF", "YOUTRN", "DMOUNT", "DSNOTREE", "files", "GRASS", "MAE", "SPELLS", "null", "EG_files", "INT_files", "CUBE", "HANDS", "SCL", "SCM", "SCN", "STAFF", "DISK", "DISKA", "DISKB", "DISKC", "DISKD", "DISKE", "SCXA", "SCXB", "SCA", "SCB", "SCC", "SCD", "SCE", "SCF", "SCG", "SCH", "SCI", "SCJ", "SCK", "CLAW", "DARKLORD", "FIZZLE", "GOON", "PYRATOP", "TITLE", "WFIRE", "WIZARD", "WIZMTH", "NWBLKSMT", "OUTDAY", "SF", "WHITE", "BALL", "BIRD", "DROP", "EGPRT", "EG", "END", "FIREMAIN", "FLY", "FOURA", "MAINBACK", "NWC", "PYRAMID", "SC", "SCENE-B", "SCENE-", "SCENE", "SKYMAIN", "TABLMAIN", "TWRSKY", "ADMIT", "ALAMAR", "CLICK", "COMET", "CORAK", "DOOR", "DREAMS", "ELLINGER", "EXPLOSIO", "FAIL", "FIGHT", "GASCOMPR", "IDO", "NORDO", "NOWRE", "PADSPELL", "PHAROHC", "PHAROHD", "PHAROHE", "PHAROHW", "PHAROHT", "PHAROH", "PHAROHA", "PHAROHB", "QUEEN", "QUEENHIS", "READY", "RUMBLE", "THUD", "VINE", "WHAT", "WINDSTOR", "YES", "FNT", "FILE_", "QNOTES", "SPECIAL", "SPLDESC", "VIEWTEXT", "FECP", "BIGSKY", "CASB", "DARKLRD", "ENDTEXT", "KINGCORD", "MIRRBACK", "PEOPLE", "ROOM", "CHAR", "DSE", "FACE", "FRAME", "PREC", "VORT", "FCAVE", "FCSTL", "FDUNG", "FTOWN", "FTOWR", "AWARD", "BLESS", "BORDER", "BUY", "CAST", "CHARPOW", "CHOICE", "COMBAT", "CONFIRM", "CPANEL", "ELEMENT", "EQUIP", "ESC", "GLOBAL", "HPBARS", "ITEMS", "LLOYDS", "MAIN", "MOUSE", "QUEST", "RESTOREX", "SCROLL", "SPELLFX", "START", "TRAIN", "VIEW", "BANK", "BIGTHEME", "CANTHEME", "CASTLE", "CAVERN", "DUNGEON", "GROUNDS", "INN", "MMTHEME", "OUTDOORS", "SMITH", "TAVERN", "TEMPLE", "WATER", "ENDGAME", "MIRROR", "MM", "MME", "BACK", "BLANK", "CREATE", "JVC", "LATER", "LOGOBAK", "MIROR-S", "THRONE", "NIGHT", "SKY", "SCAVE", "SCSTL", "SDUNG", "STOWN", "STOWR", "CAVE", "CSTL", "DUNG", "OUTDOOR", "TOWN", "TOWR", "BLCK", "BNKR", "GILD", "TMPL", "TRNG", "TVRN", "AAZE", "BOX", "DEATH", "DICE", "FREAP", "GOLMBACK", "GROUP", "GROUPO", "HAND", "INTRO", "LAKE", "LOGO", "MARB", "STARS", "TOWER", "WIZTOWER", "WIZTWER", "AHH", "BANG", "BANKER", "BARKL", "BAT", "BEAM", "BEGGER", "BLEAGH", "BLOB", "BOLTELEC", "BONG", "BOOM", "BOW", "COINA", "CRASHA", "CRODOA", "CRODOB", "CRODOC", "CRODO", "DARK", "DARKLAFF", "DRAGON", "DWARF", "ELECBOLT", "ELECSPEL", "ELECTRIC", "EXPLODE", "FIRE", "FX", "FXA", "GARGRWL", "GAZONG", "GHOUL", "GLEEN", "GOLEM", "GOODBYE", "GUILD", "GULP", "HARPY", "HELLO", "HISS", "HIT", "HUAH", "IAMKING", "INSECTS", "KING", "LAFFD", "MAYWE", "MEANGRO&", "MISS", "MONSTERA", "MONSTERB", "NOISE", "NULL", "NYAAHH", "OGRE", "OOH", "ORC", "PHANTOM", "PHOTON", "POP", "POW", "PUNCHOOH", "REAPER", "RIBBIT", "ROC", "SCORPION", "SCREAM", "SIKTROLL", "SKELETON", "SLIME", "SLIMEBOS", "SNAKEMAN", "SPELL", "SPHINX", "SPLASH", "SPRITE", "STONEGOL", "SWIPE", "THANKYOU", "TIGER", "TIGER&", "TRAININ", "TRAINING", "TREE", "UNNH", "WEB", "WHAA-KEE", "WHADDAYO", "WHERETO", "WHIP", "WHOOSH", "WINDBLOW", "XEEN", "XEENLAFF", "YA", "YAA", "ZOMBIE", "ZONGA", "DEDLTREE", "DTREE", "LAVAMNT", "LTREE", "MOUNT", "PALM", "SNOMNT", "SNOTREE", "ADMUS", "ADSND", "BLASTMUS", "BLASTSND", "CANMUS", "COVSND", "CPSTRUCT", "IBMMUS", "IBMSND", "NULLMUS", "NULLSND", "PROMUS", "PROSND", "ROLMUS", "SOURCE", "SPECMUS", "SPECSND"};

        //TODO: sort dictionaries by their likelihood, so that matches come out in a sensible order

        final List<String> numbers = new ArrayList<>();
        numbers.add("");
        for(int i=0; i<2; i++)
        {
            numbers.add(""+i);
            numbers.add("0"+i);
            if(i<100) {
                numbers.add("00"+i);
            }
        }

        //setup a fastish' int hash mane
        //Set<Integer> = new HashSet
        final Map<Integer, List<String>> idMap = new HashMap<>(idList.length);
        Arrays.stream(idList).forEach(I -> idMap.put(I, new ArrayList<>()));

        //brute force
        for (String name : names) {
            for (String extension : extensions) {
                numbers.parallelStream()
                        .map(N -> name+N+"."+extension)
                        .filter(S -> S.length() <= 12)
                        .filter(S -> idMap.containsKey(hash.apply(S)))
                        .forEach(S -> idMap.get(hash.apply(S)).add(S));
            }
        }

        //remove entries with no match
        final Map<Integer, List<String>> idMap2 = new HashMap<>();
        idMap.entrySet()
                .stream()
                .filter(E -> E.getValue().size() > 0)
                .forEach(N -> idMap2.put(N.getKey(), N.getValue()));

        //done
        return idMap2;
    }
}
