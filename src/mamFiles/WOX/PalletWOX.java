package mamFiles.WOX;

import mamFiles.MaMPallet;

/**
 * Created by duckman on 10/05/2016.
 */
public class PalletWOX extends MaMPallet
{
    public PalletWOX(String name, byte[] data)
    {
        super(name, data, 256, 2);
    }
}
