package Game.Map;

import Game.MaMGame;
import Game.Monsters.MaMMonster;
import Rendering.ISceneComposition;
import com.sun.istack.internal.NotNull;
import mamFiles.*;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;

import java.util.HashMap;
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
    MaMMazeView currentMazeView;


    protected MaMCCFileReader ccFile;
    protected MaMCCFileReader ccFileAnimations;

    protected MaMPallet currentPallate;

    public MaMWorld(MaMGame game, MaMCCFileReader ccFileReader) throws CCFileFormatException
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

    public abstract void loadMaps() throws CCFileFormatException;

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

    public MaMCCFileReader getCcFile() {
        return ccFile;
    }

    public MaMCCFileReader getCcFileAnimations() {
        return ccFileAnimations;
    }

    public MaMPallet getCurrentPallate() {
        return currentPallate;
    }

    public abstract IMaMIndoorEnvironmentSet getIndoorEnvironmentSet(int index);
    public abstract IMaMOutdoorEnvironmentSet getOutdoorEnvironmentSet(int index);

    public abstract ISceneComposition renderHUDForWorld();

    public abstract String getMazeName(int id) throws CCFileFormatException;
    public abstract String getScriptedEventsName(int id) throws CCFileFormatException;
    public abstract String getMonsterLayoutFile(int id) throws CCFileFormatException;
    public abstract String getHeadingFile(int id) throws CCFileFormatException;
    public abstract String getAreaNameFile(int id) throws CCFileFormatException;
    public abstract String getEventTextStringsFile(int id) throws CCFileFormatException;
    public abstract String getMapNameFile(int id) throws CCFileFormatException;

    @Override
    public void close() throws Exception {
        this.ccFile.close();
        this.ccFileAnimations.close();
    }

    public void setCurrentMap(int mapID)
    {
        this.currentMaze = getMazeFiles().get(mapID);
    }

    public abstract MaMSprite getNPCFaceOrNull(int id);
    public abstract MaMSprite getPlayerFaceOrNull(int id);
}
