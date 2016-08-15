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

    public Stat(int baseLevel) {
        this.baseLevel = baseLevel;
        //type = T::new;
    }

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

    public int getBaseLevel() {
        return baseLevel;
    }

    public int getTemporaryLevel() {
        return temporaryLevel;
    }

    public T getType() {
        return type;
    }

    public boolean isOfType(Stats stat)
    {
        return stat == type;
    }
}
