package mamFiles.SpriteHelpers.EnvironmentSet;

import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMSurface;

/**
 * Created by duckman on 16/06/2016.
 */
public interface IMaMEnvironmentSet
{
    IRenderableGameObject getObject(int objectIndex, int mipMapLevel);

    IRenderableGameObject getSky();
    IRenderableGameObject getGround();

    MaMSurface getSurface(int surfaceIndex) throws CCFileFormatException;

    IRenderableGameObject getMapTile(int groundIndex);
    IRenderableGameObject getMapObject(int objectIndex);
}
