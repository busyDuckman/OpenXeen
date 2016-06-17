package mamFiles.SpriteHelpers.EnvironmentSet;

import Rendering.IRenderableGameObject;

/**
 * Created by duckman on 16/06/2016.
 */
public interface IMaMOutdoorEnvironmentSet extends IMaMEnvironmentSet
{
    IRenderableGameObject getEnviron(int environIndex, int mipMapLevel);

    IRenderableGameObject getMapEnviron(int environIndex);
}
