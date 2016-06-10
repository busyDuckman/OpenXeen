package Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 19/05/2016.
 */
public interface IRelativeToLocationSprite extends IRenderableGameObject
{
    BufferedImage getImage(Point mapPosRelative, int frame);

    @Override
    default BufferedImage getImage(int frame) {
        return getImage(new Point(0,0), frame);
    }
}
