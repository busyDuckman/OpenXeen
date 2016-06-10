package GameMechanics.Adventurers;

/**
 * Created by duckman on 1/06/2016.
 */
public interface ICharCondition extends IStatModifier
{
    //drunk, poison, asleep, dead, etc
    int getBasePriceToCure();
}
