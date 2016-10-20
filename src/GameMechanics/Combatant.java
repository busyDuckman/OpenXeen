package GameMechanics;

import Game.GameEntityBase;
import Game.IAttackable;
import Toolbox.Direction;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by duckman on 20/08/2016.
 */
public abstract class Combatant extends GameEntityBase implements IAttackable, Iterable<Stat>
{
//    public int hp;
//    public int acc;
//    public int speed;

    protected static final int naturalStatMax = 1024;
    protected static final int miscStatMax = 1024*1024*32;

    public Stat armour = new Stat("stat_armor_class", 0, miscStatMax);
    public Stat level = new Stat("stat_level", 0, naturalStatMax);
    public Stat speed = new Stat("stat_speed", 0, naturalStatMax);
    public Stat accuracy = new Stat("stat_accuracy", 0, naturalStatMax);
    public Stat endurance = new Stat("stat_endurance", 0, naturalStatMax);

    public Stat fireResist = new Stat("stat_fire_res", 0, naturalStatMax);
    public Stat coldResist = new Stat("stat_cold_res", 0, naturalStatMax);
    public Stat elecResist = new Stat("stat_elec_res", 0, naturalStatMax);
    public Stat poisonResist = new Stat("stat_poison_res", 0, naturalStatMax);
    public Stat energyResist = new Stat("stat_energy_res", 0, naturalStatMax);
    public Stat magicResist = new Stat("stat_magic_res", 0, naturalStatMax);

    public Stat hitPoints = new Stat("stat_hit_points", 0, miscStatMax);

    public Combatant(int id, String name, Point location) {
        super(id, name, location);
    }

    public Combatant(int id, String name, Direction heading, Point location) {
        super(id, name, heading, location);
    }

    public Combatant(int id, String name, boolean visible, Direction heading, Point location) {
        super(id, name, visible, heading, location);
    }

    private static volatile Map<Type, Field[]> statLut = new HashMap<>();

    @Override
    public Iterator<Stat> iterator() {
        updateStatLut();

        return new Iterator<Stat>() {
            private int index = 0;
            Stat[] allStats = Arrays.stream(statLut.get(this.getClass()))
                                    .map(F -> {
                                        try {
                                            return (Stat)F.get(this);
                                        } catch (IllegalAccessException e) {
                                            return null;
                                        }
                                    })
                                    .filter(S -> (S != null))
                                    .toArray(Stat[]::new);

            @Override public boolean hasNext() { return index < allStats.length; }
            @Override public Stat next() { return allStats[index++]; }
        };
    }

    protected synchronized void updateStatLut()
    {
        if(!statLut.containsKey(this.getClass()))
        {
            statLut.put(this.getClass(),
                        Arrays.stream(this.getClass().getFields())
                                .filter(F -> Stat.class.isAssignableFrom(F.getClass()))
                                .toArray(Field[]::new));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Dear Java, It's 2016, boiler plate getters and setters should have been replaced with accessors 10 years ago.
    //------------------------------------------------------------------------------------------------------------------
    public Stat getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour.set(armour);
    }

    public Stat getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public Stat getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public Stat getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy.set(accuracy);
    }

    public Stat getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance.set(endurance);
    }

    public Stat getFireResist() {
        return fireResist;
    }

    public void setFireResist(int fireResist) {
        this.fireResist.set(fireResist);
    }

    public Stat getColdResist() {
        return coldResist;
    }

    public void setColdResist(int coldResist) {
        this.coldResist.set(coldResist);
    }

    public Stat getElecResist() {
        return elecResist;
    }

    public void setElecResist(int elecResist) {
        this.elecResist.set(elecResist);
    }

    public Stat getPoisonResist() {
        return poisonResist;
    }

    public void setPoisonResist(int poisonResist) {
        this.poisonResist.set(poisonResist);
    }

    public Stat getEnergyResist() {
        return energyResist;
    }

    public void setEnergyResist(int energyResist) {
        this.energyResist.set(energyResist);
    }

    public Stat getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int magicResist) {
        this.magicResist.set(magicResist);
    }

    public Stat getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints.set(hitPoints);
    }
}
