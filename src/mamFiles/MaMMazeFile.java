package mamFiles;

import Game.Map.MaMTile;
import Toolbox.Grid;
import mamFiles.CCFileFormatException;
import mamFiles.MAMFile;

import java.util.List;

/**
 * Created by duckman on 1/06/2016.
 */
public abstract class MaMMazeFile extends MAMFile
{
    protected String name;
    protected int mazeID;
    protected Grid<MaMTile> map;
    protected int[] joiningMaps;
    protected List<String> cantCastList;
    protected boolean canRest;
    protected boolean canSave;
    protected boolean[] miscFlags;
    protected boolean isDark;
    protected boolean isOutdoors;
    private MaMSprite tilesSprite;

    public MaMMazeFile(String name) throws CCFileFormatException {
        super(name);
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

    @Override
    public String getName() {
        return name;
    }

    public MaMSprite getTilesSprite() {
        return tilesSprite;
    }
}
