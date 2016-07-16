package mamFiles.WOX;

import mamFiles.MaMPallet;

/**
 * Created by duckman on 10/05/2016.
 *
 * All this class does is establish the PAL is 256 colors with rgb components in the range 0-63
 */
public class WOXPallet extends MaMPallet
{
    public WOXPallet(String name, String key, byte[] data)
    {
        super(name, key, data, 256, 2);
    }
}
