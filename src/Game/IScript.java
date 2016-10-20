package Game;

import Game.Map.MaMWorld;

/**
 * Created by duckman on 4/09/2016.
 */
public interface IScript<T>
{
    String getScript();
    void setScript(String script);
    void run(MaMWorld world, T owner);
}
