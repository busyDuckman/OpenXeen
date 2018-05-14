package Rendering.JSONRender;

import Game.IMaMGame;
import Rendering.IMaMRenderer;

/**
 * Implements a system for rendering the scene to a json file that holds scene graph construction.
 * A browser could display this scene via javascript / webGL
 *
 */
public class JSONRender implements IMaMRenderer<StringBuilder>
{

    //------------------------------------------------------------------------------------------------------------------
    // Instance data
    //------------------------------------------------------------------------------------------------------------------
    IMaMGame game;
    protected double scale = 1;

    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------

    /**
     * @param scale The scale to which the scene will be drawn. Will be clamped to 0.1 - 100.
     */
    public JSONRender(double scale) {
        setScale(scale);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setGame(IMaMGame game) {
        this.game = game;
    }

    @Override
    public IMaMGame getGame() {
        return game;
    }

    public double getScale() {
        return scale;
    }

    /**
     * @param scale The scale to which the scene will be drawn. Will be clamped to 0.1 - 100.
     */
    public void setScale(double scale) {
        this.scale = Math.max(0.1, Math.min(scale, 100));
    }

    //------------------------------------------------------------------------------------------------------------------
    // Rendering
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void refresh(StringBuilder g, long timeMS) {

    }
}
