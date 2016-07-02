package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMSurface;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;

/**
 * Created by duckman on 16/06/2016.
 */
public class WoXOutdoorEnvironmentSet extends WoXEnvironmentSet implements IMaMOutdoorEnvironmentSet
{

    //as per xeen wikia
//    private static final String[] surfaceNameLut = new String[] {
//            null, "DIRT.SRF", "GRASS.SRF", "SNOW.SRF",
//            "SWAMP.SRF", "LAVA.SRF", "DESERT.SRF", "ROAD.SRF",
//            "WATER.SRF", "TFLR.SRF", "SKY.SRF", "CROAD.SRF",
//            "SEWER.SRF", "CLOUD.SRF", "SCORTCH.SRF",
//            "SPACE.SRF"};

    //hacked the order a bit, to ry and get it to work
    private static final String[] surfaceNameLut = new String[] {
            null, "DIRT.SRF", "GRASS.SRF", "SNOW.SRF",
            "SWAMP.SRF", "LAVA.SRF", "DESERT.SRF", "ROAD.SRF",
            "WATER.SRF", "TFLR.SRF", "SKY.SRF", "CROAD.SRF",
            "SCORTCH.SRF", "CLOUD.SRF", "SEWER.SRF",
            "SPACE.SRF"};
    //DWATER.SRF ?

    private final MaMSurface[] surfaces;

    /**
     * Loads a set of environment sprites.
     * <p>
     * Environment keys are 4 char, EG:
     * <p>
     * Cave (CAVE)
     * Castle (CSTL)
     * Dungeon (DUNG)
     * Sci-Fi, used for example in the crashed escape pods (SCFI)
     * Town (TOWN)
     * Tower (TOWR)
     *
     * @param variant
     * @param environmentKey
     * @param ccFile
     */
    public WoXOutdoorEnvironmentSet(WoXWorld.WoxVariant variant, MaMCCFileReader ccFile) throws CCFileFormatException {
        super(variant, null, ccFile);

        surfaces = new MaMSurface[surfaceNameLut.length];
        for (int i = 0; i < surfaceNameLut.length; i++) {
            String surfaceName = surfaceNameLut[i];
            if(surfaceName != null)
            {
                surfaces[i] = ccFile.getSurface(surfaceName);
            }
        }
    }

    @Override
    public IRenderableGameObject getEnviron(int environIndex, int mipMapLevel) {
        return null;
    }

    @Override
    public IRenderableGameObject getMapEnviron(int environIndex) {
        return this.basicMapEnvrion[environIndex%this.basicMapEnvrion.length];
    }

    @Override
    public MaMSurface getSurface(int surfaceIndex) throws CCFileFormatException {
        return surfaces[(surfaceIndex)%surfaces.length];
    }

    @Override
    public IRenderableGameObject getMapTile(int groundIndex) {
        return this.basicMapGround[groundIndex%this.basicMapGround.length];
    }
}
