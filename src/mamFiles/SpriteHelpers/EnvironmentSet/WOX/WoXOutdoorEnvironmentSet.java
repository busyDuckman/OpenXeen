package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;

/**
 * Created by duckman on 16/06/2016.
 */
public class WoXOutdoorEnvironmentSet extends WoXEnvironmentSet implements IMaMOutdoorEnvironmentSet
{

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
    public IRenderableGameObject getMapTile(int groundIndex) {
        return this.basicMapGround[groundIndex%this.basicMapGround.length];
    }
}
