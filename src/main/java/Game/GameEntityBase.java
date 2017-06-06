package Game;

import Game.Map.MaMWorld;
import Rendering.IRelativeToLocationSprite;
import Toolbox.Direction;
import mamFiles.MaMMazeFile;

import java.awt.*;

/**
 * Created by duckman on 11/08/2016.
 */
public abstract class GameEntityBase implements IGameEntity
{
    /**
     * id is reserved for the implementer to make use of.
     */
    protected int id;

    /**
     * Name as per screen view (attack/talk dialog etc).
     */
    protected String Name;
    protected boolean visible;
    protected Direction heading;
    protected Point location;


    /**
     * NULL if entity not bound to any maze (adventurer)
     */
    protected MaMMazeFile parentMaze;

    public GameEntityBase(int id, String name, Point location, MaMMazeFile parentMaze) {
        this(id, name, Direction.UP, location, parentMaze);
    }

    public GameEntityBase(int id, String name, Direction heading, Point location, MaMMazeFile parentMaze) {
        this(id, name, true, heading,location, parentMaze);
    }

    public GameEntityBase(int id, String name, boolean visible, Direction heading, Point location, MaMMazeFile parentMaze) {

        this.id = id;
        Name = name;
        this.visible = visible;
        this.heading = heading;
        this.location = location;
        this.parentMaze = parentMaze;
    }

    @Override
    public Point getLocationMapSpace() {
        return location;
    }

    @Override
    public Direction getHeading() {
        return heading;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setHeading(Direction heading) {
        this.heading = heading;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public abstract IRelativeToLocationSprite getSprite();

    /**
     * Gets the parentMaze.
     *
     * NULL if entity not bound to any maze (adventurer)
     *
     * @return the parentMaze.
     */
    @Override
    public MaMMazeFile getParentMaze() {
        return parentMaze;
    }


    @Override
    public abstract void update(MaMGame game);


}
