package mamFiles.IOT;

import mamFiles.CCFileFormatException;
import mamFiles.MaMPallet;
import mamFiles.WOX.WOXSpriteFile;

/**
 * Created by duckman on 16/07/2016.
 *
 * IoT sprite is related to WoXSprite
 */
public class IoTSpriteFile extends WOXSpriteFile
{
    public IoTSpriteFile(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        super(name, key, data, pal);
    }
}
