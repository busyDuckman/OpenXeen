package Game.Monsters;

import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import GameMechanics.Adventurers.CharClass;
import GameMechanics.Attacks.AttackType;
import mamFiles.CCFileFormatException;

import java.util.ArrayList;
import java.util.List;

import static Game.Map.WoXWorld.WoxVariant.CLOUDS;
import static Game.Map.WoXWorld.WoxVariant.DARK_SIDE;

/**
 * Created by duckman on 12/08/2016.
 *
 * The monster stats were leached from http://xeen.wikia.com/wiki/Category:Monsters
 * and run through a series of regex filters to create the static initialiser in
 * this class.
 *
 */
public class MonsterFactory
{
    protected List<MaMMonsterRecord> monsters;

    public MonsterFactory(List<MaMMonsterRecord> monsters)
    {
        this.monsters = monsters;
    }

    public static MonsterFactory newWoxMonsterFactory()
    {
        List<MaMMonsterRecord> monsters = new ArrayList<>();

        MaMMonsterRecord mon = new MaMMonsterRecord("Acid Dragon");
        mon.number = 61;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Acid Dragon";    //Monster's Name
        mon.experience = 60000;    //Party experience when defeating it
        mon.hp = 220;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 22;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 61;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 161;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "explode1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Annihilator");
        mon.number = 2;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Annihilator";    //Monster's Name
        mon.experience = 1000000;    //Party experience when defeating it
        mon.hp = 1500;    //Monster's max HP
        mon.acc = 40;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 12;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 80;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 2;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 102;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "alien1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Arachnoid");
        mon.number = 52;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Arachnoid";    //Monster's Name
        mon.experience = 4000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 52;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 104;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "web";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Armadillo");
        mon.number = 5;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Armadillo";    //Monster's Name
        mon.experience = 60000;    //Party experience when defeating it
        mon.hp = 800;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 15;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.BreakWeapon;    //Special effects caused by attack
        mon.hitChance = 60;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 5;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 113;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "unnh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Autobot");
        mon.number = 3;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Autobot";    //Monster's Name
        mon.experience = 1000000;    //Party experience when defeating it
        mon.hp = 2500;    //Monster's max HP
        mon.acc = 100;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 3;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "alien2";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Barbarian (Clouds)");
        mon.number = 45;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Barbarian";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 6;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 35;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 25;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 45;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 145;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "swipe1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Barbarian (Darkside)");
        mon.number = 6;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Barbarian";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 3;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 100;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 6;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "barbarch";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Barkman");
        mon.number = 94;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Barkman";    //Monster's Name
        mon.experience = 4000000;    //Party experience when defeating it
        mon.hp = 40000;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 3;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 250;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 19;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 11;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fire";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Bat Queen");
        mon.number = 16;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Bat Queen";    //Monster's Name
        mon.experience = 700;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 22;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Weakness;    //Special effects caused by attack
        mon.hitChance = 15;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 16;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 116;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "snakeman";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Beholder Bat");
        mon.number = 18;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Beholder Bat";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 75;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 80;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 18;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 120;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "eyeball";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Breeder Slime");
        mon.number = 73;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Breeder Slime";    //Monster's Name
        mon.experience = 200;    //Party experience when defeating it
        mon.hp = 20;    //Monster's max HP
        mon.acc = 2;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 73;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 173;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "slimebos";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Captain Yang");
        mon.number = 82;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Captain Yang";    //Monster's Name
        mon.experience = 25000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 6;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 16;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 35;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 2500;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 38;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 14;    //Special effects
        mon.idleSound = 182;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "nyaahh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Carnage Hand");
        mon.number = 49;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Carnage Hand";    //Monster's Name
        mon.experience = 12000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 15;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 60;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 40;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 49;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 149;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "zombie";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Castle Guard");
        mon.number = 46;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Castle Guard";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 10;    //Resistance to fire based attacks
        mon.res_elec = 10;    //Resistance to electricity based attacks
        mon.res_cold = 10;    //Resistance to cold based attacks
        mon.res_poison = 10;    //Resistance to poison based attacks
        mon.res_energy = 10;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 40;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 84;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 146;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "nyaahh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Clan King");
        mon.number = 75;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Clan King";    //Monster's Name
        mon.experience = 2000;    //Party experience when defeating it
        mon.hp = 120;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 22;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 8;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 500;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 75;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 175;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ooh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Clan Sargent");
        mon.number = 74;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Clan Sargent";    //Monster's Name
        mon.experience = 600;    //Party experience when defeating it
        mon.hp = 60;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Dwarf;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 8;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 120;    //Gold dropped by monster
        mon.gems = 3;    //Gems dropped by monster
        mon.itemDrop = 2;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 7;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 174;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ooh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cleric of Mok");
        mon.number = 8;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Cleric of Mok";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 125;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 250;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 10;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 10;    //Resistance to cold based attacks
        mon.res_poison = 10;    //Resistance to poison based attacks
        mon.res_energy = 10;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 8;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 117;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "cleric";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cleric of Yak");
        mon.number = 17;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Cleric of Yak";    //Monster's Name
        mon.experience = 1600;    //Party experience when defeating it
        mon.hp = 60;    //Monster's max HP
        mon.acc = 8;    //Monster's armor class (physical defence)
        mon.speed = 18;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 60;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 8;    //Gems dropped by monster
        mon.itemDrop = 2;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 17;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 117;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "electric";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cloud Dragon");
        mon.number = 11;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Cloud Dragon";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 2000;    //Monster's max HP
        mon.acc = 40;    //Monster's armor class (physical defence)
        mon.speed = 150;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 600;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 25;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 11;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "tiger1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cloud Golem");
        mon.number = 60;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Cloud Golem";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 175;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 26;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 12;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 60;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 80;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 60;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 160;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "windblow";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Count Blackfang");
        mon.number = 80;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Ct. Blackfang";    //Monster's Name
        mon.experience = 2000000;    //Party experience when defeating it
        mon.hp = 1500;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 150;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Kill;    //Special effects caused by attack
        mon.hitChance = 120;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 75;    //Resistance to fire based attacks
        mon.res_elec = 75;    //Resistance to electricity based attacks
        mon.res_cold = 75;    //Resistance to cold based attacks
        mon.res_poison = 75;    //Resistance to poison based attacks
        mon.res_energy = 75;    //Resistance to energy based attacks
        mon.res_magic = 75;    //Resistance to magic based attacks
        mon.res_physical = 75;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 58;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "vamp";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Count Draco");
        mon.number = 86;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Count Draco";    //Monster's Name
        mon.experience = 35000;    //Party experience when defeating it
        mon.hp = 130;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.DrainSP;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 90;    //Resistance to electricity based attacks
        mon.res_cold = 90;    //Resistance to cold based attacks
        mon.res_poison = 90;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 48;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 8;    //Special effects
        mon.idleSound = 186;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "monstera";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Coven Leader");
        mon.number = 63;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Coven Leader";    //Monster's Name
        mon.experience = 120000;    //Party experience when defeating it
        mon.hp = 250;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.DrainSP;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 10;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 63;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 114;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecspel";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cult Leader (Clouds)");
        mon.number = 85;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Cult Leader";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 110;    //Monster's max HP
        mon.acc = 22;    //Monster's armor class (physical defence)
        mon.speed = 32;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 70;    //Resistance to electricity based attacks
        mon.res_cold = 70;    //Resistance to cold based attacks
        mon.res_poison = 70;    //Resistance to poison based attacks
        mon.res_energy = 70;    //Resistance to energy based attacks
        mon.res_magic = 70;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 450;    //Gold dropped by monster
        mon.gems = 30;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 42;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 5;    //Special effects
        mon.idleSound = 185;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "photon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cult Leader (Darkside)");
        mon.number = 68;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Cult Leader";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 100;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 8;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "cleric";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Cyclops");
        mon.number = 57;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Cyclops";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 16;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Confuse;    //Special effects caused by attack
        mon.hitChance = 35;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1300;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 57;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 157;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "whip";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Dark Wolf");
        mon.number = 66;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Dark Wolf";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 70;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 70;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 3;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 66;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "wolf";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Darzog");
        mon.number = 87;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Darzog";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.Stone;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 50;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 63;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 187;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecspel";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Darzog Clone");
        mon.number = 63;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Darzog Clone";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 30;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 63;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecspel";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Death Knight");
        mon.number = 31;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Death Knight";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 750;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 80;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 100;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 30;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "knight";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Demon");
        mon.number = 64;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Demon";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 33;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 33;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 64;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 164;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "boom1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Devil");
        mon.number = 70;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Devil";    //Monster's Name
        mon.experience = 40000;    //Party experience when defeating it
        mon.hp = 350;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 66;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 33;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 70;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 170;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fx2";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Diamond Golem");
        mon.number = 67;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Diamond Golem";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 40;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.BreakWeapon;    //Special effects caused by attack
        mon.hitChance = 50;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 67;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 167;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "pop4";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Doom Bug");
        mon.number = 2;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Doom Bug";    //Monster's Name
        mon.experience = 75;    //Party experience when defeating it
        mon.hp = 5;    //Monster's max HP
        mon.acc = 3;    //Monster's armor class (physical defence)
        mon.speed = 17;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 6;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 2;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 102;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "noise";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Doom Knight");
        mon.number = 71;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Doom Knight";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 4;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Kill;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 200;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 7;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 30;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "knight";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Dragon King");
        mon.number = 72;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Dragon King";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 2000;    //Monster's max HP
        mon.acc = 45;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 400;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 100;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 72;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 172;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gleen";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Dragon Mummy");
        mon.number = 15;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Dragon Mummy";    //Monster's Name
        mon.experience = 2000000;    //Party experience when defeating it
        mon.hp = 3000;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2000;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 2;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Disease;    //Special effects caused by attack
        mon.hitChance = 200;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 90;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 15;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "dragmum";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Earth Blaster");
        mon.number = 17;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Earth Blaster";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 200;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 90;    //Resistance to electricity based attacks
        mon.res_cold = 90;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 90;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 17;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "earthmon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Earth Golem");
        mon.number = 52;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Earth Golem";    //Monster's Name
        mon.experience = 14000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 35;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 90;    //Resistance to electricity based attacks
        mon.res_cold = 90;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 20;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 6;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 52;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 152;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "barkl";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Electrapede");
        mon.number = 7;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Electrapede";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 50;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.Paralyze;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 7;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 107;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "centi";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Enchantress");
        mon.number = 50;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Enchantress";    //Monster's Name
        mon.experience = 40000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 150;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 10;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 10;    //Resistance to cold based attacks
        mon.res_poison = 10;    //Resistance to poison based attacks
        mon.res_energy = 10;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 50;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "disint";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Energy Dragon");
        mon.number = 14;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Energy Dragon";    //Monster's Name
        mon.experience = 2000000;    //Party experience when defeating it
        mon.hp = 5000;    //Monster's max HP
        mon.acc = 100;    //Monster's armor class (physical defence)
        mon.speed = 250;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1000;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 60;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 13;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 7;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "begger";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Evil Archer");
        mon.number = 43;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Evil Archer";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 75;    //Monster's max HP
        mon.acc = 22;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 5;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 300;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 43;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 143;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "bow";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Evil Ranger");
        mon.number = 35;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Evil Ranger";    //Monster's Name
        mon.experience = 7000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 27;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Druid;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 15;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 25;    //Resistance to fire based attacks
        mon.res_elec = 25;    //Resistance to electricity based attacks
        mon.res_cold = 25;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 500;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 35;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 135;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "huah";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Fire Blower");
        mon.number = 19;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Fire Blower";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 19;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 110;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fire";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Fire Dragon");
        mon.number = 68;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Fire Dragon";    //Monster's Name
        mon.experience = 80000;    //Party experience when defeating it
        mon.hp = 350;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 200;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 68;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 168;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Dragon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Flying Feet");
        mon.number = 26;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Flying Feet";    //Monster's Name
        mon.experience = 3000;    //Party experience when defeating it
        mon.hp = 40;    //Monster's max HP
        mon.acc = 14;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 15;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 60;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 26;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 126;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "punchooh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Frost Dragon");
        mon.number = 69;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Frost Dragon";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 450;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 250;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 88;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 169;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Dragon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gamma Gazer");
        mon.number = 28;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gamma Gazer";    //Monster's Name
        mon.experience = 1000000;    //Party experience when defeating it
        mon.hp = 5000;    //Monster's max HP
        mon.acc = 60;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 7;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 28;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hydra";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gargoyle (Clouds)");
        mon.number = 47;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Gargoyle";    //Monster's Name
        mon.experience = 11000;    //Party experience when defeating it
        mon.hp = 70;    //Monster's max HP
        mon.acc = 18;    //Monster's armor class (physical defence)
        mon.speed = 32;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 4;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Paralyze;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 47;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 147;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "siktroll";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gargoyle (Darkside)");
        mon.number = 21;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gargoyle";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 60;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 21;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gargrwl";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gettlewaithe");
        mon.number = 70;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gettlewaithe";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 2000;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 25;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gremlin";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ghost Mummy");
        mon.number = 91;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Ghost Mummy";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 175;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 200;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 60;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 80;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 39;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 6;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "orc";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ghost Rider");
        mon.number = 79;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Ghost Rider";    //Monster's Name
        mon.experience = 4000;    //Party experience when defeating it
        mon.hp = 60;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 32;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 100;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 29;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 6;    //Special effects
        mon.idleSound = 179;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "yaa";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ghoul");
        mon.number = 27;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Ghoul";    //Monster's Name
        mon.experience = 3500;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Paralyze;    //Special effects caused by attack
        mon.hitChance = 22;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 27;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 127;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Ghoul";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Giant");
        mon.number = 22;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Giant";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 45;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.KnockUnconscious;    //Special effects caused by attack
        mon.hitChance = 100;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1000;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 22;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "giant";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Giant Bat");
        mon.number = 1;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Giant Bat";    //Monster's Name
        mon.experience = 60;    //Party experience when defeating it
        mon.hp = 10;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 4;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 5;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 1;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Bat";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Giant Scorpion");
        mon.number = 19;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Giant Scorpion";    //Monster's Name
        mon.experience = 1000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 14;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 19;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 119;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Scorpion";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Giant Snake");
        mon.number = 3;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Giant Snake";    //Monster's Name
        mon.experience = 100;    //Party experience when defeating it
        mon.hp = 15;    //Monster's max HP
        mon.acc = 6;    //Monster's armor class (physical defence)
        mon.speed = 18;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 2;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 3;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 103;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hiss";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Giant Spider");
        mon.number = 4;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Giant Spider";    //Monster's Name
        mon.experience = 100;    //Party experience when defeating it
        mon.hp = 20;    //Monster's max HP
        mon.acc = 4;    //Monster's armor class (physical defence)
        mon.speed = 19;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 6;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 4;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 104;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "web";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Giant Toad");
        mon.number = 13;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Giant Toad";    //Monster's Name
        mon.experience = 500;    //Party experience when defeating it
        mon.hp = 90;    //Monster's max HP
        mon.acc = 6;    //Monster's armor class (physical defence)
        mon.speed = 17;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 8;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 13;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 113;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ribbit";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gnome Vampire");
        mon.number = 48;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Gnome Vampire";    //Monster's Name
        mon.experience = 12000;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 18;    //Monster's armor class (physical defence)
        mon.speed = 36;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 16;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.DrainSP;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 90;    //Resistance to electricity based attacks
        mon.res_cold = 90;    //Resistance to cold based attacks
        mon.res_poison = 90;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 20;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 48;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 148;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "monstera";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Goblin (Clouds)");
        mon.number = 5;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Goblin";    //Monster's Name
        mon.experience = 150;    //Party experience when defeating it
        mon.hp = 20;    //Monster's max HP
        mon.acc = 6;    //Monster's armor class (physical defence)
        mon.speed = 15;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 12;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 2;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 5;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 1;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 5;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 105;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "unnh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Goblin (Darkside)");
        mon.number = 23;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Goblin";    //Monster's Name
        mon.experience = 1000;    //Party experience when defeating it
        mon.hp = 10;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 25;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 131;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gremlin";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gorgon");
        mon.number = 38;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gorgon";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 4000;    //Monster's max HP
        mon.acc = 90;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 3;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Stone;    //Special effects caused by attack
        mon.hitChance = 100;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 60;    //Resistance to magic based attacks
        mon.res_physical = 70;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 37;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "stonegol";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Graalg");
        mon.number = 84;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Graalg";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1000;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 42;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ogre";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Great Hydra");
        mon.number = 71;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Great Hydra";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 27;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 12;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 45;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 71;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 171;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "blob";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Green Dragon");
        mon.number = 13;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Green Dragon";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 2500;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 150;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 500;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 25;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 13;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "tiger1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gremlin");
        mon.number = 25;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gremlin";    //Monster's Name
        mon.experience = 2000;    //Party experience when defeating it
        mon.hp = 20;    //Monster's max HP
        mon.acc = 7;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 26;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gremlink";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gremlin Guard");
        mon.number = 26;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gremlin Guard";    //Monster's Name
        mon.experience = 3000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 6;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 26;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gremlink";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Griffin");
        mon.number = 27;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Griffin";    //Monster's Name
        mon.experience = 60000;    //Party experience when defeating it
        mon.hp = 800;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 150;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Knight;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 50;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 27;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 120;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "screech";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Guardian");
        mon.number = 21;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Guardian";    //Monster's Name
        mon.experience = 1500;    //Party experience when defeating it
        mon.hp = 40;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.DrainSP;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 70;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 83;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 9;    //Special effects
        mon.idleSound = 121;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "boltelec";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Guardian Asp");
        mon.number = 54;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Guardian Asp";    //Monster's Name
        mon.experience = 15000;    //Party experience when defeating it
        mon.hp = 90;    //Monster's max HP
        mon.acc = 22;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Confuse;    //Special effects caused by attack
        mon.hitChance = 40;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 3;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 12;    //Special effects
        mon.idleSound = 154;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hiss";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Gurodel");
        mon.number = 88;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Gurodel";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 750;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.KnockUnconscious;    //Special effects caused by attack
        mon.hitChance = 110;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 5000;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 22;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "giant";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Harpy");
        mon.number = 20;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Harpy";    //Monster's Name
        mon.experience = 1200;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 7;    //Monster's armor class (physical defence)
        mon.speed = 21;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Archer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.None_11;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 20;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 120;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Harpy";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Harpy Queen");
        mon.number = 81;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Harpy Queen";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 120;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.Weakness;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 60;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 20;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 181;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "harpy";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Head Witch");
        mon.number = 76;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Head Witch";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.CursePlayer;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 60;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 76;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 8;    //Special effects
        mon.idleSound = 176;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "spell002";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Hell Hornet");
        mon.number = 20;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Hell Hornet";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 250;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Druid;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.Weakness;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 20;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 123;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "insect";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Higher Mummy");
        mon.number = 39;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Higher Mummy";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 400;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.CurseItems;    //Special effects caused by attack
        mon.hitChance = 100;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 75;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 39;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "mummy";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Hobstadt");
        mon.number = 83;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Hobstadt";    //Monster's Name
        mon.experience = 25000;    //Party experience when defeating it
        mon.hp = 400;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 70;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1000;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 56;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "troll";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ice Troll");
        mon.number = 53;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Ice Troll";    //Monster's Name
        mon.experience = 14000;    //Party experience when defeating it
        mon.hp = 125;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Dwarf;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 53;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 153;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fx7";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Iguanasaurus");
        mon.number = 29;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Iguanasaurus";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 2500;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Insane;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 20;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 29;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 113;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "iguana";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Insane Begger");
        mon.number = 12;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Insane Begger";    //Monster's Name
        mon.experience = 450;    //Party experience when defeating it
        mon.hp = 10;    //Monster's max HP
        mon.acc = 3;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.Insane;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 12;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 112;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Begger";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Insect Swarm");
        mon.number = 15;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Insect Swarm";    //Monster's Name
        mon.experience = 1300;    //Party experience when defeating it
        mon.hp = 30;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 8;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 23;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 115;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "insects";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Iron Golem");
        mon.number = 58;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Iron Golem";    //Monster's Name
        mon.experience = 25000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 24;    //Monster's armor class (physical defence)
        mon.speed = 24;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 40;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 90;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 58;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 158;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "bong";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Jouster");
        mon.number = 29;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Jouster";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 40;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 2000;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 29;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 129;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "yaa";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Killer Cobra");
        mon.number = 46;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Killer Cobra";    //Monster's Name
        mon.experience = 25000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 46;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hiss";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Killer Sprite");
        mon.number = 22;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Killer Sprite";    //Monster's Name
        mon.experience = 1600;    //Party experience when defeating it
        mon.hp = 25;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.CursePlayer;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 20;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 6;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 22;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 122;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Sprite";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("King's Guard");
        mon.number = 84;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "King's Guard";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 4;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 100;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 84;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 14;    //Special effects
        mon.idleSound = 184;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "nyaahh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Lava Dweller");
        mon.number = 32;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Lava Dweller";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 1500;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 19;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 110;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fire";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Lava Golem");
        mon.number = 65;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Lava Golem";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 23;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.BreakWeapon;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 65;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 165;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fire";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Lava Roach");
        mon.number = 33;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Lava Roach";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 70;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 33;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 131;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Phantom";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Lord Xeen");
        mon.number = 89;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Lord Xeen";    //Monster's Name
        mon.experience = 600000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1000;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.ERADICATE;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 100;    //Resistance to magic based attacks
        mon.res_physical = 100;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 89;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 189;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Xeen";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mad Dwarf");
        mon.number = 7;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Mad Dwarf";    //Monster's Name
        mon.experience = 200;    //Party experience when defeating it
        mon.hp = 30;    //Monster's max HP
        mon.acc = 6;    //Monster's armor class (physical defence)
        mon.speed = 17;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Dwarf;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 4;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 6;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 75;    //Gold dropped by monster
        mon.gems = 2;    //Gems dropped by monster
        mon.itemDrop = 1;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 7;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 107;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ooh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mad Fool");
        mon.number = 10;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Mad Fool";    //Monster's Name
        mon.experience = 350;    //Party experience when defeating it
        mon.hp = 30;    //Monster's max HP
        mon.acc = 4;    //Monster's armor class (physical defence)
        mon.speed = 21;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Insane;    //Special effects caused by attack
        mon.hitChance = 8;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 2;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 10;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 110;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "spell001";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Magic Mage");
        mon.number = 36;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Magic Mage";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 80;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 75;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 35;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "monsterb";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mantis Ant");
        mon.number = 10;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Mantis Ant";    //Monster's Name
        mon.experience = 40000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 30;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 10;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 104;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "spell001";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Master Thief");
        mon.number = 78;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Master Thief";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Robber;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 40;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 250;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 54;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 14;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "thief";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Master Wizard");
        mon.number = 64;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Master Wizard";    //Monster's Name
        mon.experience = 120000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 150;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Knight;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 50;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 64;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "boltelec";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Medusa Sprite");
        mon.number = 53;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Medusa Sprite";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 30;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Ranger;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 3;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Stone;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 53;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 42;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hiss";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mega Dragon");
        mon.number = 69;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Mega Dragon";    //Monster's Name
        mon.experience = 100000000;    //Party experience when defeating it
        mon.hp = 64000;    //Monster's max HP
        mon.acc = 100;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 200;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.ERADICATE;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 100;    //Resistance to magic based attacks
        mon.res_physical = 90;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 232;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 11;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 7;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "tiger1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mega Mage");
        mon.number = 73;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Mega Mage";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 40;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 100;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 35;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 11;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "monsterb";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Minotaur");
        mon.number = 37;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Minotaur";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 3000;    //Monster's max HP
        mon.acc = 80;    //Monster's armor class (physical defence)
        mon.speed = 120;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 4;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 10;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 37;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "stonegol";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mok Heretic");
        mon.number = 9;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Mok Heretic";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 500;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 25;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 8;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 117;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "cleric";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Morgana");
        mon.number = 77;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Morgana";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 60;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.Paralyze;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 100;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 50;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "disint";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mummy");
        mon.number = 40;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Mummy";    //Monster's Name
        mon.experience = 9000;    //Party experience when defeating it
        mon.hp = 60;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Druid;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Disease;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 40;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "bang";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Mystic Mage");
        mon.number = 35;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Mystic Mage";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 70;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 50;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 35;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "monsterb";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ninja");
        mon.number = 24;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Ninja";    //Monster's Name
        mon.experience = 2000;    //Party experience when defeating it
        mon.hp = 65;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 4;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 50;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 24;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 124;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "whaa-kee";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Octopod");
        mon.number = 41;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Octopod";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 2500;    //Monster's max HP
        mon.acc = 40;    //Monster's armor class (physical defence)
        mon.speed = 80;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 41;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "photon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ogre (Clouds)");
        mon.number = 32;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Ogre";    //Monster's Name
        mon.experience = 6000;    //Party experience when defeating it
        mon.hp = 90;    //Monster's max HP
        mon.acc = 17;    //Monster's armor class (physical defence)
        mon.speed = 15;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 25;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 40;    //Resistance to fire based attacks
        mon.res_elec = 40;    //Resistance to electricity based attacks
        mon.res_cold = 40;    //Resistance to cold based attacks
        mon.res_poison = 40;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 32;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 132;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ogre";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Ogre (Darkside)");
        mon.number = 42;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Ogre";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 100;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 42;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 136;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "ogre";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Onyx Golem");
        mon.number = 24;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Onyx Golem";    //Monster's Name
        mon.experience = 1000000;    //Party experience when defeating it
        mon.hp = 10000;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.DrainSP;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 100;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 100;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 24;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "golem";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Orc");
        mon.number = 6;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Orc";    //Monster's Name
        mon.experience = 200;    //Party experience when defeating it
        mon.hp = 25;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 17;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 5;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 10;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 1;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 6;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 106;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Orc";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Orc Elite");
        mon.number = 74;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Orc Elite";    //Monster's Name
        mon.experience = 15000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Dwarf;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 100;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 40;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "orc";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Orc Guard");
        mon.number = 40;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Orc Guard";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 60;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Dwarf;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 50;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 2;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 40;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 125;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "orc";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Orc Shaman");
        mon.number = 43;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Orc Shaman";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 75;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 2;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 43;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 125;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fx7";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Phase Dragon");
        mon.number = 12;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Phase Dragon";    //Monster's Name
        mon.experience = 2000000;    //Party experience when defeating it
        mon.hp = 4000;    //Monster's max HP
        mon.acc = 80;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 750;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 11;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Begger";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Phase Mummy");
        mon.number = 92;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Phase Mummy";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 175;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 200;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.DrainSP;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 70;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 60;    //Resistance to magic based attacks
        mon.res_physical = 85;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 39;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 7;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "mummy";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Polter-Fool");
        mon.number = 78;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Polter-Fool";    //Monster's Name
        mon.experience = 3000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 16;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 100;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 10;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 6;    //Special effects
        mon.idleSound = 178;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "spell001";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Power Lich");
        mon.number = 34;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Power Lich";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.KnockUnconscious;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 70;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 34;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "lich";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Robber");
        mon.number = 18;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Robber";    //Monster's Name
        mon.experience = 900;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 8;    //Monster's armor class (physical defence)
        mon.speed = 23;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 200;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 2;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 80;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 118;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "whoosh2";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Robber Boss");
        mon.number = 80;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Robber Boss";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 115;    //Monster's max HP
        mon.acc = 14;    //Monster's armor class (physical defence)
        mon.speed = 27;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 12;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 15;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 400;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 80;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 14;    //Special effects
        mon.idleSound = 180;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "whoosh2";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Roc");
        mon.number = 66;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Roc";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 16;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Paralyze;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 66;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 166;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Roc";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Rogue");
        mon.number = 54;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Rogue";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Robber;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 60;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 70;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 54;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "thief";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Rooka");
        mon.number = 76;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Rooka";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 60;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Disease;    //Special effects caused by attack
        mon.hitChance = 15;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 47;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "rat";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Royal Vampire");
        mon.number = 79;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Royal Vampire";    //Monster's Name
        mon.experience = 400000;    //Party experience when defeating it
        mon.hp = 750;    //Monster's max HP
        mon.acc = 40;    //Monster's armor class (physical defence)
        mon.speed = 125;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.CurseItems;    //Special effects caused by attack
        mon.hitChance = 120;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 65;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 57;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "vamp";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sabertooth");
        mon.number = 44;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sabertooth";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 3;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 44;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "saber";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sandro");
        mon.number = 72;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sandro";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 75;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.Kill;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 90;    //Resistance to magic based attacks
        mon.res_physical = 80;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 100;    //Gems dropped by monster
        mon.itemDrop = 7;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 34;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "lich";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sand Flower");
        mon.number = 45;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sand Flower";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 5;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None_11;    //Special effects caused by attack
        mon.hitChance = 50;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 45;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 106;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "sand";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sand Golem");
        mon.number = 37;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Sand Golem";    //Monster's Name
        mon.experience = 8000;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 18;    //Monster's armor class (physical defence)
        mon.speed = 10;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 40;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 20;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 37;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 137;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "monsterb";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sand Worm");
        mon.number = 56;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Sand Worm";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 250;    //Monster's max HP
        mon.acc = 19;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 6;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Kill;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 56;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 156;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fxa";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Scraps");
        mon.number = 16;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Scraps";    //Monster's Name
        mon.experience = 2000000;    //Party experience when defeating it
        mon.hp = 3000;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2000;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 2;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 200;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 10;    //Resistance to magic based attacks
        mon.res_physical = 90;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 15;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "dragmum";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Screamer");
        mon.number = 67;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Screamer";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 3000;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.Poison;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 67;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 110;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "dragon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sewer Hag");
        mon.number = 60;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sewer Hag";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 75;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.Insane;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 62;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 108;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecspel";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sewer Rat");
        mon.number = 47;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sewer Rat";    //Monster's Name
        mon.experience = 2000;    //Party experience when defeating it
        mon.hp = 40;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 35;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 47;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 136;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "rat";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sewer Slug");
        mon.number = 48;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sewer Slug";    //Monster's Name
        mon.experience = 1000;    //Party experience when defeating it
        mon.hp = 25;    //Monster's max HP
        mon.acc = 2;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 5;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 48;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 111;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "zombie";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sewer Stalker");
        mon.number = 4;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sewer Stalker";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 250;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 50;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 4;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 113;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "iguana";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Shaalth");
        mon.number = 75;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Shaalth";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1000;    //Gold dropped by monster
        mon.gems = 50;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 43;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fx7";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sharla");
        mon.number = 90;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sharla";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Ranger;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 4;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 53;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hiss";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Skeletal Lich");
        mon.number = 49;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Skeletal Lich";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 2000;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1000;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.ERADICATE;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 70;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 49;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 140;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecbolt";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Skeleton");
        mon.number = 8;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Skeleton";    //Monster's Name
        mon.experience = 250;    //Party experience when defeating it
        mon.hp = 20;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 10;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 6;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 4;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 8;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 108;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Skeleton";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sky Golem");
        mon.number = 87;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sky Golem";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 24;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "golem";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Slayer Knight");
        mon.number = 30;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Slayer Knight";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 100;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 50;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 30;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "knight";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Slime");
        mon.number = 0;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Slime";    //Monster's Name
        mon.experience = 50;    //Party experience when defeating it
        mon.hp = 2;    //Monster's max HP
        mon.acc = 0;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 2;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 0;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Slime";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Snake Man");
        mon.number = 30;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Snake Man";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 26;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 15;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 100;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 30;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 130;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "snakeman";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Snow Beast");
        mon.number = 36;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Snow Beast";    //Monster's Name
        mon.experience = 7000;    //Party experience when defeating it
        mon.hp = 75;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 32;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 12;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 90;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 36;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 136;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "hit1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sorcerer");
        mon.number = 50;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Sorcerer";    //Monster's Name
        mon.experience = 30000;    //Party experience when defeating it
        mon.hp = 90;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 8;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 30;    //Resistance to fire based attacks
        mon.res_elec = 30;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 30;    //Resistance to poison based attacks
        mon.res_energy = 30;    //Resistance to energy based attacks
        mon.res_magic = 70;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 360;    //Gold dropped by monster
        mon.gems = 40;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 50;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 150;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecbolt";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sorceress (Clouds)");
        mon.number = 31;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Sorceress";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 75;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 27;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 12;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 15;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 31;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 131;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "zonga1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Sorceress (Darkside)");
        mon.number = 51;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Sorceress";    //Monster's Name
        mon.experience = 80000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 80;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 10;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 10;    //Resistance to cold based attacks
        mon.res_poison = 10;    //Resistance to poison based attacks
        mon.res_energy = 10;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 50;    //Gems dropped by monster
        mon.itemDrop = 5;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 50;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "disint";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Spirit Bones");
        mon.number = 77;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Spirit Bones";    //Monster's Name
        mon.experience = 2000;    //Party experience when defeating it
        mon.hp = 40;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 10;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 8;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 100;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 8;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 6;    //Special effects
        mon.idleSound = 177;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Skeleton";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Stingers");
        mon.number = 23;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Stingers";    //Monster's Name
        mon.experience = 3600;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 20;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Weakness;    //Special effects caused by attack
        mon.hitChance = 18;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Insect;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 23;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 123;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "insects";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Stone Golem");
        mon.number = 55;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Stone Golem";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 18;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 40;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 90;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 70;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 8;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 55;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 155;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "stonegol";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Swamp Thing");
        mon.number = 34;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Swamp Thing";    //Monster's Name
        mon.experience = 6000;    //Party experience when defeating it
        mon.hp = 130;    //Monster's max HP
        mon.acc = 23;    //Monster's armor class (physical defence)
        mon.speed = 12;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Druid;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 25;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 30;    //Resistance to electricity based attacks
        mon.res_cold = 30;    //Resistance to cold based attacks
        mon.res_poison = 30;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 34;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 134;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "tree";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Thief");
        mon.number = 55;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Thief";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 40;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Robber;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 20;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 200;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 54;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "thief";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Tidal Terror");
        mon.number = 61;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Tidal Terror";    //Monster's Name
        mon.experience = 500000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 100;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 61;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 101;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "splash3";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Tiger Mole");
        mon.number = 11;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Tiger Mole";    //Monster's Name
        mon.experience = 400;    //Party experience when defeating it
        mon.hp = 40;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 20;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 12;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 10;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 11;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 111;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "tiger1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Tomb Guard");
        mon.number = 33;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Tomb Guard";    //Monster's Name
        mon.experience = 6000;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 25;    //Monster's armor class (physical defence)
        mon.speed = 18;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.Age;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 33;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 133;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "Phantom";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Tomb Terror");
        mon.number = 51;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Tomb Terror";    //Monster's Name
        mon.experience = 13000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 27;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 4;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 20;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.CursePlayer;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 51;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 151;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "scream";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Troll Chief");
        mon.number = 82;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Troll Chief";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 300;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 65;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 56;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "troll";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Troll Grunt");
        mon.number = 56;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Troll Grunt";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 56;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 136;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "troll";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Troll Guard");
        mon.number = 81;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Troll Guard";    //Monster's Name
        mon.experience = 15000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 35;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 30;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 56;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "troll";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Valio");
        mon.number = 86;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Valio";    //Monster's Name
        mon.experience = 60000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 30;    //Resistance to electricity based attacks
        mon.res_cold = 30;    //Resistance to cold based attacks
        mon.res_poison = 30;    //Resistance to poison based attacks
        mon.res_energy = 40;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 65;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "wizard";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Vampire");
        mon.number = 57;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Vampire";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 400;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 80;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Weakness;    //Special effects caused by attack
        mon.hitChance = 100;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 57;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 42;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "vamp";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Vampire King");
        mon.number = 85;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Vampire King";    //Monster's Name
        mon.experience = 3000000;    //Party experience when defeating it
        mon.hp = 10000;    //Monster's max HP
        mon.acc = 60;    //Monster's armor class (physical defence)
        mon.speed = 200;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 250;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.ERADICATE;    //Special effects caused by attack
        mon.hitChance = 150;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 80;    //Resistance to energy based attacks
        mon.res_magic = 80;    //Resistance to magic based attacks
        mon.res_physical = 90;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 58;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "vamp";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Vampire Lord");
        mon.number = 58;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Vampire Lord";    //Monster's Name
        mon.experience = 300000;    //Party experience when defeating it
        mon.hp = 500;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 100;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Sleep;    //Special effects caused by attack
        mon.hitChance = 120;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 50;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 70;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 58;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 42;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "vamp";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Vulture Roc");
        mon.number = 59;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Vulture Roc";    //Monster's Name
        mon.experience = 200000;    //Party experience when defeating it
        mon.hp = 2500;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 150;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 60;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 100;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Animal;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 59;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 120;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "vulture";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Water Dragon");
        mon.number = 59;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Water Dragon";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 200;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 26;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 80;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 90;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 59;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 159;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "splash2";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Water Golem");
        mon.number = 28;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Water Golem";    //Monster's Name
        mon.experience = 4000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 16;    //Monster's armor class (physical defence)
        mon.speed = 15;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Cold;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 28;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 128;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "splash3";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Werewolf");
        mon.number = 39;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Werewolf";    //Monster's Name
        mon.experience = 9000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 28;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Disease;    //Special effects caused by attack
        mon.hitChance = 25;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 20;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 39;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 139;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gargrwl";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Whirlwind");
        mon.number = 1;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Whirlwind";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 1000;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 250;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 100;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Confuse;    //Special effects caused by attack
        mon.hitChance = 250;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 100;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 100;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 1;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 176;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "airmon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Wicked Witch");
        mon.number = 14;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Wicked Witch";    //Monster's Name
        mon.experience = 1200;    //Party experience when defeating it
        mon.hp = 50;    //Monster's max HP
        mon.acc = 9;    //Monster's armor class (physical defence)
        mon.speed = 23;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 2;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.CurseItems;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 76;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 114;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "spell002";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Witch");
        mon.number = 62;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Witch";    //Monster's Name
        mon.experience = 80000;    //Party experience when defeating it
        mon.hp = 150;    //Monster's max HP
        mon.acc = 15;    //Monster's armor class (physical defence)
        mon.speed = 70;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Electric;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 100;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 20;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 10;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 63;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 114;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "elecspel";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Wizard (Clouds)");
        mon.number = 42;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Wizard";    //Monster's Name
        mon.experience = 25000;    //Party experience when defeating it
        mon.hp = 75;    //Monster's max HP
        mon.acc = 17;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 100;    //Resistance to fire based attacks
        mon.res_elec = 60;    //Resistance to electricity based attacks
        mon.res_cold = 60;    //Resistance to cold based attacks
        mon.res_poison = 60;    //Resistance to poison based attacks
        mon.res_energy = 60;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 250;    //Gold dropped by monster
        mon.gems = 25;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 42;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 142;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "photon";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Wizard (Darkside)");
        mon.number = 65;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Wizard";    //Monster's Name
        mon.experience = 60000;    //Party experience when defeating it
        mon.hp = 250;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 125;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Paladin;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 25;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 30;    //Resistance to electricity based attacks
        mon.res_cold = 30;    //Resistance to cold based attacks
        mon.res_poison = 30;    //Resistance to poison based attacks
        mon.res_energy = 30;    //Resistance to energy based attacks
        mon.res_magic = 30;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 20;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 65;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 163;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "wizard";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Wood Golem");
        mon.number = 44;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Wood Golem";    //Monster's Name
        mon.experience = 10000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 10;    //Monster's armor class (physical defence)
        mon.speed = 10;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 25;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Golem;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 90;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 5;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 44;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 144;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "fx7";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Xeen's Guard");
        mon.number = 62;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Xeen's Guard";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 50;    //Monster's armor class (physical defence)
        mon.speed = 50;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ANY;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 100;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Unique;    //Certain monster types are affected differently by some spells
        mon.res_fire = 80;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 20;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 62;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 162;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "beam1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Xeen's Pet");
        mon.number = 88;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Xeen's Pet";    //Monster's Name
        mon.experience = 100000;    //Party experience when defeating it
        mon.hp = 400;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 250;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 1;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Poison;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Dragon;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 100;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 88;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 188;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "crash2a";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Xenoc");
        mon.number = 93;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Xenoc";    //Monster's Name
        mon.experience = 250000;    //Party experience when defeating it
        mon.hp = 700;    //Monster's max HP
        mon.acc = 35;    //Monster's armor class (physical defence)
        mon.speed = 175;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 10;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 50;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Energy;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 1;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 50;    //Resistance to fire based attacks
        mon.res_elec = 50;    //Resistance to electricity based attacks
        mon.res_cold = 50;    //Resistance to cold based attacks
        mon.res_poison = 50;    //Resistance to poison based attacks
        mon.res_energy = 100;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 100;    //Gems dropped by monster
        mon.itemDrop = 6;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 64;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "boltelec";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Yak Lich");
        mon.number = 41;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Yak Lich";    //Monster's Name
        mon.experience = 20000;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 20;    //Monster's armor class (physical defence)
        mon.speed = 27;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 5;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.KnockUnconscious;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 50;    //Resistance to magic based attacks
        mon.res_physical = 40;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 83;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 141;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "boltelec";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Yak Master");
        mon.number = 83;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Yak Master";    //Monster's Name
        mon.experience = 50000;    //Party experience when defeating it
        mon.hp = 160;    //Monster's max HP
        mon.acc = 22;    //Monster's armor class (physical defence)
        mon.speed = 30;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 5;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 10;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Magic;    //Element type of attack
        mon.specialAttack = AttackType.Kill;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 60;    //Resistance to magic based attacks
        mon.res_physical = 60;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = true;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 83;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 7;    //Special effects
        mon.idleSound = 183;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "boltelec";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Yak Priest");
        mon.number = 25;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Yak Priest";    //Monster's Name
        mon.experience = 5000;    //Party experience when defeating it
        mon.hp = 80;    //Monster's max HP
        mon.acc = 12;    //Monster's armor class (physical defence)
        mon.speed = 25;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.ALL;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Fire;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 0;    //Base probability of attack landing
        mon.rangeAttack = true;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 60;    //Resistance to fire based attacks
        mon.res_elec = 20;    //Resistance to electricity based attacks
        mon.res_cold = 20;    //Resistance to cold based attacks
        mon.res_poison = 20;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 400;    //Gold dropped by monster
        mon.gems = 12;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 25;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 125;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "gazong1";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Yang Knight");
        mon.number = 38;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Yang Knight";    //Monster's Name
        mon.experience = 8000;    //Party experience when defeating it
        mon.hp = 120;    //Monster's max HP
        mon.acc = 30;    //Monster's armor class (physical defence)
        mon.speed = 24;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 4;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Knight;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 3;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 15;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 25;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 30;    //Resistance to fire based attacks
        mon.res_elec = 30;    //Resistance to electricity based attacks
        mon.res_cold = 30;    //Resistance to cold based attacks
        mon.res_poison = 30;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 1200;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 3;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 38;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = true;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 138;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "nyaahh";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Yog");
        mon.number = 89;    //The Monster ID
        mon.side = DARK_SIDE;    //The side of Xeen it appears on
        mon.name = "Yog";    //Monster's Name
        mon.experience = 25000;    //Party experience when defeating it
        mon.hp = 100;    //Monster's max HP
        mon.acc = 5;    //Monster's armor class (physical defence)
        mon.speed = 60;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 1;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Sorcerer;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 1;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 30;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.None;    //Special effects caused by attack
        mon.hitChance = 25;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Humanoid;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 0;    //Resistance to electricity based attacks
        mon.res_cold = 0;    //Resistance to cold based attacks
        mon.res_poison = 0;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 0;    //Resistance to physical attacks
        mon.gold = 200;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 4;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 6;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 10;    //Special effects
        mon.idleSound = 100;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "barbarch";    //xxx.VOC file played when monster attacks
        monsters.add(mon);

        mon = new MaMMonsterRecord("Zombie");
        mon.number = 9;    //The Monster ID
        mon.side = CLOUDS;    //The side of Xeen it appears on
        mon.name = "Zombie";    //Monster's Name
        mon.experience = 300;    //Party experience when defeating it
        mon.hp = 30;    //Monster's max HP
        mon.acc = 2;    //Monster's armor class (physical defence)
        mon.speed = 4;    //Monster's attack speed (determines order per round)
        mon.numberOfAttacks = 2;    //Number of attacks monster gets per round
        mon.hatesClass = CharClass.Cleric;    //Class/race to attack first (ALL=attack entire party at once)
        mon.strikes = 2;    //The 'X' in the XdY equation
        mon.dmgPerStrike = 4;    //The 'Y' in the XdY equation
        mon.attackType = AttackType.Physical;    //Element type of attack
        mon.specialAttack = AttackType.Disease;    //Special effects caused by attack
        mon.hitChance = 5;    //Base probability of attack landing
        mon.rangeAttack = false;    //Can attack at a distance
        mon.monsterType = MonsterType.Undead;    //Certain monster types are affected differently by some spells
        mon.res_fire = 0;    //Resistance to fire based attacks
        mon.res_elec = 80;    //Resistance to electricity based attacks
        mon.res_cold = 80;    //Resistance to cold based attacks
        mon.res_poison = 80;    //Resistance to poison based attacks
        mon.res_energy = 0;    //Resistance to energy based attacks
        mon.res_magic = 0;    //Resistance to magic based attacks
        mon.res_physical = 50;    //Resistance to physical attacks
        mon.gold = 0;    //Gold dropped by monster
        mon.gems = 0;    //Gems dropped by monster
        mon.itemDrop = 0;    //probability that monster drops an item
        mon.flying = false;    //Boolean value: monster flies or it doesn't
        mon.imageNumber = 9;    //Sprite ID (xxx.MON and xxx.ATK files)
        mon.loopAnimation = false;    //Frames either increment and loop, or bounce start to end and back
        mon.animationEffect = 0;    //Special effects
        mon.idleSound = 109;    //Effect number played by PlayFX every 5 seconds
        mon.attackVoc = "laff3d2";    //xxx.VOC file played when monster attacks
        monsters.add(mon);


        // init the translation keys via the resource bundle.
        String[] resourceKeysInAlphaOrder = new String[] {"mon_acid_dragon", "mon_annihilator", "mon_arachnoid",
                "mon_armadillo", "mon_autobot", "mon_barbarian_clouds", "mon_barbarian_darkside", "mon_barkman",
                "mon_bat_queen", "mon_beholder_bat", "mon_breeder_slime", "mon_captain_yang", "mon_carnage_hand",
                "mon_castle_guard", "mon_clan_king", "mon_clan_sargent", "mon_cleric_of_mok", "mon_cleric_of_yak",
                "mon_cloud_dragon", "mon_cloud_golem", "mon_count_blackfang", "mon_count_draco", "mon_coven_leader",
                "mon_cult_leader_clouds", "mon_cult_leader_darkside", "mon_cyclops", "mon_dark_wolf", "mon_darzog",
                "mon_darzog_clone", "mon_death_knight", "mon_demon", "mon_devil", "mon_diamond_golem", "mon_doom_bug",
                "mon_doom_knight", "mon_dragon_king", "mon_dragon_mummy", "mon_earth_blaster", "mon_earth_golem",
                "mon_electrapede", "mon_enchantress", "mon_energy_dragon", "mon_evil_archer", "mon_evil_ranger",
                "mon_fire_blower", "mon_fire_dragon", "mon_flying_feet", "mon_frost_dragon", "mon_gamma_gazer",
                "mon_gargoyle_clouds", "mon_gargoyle_darkside", "mon_gettlewaithe", "mon_ghost_mummy",
                "mon_ghost_rider", "mon_ghoul", "mon_giant", "mon_giant_bat", "mon_giant_scorpion", "mon_giant_snake",
                "mon_giant_spider", "mon_giant_toad", "mon_gnome_vampire", "mon_goblin_clouds", "mon_goblin_darkside",
                "mon_gorgon", "mon_graalg", "mon_great_hydra", "mon_green_dragon", "mon_gremlin", "mon_gremlin_guard",
                "mon_griffin", "mon_guardian", "mon_guardian_asp", "mon_gurodel", "mon_harpy", "mon_harpy_queen",
                "mon_head_witch", "mon_hell_hornet", "mon_higher_mummy", "mon_hobstadt", "mon_ice_troll",
                "mon_iguanasaurus", "mon_insane_begger", "mon_insect_swarm", "mon_iron_golem", "mon_jouster",
                "mon_killer_cobra", "mon_killer_sprite", "mon_kings_guard", "mon_lava_dweller", "mon_lava_golem",
                "mon_lava_roach", "mon_lord_xeen", "mon_mad_dwarf", "mon_mad_fool", "mon_magic_mage", "mon_mantis_ant",
                "mon_master_thief", "mon_master_wizard", "mon_medusa_sprite", "mon_mega_dragon", "mon_mega_mage",
                "mon_minotaur", "mon_mok_heretic", "mon_morgana", "mon_mummy", "mon_mystic_mage", "mon_ninja",
                "mon_octopod", "mon_ogre_clouds", "mon_ogre_darkside", "mon_onyx_golem", "mon_orc", "mon_orc_elite",
                "mon_orc_guard", "mon_orc_shaman", "mon_phase_dragon", "mon_phase_mummy", "mon_polter-fool",
                "mon_power_lich", "mon_robber", "mon_robber_boss", "mon_roc", "mon_rogue", "mon_rooka",
                "mon_royal_vampire", "mon_sabertooth", "mon_sand_flower", "mon_sand_golem", "mon_sand_worm",
                "mon_sandro", "mon_scraps", "mon_screamer", "mon_sewer_hag", "mon_sewer_rat", "mon_sewer_slug",
                "mon_sewer_stalker", "mon_shaalth", "mon_sharla", "mon_skeletal_lich", "mon_skeleton", "mon_sky_golem",
                "mon_slayer_knight", "mon_slime", "mon_snake_man", "mon_snow_beast", "mon_sorcerer",
                "mon_sorceress_clouds", "mon_sorceress_darkside", "mon_spirit_bones", "mon_stingers", "mon_stone_golem",
                "mon_swamp_thing", "mon_thief", "mon_tidal_terror", "mon_tiger_mole", "mon_tomb_guard",
                "mon_tomb_terror", "mon_troll_chief", "mon_troll_grunt", "mon_troll_guard", "mon_valio", "mon_vampire",
                "mon_vampire_king", "mon_vampire_lord", "mon_vulture_roc", "mon_water_dragon", "mon_water_golem",
                "mon_werewolf", "mon_whirlwind", "mon_wicked_witch", "mon_witch", "mon_wizard_clouds",
                "mon_wizard_darkside", "mon_wood_golem", "mon_xeens_guard", "mon_xeens_pet", "mon_xenoc",
                "mon_yak_lich", "mon_yak_master", "mon_yak_priest", "mon_yang_knight", "mon_yog", "mon_zombie"};

        //use localisation of names.
        for (int i = 0; i < resourceKeysInAlphaOrder.length; i++) {
            MaMMonsterRecord m = monsters.get(i); //assumes both lists are in the same alpha order
            m.name = resourceKeysInAlphaOrder[i];
        }

        //create monster factory
        return new MonsterFactory(monsters);

    }

    public MaMMonster createMonster(MaMWorld world, int id) throws CCFileFormatException {
        MaMMonsterRecord rec = null;
        if(world instanceof WoXWorld)
        {
            rec = monsters.stream()
                .filter(M -> M.number == id)
                .filter(M -> M.side == ((WoXWorld)world).getVariant())
                .findFirst()
                .orElseGet(null);
        }

        return (rec == null) ? null : rec.newMonster(world);
    }

    private static class MaMMonsterRecord {
        public int number;
        public WoXWorld.WoxVariant side;
        public String name;
        public int experience;
        public int hp;
        public int acc;
        public int speed;
        public int numberOfAttacks;
        public CharClass hatesClass;
        public int strikes;
        public int dmgPerStrike;
        public AttackType attackType;
        public AttackType specialAttack;
        public int hitChance;
        public boolean rangeAttack;
        public MonsterType monsterType;
        public int res_fire;
        public int res_elec;
        public int res_cold;
        public int res_poison;
        public int res_energy;
        public int res_magic;
        public int res_physical;
        public int gold;
        public int gems;
        public int itemDrop;
        public boolean flying;
        public int imageNumber;
        public boolean loopAnimation;
        public int animationEffect;
        public int idleSound;
        public String attackVoc;

        public MaMMonsterRecord(String name) {
            this.name = name;
        }

        public MaMMonster newMonster(MaMWorld world) throws CCFileFormatException {
            MaMMonster mon = new MaMMonster(name, number, world);
            mon.setExperience(this.experience);

            mon.setHitPoints(this.hp);
            mon.setArmour(this.acc);
            mon.setSpeed(this.speed);
            mon.setNumberOfAttacks(this.numberOfAttacks);
            mon.setHatesClass(this.hatesClass);
            mon.setStrikes(this.strikes);
            mon.setDmgPerStrike(this.dmgPerStrike);
            mon.setAttackType(this.attackType);
            mon.setSpecialAttack(this.specialAttack);
            mon.setHitChance(this.hitChance);
            mon.setRangeAttack(this.rangeAttack);
            mon.setMonsterType(this.monsterType);

            mon.setFireResist(this.res_fire);
            mon.setElecResist(this.res_elec);
            mon.setColdResist(this.res_cold);
            mon.setPoisonResist(this.res_poison);
            mon.setEnergyResist(this.res_energy);
            mon.setMagicResist(this.res_magic);
            mon.setEndurance(this.res_physical);

            mon.setGold(this.gold);
            mon.setGems(this.gems);
            mon.setItemDrop(this.itemDrop);
            mon.setFlying(this.flying);
            mon.setImageNumber(this.imageNumber);
            mon.setLoopAnimation(this.loopAnimation);
            mon.setAnimationEffect(this.animationEffect);
            mon.setIdleSound(this.idleSound);
            mon.setAttackVoc(this.attackVoc);

            return mon;
        }
    }
}
