package GameMechanics.Adventurers;

import GameMechanics.Stat;
import org.joda.time.DateTime;

/**
 * Created by duckman on 10/06/2016.
 */
public interface IStatModifier
{
    DateTime getExpireTime();
    String getName();

    Stat applyTo(Stat stat);
    boolean affects(Stat stat);
}
