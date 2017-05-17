package GameMechanics.Magic;

import GameMechanics.Adventurers.Adventurer;
import Game.Map.MaMWorld;

/**
 * Created by duckman on 31/05/2016.
 */
public interface ISpell
{
    enum CastableState{CanCast, WrongClass,
                        NotIndoors,NotOutDoors, NotFromHere,
                        OnlyBattle, NotInBattle, NotSafe, NoSP, NoGems,
                        TooManyTimesADay,
                        OtherProblem}

    int getSpellPointsRequired(MaMWorld world, Adventurer caster);
    int getGemsRequired(MaMWorld world, Adventurer caster);
    CastableState canCast(MaMWorld world, Adventurer caster);
    String getDescription();
    void cast(MaMWorld world, Adventurer caster);
}
