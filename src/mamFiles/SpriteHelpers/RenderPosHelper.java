package mamFiles.SpriteHelpers;

import Rendering.RenderablePos;
import Toolbox.HProperties;
import Toolbox.IHasProperties;
import Toolbox.ParseHelpers;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duckman on 4/07/2016.
 *
 * There is a depth scheme to the 3d layout.
 * <=0     items in front of everything  (hitting the screen)
 * 0-9     items on the current tile
 * 10-19   items 1 tile away
 * 20-29   items 2 tile away
 * 30-39   items 3 tile away
 * 40-49   items 4 tile away
 *
 * Start with the base for distance away and then add the item distnce modifier
 */
public class RenderPosHelper implements IHasProperties
{
    private static RenderPosHelper globalHelper = new RenderPosHelper();

    public static RenderPosHelper getGlobalHelper() {
        return globalHelper;
    }

    public static void setGlobalHelper(RenderPosHelper globalHelper) {
        RenderPosHelper.globalHelper = globalHelper;
    }

    /**
     * The surface position, as rendered in dos, vga mode 13h
     * Renders 25 frames to a rectangle(x,y,w,h) (8, 67, 216, 73)
     * NB: These are hand curated, by lining up the wooden floor surface.
     * There is a render struct at http://xeen.wikia.com/wiki/MAZExxxx.DAT_File_Format#Outdoor_DrawStruct_list
     *    - I don't know how that one was put together, it looks reverse engineered from code
     *    - I don't know whos is better, or by how much they differ.
     */
    protected Point[] surfacePositions;
    protected RenderablePos[] outdoorEnvironmentPlacements;

    protected static Map<Point, Integer> relativePos2TileNumTable;

    //init static data
    public RenderPosHelper()
    {
        setupRelativePosToTileNumTable();
        setupSurfacePositions();
        setupOutdoorEnvironmentPlacements();
    }

    public enum RenderableType {
        SKY (-2046),
        GROUND (-1024),
        SURFACE (-256),

        OBJ_ENV (-16),
        OBJ_TOWN (-12),
        OBJ_ITEM (-8),
        OBJ_MONSTER (-4);

        int baseDepth;

        RenderableType(int baseDepth)
        {
            this.baseDepth = baseDepth;
        }

        public int getBaseDepth() {
            return baseDepth;
        }
    };

    /**
     * @param vsDistance How many steps away the item is,
     *                   ignore is ground/sky etc.
     */
    public int getDepth(RenderableType type, int vsDistance)
    {
        if(type == RenderableType.SURFACE)
        {
            return RenderableType.SURFACE.getBaseDepth() - vsDistance;
        }

        if(type.getBaseDepth() > RenderableType.SURFACE.getBaseDepth())
        {
            //we have an item on the map
            return (vsDistance * -32) + type.getBaseDepth();
        }
        return type.getBaseDepth();
    }

    private static void setupRelativePosToTileNumTable() {
        relativePos2TileNumTable = new HashMap<>();
        relativePos2TileNumTable.put(new Point(-1,  0), 0); //left
        relativePos2TileNumTable.put(new Point( 0,  0), 1); //current
        relativePos2TileNumTable.put(new Point( 1,  0), 2); //right

        // 1 step forward
        relativePos2TileNumTable.put(new Point(-1,  1), 3);
        relativePos2TileNumTable.put(new Point( 0,  1), 4);
        relativePos2TileNumTable.put(new Point( 1,  1), 5);

        // 2 steps forward
        relativePos2TileNumTable.put(new Point(-2,  2), 6);
        relativePos2TileNumTable.put(new Point(-1,  2), 7);
        relativePos2TileNumTable.put(new Point( 0,  2), 8);
        relativePos2TileNumTable.put(new Point( 1,  2), 9);
        relativePos2TileNumTable.put(new Point( 2,  2), 10);

        // 3 steps forward
        relativePos2TileNumTable.put(new Point(-3,  3), 11);
        relativePos2TileNumTable.put(new Point(-2,  3), 12);
        relativePos2TileNumTable.put(new Point(-1,  3), 13);
        relativePos2TileNumTable.put(new Point( 0,  3), 14);
        relativePos2TileNumTable.put(new Point( 1,  3), 15);
        relativePos2TileNumTable.put(new Point( 2,  3), 16);
        relativePos2TileNumTable.put(new Point( 3,  3), 17);

        // 4 steps forward
        relativePos2TileNumTable.put(new Point(-3,  4), 18);
        relativePos2TileNumTable.put(new Point(-2,  4), 19);
        relativePos2TileNumTable.put(new Point(-1,  4), 20);
        relativePos2TileNumTable.put(new Point( 0,  4), 21);
        relativePos2TileNumTable.put(new Point( 1,  4), 22);
        relativePos2TileNumTable.put(new Point( 2,  4), 23);
        relativePos2TileNumTable.put(new Point( 3,  4), 24);
    }

