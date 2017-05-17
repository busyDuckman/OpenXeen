package mamFiles.SpriteHelpers.EnvironmentSet;

import Rendering.IRenderableGameObject;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;

import java.awt.*;

/**
 * Created by duckman on 15/06/2016.
 *
 * Wrangles the jungle of sprites relating to the current environment.
 */
public interface IMaMIndoorEnvironmentSet extends IMaMEnvironmentSet
{
    IRenderableGameObject getSideWall(int wallindex, Point mapPositionRelativeToLocation);
    IRenderableGameObject getFrontWall(int wallIndex, int mipMapLevel);

    IRenderableGameObject getMapWall(int wallIndex);
}
