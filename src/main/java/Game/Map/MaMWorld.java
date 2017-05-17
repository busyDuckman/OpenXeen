package Game.Map;

import Game.IGameEntity;
import Game.IScript;
import Game.MaMGame;
import Game.Monsters.MaMMonster;
import Rendering.ISceneComposition;
import Toolbox.Direction;
//import com.sun.istack.internal.NotNull;
import mamFiles.*;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
    //MaMMonster[] entities = null;
    MaMGame game;

    String worldName;

    Map<Integer, MaMMazeFile> mazeFiles;
    Map<String, MaMMazeView> mazeViews;
    MaMMazeView currentMazeView;

    List<IGameEntity> entities;

    protected MaMCCFileReader ccFile;
    protected MaMCCFileReader ccFileAnimations;

    //protected MaMPallet currentPallate;

    public MaMWorld(MaMGame game, MaMCCFileReader ccFileReader) throws CCFileFormatException
    {
        this.ccFile = ccFileReader;
        this.game = game;
        //currentPallate = getDefaultPallate();
        //entities = ccFile.loadMonsters(this);

        mazeFiles = new HashMap<>();
        mazeViews = new HashMap<>();
        currentMazeView = null;

        entities = new ArrayList<>();
    }

    //protected abstract MaMPallet getDefaultPallate() throws CCFileFormatException;

    public MaMGame getGame() {
        return game;
    }

    public List<IGameEntity> getEntities() {
        return entities;
    }

    public String getWorldName() {
        return worldName;
    }

    public abstract void loadMaps() throws CCFileFormatException;
    public abstract void loadMazeViews() throws CCFileFormatException;

    public Map<Integer, MaMMazeFile> getMazeFiles() {
        return mazeFiles;
    }

    public MaMMazeView getCurrentMazeView() {
        return currentMazeView;
    }

    public void addMaze(MaMMazeFile maze)
    {
        mazeFiles.put(maze.getMazeID(), maze);
    }

    public MaMCCFileReader getCcFile() {
        return ccFile;
    }

    public MaMCCFileReader getCcFileAnimations() {
        return ccFileAnimations;
    }

//    public MaMPallet getCurrentPallate() {
//        return currentPallate;
//    }

    public abstract IMaMIndoorEnvironmentSet getIndoorEnvironmentSet(int index);
    public abstract IMaMOutdoorEnvironmentSet getOutdoorEnvironmentSet(int index);

    public abstract ISceneComposition renderHUDForWorld();

//    public abstract String getMazeName(int id) throws CCFileFormatException;
//    public abstract String getScriptedEventsName(int id) throws CCFileFormatException;
//    public abstract String getMonsterLayoutFile(int id) throws CCFileFormatException;
//    public abstract String getHeadingFile(int id) throws CCFileFormatException;
//    public abstract String getAreaNameFile(int id) throws CCFileFormatException;
//    public abstract String getEventTextStringsFile(int id) throws CCFileFormatException;
//    public abstract String getMapNameFile(int id) throws CCFileFormatException;

    @Override
    public void close() throws Exception {
        this.ccFile.close();
        if((ccFileAnimations != null) && (ccFileAnimations != ccFile))
        {
            this.ccFileAnimations.close();
        }
    }

//    public void setMazeView(int mapID)
//    {
//        this.currentMaze = getMazeFiles().get(mapID);
//    }


    public void setMazeView(String name) {
        this.currentMazeView = mazeViews.get(name);
    }

    public abstract MaMSprite getNPCFaceOrNull(int id);
    public abstract MaMSprite getPlayerFaceOrNull(int id);

    public void addMonster(MaMMonster mon, int x, int y)
    {
        mon.setLocation(new Point(x,y));
        entities.add(mon);
    }

    public void addThing(MaMThing thing, int x, int y, Direction dir, IScript<IGameEntity> onUpdate)
    {
        entities.add(IGameEntity.fromRenderable(thing, x, y, dir, onUpdate));
    }
}