    private void setupSurfacePositions()
    {
        surfacePositions = new Point[] {
                new Point(8,109),   //tile left
                new Point(8,109),   //tile currently on
                new Point(201,109), //tile right

                //1 tile forward
                new Point(8,93),    //left
                new Point(31,93),   //middle
                new Point(169,93),  //right

                //2 tiles forward
                new Point(8,81),
                new Point(8,81),
                new Point(63,81),
                new Point(145,81),
                new Point(202,81),

                //3 tiles forward
                new Point(8,73),
                new Point(8,73),
                new Point(30,73),
                new Point(87,73),
                new Point(129,73),
                new Point(154,73),
                new Point(181,73),

                //4 tiles forward
                new Point(8,67),
                new Point(38,67),
                new Point(84,67),
                new Point(103,67),
                new Point(117,67),
                new Point(117,67),
                new Point(134,67)};
    }

    private void setupOutdoorEnvironmentPlacements()
    {
        int[] levels = new int[5];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = getDepth(RenderableType.OBJ_ENV, i);
        };
        RenderablePos.ScalePosition sPos = RenderablePos.ScalePosition.TopLeft;

        outdoorEnvironmentPlacements = new RenderablePos[]
        {
        new RenderablePos(-112, 30,  1.0,   sPos, levels[0]),      //Outdoor object 1 steps forward, 1 step left
        new RenderablePos( 98,  30,  1.0,   sPos, levels[0]),      //Outdoor object 1 steps forward, 1 step right
        new RenderablePos(-7,   30,  1.0,   sPos, levels[0]),      //Outdoor object directly 2 steps forward

        new RenderablePos(-112, 30,  1/2.0,   sPos, levels[1]),    //Outdoor object 1 steps forward, 1 step left
        new RenderablePos( 98,  30,  1/2.0,   sPos, levels[1]),    //Outdoor object 1 steps forward, 1 step right
        new RenderablePos(-7,   30,  1/2.0,   sPos, levels[1]),    //Outdoor object directly 2 steps forward

        null,
        new RenderablePos(-112, 30,  1/7.0,   sPos, levels[2]),    //Outdoor object 2 steps forward, 1 step left
        new RenderablePos( 98,  30,  1/7.0,   sPos, levels[2]),    //Outdoor object 2 steps forward, 1 step right
        new RenderablePos(-7,   30,  1/7.0,   sPos, levels[2]),    //Outdoor object directly 2 steps forward
        null,

        null,
        null,
        new RenderablePos( 57,  54,  1/12.0,  sPos, levels[3]),    //Outdoor object 3 steps forward, 1 step righ
        new RenderablePos(-73,  54,  1/12.0,  sPos, levels[3]),    //Outdoor object 3 steps forward, 1 step left
        new RenderablePos(-8,   54,  1/12.0,  sPos, levels[3]),    //Outdoor object directly 3 steps forward
        null,
        null,

        null,
        null,
        new RenderablePos( 64,  61,  1/14.0,  sPos, levels[4]),    //Outdoor object 4 steps forward, 2 steps right
        new RenderablePos(-82,  61,  1/14.0,  sPos, levels[4]),    //Outdoor object 4 steps forward, 2 steps left
        new RenderablePos( 40,  61,  1/14.0,  sPos, levels[4]),    //Outdoor object 4 steps forward, 1 step right
        new RenderablePos(-58,  61,  1/14.0,  sPos, levels[4]),    //Outdoor object 4 steps forward, 1 step left
        new RenderablePos(-9,   61,  1/14.0,  sPos, levels[4]),    //Outdoor object directly 4 steps forward
        null,
        null,
        };
    }

    //--------------------------------------------------------------------------------------------------------------
    public int getTileIndex(Point mapPosRelative)
    {
        return relativePos2TileNumTable.getOrDefault(mapPosRelative, -1);
    }

    public Point getSurfaceEnvPos(Point relaivePos)
    {
        int index = relativePos2TileNumTable.getOrDefault(relaivePos, -1);
        if(index >= 0)
        {
            return surfacePositions[index];
        }
        return null;
    }

    public Point getSurfaceEnvPos(int index)
    {
        if(index >= 0)
        {
            return surfacePositions[index];
        }
        return null;
    }


    public RenderablePos getOutdoorEnvPos(Point relaivePos)
    {
        int index = relativePos2TileNumTable.getOrDefault(relaivePos, -1);
        if(index >= 0)
        {
            return outdoorEnvironmentPlacements[index];
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    // IHasProperties
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean getProperties(HProperties p)
    {
        surfacePositions = p.getArray("surfacePositions", ParseHelpers::parsePointOrNull, Point[]::new);
        return true;
    }

    @Override
    public boolean setProperties(HProperties p)
    {
        p.setArray("surfacePositions", surfacePositions, ParseHelpers::pointToString);
        return true;
    }
}
