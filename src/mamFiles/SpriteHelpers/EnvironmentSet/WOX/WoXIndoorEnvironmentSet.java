package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import mamFiles.CCFileFormatException;
import mamFiles.CCFileReader;
import mamFiles.MaMSprite;
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
    public WoXIndoorEnvironmentSet(WoXWorld.WoxVariant variant, String environmentKey, CCFileReader ccFile) throws CCFileFormatException {
        super(variant, environmentKey, ccFile);

        String key = environmentKey.toUpperCase().trim();
        this.sideWallSprite = ccFile.getSprite("S" + key +".SWL");  // Sxxxx.SWL

        this.frontWallsSprites = new MaMSprite[4];
        for (int i = 0; i < frontWallsSprites.length; i++) {
            frontWallsSprites[i] = ccFile.getSprite("F" + key + (i+1) + ".FWL");  //Fxxxxi.FWL
        }

        loadFrontWallSpriteTable();

        // Tileset for indoors is different to outdoors.
        //   - It has walls
        //   - It has 2 alternate floor tiles
        //   - frames in different position
        MaMSprite allMapTiles = ccFile.getSprite(key +".TIL");         // xxxx.TIL

        // 2 map tiles,
        // 32 wall tiles (V and H versions of 16 walls)
        // 2 Post tiles (V and H)
        // 16 ground tiles (outdoor).
        CCFileFormatException.assertFalse(allMapTiles == null);
        this.mapTileSetFloorSprite = allMapTiles.subSetOfFrames("a", 0, 2)
                                            .appendSprite("surface map tiles", allMapTiles.subSetOfFrames("b", 36, 16))
                                            .eachFrameAsRenderable();
        this.mapTileSetWallSprite = allMapTiles.subSetOfFrames("indoor wall map tiles", 2, 32+2).eachFrameAsRenderable();
    }

    public void loadFrontWallSpriteTable()
    {
        //first set spans two sprites
        MaMSprite allFullSize = frontWallsSprites[0].appendSprite("full size", frontWallsSprites[1]);
        ///next two span 1 sprite (due to storage space reduction)
        MaMSprite allHalfSize = frontWallsSprites[2].subSetOfFrames("1/2 size", 0, 17);
        MaMSprite allQuaterSize = frontWallsSprites[2].subSetOfFrames("1/4 size", 17, 17);
        //last level, one sprite
        MaMSprite allEighthSize = frontWallsSprites[3];//.subSetOfFrames("1/8 size", 0, 17);

        //load table.
        loadSpritesIntoTable(allFullSize,   "full size", 0);
        loadSpritesIntoTable(allHalfSize,   "1/2 size",  1);
        loadSpritesIntoTable(allQuaterSize, "1/4 size",  2);
        loadSpritesIntoTable(allEighthSize, "1/8 size",  3);


    }

    private void loadSpritesIntoTable(MaMSprite sprite, String levelString, int levelNum) {
        if(sprite.getRenderedFrames().length > 16)
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

    public static WoXIndoorEnvironmentSet[] getEnvironmentSets(WoXWorld.WoxVariant variant,
                                                         CCFileReader ccFile) throws CCFileFormatException {
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
                CCFileFormatException.throwFeatureNotReady("Clouds environment set");
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
    public IRenderableGameObject getMapTile(int groundIndex) {
        return mapTileSetFloorSprite[groundIndex];
    }
}
