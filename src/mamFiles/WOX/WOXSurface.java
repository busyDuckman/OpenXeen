package mamFiles.WOX;

import mamFiles.CCFileFormatException;
import mamFiles.MaMPallet;
import mamFiles.MaMSurface;

/**
 * Created by duckman on 19/05/2016.
 */
public class WOXSurface extends MaMSurface
{
    public WOXSurface(String name, byte[] data, MaMPallet pal) throws CCFileFormatException {
        super(new SpriteFileWOX(name, data, pal));
    }
}
