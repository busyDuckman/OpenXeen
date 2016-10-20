package GameMechanics;

import Game.GlobalSettings;
import GameMechanics.Adventurers.IStatModifier;

/**
 * Created by duckman on 31/05/2016.
 * Any stat, adventurer or monster.
 */
public class Stat
{
    int baseLevel;
    int temporaryLevel;
    String key;

    public Stat(String key, int min, int max) {

    }

    public String getName()
    {
        return GlobalSettings.INSTANCE.getLocalText().getString(key);
    }

    public String describe()
    {
        return null;
    } //TODO

    public void resetTemporyLevel() {temporaryLevel = baseLevel;}

    public void applyModifier(IStatModifier mod)
    {
        Stat stat = mod.applyTo(this);
        this.baseLevel = stat.baseLevel;
        this.temporaryLevel = stat.temporaryLevel;
    }

    public int getBaseLevel() {
        return baseLevel;
    }

    public int getTemporaryLevel() {
        return temporaryLevel;
    }


    public void set(int value) {
        baseLevel = value;
    }
}
