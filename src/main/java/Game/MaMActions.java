package Game;

/**
 * Created by duckman on 24/05/2016.
 */
public enum MaMActions
{
    WalkForward(true, false),
    WalkBackWard(true, false),
    WalkLeft(true, false),
    WalkRight(true, false),
    TurnLeft(true, true),
    TurnRight(true, true),
    Inspect(true, false),
    Shoot(true, false),
    Bash(true, false),
    Run(true, false),
    Spell(true, true),
    MelleAttack(false, true),
    MelleShield(true, true),
    Sleep(true, false);

    boolean duringExplore;
    boolean duringCombat;

    MaMActions(boolean duringExplore, boolean duringCombat) {
        this.duringExplore = duringExplore;
        this.duringCombat = duringCombat;
    }
}