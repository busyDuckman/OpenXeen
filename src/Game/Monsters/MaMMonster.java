package Game.Monsters;

import Game.*;
import Game.Attacks.IAttack;
import Game.Map.MaMWorld;
import GameMechanics.Stat;
import GameMechanics.Stats;
import Rendering.AnimationSettings;
import Rendering.IMaMSprite;
import Rendering.IRelativeToLocationSprite;
import Rendering.IRenderableGameObject;
import Toolbox.Direction;
import mamFiles.CCFileFormatException;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 15/05/2016.
 */
public class MaMMonster extends GameEntityBase implements IGameEntity, IAttackable, IRenderableGameObject
{
    IMaMSprite idleAnimation;
    IMaMSprite attackAnimation;


    //number	5	The Monster ID
    //side	DARKSIDE	The side of Xeen it appears on
    //name	Armadillo	Monster's Name

    // Party experience when defeating it
    Stat<Stats.ExperiencePoints> experience;

    //Monster's max HP
    Stat<Stats.HitPoints> hitPoints;

    //Monster's armor class (physical defence)
    int acc;

    //Monster's attack speed (determines order per round)
    int speed;
//al attacks
//    numberOfAttacks	1	Number of attacks monster gets per round
//    hatesClass	ANY	Class/race to attack first (ALL=attack entire party at once)
//    strikes	100	The 'X' in the XdY equation
//    dmgPerStrike	6	The 'Y' in the XdY equation
//    attackType	Physical	Element type of attack
//    specialAttack	Break Weapon	Special effects caused by attack
//    hitChance	60	Base probability of attack landing
//    rangeAttack	False	Can attack at a distance
//    monsterType	Animal	Certain monster types are affected differently by some spells
//    res_fire	50	Resistance to fire based attacks
//    res_elec	0	Resistance to electricity based attacks
//    res_cold	80	Resistance to cold based attacks
//    res_poison	80	Resistance to poison based attacks
//    res_energy	50	Resistance to energy based attacks
//    res_magic	0	Resistance to magic based attacks
//    res_physical	50	Resistance to physic


//    field_29	0	unknown! Doesn't seem to be used anywhere
//    gold	0	Gold dropped by monster
//    gems	0	Gems dropped by monster
//    itemDrop	0	probability that monster drops an item
//    flying	False	Boolean value: monster flies or it doesn't
//    imageNumber	5	Sprite ID (xxx.MON and xxx.ATK files)
//    loopAnimation	True	Frames either increment and loop, or bounce start to end and back
//    animationEffect	0	Special effects
//    idleSound	113	Effect number played by PlayFX every 5 seconds
//    attackVoc	unnh	xxx.VOC file played when monster attacks


    public MaMMonster(String name, int id, IMaMSprite idleAnimation, IMaMSprite attackAnimation)
    {
        super(id, name, new Point(0,0));
        this.idleAnimation = idleAnimation;
        this.attackAnimation = attackAnimation;
        this.id = id;
        Name = name;
    }

    public MaMMonster(String name, int id, MaMWorld world, String idleAnimationFile, String attackAnimationFile ) throws CCFileFormatException {
        super(id, name, new Point(0,0));
        this.idleAnimation = world.getCcFile().getSprite(idleAnimationFile);
        this.attackAnimation = world.getCcFile().getSprite(attackAnimationFile);
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


    @Override
    public void copThis(IAttack attack) {

    }

    @Override
    public IRelativeToLocationSprite getSprite() {
        //return IRelativeToLocationSprite.idleAnimation;
        return null; //TODO
    }

    @Override
    public void update(MaMWorld world) {

    }
}
