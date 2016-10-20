package GameMechanics.Adventurers;

import GameMechanics.Stat;
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
        @Override public DateTime getExpireTime() { return null; }
        @Override public String getName() { return name; }

        @Override
        public Stat applyTo(Stat stat) {
            return null;
        }

        @Override
        public boolean affects(Stat stat) {
            return false;
        }
    }

    String name;
    IStatModifier[] modifiers;

    CharRace(String name, IStatModifier[] modifiers) {
        this.name = name;
        this.modifiers = modifiers;
    }
}
