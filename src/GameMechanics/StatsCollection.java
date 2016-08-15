package GameMechanics;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by duckman on 14/08/2016.
 */
public class StatsCollection implements Iterable<Stat>
{
    protected Stat<Stats.Might> might;
    protected Stat<Stats.Intelligence> intelligence;
    protected Stat<Stats.Personality> personality;
    protected Stat<Stats.Endurance> endurance;
    protected Stat<Stats.Speed> speed;
    protected Stat<Stats.Accuracy> accuracy;
    protected Stat<Stats.Luck> luck;
    protected Stat<Stats.Armour> armour;
    protected Stat<Stats.Level> level;
    protected Stat<Stats.Age> age;
    protected Stat<Stats.FireResist> fireResist;
    protected Stat<Stats.ColdResist> coldResist;
    protected Stat<Stats.ElecResist> elecResist;
    protected Stat<Stats.PoisonResist> poisonResist;
    protected Stat<Stats.EnergyResist> energyResist;
    protected Stat<Stats.MagicResist> magicResist;
    protected Stat<Stats.HitPoints> hitPoints;
    protected Stat<Stats.SpellPoints> spellPoints;
    protected Stat<Stats.ExperiencePoints> experiencePoints;

    protected StatsCollection() {
        might = new Stat<Stats.Might>(0);
        intelligence = new Stat<Stats.Intelligence>(0);

    }

    public void rollNewAdvventurer()
    {
        Random r = new Random();
        int attribMax = 18;
        Dice.Tally tally = Dice.Tally.fromString("2:D6 + 5");
        might = new Stat<Stats.Might>(tally.roll());
    }

    @Override
    public Iterator<Stat> iterator() {
        return new Iterator<Stat>() {
            private int index = 0;
            Stat[] allStats = new Stat[] { might, intelligence, personality, endurance, speed, accuracy, luck, armour,
                                           level, age, fireResist, coldResist, elecResist, poisonResist, energyResist,
                                           magicResist, hitPoints, spellPoints, experiencePoints};
            @Override public boolean hasNext() { return index < allStats.length; }
            @Override public Stat next() { return allStats[index++]; }
        };
    }
}
