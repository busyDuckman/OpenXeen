package Game.Map;

import Game.IGameEntity;
import Toolbox.Direction;
import Toolbox.Grid;
import Toolbox.IReadonlyGrid;
import Toolbox.LRUCache;
import mamFiles.MaMMazeFile;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by duckman on 1/07/2016.
 *
 * Mazes do not necessarily exist on their own.
 *  - They join onto other mazes.
 *  - They  have a null space around them
 *
 * This class joins mazes together and provides a simple
 * interface for interacting with the world. Behind the
 * scenes It is loading and caching mazes as needed.
 *
 * Importantly this is also where the state of the maze
 * in the game is saved/loaded. So when you leave a map
 * area, its state is serialised and stored for later.
 *
 * Concepts:
 *    NAME          PREFIX     DESCRIPTION
 *    --------------------------------------------------------------------
 *  - World space   ws         The absolute location of a tile or the party.
 *  - View space    vs         The world, from the perspective of the party.
 *  - Maze space    ms         Local co-ordinates for any individual maze.
 *  - Join space    js         The location of a maze (unit is 'one maze length')
 *
 */
public class MaMMazeView implements IReadonlyGrid<MaMTile>
{
    /**
     * A collection of mazes, Presumably entities are not active in these.
     * The point is in Join space
     */
    LRUCache<Point, MaMMazeFile> recentMazes;
    MaMMazeFile currentMaze;


    // All mazes must be of the same size
    protected final int msWidth;
    protected final int msHeight;

    // The number of mazes in our grid
    protected final int jsWidth;
    protected final int jsHeight;
    //protected final Function<Point, MaMMazeFile> getMaze;
    protected final Map<Point, MaMMazeFile> mazeLut;
    transient protected final Map<MaMMazeFile, Point> reverseMazeLut;

    protected final Function<Point, MaMMazeFile> onLeaveMaze;
    protected final Function<Point, MaMMazeFile> onEnterMaze;

    protected Point wsPlayerPos;

    /**
     * @param mazeLut Returns a sub maze for a point (point is in join space, not world space)
     * @param onLeaveMaze Notifies game that the player has left a maze (so it can save it)
     * @param onEnterMaze  Notifies game that the player has entered a maze (activate Monsters)
     */
    public MaMMazeView(int subMazeWidth, int subMazeHeight,
                       int totalMapsInXAxis, int totalMapsInYAxis,
                       Map<Point, MaMMazeFile> mazeLut,
                       Function<Point, MaMMazeFile> onLeaveMaze,
                       Function<Point, MaMMazeFile> onEnterMaze)
    {
        this.msWidth = subMazeWidth;
        this.msHeight = subMazeHeight;
        this.jsWidth = totalMapsInXAxis;
        this.jsHeight = totalMapsInYAxis;
        this.mazeLut = mazeLut;
        this.onLeaveMaze = onLeaveMaze;
        this.onEnterMaze = onEnterMaze;

        recentMazes = new LRUCache<>(16);

        wsPlayerPos = new Point(8,8);
        currentMaze = getMazeFileForPoint(wsPlayerPos);
        recentMazes.put(getJoinSpace(wsPlayerPos), currentMaze);

        reverseMazeLut = mazeLut.entrySet()
                            .stream()
                            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public void changePlayerPos(Point wsPos)
    {
        Point jsPosNew = getJoinSpace(wsPos);
        Point jsPosOld = getJoinSpace(wsPlayerPos);

        wsPlayerPos = wsPos;

        if(!jsPosNew.equals(jsPosOld))
        {
            currentMaze = mazeLut.getOrDefault(jsPosNew, null);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // IReadonlyGrid<MaMTile>
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public MaMTile get(int wsX, int wsY) {
        MaMMazeFile maze = getMazeFileForPoint(wsX, wsY);
        Point msPos = getMazeSpace(wsX, wsY);
        if((maze != null) && (maze.getMap().isValidLocation(msPos.x, msPos.y)))
        {
            return maze.getMap().get(msPos.x, msPos.y);
        }
        return null;
    }

    @Override
    public boolean isValidLocation(int wsX, int wsY) {
        MaMMazeFile maze = getMazeFileForPoint(wsX, wsY);
        Point msPos = getMazeSpace(wsX, wsY);
        return ((maze != null) && (maze.getMap().isValidLocation(msPos.x, msPos.y)));
    }

    @Override
    public MaMTile get(Point wsPos) {
        return get(wsPos.x, wsPos.y);
    }

    @Override
    public int getWidth() {
        return msWidth * jsWidth;
    }

    @Override
    public int getHeight() {
        return msHeight * jsHeight;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Misc
    //------------------------------------------------------------------------------------------------------------------
    protected final Point getJoinSpace(int wsX, int wsY) {
        return new Point(wsX/msWidth, wsY/msWidth);
    }

    protected final Point getJoinSpace(Point wsPos) {
        return getJoinSpace(wsPos.x, wsPos.y);
    }

    public final Point getMazeSpace(int wsX, int wsY) {
        return new Point(wsX%msWidth, wsY%msWidth);
    }

    public final Point getMazeSpace(Point wsPos) {
        return getMazeSpace(wsPos.x, wsPos.y);
    }

    public final MaMMazeFile getMazeFileForPoint(Point wsPos)
    {
        return getMazeFileForPoint(wsPos.x, wsPos.y);
    }

    public final MaMMazeFile getMazeFileForPoint(int wsX, int wsY)
    {
        Point jsPos = getJoinSpace(wsX, wsY);
        MaMMazeFile maze = recentMazes.getOrDefault(jsPos, null);
        if (maze == null)
        {
            maze = mazeLut.getOrDefault(jsPos, null);

            if(maze != null)
            {
                recentMazes.put(jsPos, maze);
            }
        }
        return maze;
    }


    /**
     * Gets A world space position, or null if not in the view.
     */
    public Point getWorldSpaceForEntity(IGameEntity entity)
    {
        MaMMazeFile maze = entity.getParentMaze();
        Point jsMazePos = reverseMazeLut.getOrDefault(maze, null);
        if(jsMazePos != null) {
            Point wsPos = new Point((msWidth * jsMazePos.x) + entity.getLocationMapSpace().x,
                    (msHeight * jsMazePos.y) + entity.getLocationMapSpace().y);
            return wsPos;
        }

        return null;
    }

}
