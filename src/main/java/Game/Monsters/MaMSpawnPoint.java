package Game.Monsters;

import Game.GameEntityBase;
import Game.MaMGame;
import Game.Map.MaMWorld;
import Rendering.IRelativeToLocationSprite;
import Toolbox.Direction;
import mamFiles.MaMMazeFile;

import java.awt.*;

/**
 * Created by duckman on 12/08/2016.
 */
public class MaMSpawnPoint extends GameEntityBase
{
    long monstersSpawned;


    public MaMSpawnPoint(int id, String name, MaMMazeFile maze, Point location) {
        this(id, name, Direction.UP, maze, location);
    }

    public MaMSpawnPoint(int id, String name, Direction heading, MaMMazeFile maze, Point location) {
        this(id, name, false, heading, maze, location);
    }

    public MaMSpawnPoint(int id, String name, boolean visible, Direction heading, MaMMazeFile maze, Point location) {
        super(id, name, visible, heading, location, maze);
        monstersSpawned = 0;
    }

    @Override
    public IRelativeToLocationSprite getSprite() {
        return null;
    }

    @Override
    public void update(MaMGame game) {
        if(monstersSpawned == 0) {
            spawnMonster(game.getWorld());
        }
    }

    private void spawnMonster(MaMWorld world) {
        //MaMMonster mon = new MaMMonster(
        //world.addMonster(
                //TODO:
    }


}
