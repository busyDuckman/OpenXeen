package Game.Monsters;

import Game.*;
import Game.Map.WoXWorld;
import GameMechanics.Adventurers.CharClass;
import GameMechanics.Attacks.AttackType;
import GameMechanics.Attacks.IAttack;
import Game.Map.MaMWorld;
import GameMechanics.Combatant;
import GameMechanics.Stat;
import Rendering.AnimationSettings;
import Rendering.IMaMSprite;
import Rendering.IRelativeToLocationSprite;
import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMMazeFile;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 15/05/2016.
 */
public class MaMMonster extends Combatant implements IGameEntity, IAttackable, IRenderableGameObject
{
    IMaMSprite idleAnimation;
    IMaMSprite attackAnimation;

    //public int number;
    //public WoXWorld.WoxVariant side;
    protected String name;
    protected int experience;
//    public int hp;
//    public int acc;
//    public int speed;
    protected int numberOfAttacks;
    protected CharClass hatesClass;
    protected int strikes;
    protected int dmgPerStrike;
    protected AttackType attackType;
    protected AttackType specialAttack;
    protected int hitChance;
    protected boolean rangeAttack;
    protected MonsterType monsterType;
//    public int res_fire;
//    public int res_elec;
//    public int res_cold;
//    public int res_poison;
//    public int res_energy;
//    public int res_magic;
//    public int res_physical;
    protected int gold;
    protected int gems;
    protected int itemDrop;
    protected boolean flying;
    protected int imageNumber;
    protected boolean loopAnimation;
    protected int animationEffect;
    protected int idleSound;
    protected String attackVoc;



    public MaMMonster(String name, int id, MaMMazeFile parentMaze, IMaMSprite idleAnimation, IMaMSprite attackAnimation)
    {
        super(id, name, new Point(0,0), parentMaze);
        this.idleAnimation = idleAnimation;
        this.attackAnimation = attackAnimation;
        this.id = id;
        Name = name;
    }

    public MaMMonster(String name, int id, MaMWorld world, MaMMazeFile parentMaze, String idleAnimationFile, String attackAnimationFile) throws CCFileFormatException {
        super(id, name, new Point(0,0), parentMaze);
        this.idleAnimation = world.getCcFile().getSprite(idleAnimationFile);
        this.attackAnimation = world.getCcFile().getSprite(attackAnimationFile);
        this.id = id;
        Name = name;
    }

    public MaMMonster(String name, int id, MaMWorld world, MaMMazeFile parentMaze) throws CCFileFormatException {
        this(name,
                id,
                world,
                parentMaze,
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
        return idleAnimation.asIRelativeToLocationSprite();
        //return IRelativeToLocationSprite. idleAnimation;
        //return null; //TODO
    }

    @Override
    public void update(MaMWorld world) {

    }

    //------------------------------------------------------------------------------------------------------------------


    public void setIdleAnimation(IMaMSprite idleAnimation) {
        this.idleAnimation = idleAnimation;
    }

    public void setAttackAnimation(IMaMSprite attackAnimation) {
        this.attackAnimation = attackAnimation;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public void setNumberOfAttacks(int numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }

    public CharClass getHatesClass() {
        return hatesClass;
    }

    public void setHatesClass(CharClass hatesClass) {
        this.hatesClass = hatesClass;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public int getDmgPerStrike() {
        return dmgPerStrike;
    }

    public void setDmgPerStrike(int dmgPerStrike) {
        this.dmgPerStrike = dmgPerStrike;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public AttackType getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(AttackType specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getHitChance() {
        return hitChance;
    }

    public void setHitChance(int hitChance) {
        this.hitChance = hitChance;
    }

    public boolean isRangeAttack() {
        return rangeAttack;
    }

    public void setRangeAttack(boolean rangeAttack) {
        this.rangeAttack = rangeAttack;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getItemDrop() {
        return itemDrop;
    }

    public void setItemDrop(int itemDrop) {
        this.itemDrop = itemDrop;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public boolean isLoopAnimation() {
        return loopAnimation;
    }

    public void setLoopAnimation(boolean loopAnimation) {
        this.loopAnimation = loopAnimation;
    }

    public int getAnimationEffect() {
        return animationEffect;
    }

    public void setAnimationEffect(int animationEffect) {
        this.animationEffect = animationEffect;
    }

    public int getIdleSound() {
        return idleSound;
    }

    public void setIdleSound(int idleSound) {
        this.idleSound = idleSound;
    }

    public String getAttackVoc() {
        return attackVoc;
    }

    public void setAttackVoc(String attackVoc) {
        this.attackVoc = attackVoc;
    }
}
