package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMSprite;
import mamFiles.MaMSurface;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;

import java.awt.*;

/**
 * Created by duckman on 16/06/2016.
 */
public class WoXIndoorEnvironmentSet extends WoXEnvironmentSet implements IMaMIndoorEnvironmentSet
{
    //------------------------------------------------------------------------------------------------------------------
    // Instance data
    //------------------------------------------------------------------------------------------------------------------
    protected MaMSprite[] frontWallsSprites;
    protected MaMSprite sideWallSprite;
    protected IRenderableGameObject[] mapTileSetFloorSprite;
    protected IRenderableGameObject[] mapTileSetWallSprite;

    // frontWallMap(wallNum, mipmapLevel)
    protected Table<Integer, Integer, MaMSprite> frontWallMap = HashBasedTable.create();

    //------------------------------------------------------------------------------------------------------------------
    // Constructors, setup and factory methods
    //------------------------------------------------------------------------------------------------------------------

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
    public WoXIndoorEnvironmentSet(WoXWorld.WoxVariant variant, String environmentKey, MaMCCFileReader ccFile) throws CCFileFormatException {
        super(variant, environmentKey, ccFile);

        System.out.println("Loading environment set: " + environmentKey);

        String key = environmentKey.toUpperCase().trim();
        this.sideWallSprite = ccFile.getSprite("S" + key +".SWL");  // Sxxxx.SWL

        this.frontWallsSprites = new MaMSprite[4];
        for (int i = 0; i < frontWallsSprites.length; i++) {
            frontWallsSprites[i] = ccFile.getSprite("F" + key + (i+1) + ".FWL");  //Fxxxxi.FWL
        }

        loadFrontWallSpriteTable(variant, key);

        // Tileset for indoors is different to outdoors.
        //   - It has walls
        //   - It has 2 alternate floor tiles
        //   - frames in different position
        MaMSprite allMapTiles = ccFile.getSprite(key +".TIL");         // xxxx.TIL

        // 2 map tiles,
        // 32 wall tiles (V and H versions of 16 walls)
        // 2 Post tiles (V and H)
        // 16 ground tiles (outdoor).
        CCFileFormatException.assertFalse(allMapTiles == null, "WoXIndoorEnvironmentSet() allMapTiles == null");
        this.mapTileSetFloorSprite = allMapTiles.subSetOfFrames("a", 0, 2)
                                            .appendSprite("surface map tiles", allMapTiles.subSetOfFrames("b", 36, 16))
                                            .eachFrameAsRenderable();
        this.mapTileSetWallSprite = allMapTiles.subSetOfFrames("indoor wall map tiles", 2, 32+2).eachFrameAsRenderable();
    }

    public void loadFrontWallSpriteTable(WoXWorld.WoxVariant variant, String key) throws CCFileFormatException {
        MaMSprite allFullSize=null;
        MaMSprite allHalfSize=null;
        MaMSprite allQuaterSize=null;
        MaMSprite allEighthSize=null;
        switch(variant)
        {
            case DARK_SIDE:
                //first set spans two sprites
                allFullSize = frontWallsSprites[0].appendSprite("full size", frontWallsSprites[1]);
                ///next two span 1 sprite (due to storage space reduction)
                allHalfSize = frontWallsSprites[2].subSetOfFrames("1/2 size", 0, 17);
                allQuaterSize = frontWallsSprites[2].subSetOfFrames("1/4 size", 17, 17);
                //last level, one sprite
                allEighthSize = frontWallsSprites[3];//.subSetOfFrames("1/8 size", 0, 17);
                break;
            case CLOUDS:
                //CCFileFormatException.throwFeatureNotReady("");
                allFullSize = frontWallsSprites[0].appendSprite("full size", frontWallsSprites[1]);
                ///next two span 1 sprite (due to storage space reduction)
                allHalfSize = frontWallsSprites[2].subSetOfFrames("1/2 size", 0, 17);
                allQuaterSize = frontWallsSprites[2].subSetOfFrames("1/4 size", 17, 17);
                //last level, one sprite
                allEighthSize = frontWallsSprites[3];//.subSetOfFrames("1/8 size", 0, 17);
                break;
            case SWORDS:
                CCFileFormatException.throwFeatureNotReady("");
                break;
        }


        //load table.
        loadSpritesIntoTable(allFullSize,   key + " - full size", 0, variant);
        loadSpritesIntoTable(allHalfSize,   key + " - 1/2 size",  1, variant);
        loadSpritesIntoTable(allQuaterSize, key + " - 1/4 size",  2, variant);
        loadSpritesIntoTable(allEighthSize, key + " - 1/8 size",  3, variant);

    }

