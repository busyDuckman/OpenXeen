package Game;

import Rendering.IRelativeToLocationSprite;
import Toolbox.Direction;
import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * Created by duckman on 3/07/2016.
 *
 * If its in the world, and it not the floor, its an entity.
 */
public interface IGameEntity extends IUpdateable
{
    /**
     * World space position of the item.
     */
    @NotNull Point getLocation();

    /**
     * Direction the entity is pointing.
     * @return NULL iff the entity has no direction (monsters only face the user)
     */
    Direction getHeading();

    /**
     * False means "not part of the game".
     * NB: does not mean not updateable.
     *
     */
    boolean isVisible();

    /**
     * Gets a sprite for use in game.
     */
    IRelativeToLocationSprite getSprite();

}
