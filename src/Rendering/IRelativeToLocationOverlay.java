package Rendering;

import Toolbox.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 3/07/2016.
 *
 * This handels things that are flat painted over the whole scene, but respond to the
 * Currently that's the surfaces. But this may come to include the sky.
 *
 * Note: No 'rendering position' is returned. The setup is to return a
 * IRenderableGameObject that is large enough to encompass the entire
 * area being layered over. This is wasteful in memory, but should
 * make game modding sane.
 */
public interface IRelativeToLocationOverlay extends IRenderableGameObject
{
    /**
     * Returns a overlay of this surface on the ground/sky.
     * @param mapPosRelative The relative map position of the tile to be filled with this surface.
     *                       NB: null returns full ground over.
     * @param viewDir The direction the viewer is looking. This allows a directional object to
     *                show the correct side to the viewport.
     */
    IRenderableGameObject getSurfaceOverlay(Point mapPosRelative, Direction viewDir);

    @Override
    default BufferedImage getImage(int frame) {
        return getSurfaceOverlay(new Point(0,0), Direction.UP).getImage(frame);
    }
}
