package GameMechanics.Adventurers;

import GameMechanics.Stat;
import GameMechanics.Stats;
import org.joda.time.DateTime;

/**
 * Created by duckman on 10/06/2016.
 */
public interface IStatModifier
{
    DateTime getExpireTime();
    String getName();

    <T extends Stats> Stat<T> applyTo(Stat<T> stat);
}
