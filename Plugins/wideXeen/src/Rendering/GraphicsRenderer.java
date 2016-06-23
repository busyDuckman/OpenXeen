package Rendering;


import java.awt.*;

/**
 * Created by duckman on 22/06/2016.
 */
public class GraphicsRenderer extends Rendering.SimpleCanvas.GraphicsRenderer
{
    /**
     * @param scale The scale to which the scene will be drawn. Will be clamped to 0.1 - 100.
     */
    public GraphicsRenderer(double scale) {
        super(scale);
    }

    @Override
    public void refresh(Graphics g, long timeMS) {
        super.refresh(g, timeMS);
    }
}
