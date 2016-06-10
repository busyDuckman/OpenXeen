package GameMechanics;

import GameMechanics.Adventurers.IStatModifier;

/**
 * Created by duckman on 31/05/2016.
 * Any stat, adventurer or monster.
 */
public class Stat<T extends Stats>
{
    int baseLevel;
    int temporaryLevel;
    T type;

    public String describe()
    {
        return type.describe(temporaryLevel);
    }

    public void resetTemporyLevel() {temporaryLevel = baseLevel;}

    public void applyModifier(IStatModifier mod)
    {
        Stat<T> stat = mod.applyTo(this);
        this.baseLevel = stat.baseLevel;
        this.temporaryLevel = stat.temporaryLevel;
    }
}
