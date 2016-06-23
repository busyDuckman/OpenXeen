package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMSprite;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;

import java.awt.image.BufferedImage;

/**
 * Created by duckman on 15/06/2016.
 *
 * Wrangles the jungle of sprites relating to the current environment.
 */
public abstract class WoXEnvironmentSet implements IMaMEnvironmentSet
{
    private static final int basicMapTableSize = 16;
    protected MaMSprite skySprite;
    protected MaMSprite groundSprite;
    protected MaMSprite basicMapTileSet; //has the basics, lava, mountains, fountains, huts towns etc.

    IRenderableGameObject[] basicMapObjects = new IRenderableGameObject[basicMapTableSize];
    IRenderableGameObject[] basicMapEnvrion = new IRenderableGameObject[basicMapTableSize];
    IRenderableGameObject[] basicMapGround  = new IRenderableGameObject[basicMapTableSize];
    /**
     * Loads a set of environment sprites.
     *
     * Environment keys are 4 char, EG:
     *
     * Cave (CAVE)
     * Castle (CSTL)
     * Dungeon (DUNG)
     * Sci-Fi, used for example in the crashed escape pods (SCFI)
     * Town (TOWN)
     * Tower (TOWR)
     */
    public WoXEnvironmentSet(WoXWorld.WoxVariant variant,
                             String environmentKey,
                             MaMCCFileReader ccFile) throws CCFileFormatException {

        if(environmentKey != null)
        {
            String key = environmentKey.toUpperCase().trim();

            this.skySprite      = ccFile.getSprite(key +".SKY");        // xxxx.SKY
            this.groundSprite   = ccFile.getSprite(key +".GND");        // xxxx.GND
        }
        else
        {
            this.skySprite      = ccFile.getSprite("SKY.SKY");
            this.groundSprite   = ccFile.getSprite("TOWN.GND");
        }
        this.basicMapTileSet = ccFile.getSprite("OUTDOOR.TIL");         // xxxx.TIL

        //load some tables with sprites ready to go.
        BufferedImage[] basicMapFrames = basicMapTileSet.getRenderedFrames();
        for (int i = 0; i < basicMapTableSize; i++) {
            basicMapGround[i]  = IRenderableGameObject.fromImage(basicMapFrames[i]);
            basicMapEnvrion[i] = IRenderableGameObject.fromImage(basicMapFrames[basicMapTableSize + i]);
            basicMapObjects[i] = IRenderableGameObject.fromImage(basicMapFrames[(2*basicMapTableSize) + i]);
        }
    }

    @Override
    public IRenderableGameObject getObject(int objectIndex, int mipMapLevel) {
        return null;
    }

    @Override
    public IRenderableGameObject getSky() {
        return skySprite;
    }

    @Override
    public IRenderableGameObject getGround() {
        return groundSprite;
    }

    @Override
    public IRenderableGameObject getMapObject(int objectIndex) {
//        objectIndex = objectIndex % 16;
//        return IRenderableGameObject.fromImage(basicMapTileSet.getRenderedFrames()[32 + objectIndex]);
        return basicMapObjects[objectIndex%basicMapObjects.length];
    }

}
