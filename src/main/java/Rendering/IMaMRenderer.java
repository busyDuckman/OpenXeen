package Rendering;

import Game.IMaMGame;

/**
 * Created by duckman on 15/05/2016.
 */
public interface IMaMRenderer<NATIVE_CANVAS>
{
    void setGame(IMaMGame game);
    IMaMGame getGame();
    void refresh(NATIVE_CANVAS g, long timeMS);
}
