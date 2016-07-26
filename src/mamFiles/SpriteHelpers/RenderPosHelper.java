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
    //------------------------------------------------------------------------------------------------------------------
    // Nested classes
    //------------------------------------------------------------------------------------------------------------------
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

    //------------------------------------------------------------------------------------------------------------------
    // Static data
    //------------------------------------------------------------------------------------------------------------------
    public static final Dimension screenSize = new Dimension(216, 132);
    private static RenderPosHelper globalHelper = new RenderPosHelper();


    //------------------------------------------------------------------------------------------------------------------
    // Instance data
    //------------------------------------------------------------------------------------------------------------------

    //----------------------------
    // The kernel of relative positions processed
    protected static Map<Point, Integer> relativePos2TileNumTable;

    //----------------------------
    // Surfaces
    /**
     * The surface position, as rendered in dos, vga mode 13h
     * Renders 25 frames to a rectangle(x,y,w,h) (8, 67, 216, 73)
     * NB: These are hand curated, by lining up the wooden floor surface.
     * There is a render struct at http://xeen.wikia.com/wiki/MAZExxxx.DAT_File_Format#Outdoor_DrawStruct_list
     *    - I don't know how that one was put together, it looks reverse engineered from code
     *    - I don't know whos is better, or by how much they differ.
     */
    protected Point[] surfacePositions;

    //----------------------------
    // Outdoor environment objects
    protected RenderablePos[] outdoorEnvironmentPlacements;
    protected int[] outdoorEnvironmentFrames;

    //------------------------------------------------------------------------------------------------------------------
    // Constructors and setup
    //------------------------------------------------------------------------------------------------------------------
    //init static data
    public RenderPosHelper()
    {
        setupRelativePosToTileNumTable();
        setupSurfacePositions();
        setupOutdoorEnvironmentPlacementsAndFrmes();
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
        relativePos2TileNumTable.put(new Point(-4,  4), 18);
        relativePos2TileNumTable.put(new Point(-3,  4), 19);
        relativePos2TileNumTable.put(new Point(-2,  4), 20);
        relativePos2TileNumTable.put(new Point(-1,  4), 21);
        relativePos2TileNumTable.put(new Point( 0,  4), 22);
        relativePos2TileNumTable.put(new Point( 1,  4), 23);
        relativePos2TileNumTable.put(new Point( 2,  4), 24);
        relativePos2TileNumTable.put(new Point( 3,  4), 25);
        relativePos2TileNumTable.put(new Point( 4,  4), 26);
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
                null,
                new Point(8,67),
                new Point(38,67),
                new Point(84,67),
                new Point(103,67),
                new Point(117,67),
                new Point(117,67),
                new Point(134,67),
                null};
    }

    private void setupOutdoorEnvironmentPlacementsAndFrmes()
    {
        // Size = 3 3 5 7 9
        // assumes 0 1 2 = edge, middle, far edge
        //         3 4 5 = mirrored frames of the above
        outdoorEnvironmentFrames = new int[] {
                -1, 1, -1,
                0, 1, 3,
                -1, 2, 1, 5, -1,

                4, 1, 4, 1, 4, 1, 4,
                1, 4, 1, 4, 1, 4, 1, 4, 1
        };

        //Placements
        int[] levels = new int[5];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = getDepth(RenderableType.OBJ_ENV, i);
        };
        RenderablePos.ScalePosition sPos = RenderablePos.ScalePosition.TopLeft;

        outdoorEnvironmentPlacements = new RenderablePos[]
                {
                        null,
                        new RenderablePos(0,     0,  MaMScaleLut(-9),  sPos, levels[0]),
                        null,

                        new RenderablePos(0,    24,  MaMScaleLut(0),   sPos, levels[1]),        //Outdoor object 1 steps forward, 1 step left
                        new RenderablePos(41,   24,  MaMScaleLut(0),   sPos, levels[1]),        //Outdoor object directly 2 steps forward
                        new RenderablePos(176,  24,  MaMScaleLut(0),   sPos, levels[1]),        //Outdoor object 1 steps forward, 1 step right

                        //this looks odd because of those odd pre scaled edge sprites
                        null,
                        new RenderablePos(-4,   37,  MaMScaleLut(0),   sPos, levels[2]),    //Outdoor object 2 steps forward, 1 step left
                        new RenderablePos(60,   33,  MaMScaleLut(5),   sPos, levels[2]),        //Outdoor object directly 2 steps forward
                        new RenderablePos(169,  37,  MaMScaleLut(0),   sPos, levels[2]),        //Outdoor object 2 steps forward, 1 step right
                        null,

                        null,
                        new RenderablePos( -20,  50,  MaMScaleLut(10),  sPos, levels[3]),
                        new RenderablePos( 27,   50,  MaMScaleLut(10),  sPos, levels[3]),    //Outdoor object 3 steps forward, 1 step righ
                        new RenderablePos( 91,   50,  MaMScaleLut(10),  sPos, levels[3]),    //Outdoor object 3 steps forward, 1 step left
                        new RenderablePos(149,   50,  MaMScaleLut(10),  sPos, levels[3]),    //Outdoor object directly 3 steps forward
                        new RenderablePos( 180,  50,  MaMScaleLut(10),  sPos, levels[3]),
                        null,

                        new RenderablePos(8,     61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(31,    61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(53,    61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(76,    61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(99,    61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(122,   61,  MaMScaleLut(14),  sPos, levels[4]), //middle
                        new RenderablePos(145,   61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(168,   61,  MaMScaleLut(14),  sPos, levels[4]),
                        new RenderablePos(191,   61,  MaMScaleLut(14),  sPos, levels[4]),
                };
    }


    //------------------------------------------------------------------------------------------------------------------
    // Getters and setters
    //------------------------------------------------------------------------------------------------------------------
    public int getNumMappedPoints() { return relativePos2TileNumTable.size(); }
    public int getNumSurfacePositions() { return 25; }

    public int getTileIndex(Point mapPosRelative)
    {
        return relativePos2TileNumTable.getOrDefault(mapPosRelative, -1);
    }

    public static RenderPosHelper getGlobalHelper() {
        return globalHelper;
    }

    public static void setGlobalHelper(RenderPosHelper globalHelper) {
        RenderPosHelper.globalHelper = globalHelper;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Depth Helpers
    //------------------------------------------------------------------------------------------------------------------
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

    //------------------------------------------------------------------------------------------------------------------
    // Surface Helpers
    //------------------------------------------------------------------------------------------------------------------
    public Point getSurfaceEnvPos(Point relativePos)
    {
        int index = relativePos2TileNumTable.getOrDefault(relativePos, -1);
        return getSurfaceEnvPos(index);
    }

    public Point getSurfaceEnvPos(int index)
    {
        if((index >= 0) && (index < surfacePositions.length))
        {
            return surfacePositions[index];
        }
        return null;
    }

    public boolean surfaceVisibleFor(Point relativePos)
    {
        int index = relativePos2TileNumTable.getOrDefault(relativePos, -1);
        return surfaceVisibleFor(index);
    }
    
    public boolean surfaceVisibleFor(int index)
    {
        return getSurfaceEnvPos(index) != null;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Outdoor environment item helpers
    //------------------------------------------------------------------------------------------------------------------
    public RenderablePos getOutdoorEnvPos(Point relativePos)
    {
        int index = relativePos2TileNumTable.getOrDefault(relativePos, -1);
        if(index >= 0)
        {
            return outdoorEnvironmentPlacements[index];
        }
        return null;
    }

    //TODO: For now this returns the sprite frame... should return a full sprite
    public int getOutdoorEnvFrame(Point relativePos)
    {
        int index = relativePos2TileNumTable.getOrDefault(relativePos, -1);
        if(index >= 0)
        {
            return outdoorEnvironmentFrames[index];
        }
        return 0;
    }


    //------------------------------------------------------------------------------------------------------------------
    // Misc
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Scale values translated to n in 16 lines skipped.
     * I am not going to skip lines, but instead calculate an equivalent scale.
     *
     */
    protected double MaMScaleLut(int scaleValue)
    {
        //scaleValue = Math.max(Math.min(scaleValue, 15), -256);
        return  1.0 - (scaleValue/16.0);
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
