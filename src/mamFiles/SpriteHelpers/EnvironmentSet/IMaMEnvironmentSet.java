package mamFiles.SpriteHelpers.EnvironmentSet;

import Rendering.IRenderableGameObject;

/**
 * Created by duckman on 16/06/2016.
 */
public interface IMaMEnvironmentSet
{
    IRenderableGameObject getObject(int objectIndex, int mipMapLevel);

    IRenderableGameObject getSky();
    IRenderableGameObject getGround();

    IRenderableGameObject getMapTile(int groundIndex);
    IRenderableGameObject getMapObject(int objectIndex);
}
