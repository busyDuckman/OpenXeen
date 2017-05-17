package mamFiles.SpriteHelpers.EnvironmentSet.IOT;

import Rendering.IRenderableGameObject;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;

public class IoTOutdoorEnvironmentSet extends IoTEnvironmentSet implements IMaMOutdoorEnvironmentSet{
    public IoTOutdoorEnvironmentSet() {
        super();
    }

    @Override
    public IRenderableGameObject getEnviron(int environIndex, int mipMapLevel) {
        return null;
    }

    @Override
    public IRenderableGameObject getMapEnviron(int environIndex) {
        return null;
    }
}
