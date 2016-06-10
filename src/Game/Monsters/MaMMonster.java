package Game.Monsters;

import Game.MaMGame;
import Game.Map.MaMWorld;
import Rendering.AnimationSettings;
import Rendering.IMaMSprite;
import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;

import java.awt.image.BufferedImage;

/**
 * Created by duckman on 15/05/2016.
 */
public class MaMMonster implements IRenderableGameObject
{
    IMaMSprite idleAnimation;
    IMaMSprite attackAnimation;
    int id;
    String Name;

    public MaMMonster(String name, int id, IMaMSprite idleAnimation, IMaMSprite attackAnimation)
    {
        this.idleAnimation = idleAnimation;
        this.attackAnimation = attackAnimation;
        this.id = id;
        Name = name;
    }

    public MaMMonster(String name, int id, MaMWorld world, String idleAnimationFile, String attackAnimationFile ) throws CCFileFormatException {
        this.idleAnimation = world.getCcFile().getSprite(idleAnimationFile, world.getCurrentPallate());
        this.attackAnimation = world.getCcFile().getSprite(attackAnimationFile, world.getCurrentPallate());
        this.id = id;
        Name = name;
    }

    public MaMMonster(String name, int id, MaMWorld world) throws CCFileFormatException {
        this(name,
                id,
                world,
                world.getCcFile().getMonsterIdleSpriteFileName(id),
                world.getCcFile().getMonsterIdleSpriteFileName(id));
    }

    @Override
    public String toString() {
        return "MaMMonster{" +
                "idleAnimation=" + idleAnimation +
                ", attackAnimation=" + attackAnimation +
                ", id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }

    public IMaMSprite getIdleAnimation() {
        return idleAnimation;
    }

    public IMaMSprite getAttackAnimation() {
        return attackAnimation;
    }

    @Override
    public BufferedImage getImage(int frame) {
        return idleAnimation.getRenderedFrames()[frame];
    }

    @Override
    public AnimationSettings getAnimationSettings() {
        return null;
    }
}
