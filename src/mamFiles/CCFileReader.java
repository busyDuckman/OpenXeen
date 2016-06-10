package mamFiles;

import Game.MaMGame;
import Game.Map.MaMWorld;
import Game.Monsters.MaMMonster;
import Rendering.IMaMSprite;
import Toolbox.ArrayHelpers;
import Toolbox.FileHelpers;
import Toolbox.IValidatable;
import mamFiles.WOX.SpriteFileWOX;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by duckman on 3/05/2016.
 */
public abstract class CCFileReader extends MAMFile implements AutoCloseable
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

        public byte[] readAndDecryptFile(CCFileReader ccFile) throws CCFileFormatException
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
    protected int fileSize;

    //-------------------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------------------
    protected CCFileReader(String fileName, int size)
    {
        super(FileHelpers.getFileNameNoExtension(fileName));
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
    public abstract MaMSprite getSprite(int id, MaMPallet pal) throws CCFileFormatException;
    public abstract MaMPallet getPallet(int id) throws CCFileFormatException;
    public abstract MaMSurface getSurface(int id, MaMPallet pal) throws CCFileFormatException;
    public MaMRawImage getRawImage(int id, MaMPallet pal) throws CCFileFormatException
    {
        return new MaMRawImage(getFileNameForID(id), getFileRaw(id), pal);
    }

    public abstract MaMPallet getPalletForFile(int id) throws CCFileFormatException;

    public abstract MaMMazeFile getMapFile(int id, MaMWorld world) throws CCFileFormatException;



    //-------------------------------------------------------------------------------------------------
    // Auto file open
    //-------------------------------------------------------------------------------------------------
    public MAMFile getFile(int id) throws CCFileFormatException
    {
        String fileName = getFileNameForID(id);
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
                default:
                    return new MaMBinaryFile(fileName, getFileRaw(id));
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
        return getSprite(hashFileName(fileName));
    }

    public MaMSurface getSurface(String fileName) throws CCFileFormatException
    {
        return getSurface(hashFileName(fileName));
    }

    public MaMRawImage getRawImage(String fileName) throws CCFileFormatException
    {
        return getRawImage(hashFileName(fileName));
    }

    //-------------------------------------------------------------------------------------------------
    // Access to files, by using file name
    //-------------------------------------------------------------------------------------------------

    public MaMSprite getSprite(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        return getSprite(hashFileName(fileName), pal);
    }


    public MaMPallet getPallet(String fileName) throws CCFileFormatException
    {
        return getPallet(hashFileName(fileName));
    }

    public MaMSurface getSurface(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        return getSurface(hashFileName(fileName), pal);
    }

    public MaMRawImage getRawImage(String fileName, MaMPallet pal) throws CCFileFormatException
    {
        return getRawImage(hashFileName(fileName), pal);
    }

    public MaMTextFile getText(String fileName) throws CCFileFormatException
    { return getText(hashFileName(fileName)); }

    public MaMTextFile getText(int id) throws CCFileFormatException
    { return new MaMTextFile(getFileNameForID(id), getFileRaw(id)); }

    public MAMVocFile getVoc(String fileName) throws CCFileFormatException
    { return getVoc(hashFileName(fileName)); }

    public MAMVocFile getVoc(int id) throws CCFileFormatException
    {
        return new MAMVocFile(getFileNameForID(id), getFileRaw(id));
    }

    public MaMMazeFile getMapFile(String fileName, MaMWorld world) throws CCFileFormatException {
        return getMapFile(hashFileName(fileName), world);
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
    public String getFileNameForID(int id)
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
        Path path = Paths.get((filePath.toLowerCase() + "FFSJAVA").replace(".ccFFSJAVA", ".csv"));
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
            String fileName = getFileNameForID(id);

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
                MaMSprite sprite = new SpriteFileWOX("SPRITE_FOR_"+fileName,getFileRaw(id), getPalletForFile(id));
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
                MaMRawImage img = new MaMRawImage("IMAGE_FOR_"+fileName,getFileRaw(id), getPalletForFile(id));
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
                    MaMTextFile txt = new MaMTextFile("TEXT_FOR_"+fileName, getFileRaw(id));
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
}
