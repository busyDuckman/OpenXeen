package GameMechanics.Equipment;

/**
 * Created by duckman on 31/05/2016.
 */
public interface IInventoryItem
{
    String getName();
    int getValue();
    boolean isBroken();
    int getCurseLevel();
}
