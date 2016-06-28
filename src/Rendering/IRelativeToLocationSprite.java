package Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 19/05/2016.
 */
public interface IRelativeToLocationSprite extends IRenderableGameObject
{
    /**
     * Returns a overlay of this surface on the ground.
     * @param mapPosRelative The relative map position of the tile to be filled with this surface.
     *                       NB: null returns full ground over.
     */
    IRenderableGameObject getSurfaceOverlay(Point mapPosRelative);

    @Override
    default BufferedImage getImage(int frame) {
        return getSurfaceOverlay(new Point(0,0)).getImage(frame);
    }
}
