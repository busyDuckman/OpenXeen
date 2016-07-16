package mamFiles.SpriteHelpers.EnvironmentSet.IOT;

import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMSurface;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;

/**
 * Created by duckman on 16/07/2016.
 */
public abstract class IoTEnvironmentSet implements IMaMEnvironmentSet
{
    public IoTEnvironmentSet() {
    }

    @Override
    public IRenderableGameObject getObject(int objectIndex, int mipMapLevel) {
        return null;
    }

    @Override
    public IRenderableGameObject getSky() {
        return null;
    }

    @Override
    public IRenderableGameObject getGround() {
        return null;
    }

    @Override
    public MaMSurface getSurface(int surfaceIndex) throws CCFileFormatException {
        return null;
    }

    @Override
    public IRenderableGameObject getMapTile(int groundIndex) {
        return null;
    }

    @Override
    public IRenderableGameObject getMapObject(int objectIndex) {
        return null;
    }
}

