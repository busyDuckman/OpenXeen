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
        //super(new SpriteFileWOX(name, key, data, pal), MAMFile.generateKeyFromJoin();
        super(new SpriteFileWOX(name, MAMFile.generateUniqueKey(key), data, pal), key);
    }
}
