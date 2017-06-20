package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import Toolbox.*;
import mamFiles.*;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;
import mamFiles.WOX.WoXThing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
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

    Map<Integer, Map<Direction, MaMSprite.SpriteView>> things;

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
                loadObjectConfigFile("DARK.DAT");
                break;
            case CLOUDS:
                loadObjectConfigFile("CLOUDS.DAT");
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
        //Not sure where to get this file in some versions.
        MaMCCFileReader finalCCFile = ccFile.searchWithAssociatesForFile(file, true);
        byte[] data;

        if(finalCCFile == null) {
            //There is a CLOUDS.DAT.BIN in resources, because it was hard coded into the mm4 exe.
            data = FileHelpers.tryGetResourceFile(file+".BIN");
            if(data == null) {
                throw CCFileFormatException.fromMissingFile(file, ccFile);
            }
        }
        else {
            data = finalCCFile.getFileRaw(file);
        }

        ByteArrayInputStream bisConfig = new ByteArrayInputStream(data);
        Direction[] unPackOrder = new Direction[] {Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT};
        if(things == null) {
            things = new HashMap<>();
        }

        for(int i=0; i<121; i++)
        {
            int[] startFrames = BinaryHelpers.readBYTEs(bisConfig, 4);
            int[] flipFrames = BinaryHelpers.readBYTEs(bisConfig, 4);
            int[] endFrames = BinaryHelpers.readBYTEs(bisConfig, 4);

            Map<Direction, MaMSprite.SpriteView> sViews = new HashMap<>();

            for(int d=0; d<4; d++)
            {
                MaMSprite.SpriteView sView = new MaMSprite.SpriteView(
                        startFrames[d],
                        endFrames[d],
                        (flipFrames[d] != 0) ? ImageTransform.FLIP : ImageTransform.NO_OPERATION);

                sViews.put(unPackOrder[d], sView);
            }

            things.put(i, sViews);
        }

    }

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
                objectIndex = objectIndex % 121;
                fileName = Misc.padZeros(objectIndex, 3)+".OBJ";
                break;
            case SWORDS:
                break;
            case UNKNOWN:
                break;
        }

        try {
            return ccFile.getThing(fileName);
            //TODO: Cache this
            //return new WoXThing(ccFile.getSprite(fileName), MAMFile.generateUniqueKey(fileName));

            //return ccFile.getSprite(fileName).getView(
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
        return basicMapObjects[(objectIndex)%basicMapObjects.length];
    }

}
