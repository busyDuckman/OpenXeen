package mamFiles.WOX;

import mamFiles.CCFileFormatException;
import mamFiles.MAMFile;
import mamFiles.MaMPallet;
import mamFiles.MaMSurface;

/**
 * Created by duckman on 19/05/2016.
 */
public class WOXSurface extends MaMSurface
{
    public WOXSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        //super(new WOXSpriteFile(name, key, data, pal), MAMFile.generateKeyFromJoin();
        super(new WOXSpriteFile(name, MAMFile.generateUniqueKey(key), data, pal), key);
    }
}
