package mamFiles.WOX;

import mamFiles.MaMPallet;

/**
 * Created by duckman on 10/05/2016.
 */
public class PalletWOX extends MaMPallet
{
    public PalletWOX(String name, String key, byte[] data)
    {
        super(name, key, data, 256, 2);
    }
}
