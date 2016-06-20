package mamFiles.WOX;

import Game.GlobalSettings;
import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import Toolbox.BinaryHelpers;
import Toolbox.FileHelpers;
import mamFiles.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static Toolbox.BinaryHelpers.*;

/**
 * Created by duckman on 7/05/2016.
 */
public class CCFileReaderWOX extends CCFileReader
{
    public  enum WoXCCVariant {
        CLOUDS_INTRO (null, WoXWorld.WoxVariant.CLOUDS),
        CLOUDS_WORLD (null, WoXWorld.WoxVariant.CLOUDS),
        CLOUDS_CUR (null, WoXWorld.WoxVariant.CLOUDS),
        CLOUDS_BOSS (null, WoXWorld.WoxVariant.CLOUDS),
        DARK_CC("MM4.PAL", WoXWorld.WoxVariant.DARK_SIDE),
        DARK_CUR (null, WoXWorld.WoxVariant.DARK_SIDE),
        DARK_SAV (null, WoXWorld.WoxVariant.DARK_SIDE),
        DARK_INTRO("DARK.PAL", WoXWorld.WoxVariant.DARK_SIDE),
        CLOUDS_CC ("MM4.PAL", WoXWorld.WoxVariant.CLOUDS),
        CLOUDS_DAT (null, WoXWorld.WoxVariant.CLOUDS),
        CLOUDS_SAV (null, WoXWorld.WoxVariant.CLOUDS),
        UNKNOWN (null, WoXWorld.WoxVariant.UNKNOWN);

        String defaultPallate;
        WoXWorld.WoxVariant woxVariant;

        WoXCCVariant(String defaultPallate, WoXWorld.WoxVariant woxVariant) {
            this.defaultPallate = defaultPallate;
            this.woxVariant = woxVariant;
        }

        public String getDefaultPallate() {
            return defaultPallate;
        }

        public String asText()
        {
            return this.name().toLowerCase();
        }

        public WoXWorld.WoxVariant getWoxVariant() {
            return woxVariant;
        }
    };

    WoXCCVariant variant;

    protected CCFileReaderWOX(String name, WoXCCVariant variant) {
        super(name);
        this.variant = variant;
    }

    public static CCFileReaderWOX open(String filePath)
    {
        //validate params and get file info
        if(!FileHelpers.fileExists(filePath))
        {
            return null;
        }
        Path path = Paths.get(filePath);
        String name = path.getFileName().toString();

        //create cc file reader.
        CCFileReaderWOX ccFile = new CCFileReaderWOX(name, getVariant(filePath));
        try
        {
            //Open the file stream
            ccFile.fileStream = new RandomAccessFile(filePath, "r");
            RandomAccessFile fs = ccFile.fileStream;
            int fileSize = (int)fs.getChannel().size();

            //read and validate number of files
            ccFile.numberOfFiles = fs.read() | (fs.read() << 8);
            if(ccFile.numberOfFiles < 0)
            {
                throw CCFileFormatException.fromBadHeader(ccFile, "Invalid number of files in cc file (" + ccFile.numberOfFiles + ")");
            }

            //read and decrypt file header
            int tocLen = ccFile.numberOfFiles * 8;
            byte[] rawToc = new byte[tocLen];
            int readLen = fs.read(rawToc, 0, tocLen);
            if(readLen != tocLen)
            {
                throw CCFileFormatException.fromBadReadLength(ccFile, readLen, tocLen);
            }
            ccFile.seekDataBegin = tocLen + 2;
            ccFile.createTocFromEncryptedHeader(rawToc, ccFile.numberOfFiles, fileSize);

            //parse known files
            ccFile.loadKnownFiles(filePath);

            //generate local copies
            if(GlobalSettings.INSTANCE.rebuildProxies())
            {
                ccFile.makeProxiesOfAllEntries();
            }

            //done
            return ccFile;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            ccFile.close();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            ccFile.close();
            return null;
        } catch (CCFileFormatException e) {
            e.printStackTrace();
            ccFile.close();
            return null;
        }

    }

