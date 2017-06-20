package GameMechanics;

import Game.GlobalSettings;
import Game.Map.MaMWorld;
import GameMechanics.Adventurers.IStatModifier;
import Rendering.GUI.GUIGraphicsSet;
import Rendering.IRenderableGameObject;

/**
 * Created by duckman on 31/05/2016.
 * Any stat, adventurer or monster.
 */
public class Stat
{
    int baseLevel;
    int temporaryLevel;
    String key;
    int min;
    int max;

    public Stat(String key, int min, int max) {
        this.key = key;
        this.min = min;
        this.max = max;
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

    public void setBaseLevel(int baseLevel) {
        this.baseLevel = baseLevel;
    }

    public void setTemporaryLevel(int temporaryLevel) {
        this.temporaryLevel = temporaryLevel;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getKey() {
        return key;
    }

    public void set(int value) {
        baseLevel = value;
    }

    public IRenderableGameObject getIcon(MaMWorld world) {
        GUIGraphicsSet set = world.getGraphicsSet();

        switch (this.key) {
            case "stat_armor_class": return set.getIcnStatAC();
            case "stat_level": return set.getIcnStatLvL();
            case "stat_speed": return set.getIcnStatSpeed();
            case "stat_accuracy": return set.getIcnStatAcy();
            case "stat_endurance": return set.getIcnStatEnd();
            case "stat_fire_res": return set.getIcnStatRes();
            case "stat_cold_res": return set.getIcnStatRes();
            case "stat_elec_res": return set.getIcnStatRes();
            case "stat_poison_res": return set.getIcnStatRes();
            case "stat_energy_res": return set.getIcnStatRes();
            case "stat_magic_res": return set.getIcnStatRes();
            case "stat_hit_points": return set.getIcnStatHP();
            default:
                System.out.println("MISSING CODE: case \"" + this.key + "\": return set.get;");
        }

        return null;
    }
}
