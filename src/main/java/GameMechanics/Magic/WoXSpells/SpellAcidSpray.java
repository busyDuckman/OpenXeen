package GameMechanics.Magic.WoXSpells;

import GameMechanics.Adventurers.Adventurer;
import Game.Map.MaMWorld;
import GameMechanics.Magic.Spell;

/**
 * Created by duckman on 31/05/2016.
 */
public class SpellAcidSpray extends Spell
{
    @Override
    public int getSpellPointsRequired(MaMWorld world, Adventurer caster) {
        return 0;
    }

    @Override
    public int getGemsRequired(MaMWorld world, Adventurer caster) {
        return 0;
    }

    @Override
    public CastableState canCast(MaMWorld world, Adventurer caster) {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void cast(MaMWorld world, Adventurer caster) {

    }
}
