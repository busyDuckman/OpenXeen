package Game;

import Game.Map.MaMWorld;

/**
 * Created by duckman on 3/07/2016.
 */
public interface IUpdateable
{
    /**
     * Update the state of the object, it can see the world.
     */
    void update(MaMGame game);
}
