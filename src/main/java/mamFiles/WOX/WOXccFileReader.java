package mamFiles.WOX;

import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import Game.Monsters.MonsterFactory;
import Toolbox.FileHelpers;
import mamFiles.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static Toolbox.Misc.ifNull;

/**
 * Created by duckman on 7/05/2016.
 */
public class WOXccFileReader extends MaMCCFileReader
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
        UNKNOWN ("MM3.PAL", WoXWorld.WoxVariant.UNKNOWN);

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

    protected WOXccFileReader(String name, WoXCCVariant variant) {
        super(name);
        this.variant = variant;

        monsterFactory = MonsterFactory.newWoxMonsterFactory();
    }

    public static WOXccFileReader open(String filePath) throws CCFileFormatException
    {
        //validate params and get file info
        if(!FileHelpers.fileExists(filePath))
        {
            throw new CCFileFormatException("File not found: " + ifNull(filePath, "<NULL>"));
        }
        Path path = Paths.get(filePath);
        String name = path.getFileName().toString();

        //create cc file reader.
        WOXccFileReader ccFile = new WOXccFileReader(name, getVariant(filePath));

        ccFile.parseTocAndLoadFiles();

        return ccFile;
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

    @Override
    protected byte[] extractFileFromRawCCData(byte[] data)
    {
        switch (variant)
        {
            case DARK_CUR:
            case UNKNOWN:
                return data;
            default:
                byte[] decrypt = new byte[data.length];
                for (int i = 0; i < data.length; i++)
                {
                    decrypt[i] = (byte)((data[i] ^ 0x35) & 0xff);
                }
                return decrypt;
        }
    }

    @Override
    protected MaMSprite decodeSprite(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new WOXSpriteFile(name, key, data, pal);
    }

    @Override
    protected MaMPallet decodePallet(String name, String key, byte[] data) throws CCFileFormatException {
        return new WOXPallet(name, key, data);
    }

    @Override
    protected MaMSurface decodeSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new WOXSurface(name, key, data, pal);
    }

    @Override
    protected MaMThing decodeThing(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new WoXThing(name, key, data, pal);
    }

    @Override
    protected MaMMazeFile decodeMapFile(String name, String key, byte[] data, MaMWorld world, int mazeID) throws CCFileFormatException {
        return new WOXMazeFile(mazeID, key, (WoXWorld)world);
    }


//    @Override
//    protected MaMMazeFile __getMapFile(int id, MaMWorld world, int mazeID) throws CCFileFormatException {
//        return new WOXMazeFile(mazeID, MAMFile.generateKeyFromCCFile(id, this), world);
//    }

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
            if(!inForceDiscoveryMode())
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
