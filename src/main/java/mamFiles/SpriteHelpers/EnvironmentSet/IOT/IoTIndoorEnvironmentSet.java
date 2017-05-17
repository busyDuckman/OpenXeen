package mamFiles.SpriteHelpers.EnvironmentSet.IOT;

import Rendering.IRenderableGameObject;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;

import java.awt.*;

public class IoTIndoorEnvironmentSet extends  IoTEnvironmentSet implements IMaMIndoorEnvironmentSet
{
    public IoTIndoorEnvironmentSet()
    {
        super();
    }

    @Override
    public IRenderableGameObject getSideWall(int wallindex, Point mapPositionRelativeToLocation) {
        return null;
    }

    @Override
    public IRenderableGameObject getFrontWall(int wallIndex, int mipMapLevel) {
        return null;
    }

    @Override
    public IRenderableGameObject getMapWall(int wallIndex) {
        return null;
    }
}
