package Toolbox;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static Toolbox.BinaryHelpers.*;

/**
 * Created by duckman on 13/05/2016.
 */
public class ImageHelpers
{
    public static BufferedImage cloneToARGBImage(BufferedImage img)
    {
        BufferedImage imgARGB = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        try
        {
            int[] data = img.getRGB(0,0, img.getWidth(), img.getHeight(),  null, 0, img.getWidth());
            imgARGB.setRGB(0,0, img.getWidth(), img.getHeight(),  data, 0, img.getWidth());
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            // A (slower) backup method
            Graphics g = imgARGB.getGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
        }
        return imgARGB;
    }

    public static BufferedImage RGB2Image(byte[] data, int width, int height)
    {
        BufferedImage imgARGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                int pos = (x+(y*width))*3;
                Color c = new Color(INT(data[pos]), INT(data[pos+1]), INT(data[pos+2]));
                imgARGB.setRGB(x, y, c.getRGB());
            }
        }

        /*
        int[] pixels = new int[(data.length * 3) * 4];
        for (int i = 0; i < data.length; i += 3) {
            pixels[i] = 0xFF000000 | BYTES2INT_lsb(data[i], data[i+1], data[i+2]);
        }
        imgARGB.setRGB(0,0, width, height,  pixels, 0, width);
        */
        return imgARGB;
    }

    public static BufferedImage RGBA2Image(int[] data, Dimension size) {
        return RGBA2Image(data, size.width, size.height);
    }

    public static BufferedImage RGBA2Image(int[] data, int width, int height)
    {
        if(data.length < (width* height))
        {
            return null;
        }
        BufferedImage imgARGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //final WritableRaster wr = (WritableRaster) imgARGB.getData();
        //wr.setPixels(0, 0, width, height, data);

        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                int pos = (x+(y*width));
                imgARGB.setRGB(x, y, data[pos]);
            }
        }

        return imgARGB;
    }

    public static BufferedImage RGBA2Image(byte[] data, int width, int height) {
        BufferedImage imgARGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                int pos = (x+(y*width))*4;
                //rgba constructor given argb data
                Color c = new Color(INT(data[pos+1]), INT(data[pos+2]), INT(data[pos+3]), INT(data[pos]));
                imgARGB.setRGB(x, y, c.getRGB());
            }
        }
        return imgARGB;
    }

    public static int[] image2RGBA(BufferedImage image)
    {
        int[] data = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0,0,image.getWidth(), image.getHeight(), data, 0, image.getWidth());
        return data;
    }

    public static BufferedImage[] copyOf(BufferedImage[] array)
    {
        BufferedImage[] copy = new BufferedImage[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = copyOf(array[i]);
        }
        return copy;
    }

    private static BufferedImage copyOf(BufferedImage image) {
        //ffs java
        ColorModel cm = image.getColorModel();
        boolean pm = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, pm, null);

    }

    /**
     * Evey alpha value in the image as an array.
     */
    public static byte[] getAlphaChannel(BufferedImage image)
    {
        int[] data = image2RGBA(image);
        byte[] alphachannel = new byte[image.getWidth()*image.getHeight()];
        for (int i = 0; i < data.length; i++) {
            //alphachannel[i] = (byte)(data[i] & 0xff);
            Color c = new Color(data[i], true);
            alphachannel[i] = (byte)(c.getAlpha() & 0xff);
        }
        return alphachannel;
    }

    /**
     * Evey alpha value in the image as an array.
     */
    public static BufferedImage applyAlphaChannel(BufferedImage image, byte[] alpha)
    {
        int[] data = image2RGBA(image);
        for (int i = 0; i < data.length; i++) {
            //TODO: I am not proud of this
            Color c = new Color(data[i], true);
            c = new Color(c.getRed(), c.getGreen(), c.getBlue(), BinaryHelpers.INT(alpha[i]));
            data[i] = c.getRGB();
        }
        return RGBA2Image(data, image.getWidth(), image.getHeight());
    }



    /**
     * Some code ripped from the net
     * http://stackoverflow.com/questions/32259121/animated-gif-frames-to-array-of-bufferedimages
     */
    public static BufferedImage[] getGifFrames(String path) throws IOException
    {
        File gif = new File(path);
        ArrayList<BufferedImage> frames = new ArrayList<>();
        BufferedImage master = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);

        ImageReader ir = new GIFImageReader(new GIFImageReaderSpi());
        ir.setInput(ImageIO.createImageInputStream(gif));
        for (int i = 0; i < ir.getNumImages(true); i++) {
            frames.add(new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB));
            master.getGraphics().drawImage(ir.read(i), 0, 0, null);
            frames.get(i).setData(master.getData());
        }
        return ArrayHelpers.toBufferedImageArray(frames);
    }

    public static boolean saveAsGif(String path, BufferedImage[] frames, int frameDelayMS, boolean loop)
    {
        try
        {
            // create a new BufferedOutputStream with the last argument
            ImageOutputStream output = new FileImageOutputStream(new File(path));

            // create a gif sequence with the type of the first image, 1 second
            // between frames, which loops continuously
            GifSequenceWriter writer = new GifSequenceWriter(output, frames[0].getType(), frameDelayMS, loop);

            // write out the first image to our sequence...
            for (BufferedImage frame : frames) {
                writer.writeToSequence(frame);
            }

            //done
            writer.close();
            output.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static BufferedImage joinHorizontally(BufferedImage[] images) {
        //find width and heaight
        int width=0, height=0;
        int type = BufferedImage.TYPE_INT_RGB;
        for(BufferedImage image : images){
            width += image.getWidth();
            height = Math.max(height, image.getHeight());
            type = image.getType();
        }

        //create a new result image
        BufferedImage result = new BufferedImage(width, height, type);
        Graphics g = result.getGraphics();

        //draw all imagesonto the new graphics context
        int x=0;
        for(int i=0; i<images.length; i++)
        {
            BufferedImage image = images[i];
            g.drawImage(image, x, 0, null);
            x += image.getWidth();
        }

        //free the graphics
        g.dispose();

        //return the image
        return result;
    }

    public static BufferedImage[] splitHorizontally(BufferedImage img, int width)
    {
        int numImages = img.getWidth() / width;
        BufferedImage[] images = new BufferedImage[numImages];

        for(int i=0; i<numImages; i++)
        {
            images[i] = img.getSubimage(width * i, 0, width, img.getHeight());
        }

        return images;
    }

    public enum AlphaTransforms
    {
        SET_MAX {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(Math.min(alpha, level));
            }
        },
        SET_MIN {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(Math.max(alpha, level));
            }
        },
        SET_FOR_ALL {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(level);
            }
        },
        ADD {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(alpha + level);
            }
        },
        SUB {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(alpha - level);
            }
        },
        DIVIDE {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(alpha / level);
            }
        },
        MULTIPLY {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(alpha * level);
            }
        },
        ADD_NOISE {
            @Override
            public int applyTransform(int alpha, int level) {
                return clamp(alpha + rand.nextInt(level/2) - level);
            }
        },
        NO_OPERATION {
        @Override
        public int applyTransform(int alpha, int level) {
            return clamp(alpha);
        }
    };

        public abstract int applyTransform(int alpha, int level);

        private static int clamp(int alpha) {return Math.max(0, Math.min(255, alpha));}
        private static Random rand = new Random(9001);
    }

    /**
     * Alter the alpha values of an image in several useful ways.
     * @param level A parameter to the transform operation.
     * @param transform The transform to perform.
     * @param nonZeroAlphaOnly If true, then only visible pixels are altered.
     */
    public static BufferedImage applyAlphaTransform(BufferedImage image,
                                                    int level,
                                                    AlphaTransforms transform,
                                                    boolean nonZeroAlphaOnly) {
        int[] data = image2RGBA(image);
        for (int i = 0; i < data.length; i++) {
            //TODO: I am not proud of this
            Color c = new Color(data[i], true);
            if(!(nonZeroAlphaOnly && (c.getAlpha() == 0)))
            {
                c = new Color(c.getRed(), c.getGreen(), c.getBlue(), transform.applyTransform(c.getAlpha(), level));
                data[i] = c.getRGB();
            }
        }
        return RGBA2Image(data, image.getWidth(), image.getHeight());
    }
}
