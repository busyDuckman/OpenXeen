package mamFiles.IOT;

import Game.Map.MaMWorld;
import Toolbox.BinaryHelpers;
import Toolbox.FileHelpers;
import mamFiles.*;
import mamFiles.WOX.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by duckman on 9/07/2016.
 */
public class IoTccFileReader extends MaMCCFileReader
{
    protected IoTccFileReader(String fileName) {
        super(fileName);
    }

    public static IoTccFileReader open(String filePath)
    {
        //validate params and get file info
        if(!FileHelpers.fileExists(filePath))
        {
            return null;
        }
        Path path = Paths.get(filePath);
        String name = path.getFileName().toString();

        //create cc file reader.
        IoTccFileReader ccFile = new IoTccFileReader(name);

        ccFile.parseTocAndLoadFiles();

        return ccFile;
    }

    @Override
    protected byte[] extractFileFromRawCCData(byte[] data) throws CCFileFormatException {
        int size = BinaryHelpers.BYTES2INT_msb(data[2], data[3]);

        if(data[0] == data[1])
        {
            // Gets me every time, copyOfRange uses an end index, not a length
            // But the crazy does not stop... the index is an exclusive value.
            // So the code below is correct. ffs java.
            byte[] compressedData = Arrays.copyOfRange(data, 4, data.length);
            byte[] uData = CompressionLZHuf.rwf_lzhuf_decompress(compressedData, size, data[0]);
            //TODO: uData.length == size
            return uData;
        }
        //some uncompressed files exist
        return data;
    }

    @Override
    protected MaMSprite decodeSprite(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new IoTSpriteFile(name, key, data, pal);
    }

    @Override
    protected MaMPallet decodePallet(String name, String key, byte[] data) throws CCFileFormatException {
        return new WOXPallet(name, key, data);
    }

    @Override
    protected MaMSurface decodeSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new WOXSurface(name, key, data, pal);
    }

    @Override
    protected MaMThing decodeThing(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        return new WoXThing(name, key, data, pal);
    }

    @Override
    protected MaMMazeFile decodeMapFile(String name, String key, byte[] data, MaMWorld world, int mazeID) throws CCFileFormatException {
        return new WOXMazeFile(mazeID, key, world);
    }



    @Override
    public MaMPallet getPalletForFile(int id) throws CCFileFormatException {
        //ignoring id for now, so far this pallet works for all sprites
        try
        {
            return getPallet("MM3.PAL");
        }
        catch (Exception ex)
        {
            if(!inForceDiscoveryMode())
            {
                System.out.println("Problem getting pallate, using standard.");
            }
            return MaMPallet.getDefaultMaMPallate();
        }
    }

    @Override
    public String suggestProxyFileName() {
        return name + ".ZIP";
    }

    @Override
    public boolean saveProxy(String path) {
        return false;
    }
}
