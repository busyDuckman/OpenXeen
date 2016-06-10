package Toolbox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by duckman on 30/05/2016.
 *
 *  ImageHelpers.RGBArrayParams imgBuf = ImageHelpers.RGBArrayFromImage(image);
 *  for(int y=0; y<imgBuf.height; y++)
 *  {
 *     for(int x=0; x<imgBuf.width; x++)
 *     {
 *        int pixel = imgBuf.data[imgBuf.lut[y]+x];  //ALWAYS A BufferedImage.TYPE_INT_ARGB pixel
 *     }
 *   }
 */
public class IntegerImage
{
    public int[] data;
    public int scanSize;
    public int[] lut;
    public int width;
    public int height;
    public int pixelFormat;

    private IntegerImage() {
    }

    private IntegerImage(int[] data, int width, int height, int scanSize, int[] lut, int pixelFormat) {
            this.data = data;
            this.height = height;
            this.lut = lut;
            this.scanSize = scanSize;
            this.width = width;
            this.pixelFormat = pixelFormat;
        }

    public IntegerImage(int width, int height, Color fillColor)
    {
        this.scanSize = getSystemScanSizeFor(width);
        this.height = height;
        this.width = width;
        this.data = new int[this.scanSize * height];
        int fill = fillColor.getRGB();
        Arrays.setAll(this.data, I -> fill);
        this.width = width;
        this.pixelFormat = BufferedImage.TYPE_INT_ARGB;
        createLut();
    }

    public static IntegerImage getEmpty() {
        return new IntegerImage(new int[0], 0, 0, 0,  new int[0], BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage toBufferedImage()
    {
        BufferedImage bi = new BufferedImage(width, height, pixelFormat); //BufferedImage.TYPE_INT_ARGB
        bi.setRGB(0, 0, width, height, data, 0, scanSize);
        return bi;
    }

    public List<Integer> getPixels(int xPos, int yPos, int sampleWidth, int sampleHeight)
    {
        List<Integer> pixels = new ArrayList<Integer>();
        for(int y=0; y<sampleHeight; y++)
        {
            for(int x=0; x<sampleWidth; x++)
            {
                int _x = x + xPos;
                int _y = y + yPos;

                //is the pixel location valid
                if((_x>=0)&&(_x<width)&&(_y>=0)&&(_y<height))
                {
                    pixels.add(data[lut[_y]+_x]);
                }
            }
        }

        return pixels;
    }

    /**
     * Returns some usual ways of accessing image data/
     * @param image
     * @return
     */
    public static IntegerImage fromImage(BufferedImage image)
    {
        if(image == null)
        {
            return IntegerImage.getEmpty();
        }

        IntegerImage params = new IntegerImage();
        params.scanSize = getSystemScanSizeFor(image.getWidth());
        params.data = new int[params.scanSize * image.getHeight()];
        image.getRGB(0,0,image.getWidth(), image.getHeight(), params.data, 0, params.scanSize);
        params.lut = new int[image.getHeight()];
        params.width = image.getWidth();
        params.height = image.getHeight();
        params.pixelFormat = image.getType();

        if(params.scanSize < params.width)
        {
            params.scanSize = params.width;
        }
        params.createLut();

        return params;
    }

    private void createLut() {
        lut = new int[height];
        for(int i=0; i<lut.length; i++)
        {
            lut[i] = i * scanSize;
        }
    }

    private static int getSystemScanSizeFor(int width)
    {
        int segmentAlignment = 64;
        int rem = (width % segmentAlignment);
        if(rem == 0)
        {
            return width;
        }
        else
        {
            return width + (segmentAlignment - rem);
        }
    }

    public final void set(int x, int y, int pixel) {
        if( (x < width) && (x >= 0) && (y < height) && (y >=0))
        {
            data[lut[y]+x] = pixel;
        }
    }
}
