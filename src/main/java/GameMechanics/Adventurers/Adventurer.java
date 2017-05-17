package GameMechanics.Adventurers;

import GameMechanics.Attacks.IAttack;
import Game.IAttackable;
import Game.IUpdateable;
import Game.MaMActions;
import Game.Map.MaMWorld;
import GameMechanics.Combatant;
import GameMechanics.Dice;
import GameMechanics.Equipment.*;
import GameMechanics.Inventory;
import GameMechanics.Stat;
import Rendering.IRelativeToLocationSprite;
import Rendering.IRenderableGameObject;
import mamFiles.MaMMazeFile;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by duckman on 31/05/2016.
 *
 *
 *
 *
 *
 */
public class Adventurer extends Combatant implements IUpdateable, IAttackable, Iterable<Stat>
{

    //As a reference http://xeen.wikia.com/wiki/CharacterStruct
    /*
    0x00	name	    16 bytes	The character's name (ASCIIZ). The name is limited to ten characters in length, but the field is padded out with null bytes.
    0x10	sex	        1 byte	    Index into the SexNames array, eg. 0 = Male, 1 = Female.
    0x11	race	    1 byte	    Index into the RaceNames array, eg. 0 = Human, 1 = Elf, etc. Used to look up values from other tables as well, eg. base HP mod, resistances, base thievery, base skills, base SP/level modifiers, etc.
    0x12	saveSide	1 byte	    Unexplored
    0x13	class	    1 byte	    Index into the ClassNames array, eg. 0 = Knight, 1 = Paladin, etc. Used to look up values from other tables as well, eg. base HP gained per level, number of levels to gain an attack, default skills, available spells, etc.
    0x14	mgt	        2 bytes	    Each of these 2-byte structures represents the base statistic value (1 byte) and the temporary statistic value (1 byte), which is added to the base value to get the "real" value. The base represents the character's innate Might (for example), and the temporary value represents any bonuses or penalties applied through drinking from fountains, scripted events, etc. For more information, see theStatistics page.
    0x16	int	        2 bytes
    0x18	pers	    2 bytes
    0x1A	end	        2 bytes
    0x1C	spd	        2 bytes
    0x1E	acc	        2 bytes
    0x20	luck	    2 bytes
    0x22	ac_temp	    1 byte?	    Temporary AC bonus
    0x23	level	    2 bytes	    Character's level; two bytes, first byte is base level, second byte is temporary level bonus (same storage method as any of the statistics, above).
    0x25	db_day	    1 byte	    The character's birthday (Day of the year)
    0x26	age_temp	1 byte	    The character's temporary age (unnatural aging)
    0x27	skills	    18 bytes	The skills the character possesses. See: Skills
    0x39	awards	    64 bytes	The awards the character has received. See: Awards
    0x79	spells	    39 bytes	The spells the character can cast. See: Spells
    0xA0	lloydmap	1 byte	    The Map ID the character's Lloyd's Beacon spell is set to.
    0xA1	lloydx	    1 byte	    The X-coordinate for the saved Lloyd's Beacon position.
    0xA2	lloydy	    1 byte	    The Y-coordinate for the saved Lloyd's Beacon position.
    0xA3	hasspells	1 byte	    0 = character cannot cast spells, 1 = character can cast spells
    0xA4	curspell    1 byte	    The character's last-selected spell
    0xA5	quickOpt	1 byte	    The character's last-selected quick option
    0xA6	weapon	    9*4 bytes	The character's weapons
    0xCA	armor	    9*4 bytes	The character's armor
    0xEE	acces	    9*4 bytes	The character's accessories
    0x112	misc	    9*4 bytes	The character's miscellaneous items
    0x136	lloydsSide	1 byte	    The side of the world the Lloyd's Beacon is set to (ie. Clouds or Darkside)
    0x137	fire	    2 bytes	    Each of these fields represents the character's resistance to the specified element.
                                    The first byte is their base permanent resistance, the second byte is the temporary
                                    modifier.
    0x139	cold	    2 byte
    0x13B	elec	    2 byte
    0x13D	poison	    2 bytes
    0x13F	energy	    2 bytes
    0x141	magic	    2 bytes
    0x143	conditions	16 bytes	The character's conditions (Drunk, Unconscious, Dead, Eradicated, etc.) are stored here. See: Conditions
    0x153	 ?	        uint16	    Possibly "town"? Purpose unknown.
    0x155	 ?	        1 byte	    ?
    0x156	cur_hp	    uint16	    Current HP
    0x158	cur_sp	    uint16	    Current SP
    0x15A	yb_day	    uint16	    The character's birth year
    0x15C	experience	dword	    Current amount of experience
    0x160	curSplAdv	1 byte	    Index to the character's currently selected adventuring (ie. non-combat) spell.
    0x161	curSplCom	1 byte	    Index to the character's currently selected combat spell.
     */