    private void loadSpritesIntoTable(MaMSprite sprite,
                                      String levelString,
                                      int levelNum,
                                      WoXWorld.WoxVariant variant) throws CCFileFormatException
    {
        try
        {
            //TODO: this was 16 and worked for darkside, but cause issues for clouds
            if(sprite.getRenderedFrames().length > 17)
            {
                //The towns(?) have an extra sprite to animate walking through a door.
                sprite = sprite.whereFrames(sprite.getName(), I -> I != 7);
            }

            frontWallMap.put(0, levelNum, sprite.subSetOfFrames("Standard wall " + levelString, 0, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Torch wall " + levelString, 1, 5));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Wall with niche " + levelString, 6, 1));
            //frontWallMap.put(1, levelNum, allFullSize.subSetOfFrames("Unknown " + levelString, 6, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Door (Closed) " + levelString, 7, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Door (Open) " + levelString, 8, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Grate (Closed) " + levelString, 9, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Bashed Wall " + levelString, 10, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Stairs (Up) " + levelString, 11, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Stairs (Down) " + levelString, 12, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Safe (Closed) " + levelString, 13, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Grate (Open) " + levelString, 14, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Safe (Open) " + levelString, 15, 1));
            frontWallMap.put(1, levelNum, sprite.subSetOfFrames("Posts " + levelString, 16, 1));
        }
        catch (CCFileFormatException ex)
        {
            throw CCFileFormatException.wrapWith(ex, sprite);
        }

    }

    public static WoXIndoorEnvironmentSet[] getEnvironmentSets(WoXWorld.WoxVariant variant,
                                                         MaMCCFileReader ccFile) throws CCFileFormatException {
        WoXIndoorEnvironmentSet[] sets = new WoXIndoorEnvironmentSet[0];
        switch (variant) {
            case DARK_SIDE:
                sets = new WoXIndoorEnvironmentSet[6];
                sets[0] = new WoXIndoorEnvironmentSet(variant, "CAVE", ccFile);
                sets[1] = new WoXIndoorEnvironmentSet(variant, "CSTL", ccFile);
                sets[2] = new WoXIndoorEnvironmentSet(variant, "DUNG", ccFile);
                sets[3] = new WoXIndoorEnvironmentSet(variant, "SCFI", ccFile);
                sets[4] = new WoXIndoorEnvironmentSet(variant, "TOWN", ccFile);
                sets[5] = new WoXIndoorEnvironmentSet(variant, "TOWR", ccFile);
                break;
            case CLOUDS:
                sets = new WoXIndoorEnvironmentSet[5];
                sets[0] = new WoXIndoorEnvironmentSet(variant, "CAVE", ccFile);
                sets[1] = new WoXIndoorEnvironmentSet(variant, "CSTL", ccFile);
                sets[2] = new WoXIndoorEnvironmentSet(variant, "DUNG", ccFile);
                sets[3] = new WoXIndoorEnvironmentSet(variant, "TOWN", ccFile);
                sets[4] = new WoXIndoorEnvironmentSet(variant, "TOWR", ccFile);
                break;
            case SWORDS:
                CCFileFormatException.throwFeatureNotReady("Clouds environment set");
                break;
            default:
                CCFileFormatException.notSupposedToBeHere();
                break;
        }

        return sets;
    }

    //------------------------------------------------------------------------------------------------------------------
    // IMaMIndoorEnvironmentSet
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public IRenderableGameObject getSideWall(int wallindex, Point mapPositionRelativeToLocation) {
        //TODO: [0] is a fudge.
        return IRenderableGameObject.fromImage(sideWallSprite.getRenderedFrames()[0]);
    }

    @Override
    public IRenderableGameObject getFrontWall(int wallIndex, int mipMapLevel) {

        return frontWallMap.get(wallIndex, mipMapLevel);
    }

    @Override
    public IRenderableGameObject getMapWall(int wallIndex) {
        return mapTileSetWallSprite[wallIndex];
    }


    @Override
    public MaMSurface getSurface(int surfaceIndex) throws CCFileFormatException {
        CCFileFormatException.throwFeatureNotReady("No surfaces for indoor maps yet.");
        return null;
    }

    @Override
    public IRenderableGameObject getMapTile(int groundIndex) {
        return mapTileSetFloorSprite[groundIndex];
    }
}
