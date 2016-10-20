package Game;

import Game.Map.MaMWorld;

/**
 * Created by duckman on 4/09/2016.
 */
public class EntityScript implements IScript<IGameEntity>
{
    String script;

    @Override
    public String getScript() {
        return script;
    }

    @Override
    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public void run(MaMWorld world, IGameEntity owner) {
    }
}