    String name;
    CharGender sex;
    CharRace race;
    String homeWorldName; //Where the character goes when dismissed. See MaMWorld.getWorldName();
    CharClass klass;
    int birthDOY;
    int birthYear;

    protected Stat age = new Stat("stat_age", 0, 120);
    protected Stat might = new Stat("stat_might", 0, naturalStatMax);
    protected Stat intelligence = new Stat("stat_intellect", 0, naturalStatMax);
    protected Stat personality = new Stat("stat_personality", 0, naturalStatMax);
    protected Stat luck = new Stat("stat_luck", 0, naturalStatMax);
    protected Stat spellPoints = new Stat("stat_spell_points", 0, miscStatMax);
    protected Stat experiencePoints = new Stat("stat_experience", 0, miscStatMax);


//    @Override
//    public Iterator<Stat> iterator() {
//        return new Iterator<Stat>() {
//            private int index = 0;
//            Stat[] allStats = new Stat[] { might, intelligence, personality, endurance, speed, accuracy, luck, armour,
//                    level, age, fireResist, coldResist, elecResist, poisonResist, energyResist,
//                    magicResist, hitPoints, spellPoints, experiencePoints};
//            @Override public boolean hasNext() { return index < allStats.length; }
//            @Override public Stat next() { return allStats[index++]; }
//        };
//    }

//    Stat<Stats.Might> might;
//    Stat<Stats.Intelligence> intelligence;
//    Stat<Stats.Personality> personality;
//    Stat<Stats.Endurance> endurance;
//    Stat<Stats.Speed> speed;
//    Stat<Stats.Accuracy> accuracy;
//    Stat<Stats.Luck> luck;
//    Stat<Stats.Armour> armour;
//    Stat<Stats.Level> level;
//    Stat<Stats.Age> age;
//    Stat<Stats.FireResist> fireResist;
//    Stat<Stats.ColdResist> coldResist;
//    Stat<Stats.ElecResist> elecResist;
//    Stat<Stats.PoisonResist> poisonResist;
//    Stat<Stats.EnergyResist> energyResist;
//    Stat<Stats.MagicResist> magicResist;
//    Stat<Stats.HitPoints> hitPoints;
//    Stat<Stats.SpellPoints> spellPoints;
//    Stat<Stats.ExperiencePoints> experiencePoints;

    List<CharSkill> skills;
    List<Award> Awards;
    List<Integer> Spells;  //Indexes into Spells.allSpells.get(...);
    String lloydWorld;
    int lloydXPos;
    int lloydYPos;
    boolean spellCaster;
    MaMActions quickOpt;
    Inventory<Weapon> weaponInv;
    Inventory<Armour> armorInv;
    Inventory<Accessory> accesInv;
    Inventory<MiscItem> miscInv;

    List<ICharCondition> conditions;

    int curSplAdventure;
    int curSplCombat;

    int faceNum = 1;


    public Adventurer(String name, CharGender sex, CharRace race, CharClass klass, int faceNum) {
        super(faceNum, name, new Point(0,0), null);
        this.name = name;
        this.sex = sex;
        this.race = race;
        this.klass = klass;
        this.faceNum = faceNum;
    }

    public void rollNewAdvventurer()
    {
        Dice.Tally tally = Dice.Tally.fromString("2:D6 + 5");
        Dice.Tally resistTally = Dice.Tally.fromString("1:D6+1");
        might.set(tally.roll());
        intelligence.set(tally.roll());
        personality.set(tally.roll());
        endurance.set(tally.roll());
        speed.set(tally.roll());
        accuracy.set(tally.roll());
        luck.set(tally.roll());
        armour.set(tally.roll());
        level.set(1);
        age.set(18);

        fireResist.set(resistTally.roll());
        coldResist.set(resistTally.roll());
        elecResist.set(resistTally.roll());
        poisonResist.set(resistTally.roll());
        energyResist.set(resistTally.roll());
        magicResist.set(resistTally.roll());

        hitPoints.set(tally.roll());
        spellPoints.set(10);
        experiencePoints.set(0);
    }


    @Override
    public IRelativeToLocationSprite getSprite() {
        return IRenderableGameObject.fromText("Player", Color.RED, 100, 100).asIRelativeToLocationSprite();
    }

    /*
         * Called evey ?
         *  -age char
         *  -adjust sickness etc
         *  -regen spell points
         */
    @Override
    public void update(MaMWorld world) {

    }

    @Override
    public void copThis(IAttack attack) {

    }

}
