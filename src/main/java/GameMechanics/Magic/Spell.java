package GameMechanics.Magic;

import GameMechanics.Adventurers.Adventurer;
import Game.Map.MaMWorld;

/**
 * Created by duckman on 31/05/2016.
 */
public abstract class Spell implements ISpell
{
    @FunctionalInterface
    interface CastSpell {
        public int apply(MaMWorld world, Adventurer caster);
    }

    int spellPointsRequired;
    int gemsRequired;

    public Spell()
    {
    }


}
