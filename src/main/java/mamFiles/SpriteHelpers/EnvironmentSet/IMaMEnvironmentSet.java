package mamFiles.SpriteHelpers.EnvironmentSet;

import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMSurface;
import mamFiles.MaMThing;

/**
 * Created by duckman on 16/06/2016.
 */
public interface IMaMEnvironmentSet
{
    MaMThing getObject(int objectIndex);

    IRenderableGameObject getSky();
    IRenderableGameObject getGround();

    MaMSurface getSurface(int surfaceIndex) throws CCFileFormatException;

    IRenderableGameObject getMapTile(int groundIndex);
    IRenderableGameObject getMapObject(int objectIndex);
}
