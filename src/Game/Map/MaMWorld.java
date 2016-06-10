package Game.Map;

import Game.MaMGame;
import Game.Monsters.MaMMonster;
import Rendering.ISceneComposition;
import Rendering.MaM2DInsertionOrderComposition;
import Rendering.MaM2DMapComposition;
import com.sun.istack.internal.NotNull;
import mamFiles.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duckman on 15/05/2016.
 *
 * Why is the class not part of Game class? because a game can have
 * multiple worlds (darkside, clouds)
 *
 * It's also the break between the game mechanics (my rules) to the
 * game art and resources (which are variable).
 *
 */
public abstract class MaMWorld implements AutoCloseable
{
    MaMMonster[] monsters = null;
    MaMGame game;

    String worldName;

    Map<Integer, MaMMazeFile> MazeFiles;
    MaMMazeFile currentMaze;

    protected CCFileReader ccFile;
    protected CCFileReader ccFileAnimations;

    protected MaMPallet currentPallate;

    public MaMWorld(MaMGame game, CCFileReader ccFileReader) throws CCFileFormatException
    {
        this.ccFile = ccFileReader;
        this.game = game;
        currentPallate = getDefaultPallate();
        monsters = ccFile.loadMonsters(this);
        MazeFiles = new HashMap<>();
        currentMaze = null;

    }

    protected abstract MaMPallet getDefaultPallate() throws CCFileFormatException;

    public MaMGame getGame() {
        return game;
    }

    public MaMMonster[] getMonsters() {
        return monsters;
    }

    public String getWorldName() {
        return worldName;
    }

    public Map<Integer, MaMMazeFile> getMazeFiles() {
        return MazeFiles;
    }

    public MaMMazeFile getCurrentMaze() {
        return currentMaze;
    }

    public void addMaze(@NotNull MaMMazeFile maze)
    {
        MazeFiles.put(maze.getMazeID(), maze);
    }

    public CCFileReader getCcFile() {
        return ccFile;
    }

    public CCFileReader getCcFileAnimations() {
        return ccFileAnimations;
    }

    public MaMPallet getCurrentPallate() {
        return currentPallate;
    }

    public abstract ISceneComposition renderHUDForWorld();

    public abstract String getMazeName(int id);
    public abstract String getScriptedEventsName(int id);
    public abstract String getMonsterLayoutFile(int id);
    public abstract String getHeadingFile(int id);
    public abstract String getAreaNameFile(int id);
    public abstract String getEventTextStringsFile(int id);
    public abstract String getMapNameFile(int id);

    @Override
    public void close() throws Exception {
        this.ccFile.close();
        this.ccFileAnimations.close();
    }
}
