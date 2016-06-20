package GameMechanics.Adventurers;

import GameMechanics.Stat;
import GameMechanics.Stats;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duckman on 31/05/2016.
 */
public enum CharRace
{
    ORC      ("Orc", new RaceModifier[] {new RaceModifier()}),
    HALF_ORC ("Orc", new RaceModifier[] {new RaceModifier()}),
    ELF      ("Orc", new RaceModifier[] {new RaceModifier()}),
    HALF_ELF ("Orc", new RaceModifier[] {new RaceModifier()}),
    HUMAN    ("Orc", new RaceModifier[] {new RaceModifier()}),
    DWARF    ("Orc", new RaceModifier[] {new RaceModifier()}),
    HAUFLIN  ("Orc", new RaceModifier[] {new RaceModifier()});

    public static class RaceModifier implements IStatModifier
    {
        String name;
        Stats modifiedStat;
        @Override public DateTime getExpireTime() { return null; }
        @Override public String getName() { return name; }
        @Override public <T extends Stats> Stat<T> applyTo(Stat<T> stat) {
            //if(stat.;
            return null;
        }

    }

    String name;
    IStatModifier[] modifiers;

    CharRace(String name, IStatModifier[] modifiers) {
        this.name = name;
        this.modifiers = modifiers;
    }
}
