package Rendering;

import Toolbox.Direction;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 19/05/2016.
 *
 * TODO: Next code cleanup, this should not implement IRenderableGameObject.
 *       Conceptually it delivers the IRenderableGameObject required.
 *       Implementing IRenderableGameObject, generates legacy code that will never be used.
 *
 */
public interface IRelativeToLocationSprite extends IRenderableGameObject
{
    /**
     * Returns "view" of this sprite.
     * The view includes perspective, but not scaling or positioning.
     * @param mapPosRelative The relative map position of the tile to be filled with this surface.
     * @param viewDir The direction the viewer is looking. This allows a directional object to
     *                show the correct side to the viewport.
     */
    IRenderableGameObject getView(@NotNull Point mapPosRelative, Direction viewDir);

    @Override
    default BufferedImage getImage(int frame) {
        return getView(new Point(0,0), Direction.UP).getImage(frame);
    }
}
