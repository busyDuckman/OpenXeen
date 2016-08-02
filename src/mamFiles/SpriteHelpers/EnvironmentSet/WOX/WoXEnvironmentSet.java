package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import Rendering.RenderablePos;
import Toolbox.BinaryHelpers;
import Toolbox.Direction;
import Toolbox.IImageWorker;
import Toolbox.Misc;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMSprite;
import mamFiles.MaMThing;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Map;

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

    protected WoXWorld.WoxVariant variant;
    protected MaMCCFileReader ccFile;

    IRenderableGameObject[] basicMapObjects = new IRenderableGameObject[basicMapTableSize];
    IRenderableGameObject[] basicMapEnvrion = new IRenderableGameObject[basicMapTableSize];
    IRenderableGameObject[] basicMapGround  = new IRenderableGameObject[basicMapTableSize];

    MaMThing[] basicThings = new MaMThing[basicMapTableSize];

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

        this.ccFile = ccFile;
        this.variant = variant;

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

        switch (variant)
        {
            case DARK_SIDE:

                break;
            case CLOUDS:
                break;
            case SWORDS:
                break;
            case UNKNOWN:
                break;
        }
    }

    /**
     * Info from: http://xeen.wikia.com/wiki/CLOUDS.DAT_File
     * The DAT files are 1452 bytes in size, with
     *   - 12 bytes representing one object, for a total of 121 possible objects.
     *   Each block of 12 bytes is broken into three blocks of 4 bytes.
     *    - The first block represents the first frame of the objects animation sequence.
     *    - The second block indicates whether the sprite needs to be horizontally flipped or not.
     *    - The last block is the last frame of the objects animation.
     *    Note, the first frame and last frame can be the same, indicating that the "animation" is just a static image.
     *    Most objects are static images.
     *    Each block of 4 bytes is broken into frames as front, left, back, right.
     */
    protected void loadObjectConfigFile(String file) throws CCFileFormatException {
        byte[] data = ccFile.getFileRaw(file);
        ByteArrayInputStream bisConfig = new ByteArrayInputStream(data);
        Direction[] unPackOrder = new Direction[] {Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT};

        for(int i=0; i<121; i++)
        {
            int[] startFrames = BinaryHelpers.readBYTEs(bisConfig, 4);
            int[] flipFrames = BinaryHelpers.readBYTEs(bisConfig, 4);
            int[] endFrames = BinaryHelpers.readBYTEs(bisConfig, 4);

            for(int d=0; d<4; d++)
            {
            }
        }

    }

    Map<Integer, Map<Direction, IImageWorker>> transroms;


    @Override
    public MaMThing getObject(int objectIndex)
    {
        if(objectIndex == 0)
        {
            return null;
        }

        objectIndex = Math.abs(objectIndex);
        String fileName = null;

        switch (variant)
        {
            case DARK_SIDE:
                objectIndex = objectIndex % 68;
                fileName = Misc.padZeros(objectIndex, 3)+".OBJ";
                break;
            case CLOUDS:
                break;
            case SWORDS:
                break;
            case UNKNOWN:
                break;
        }

        try {
            return ccFile.getThing(fileName);
        } catch (CCFileFormatException e) {
            e.printStackTrace();
            return null;
        }
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
