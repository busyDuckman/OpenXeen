package Game.Monsters;

import Game.GameEntityBase;
import Game.Map.MaMWorld;
import Rendering.IRelativeToLocationSprite;
import Toolbox.Direction;

import java.awt.*;

/**
 * Created by duckman on 12/08/2016.
 */
public class MaMSpawnPoint extends GameEntityBase
{
    long monstersSpawned;

    public MaMSpawnPoint(int id, String name, Point location) {
        this(id, name, Direction.UP, location);
    }

    public MaMSpawnPoint(int id, String name, Direction heading, Point location) {
        this(id, name, false, heading, location);
    }

    public MaMSpawnPoint(int id, String name, boolean visible, Direction heading, Point location) {
        super(id, name, visible, heading, location);
        monstersSpawned = 0;
    }

    @Override
    public IRelativeToLocationSprite getSprite() {
        return null;
    }

    @Override
    public void update(MaMWorld world) {
        if(monstersSpawned == 0) {
            spawnMonster(world);
        }
    }

    private void spawnMonster(MaMWorld world) {
        //MaMMonster mon = new MaMMonster(
        //world.addMonster(
                //TODO:
    }


}
