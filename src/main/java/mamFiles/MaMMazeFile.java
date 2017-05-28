package mamFiles;

import Game.Map.MaMMazeView;
import Game.Map.MaMTile;
import Toolbox.Grid;
import mamFiles.CCFileFormatException;
import mamFiles.MAMFile;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duckman on 1/06/2016.
 */
public abstract class MaMMazeFile extends MAMFile
{
    protected String name;
    protected int mazeID;
    protected Grid<MaMTile> map;
    /**
     * -1 = nno map
     * Use as per joiningMaps[Direction.Left.ordinal()]
     */
    protected int[] joiningMaps;
    protected List<String> cantCastList;
    protected boolean canRest;
    protected boolean canSave;
    protected boolean[] miscFlags;
    protected boolean isDark;
    protected boolean isOutdoors;
    //private MaMSprite tilesSprite;
    protected int floorType;
    protected int wallNoPass;
    protected int surfNoPass;
    protected int unlockDoor;
    protected int unlockBox;
    protected int bashDoor;
    protected int bashGrate;
    protected int bashWall;
    protected int chanceToRun;
    protected int trapDamage;
    protected int wallKind;
    protected int tavernTips;
    protected Point runPos;

    protected IMaMEnvironmentSet environmentSet;

    protected MaMMazeView parentView;
    //protected Point worldSpacePos;

    public MaMMazeFile(String name, String key) throws CCFileFormatException {
        super(name, key);

        cantCastList = new ArrayList<>();
    }

    @Override
    public String suggestProxyFileName() {
        return null;
    }

    @Override
    public boolean saveProxy(String path) throws CCFileFormatException {
        return false;
    }

    public int getMazeID() {
        return mazeID;
    }

    public Grid<MaMTile> getMap() {
        return map;
    }

    public int[] getJoiningMaps() {
        return joiningMaps;
    }

    public List<String> getCantCastList() {
        return cantCastList;
    }

    public boolean canRest() {
        return canRest;
    }

    public boolean canSave() {
        return canSave;
    }

    public boolean[] getMiscFlags() {
        return miscFlags;
    }

    public boolean isDark() {
        return isDark;
    }

    public boolean isOutdoors() {
        return isOutdoors;
    }

    public IMaMEnvironmentSet getEnvironmentSet() {
        return environmentSet;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
