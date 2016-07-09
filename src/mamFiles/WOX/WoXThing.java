package mamFiles.WOX;

import mamFiles.*;

/**
 * Created by duckman on 4/07/2016.
 */
public class WoXThing extends MaMThing
{
    public WoXThing(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        this(new WOXSpriteFile(name, MAMFile.generateUniqueKey(key), data, pal), key);
    }

    public WoXThing(MaMSprite mamStyleSprite, String key) throws CCFileFormatException {
        super(mamStyleSprite, key);
    }
}