    protected static WoXCCVariant getVariant(String path)
    {
        Map<String, WoXCCVariant> patterns = new HashMap<>();
        patterns.put("BOSS\\.MM4", WoXCCVariant.CLOUDS_BOSS);
        patterns.put("DARK\\.CC", WoXCCVariant.DARK_CC);
        patterns.put("DARK\\.CUR", WoXCCVariant.DARK_CUR);
        patterns.put("DARK0.\\.WOX", WoXCCVariant.DARK_SAV);
        patterns.put("INTRO\\.CC", WoXCCVariant.DARK_INTRO);
        patterns.put("XEEN\\.CC", WoXCCVariant.CLOUDS_CC);
        patterns.put("XEEN\\.CUR", WoXCCVariant.CLOUDS_CUR);
        patterns.put("XEEN\\.DAT", WoXCCVariant.CLOUDS_DAT);
        patterns.put("XEEN0.\\.WOX", WoXCCVariant.CLOUDS_SAV);

        WoXCCVariant var = patterns.entrySet().stream()
                .filter(P -> Pattern.compile(P.getKey(), Pattern.CASE_INSENSITIVE)
                                    .matcher(FileHelpers.getFileName(path))
                                    .matches())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(WoXCCVariant.UNKNOWN);

        return var;
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
            toc.ID = BYTES2INT(rawToc[offset], rawToc[offset + 1]);
            toc.offset = BYTES2INT(rawToc[offset + 2], rawToc[offset + 3], rawToc[offset + 4]);
            toc.length = BYTES2INT(rawToc[offset+5], rawToc[offset + 6]);
            toc.padding = rawToc[offset+7];

            if(!toc.isValid())
            {
                throw CCFileFormatException.fromBadHeader(this, "Toc entry " + i + " is invalid (" + toc.toString() + ")");
            }
            if(toc.offset > fileSize)
            {
                throw CCFileFormatException.fromBadHeader(this, "Toc entry " + i + " points to position beyond end of cc file.");
            }

            tocEntries[i] = toc;
        }
    }

    @Override
    protected int hashFileName(String name)
    {
        return _hashFileName(name);
    }

    static int _hashFileName(String name)
    {
        return _hashFileName(name.toCharArray());
    }

    static int _hashFileName(char[] name)
    {
        int i, h;

        if( name.length < 1 ) return( -1 );

        for( i = 1, h = name[0] ; i < name.length ; h += name[i++] )
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

    @Override
    protected void decrypt(byte[] data)
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = (byte)((data[i] ^ 0x35) & 0xff);
        }
    }

    @Override
    protected MaMSprite __getSprite(int id, MaMPallet pal) throws CCFileFormatException
    {
        return new SpriteFileWOX(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id), pal);
    }

    @Override
    protected MaMPallet __getPallet(int id) throws CCFileFormatException
    {
        return new PalletWOX(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id));
    }

    @Override
    protected MaMSurface __getSurface(int id, MaMPallet pal) throws CCFileFormatException {
        return new WOXSurface(getNameForID(id), MAMFile.generateKeyFromCCFile(id, this), getFileRaw(id), pal);
    }

    @Override
    protected MaMMazeFile __getMapFile(int id, MaMWorld world, int mazeID) throws CCFileFormatException {
        return new WOXMazeFile(mazeID, MAMFile.generateKeyFromCCFile(id, this), world);
    }

    //-------------------------------------------------------------------------------------------------
    // Pallet helpers
    //-------------------------------------------------------------------------------------------------
    @Override
    public MaMPallet getPalletForFile(int id) throws CCFileFormatException {
        //ignoring id for now, so far this pallet works for all sprites
        try
        {
            return getPallet(variant.getDefaultPallate());
        }
        catch (Exception ex)
        {
            if(!inForceDiscovery())
            {
                System.out.println("Problem getting pallate, using standard.");
            }
            return MaMPallet.getDefaultMaMPallate();
        }
    }

    public WoXCCVariant getVariant() {
        return variant;
    }

    @Override
    public String suggestProxyFileName() {
        return name + ".ZIP";
    }

    @Override
    public boolean saveProxy(String path) {
        return false;
    }
}
