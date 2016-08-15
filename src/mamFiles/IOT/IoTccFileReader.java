package mamFiles.IOT;

import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import Toolbox.BinaryHelpers;
import Toolbox.FileHelpers;
import mamFiles.*;
import mamFiles.WOX.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by duckman on 9/07/2016.
 */
public class IoTccFileReader extends MaMCCFileReader
{
    private Color[] mm3Pal = {
            fixMode13h(new Color(0x00, 0x00, 0x00)),
            fixMode13h(new Color(0x3F, 0x3F, 0x3F)),
            fixMode13h(new Color(0x3C, 0x3C, 0x3C)),
            fixMode13h(new Color(0x3A, 0x3A, 0x3A)),
            fixMode13h(new Color(0x38, 0x38, 0x38)),
            fixMode13h(new Color(0x35, 0x35, 0x35)),
            fixMode13h(new Color(0x33, 0x33, 0x33)),
            fixMode13h(new Color(0x31, 0x31, 0x31)),
            fixMode13h(new Color(0x2F, 0x2F, 0x2F)),
            fixMode13h(new Color(0x2C, 0x2C, 0x2C)),
            fixMode13h(new Color(0x2A, 0x2A, 0x2A)),
            fixMode13h(new Color(0x28, 0x28, 0x28)),
            fixMode13h(new Color(0x25, 0x25, 0x25)),
            fixMode13h(new Color(0x23, 0x23, 0x23)),
            fixMode13h(new Color(0x21, 0x21, 0x21)),
            fixMode13h(new Color(0x1F, 0x1F, 0x1F)),
            fixMode13h(new Color(0x1D, 0x1D, 0x1D)),
            fixMode13h(new Color(0x1B, 0x1B, 0x1B)),
            fixMode13h(new Color(0x19, 0x19, 0x19)),
            fixMode13h(new Color(0x17, 0x17, 0x17)),
            fixMode13h(new Color(0x15, 0x15, 0x15)),
            fixMode13h(new Color(0x13, 0x13, 0x13)),
            fixMode13h(new Color(0x11, 0x11, 0x11)),
            fixMode13h(new Color(0x0F, 0x0F, 0x0F)),
            fixMode13h(new Color(0x0D, 0x0D, 0x0D)),
            fixMode13h(new Color(0x0B, 0x0B, 0x0B)),
            fixMode13h(new Color(0x09, 0x09, 0x09)),
            fixMode13h(new Color(0x07, 0x07, 0x07)),
            fixMode13h(new Color(0x05, 0x05, 0x05)),
            fixMode13h(new Color(0x03, 0x03, 0x03)),
            fixMode13h(new Color(0x01, 0x01, 0x01)),
            fixMode13h(new Color(0x00, 0x00, 0x00)),
            fixMode13h(new Color(0x3F, 0x3A, 0x3A)),
            fixMode13h(new Color(0x3E, 0x35, 0x35)),
            fixMode13h(new Color(0x3D, 0x30, 0x30)),
            fixMode13h(new Color(0x3C, 0x2C, 0x2C)),
            fixMode13h(new Color(0x3B, 0x28, 0x28)),
            fixMode13h(new Color(0x3A, 0x23, 0x23)),
            fixMode13h(new Color(0x39, 0x1F, 0x1F)),
            fixMode13h(new Color(0x39, 0x1B, 0x1B)),
            fixMode13h(new Color(0x38, 0x17, 0x17)),
            fixMode13h(new Color(0x37, 0x13, 0x13)),
            fixMode13h(new Color(0x36, 0x10, 0x10)),
            fixMode13h(new Color(0x35, 0x0C, 0x0C)),
            fixMode13h(new Color(0x34, 0x08, 0x08)),
            fixMode13h(new Color(0x33, 0x05, 0x05)),
            fixMode13h(new Color(0x32, 0x02, 0x02)),
            fixMode13h(new Color(0x32, 0x00, 0x00)),
            fixMode13h(new Color(0x2E, 0x00, 0x00)),
            fixMode13h(new Color(0x2A, 0x00, 0x00)),
            fixMode13h(new Color(0x26, 0x00, 0x00)),
            fixMode13h(new Color(0x21, 0x00, 0x00)),
            fixMode13h(new Color(0x1D, 0x00, 0x00)),
            fixMode13h(new Color(0x19, 0x00, 0x00)),
            fixMode13h(new Color(0x15, 0x00, 0x00)),
            fixMode13h(new Color(0x11, 0x00, 0x00)),
            fixMode13h(new Color(0x0D, 0x00, 0x00)),
            fixMode13h(new Color(0x3F, 0x1D, 0x00)),
            fixMode13h(new Color(0x37, 0x19, 0x00)),
            fixMode13h(new Color(0x30, 0x16, 0x00)),
            fixMode13h(new Color(0x28, 0x12, 0x00)),
            fixMode13h(new Color(0x21, 0x0F, 0x00)),
            fixMode13h(new Color(0x19, 0x0B, 0x00)),
            fixMode13h(new Color(0x12, 0x08, 0x00)),
            fixMode13h(new Color(0x3F, 0x3F, 0x36)),
            fixMode13h(new Color(0x3F, 0x3F, 0x2E)),
            fixMode13h(new Color(0x3E, 0x3F, 0x26)),
            fixMode13h(new Color(0x3E, 0x3F, 0x1E)),
            fixMode13h(new Color(0x3E, 0x3F, 0x16)),
            fixMode13h(new Color(0x3D, 0x3F, 0x0E)),
            fixMode13h(new Color(0x3D, 0x3F, 0x06)),
            fixMode13h(new Color(0x3B, 0x3D, 0x00)),
            fixMode13h(new Color(0x3B, 0x3B, 0x00)),
            fixMode13h(new Color(0x38, 0x37, 0x00)),
            fixMode13h(new Color(0x35, 0x33, 0x00)),
            fixMode13h(new Color(0x32, 0x2E, 0x00)),
            fixMode13h(new Color(0x2F, 0x2A, 0x00)),
            fixMode13h(new Color(0x2C, 0x26, 0x00)),
            fixMode13h(new Color(0x29, 0x22, 0x00)),
            fixMode13h(new Color(0x26, 0x1F, 0x00)),
            fixMode13h(new Color(0x22, 0x1A, 0x00)),
            fixMode13h(new Color(0x1E, 0x16, 0x00)),
            fixMode13h(new Color(0x1A, 0x12, 0x00)),
            fixMode13h(new Color(0x16, 0x0F, 0x00)),
            fixMode13h(new Color(0x12, 0x0B, 0x00)),
            fixMode13h(new Color(0x0E, 0x08, 0x00)),
            fixMode13h(new Color(0x0A, 0x05, 0x00)),
            fixMode13h(new Color(0x06, 0x03, 0x00)),
            fixMode13h(new Color(0x36, 0x3F, 0x16)),
            fixMode13h(new Color(0x31, 0x3B, 0x11)),
            fixMode13h(new Color(0x2D, 0x38, 0x0D)),
            fixMode13h(new Color(0x29, 0x34, 0x0A)),
            fixMode13h(new Color(0x25, 0x31, 0x06)),
            fixMode13h(new Color(0x21, 0x2D, 0x03)),
            fixMode13h(new Color(0x1D, 0x2A, 0x01)),
            fixMode13h(new Color(0x1A, 0x27, 0x00)),
            fixMode13h(new Color(0x15, 0x24, 0x00)),
            fixMode13h(new Color(0x13, 0x21, 0x00)),
            fixMode13h(new Color(0x12, 0x1F, 0x00)),
            fixMode13h(new Color(0x11, 0x1D, 0x00)),
            fixMode13h(new Color(0x10, 0x1B, 0x00)),
            fixMode13h(new Color(0x0E, 0x19, 0x00)),
            fixMode13h(new Color(0x0D, 0x17, 0x00)),
            fixMode13h(new Color(0x0C, 0x15, 0x00)),
            fixMode13h(new Color(0x0B, 0x13, 0x00)),
            fixMode13h(new Color(0x2F, 0x3E, 0x2F)),
            fixMode13h(new Color(0x27, 0x3C, 0x26)),
            fixMode13h(new Color(0x20, 0x3A, 0x1F)),
            fixMode13h(new Color(0x17, 0x38, 0x17)),
            fixMode13h(new Color(0x10, 0x37, 0x10)),
            fixMode13h(new Color(0x0B, 0x35, 0x0A)),
            fixMode13h(new Color(0x0A, 0x32, 0x09)),
            fixMode13h(new Color(0x08, 0x2F, 0x08)),
            fixMode13h(new Color(0x07, 0x2D, 0x07)),
            fixMode13h(new Color(0x06, 0x2A, 0x06)),
            fixMode13h(new Color(0x05, 0x27, 0x04)),
            fixMode13h(new Color(0x04, 0x24, 0x04)),
            fixMode13h(new Color(0x03, 0x22, 0x03)),
            fixMode13h(new Color(0x02, 0x1F, 0x02)),
            fixMode13h(new Color(0x02, 0x1C, 0x02)),
            fixMode13h(new Color(0x01, 0x1A, 0x01)),
            fixMode13h(new Color(0x01, 0x17, 0x01)),
            fixMode13h(new Color(0x01, 0x14, 0x00)),
            fixMode13h(new Color(0x00, 0x11, 0x00)),
            fixMode13h(new Color(0x00, 0x0F, 0x00)),
            fixMode13h(new Color(0x00, 0x0C, 0x00)),
            fixMode13h(new Color(0x00, 0x09, 0x00)),
            fixMode13h(new Color(0x00, 0x07, 0x00)),
            fixMode13h(new Color(0x3C, 0x3C, 0x3F)),
            fixMode13h(new Color(0x38, 0x38, 0x3F)),
            fixMode13h(new Color(0x33, 0x33, 0x3F)),
            fixMode13h(new Color(0x2F, 0x2F, 0x3F)),
            fixMode13h(new Color(0x2B, 0x2C, 0x3F)),
            fixMode13h(new Color(0x27, 0x28, 0x3F)),
            fixMode13h(new Color(0x23, 0x23, 0x3F)),
            fixMode13h(new Color(0x1F, 0x20, 0x3F)),
            fixMode13h(new Color(0x1B, 0x1C, 0x3F)),
            fixMode13h(new Color(0x17, 0x18, 0x3F)),
            fixMode13h(new Color(0x13, 0x14, 0x3F)),
            fixMode13h(new Color(0x0F, 0x10, 0x3F)),
            fixMode13h(new Color(0x0B, 0x0C, 0x3F)),
            fixMode13h(new Color(0x07, 0x08, 0x3F)),
            fixMode13h(new Color(0x03, 0x04, 0x3F)),
            fixMode13h(new Color(0x00, 0x01, 0x3F)),
            fixMode13h(new Color(0x00, 0x00, 0x3F)),
            fixMode13h(new Color(0x00, 0x00, 0x3B)),
            fixMode13h(new Color(0x00, 0x00, 0x37)),
            fixMode13h(new Color(0x00, 0x00, 0x33)),
            fixMode13h(new Color(0x00, 0x00, 0x2F)),
            fixMode13h(new Color(0x00, 0x00, 0x2B)),
            fixMode13h(new Color(0x00, 0x00, 0x27)),
            fixMode13h(new Color(0x00, 0x00, 0x24)),
            fixMode13h(new Color(0x00, 0x00, 0x20)),
            fixMode13h(new Color(0x00, 0x00, 0x1C)),
            fixMode13h(new Color(0x00, 0x00, 0x18)),
            fixMode13h(new Color(0x00, 0x00, 0x14)),
            fixMode13h(new Color(0x00, 0x00, 0x10)),
            fixMode13h(new Color(0x00, 0x00, 0x0C)),
            fixMode13h(new Color(0x00, 0x00, 0x08)),
            fixMode13h(new Color(0x00, 0x00, 0x05)),
            fixMode13h(new Color(0x3C, 0x36, 0x3F)),
            fixMode13h(new Color(0x39, 0x2E, 0x3F)),
            fixMode13h(new Color(0x36, 0x27, 0x3F)),
            fixMode13h(new Color(0x34, 0x1F, 0x3F)),
            fixMode13h(new Color(0x32, 0x17, 0x3F)),
            fixMode13h(new Color(0x2F, 0x10, 0x3F)),
            fixMode13h(new Color(0x2D, 0x08, 0x3F)),
            fixMode13h(new Color(0x2A, 0x00, 0x3F)),
            fixMode13h(new Color(0x26, 0x00, 0x39)),
            fixMode13h(new Color(0x20, 0x00, 0x32)),
            fixMode13h(new Color(0x1B, 0x00, 0x2B)),
            fixMode13h(new Color(0x15, 0x00, 0x23)),
            fixMode13h(new Color(0x0F, 0x00, 0x1B)),
            fixMode13h(new Color(0x0A, 0x00, 0x14)),
            fixMode13h(new Color(0x06, 0x00, 0x0C)),
            fixMode13h(new Color(0x02, 0x00, 0x05)),
            fixMode13h(new Color(0x33, 0x3F, 0x3F)),
            fixMode13h(new Color(0x2D, 0x3B, 0x3B)),
            fixMode13h(new Color(0x27, 0x38, 0x38)),
            fixMode13h(new Color(0x22, 0x35, 0x35)),
            fixMode13h(new Color(0x1D, 0x32, 0x32)),
            fixMode13h(new Color(0x19, 0x2F, 0x2F)),
            fixMode13h(new Color(0x14, 0x2B, 0x2B)),
            fixMode13h(new Color(0x11, 0x28, 0x28)),
            fixMode13h(new Color(0x0D, 0x24, 0x24)),
            fixMode13h(new Color(0x09, 0x1F, 0x1F)),
            fixMode13h(new Color(0x07, 0x1B, 0x1B)),
            fixMode13h(new Color(0x04, 0x17, 0x17)),
            fixMode13h(new Color(0x02, 0x13, 0x13)),
            fixMode13h(new Color(0x01, 0x0F, 0x0F)),
            fixMode13h(new Color(0x00, 0x0B, 0x0B)),
            fixMode13h(new Color(0x00, 0x07, 0x07)),
            fixMode13h(new Color(0x3A, 0x3C, 0x3E)),
            fixMode13h(new Color(0x36, 0x3A, 0x3D)),
            fixMode13h(new Color(0x31, 0x37, 0x3D)),
            fixMode13h(new Color(0x2D, 0x35, 0x3D)),
            fixMode13h(new Color(0x29, 0x33, 0x3C)),
            fixMode13h(new Color(0x25, 0x31, 0x3C)),
            fixMode13h(new Color(0x21, 0x30, 0x3C)),
            fixMode13h(new Color(0x1D, 0x2E, 0x3B)),
            fixMode13h(new Color(0x19, 0x2C, 0x3B)),
            fixMode13h(new Color(0x15, 0x2B, 0x3B)),
            fixMode13h(new Color(0x11, 0x29, 0x3A)),
            fixMode13h(new Color(0x0D, 0x28, 0x3A)),
            fixMode13h(new Color(0x0A, 0x26, 0x3A)),
            fixMode13h(new Color(0x06, 0x25, 0x39)),
            fixMode13h(new Color(0x02, 0x24, 0x39)),
            fixMode13h(new Color(0x01, 0x21, 0x36)),
            fixMode13h(new Color(0x01, 0x1F, 0x33)),
            fixMode13h(new Color(0x00, 0x1D, 0x30)),
            fixMode13h(new Color(0x00, 0x1B, 0x2D)),
            fixMode13h(new Color(0x00, 0x19, 0x2B)),
            fixMode13h(new Color(0x00, 0x17, 0x28)),
            fixMode13h(new Color(0x00, 0x15, 0x25)),
            fixMode13h(new Color(0x00, 0x14, 0x22)),
            fixMode13h(new Color(0x00, 0x12, 0x1F)),
            fixMode13h(new Color(0x00, 0x10, 0x1C)),
            fixMode13h(new Color(0x00, 0x0E, 0x18)),
            fixMode13h(new Color(0x00, 0x0C, 0x15)),
            fixMode13h(new Color(0x00, 0x0A, 0x12)),
            fixMode13h(new Color(0x00, 0x08, 0x0F)),
            fixMode13h(new Color(0x00, 0x06, 0x0C)),
            fixMode13h(new Color(0x00, 0x05, 0x09)),
            fixMode13h(new Color(0x00, 0x03, 0x06)),
            fixMode13h(new Color(0x3F, 0x3A, 0x37)),
            fixMode13h(new Color(0x3F, 0x37, 0x33)),
            fixMode13h(new Color(0x3F, 0x35, 0x30)),
            fixMode13h(new Color(0x3F, 0x33, 0x2C)),
            fixMode13h(new Color(0x3F, 0x31, 0x29)),
            fixMode13h(new Color(0x3F, 0x2F, 0x25)),
            fixMode13h(new Color(0x3F, 0x2D, 0x22)),
            fixMode13h(new Color(0x3F, 0x2B, 0x1F)),
            fixMode13h(new Color(0x3F, 0x29, 0x1B)),
            fixMode13h(new Color(0x3F, 0x27, 0x18)),
            fixMode13h(new Color(0x3C, 0x25, 0x17)),
            fixMode13h(new Color(0x3A, 0x24, 0x16)),
            fixMode13h(new Color(0x38, 0x22, 0x15)),
            fixMode13h(new Color(0x36, 0x21, 0x14)),
            fixMode13h(new Color(0x34, 0x20, 0x14)),
            fixMode13h(new Color(0x32, 0x1F, 0x13)),
            fixMode13h(new Color(0x2F, 0x1D, 0x11)),
            fixMode13h(new Color(0x2C, 0x1B, 0x10)),
            fixMode13h(new Color(0x29, 0x1A, 0x0E)),
            fixMode13h(new Color(0x26, 0x18, 0x0D)),
            fixMode13h(new Color(0x23, 0x16, 0x0C)),
            fixMode13h(new Color(0x20, 0x15, 0x0A)),
            fixMode13h(new Color(0x1D, 0x13, 0x09)),
            fixMode13h(new Color(0x1A, 0x11, 0x08)),
            fixMode13h(new Color(0x17, 0x0F, 0x07)),
            fixMode13h(new Color(0x14, 0x0D, 0x06)),
            fixMode13h(new Color(0x11, 0x0C, 0x05)),
            fixMode13h(new Color(0x0E, 0x0A, 0x03)),
            fixMode13h(new Color(0x0B, 0x08, 0x03)),
            fixMode13h(new Color(0x09, 0x06, 0x02)),
            fixMode13h(new Color(0x06, 0x04, 0x01)),
            fixMode13h(new Color(0x3F, 0x3F, 0x3F))

    } ;

    private static final Color fixMode13h(Color c) {
        return new Color(
                mode13hScale(c.getRed()),
                mode13hScale(c.getGreen()),
                mode13hScale(c.getBlue()));
    }

    /**
     * Scaling 0-63 to 0-255 is not a simple shift
     * ie 63 >>> 2 == 252
     * So I am reversing the number and using signed left shift >> to
     * repeat the last bit. Then reversing again.
     */
    private static int mode13hScale(int b)
    {
        int t = Integer.reverse(b) >> 2;
        t = Integer.reverse(t);
        return (t & 0xff);
    }

    private Color fixMSB(Color c) {
        return new Color(c.getBlue(), c.getGreen(), c.getRed());
    }

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
        return new WOXMazeFile(mazeID, key, (WoXWorld)world);
    }



    @Override
    public MaMPallet getPalletForFile(int id) throws CCFileFormatException {
        //ignoring id for now, so far this pallet works for all sprites
        try
        {
            return new MaMPallet("MM3PAL", "MM3PAL", mm3Pal);
            //return getPallet("MM3.PAL");
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
