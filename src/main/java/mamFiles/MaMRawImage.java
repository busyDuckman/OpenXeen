package mamFiles;

import Rendering.AnimationSettings;
import Toolbox.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by duckman on 9/06/2016.
 *
 * When an image is just a dump of bytes (this is old school)
 */
public class MaMRawImage extends MAMFile implements Rendering.IMaMSprite
{
    protected BufferedImage image;
    static Dimension[] knownSizes = {new Dimension(320,200),
                                     new Dimension(320,240)};

    protected MaMPallet pallet;

    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public MaMRawImage(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException
    {
        super(name, key);
        Dimension size = Arrays.stream(knownSizes)
                            .filter(S -> (S.width * S.height) == data.length)
                            .findFirst()
                            .orElse(null);

        if(size != null)
        {
            pallet = pal;
            image = bytesToImage(size, data, pal);
        }
        else
        {
            throw new CCFileFormatException("Raw image not of known size.");
        }
    }

    public MaMRawImage(String name, String key, BufferedImage image) {
        super(name, key);
        this.image = image;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Raw image logic
    //------------------------------------------------------------------------------------------------------------------
    protected static BufferedImage bytesToImage(Dimension size, byte[] data, MaMPallet pal) throws CCFileFormatException {
        int[] pixels = new int[size.width*size.height];
        CCFileFormatException.assertFalse(data.length < pixels.length, "bytesToImage(): data.length < pixels.length" );

        for(int i=0; i<pixels.length; i++)
        {
            pixels[i] = pal.colors[BinaryHelpers.INT(data[i])].getRGB();
        }

        return ImageHelpers.RGBA2Image(pixels, size);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public MaMPallet getPallet() {
        return pallet;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public BufferedImage[] getRenderedFrames() {
        return new BufferedImage[] {image};
    }

    @Override
    public BufferedImage getImage(int frame) {
        return image;
    }

    @Override
    public AnimationSettings getAnimationSettings() {
        return null;
    }

    @Override
    public String suggestProxyFileName() {
        return name + ".png";
    }

    @Override
    public boolean saveProxy(String path) throws CCFileFormatException {
        CCFileFormatException.assertFalse(image == null, "MaMRawImage::saveProxy() image == null");
        CCFileFormatException.assertFalse(getWidth() <= 0, "MaMRawImage::saveProxy() getWidth() <= 0");
        CCFileFormatException.assertFalse(getHeight() <= 0, "MaMRawImage::saveProxy() getHeight() <= 0");
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static MaMRawImage fromPNGFile(String path)
    {
        try
        {
            if(FileHelpers.fileExists(path))
            {
                BufferedImage image = ImageIO.read(new File(path));

                //done
                return new MaMRawImage(FileHelpers.getFileNameTillFirstDot(path),
                                        MAMFile.generateKeyFromPath(path),
                                        image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
