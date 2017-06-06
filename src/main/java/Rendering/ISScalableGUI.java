package Rendering;

import java.awt.*;

/**
 * Created by duckman on 3/06/17.
 *
 * A control that can be resized by a constant factor.
 */
public interface ISScalableGUI
{
    double getScale();
    void setScale(double scale);

    /**
     * Use this to allow the control to scale relative to a parent container.
     * Otherwise the original size will be lost to accumulated arithmetic errors.
     */
    Rectangle getUnscaledBounds();

    /**
     * Use this to allow the control to scale relative to a parent container.
     * Otherwise the original size will be lost to accumulated arithmetic errors.
     */
    void setUnscaledBounds(Rectangle r);

    default Rectangle getScaledBounds() {
        Rectangle r = getUnscaledBounds();
        double scale = getScale();
        int x = (int)(r.x * scale);
        int y = (int)(r.y * scale);
        int w = (int)(r.width * scale);
        int h = (int)(r.height * scale);

        return new Rectangle(x, y, w, h);
    }

}
