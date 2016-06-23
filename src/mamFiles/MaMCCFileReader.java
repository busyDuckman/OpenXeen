package mamFiles;

import Game.Map.MaMWorld;
import Game.Monsters.MaMMonster;
import Toolbox.FileHelpers;
import Toolbox.IValidatable;
import mamFiles.WOX.WOXSpriteFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
                ccFile.decrypt(data);
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
    // hash and decrypt
    //-------------------------------------------------------------------------------------------------
    protected abstract int hashFileName(String name);
    protected abstract void decrypt(byte[] data);

    //-------------------------------------------------------------------------------------------------
    // Actual file loading
    //-------------------------------------------------------------------------------------------------
    // A) THESE ARE THE ONLY FUNCTIONS TO EXTRACT FROM CC FILES.
    // B) THEY ARE ONLY CALLED FROM A CACHE MISS

    protected abstract MaMSprite __getSprite(int id, MaMPallet pal) throws CCFileFormatException;
    protected abstract MaMPallet __getPallet(int id) throws CCFileFormatException;
    protected abstract MaMSurface __getSurface(int id, MaMPallet pal) throws CCFileFormatException;
    protected MaMRawImage __getRawImage(int id, MaMPallet pal) throws CCFileFormatException
    {
        return new MaMRawImage(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id), pal);
    }
    protected abstract MaMMazeFile __getMapFile(int id, MaMWorld world, int mazeID) throws CCFileFormatException;

    protected MaMTextFile __getText(int id) throws CCFileFormatException
    { return new MaMTextFile(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id)); }

    protected MAMVocFile __getVoc(int id) throws CCFileFormatException
    {
        return new MAMVocFile(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id));
    }

    protected MaMBinaryFile __getMaMBinaryFile(int id) throws CCFileFormatException
    {
        return new MaMBinaryFile(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id));
    }


    //-------------------------------------------------------------------------------------------------
    // Pallet helpers
    //-------------------------------------------------------------------------------------------------
    public abstract MaMPallet getPalletForFile(int id) throws CCFileFormatException;

    //-------------------------------------------------------------------------------------------------
    // Cached file objects
    //-------------------------------------------------------------------------------------------------


    public MaMSprite getSprite(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::__getSprite);
    }

    public MaMPallet getPallet(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::__getPallet);
    }

    public MaMSurface getSurface(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::__getSurface);
    }

    public MaMRawImage getRawImage(int id, MaMPallet pal) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, pal, this::__getRawImage);
    }

    public MaMMazeFile getMapFile(int id, MaMWorld world, int mazeID) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, (I) -> this.__getMapFile(I, world, mazeID));
    }

    public MaMTextFile getText(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::__getText);
    }

    public MAMVocFile getVoc(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::__getVoc);
    }

    public MaMBinaryFile getMaMBinaryFile(int id) throws CCFileFormatException
    {
        return CCFileCache.INSTANCE.cachedGetFile(this, id, this::__getMaMBinaryFile);
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
                case "OBJ":
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
        return getFileRaw(hashFileName(fileName));
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
            return null;
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
        //find a matcching csv and pars out the file names
        Path path = Paths.get(filePath + ".csv");
        if(FileHelpers.fileExists(path))
        {
            String[] lines = FileHelpers.readAllLines(path);
            String[] fileNameList = Arrays.stream(lines)
                                    .map(s -> getFileNameFromCSVLine(s))
                                    .filter(s -> s != null)
                                    .toArray(size -> new String[size]);

            knownFileNames = new HashMap<>();
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
                MaMSprite sprite = new WOXSpriteFile("SPRITE_FOR_"+fileName,
                                                    MAMFile.generateKeyFromCCFile(id, this),
                                                    getFileRaw(id),
                                                    getPalletForFile(id));
                //if we are here, we parsed a sprite
                System.out.println(describeFile(fileName) + " was a computer sprite alright.");
                return sprite;
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

    protected boolean inForceDiscovery()
    {
        //TODO: Nasty hack
        return Arrays.stream(Thread.currentThread()
                .getStackTrace())
                .map(StackTraceElement::getMethodName)
                .anyMatch(S -> S.contains("forceDiscovery"));
    }
}
