package Toolbox.xBR;

import Toolbox.ImageHelpers;
import Toolbox.IntegerImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Created by duckman on 29/05/2016.
 *
 * xBR is a good enough solution to upscaling sprites for now.
 * Ideally I would say the ffMPEG is a requirement and then call:
 *     ffmpeg -i 015.MON.png -vf xbr=4,scale=8000:596 015.MON.xBR.png
 * But it does not do transparency. So I am rolling my own implementation.
 *
 * Working from:
 *    The authors description
 *          http://web.archive.org/web/20140729022435/http://board.byuu.org/viewtopic.php?f=10&t=2248
 *    The Authors implementations:
 *
 *    A javascript implementation that is very readable
 *          https://github.com/carlosascari/2xBR-Filter/blob/master/xbr.js
 *    A c# implementation that produced some very good results
 *
 *    This is one of those homebrew algorithms that circulates via forum posts etc.
 *    So reference implementations, tuning info and doco is scarce.
 *    These sites give some Context:
 *          http://filthypants.blogspot.com.au/2012/03/xbr-vs-hqx-interpolation-filter.html
 *          https://ffmpeg.org/pipermail/ffmpeg-devel/2014-November/164865.html
 *          https://en.wikipedia.org/wiki/Image_scaling
 *
 */
public class ResizeXBR
{

    static int intAbs(double d)
    {
        return (int)Math.round(Math.abs(d));
    }

    static int intRound(double d)
    {
        return (int)Math.round(d);
    }

    static double dist(int colA, int colB)
    {
        return dist(new Color(colA, true), new Color(colB, true));
    }

    static double dist(Color colA, Color colB)
    {
        int r, g, b;
        double y, u, v;

        b = intAbs(colA.getBlue()  - colB.getBlue());
        g = intAbs(colA.getGreen() - colB.getGreen());
        r = intAbs(colA.getRed()   - colB.getRed());

        y = Math.abs(0.299*r + 0.587*g + 0.114*b);
        u = Math.abs(-0.169*r - 0.331*g + 0.500*b);
        v = Math.abs(0.500*r - 0.419*g - 0.081*b);

        //NB: I don't like this; I'm sure it's supposed to be a different formula
        return 48*y + 7*u + 6*v;
    }

    static int blend(int colA, int colB, double percentB)
    {
        return blend(new Color(colA, true), new Color(colB, true), percentB).getRGB();
    }

    static Color blend(Color colA, Color colB, double percentB)
    {
        double percentA = 1.0 - percentB;
        int r = intRound((percentB * colB.getRed()) + (percentA * colA.getRed()));
        int g = intRound((percentB * colB.getGreen()) + (percentA * colA.getGreen()));
        int b = intRound((percentB * colB.getBlue()) + (percentA * colA.getBlue()));
        int a = Math.min(colB.getAlpha(), colA.getAlpha());
        return new Color(r,g,b,a);
    }

    @FunctionalInterface
    interface getIndex {
        public int apply(int xPos, int yPos);
    }

    private static int arrayBound(int index, int arraySize)
    {
        return Math.max(0, Math.min(index, arraySize-1));
    }

    public static BufferedImage xBR(BufferedImage src, int scale)
    {
        int width = src.getWidth();
        int height = src.getHeight();

        // scaled
        int scaledWidth = width * scale;
        int scaledHeight = height * scale;
        int[] srcData = ImageHelpers.Image2RGBA(src);
        IntegerImage destData = new IntegerImage(width*scale, height*scale, Color.magenta);
        int[] kernel = new int[21];

        getIndex coord2index = (xPos, yPos) -> (arrayBound(yPos, height) * width) + arrayBound(xPos, width);

        //apply xBR kernel
        for (int x = 0; x < width; ++x)
        {
            for (int y = 0; y < height; ++y)
            {
                createKernel(srcData, kernel, coord2index, x, y);

                // chromatic distances between various points in the kernel
                double d_10_9  = dist(kernel[10], kernel[9]);
                double d_10_5  = dist(kernel[10], kernel[5]);
                double d_10_11 = dist(kernel[10], kernel[11]);
                double d_10_15 = dist(kernel[10], kernel[15]);
                double d_10_14 = dist(kernel[10], kernel[14]);
                double d_10_6  = dist(kernel[10], kernel[6]);
                double d_4_8   = dist(kernel[4], kernel[8]);
                double d_4_1   = dist(kernel[4], kernel[1]);
                double d_9_5   = dist(kernel[9], kernel[5]);
                double d_9_15  = dist(kernel[9], kernel[15]);
                double d_9_3   = dist(kernel[9], kernel[3]);
                double d_5_11  = dist(kernel[5], kernel[11]);
                double d_5_0   = dist(kernel[5], kernel[0]);
                double d_10_4  = dist(kernel[10], kernel[4]);
                double d_10_16 = dist(kernel[10], kernel[16]);
                double d_6_12  = dist(kernel[6], kernel[12]);
                double d_6_1   = dist(kernel[6], kernel[1]);
                double d_11_15 = dist(kernel[11], kernel[15]);
                double d_11_7  = dist(kernel[11], kernel[7]);
                double d_5_2   = dist(kernel[5], kernel[2]);
                double d_14_8  = dist(kernel[14], kernel[8]);
                double d_14_19 = dist(kernel[14], kernel[19]);
                double d_15_18 = dist(kernel[15], kernel[18]);
                double d_9_13  = dist(kernel[9], kernel[13]);
                double d_16_12 = dist(kernel[16], kernel[12]);
                double d_16_19 = dist(kernel[16], kernel[19]);
                double d_15_20 = dist(kernel[15], kernel[20]);
                double d_15_17 = dist(kernel[15], kernel[17]);

                // Top Left Edge Detection Rule
                double a1 = (d_10_14 + d_10_6 + d_4_8  + d_4_1 + (4 * d_9_5));
                double b1 = ( d_9_15 +  d_9_3 + d_5_11 + d_5_0 + (4 * d_10_4));
                if (a1 < b1)
                {
                    int new_pixel= (d_10_9 <= d_10_5) ? kernel[9] : kernel[5];
                    int blended_pixel = blend(new_pixel, kernel[10], 0.5);
                    destData.set(x * scale, y * scale, blended_pixel);
                }
                else
                {
                    destData.set(x * scale, y * scale, kernel[10]);
                }

                // Top Right Edge Detection Rule
                double a2 = (d_10_16 + d_10_4 + d_6_12 + d_6_1 + (4 * d_5_11));
                double b2 = (d_11_15 + d_11_7 +  d_9_5 + d_5_2 + (4 * d_10_6));
                if (a2 < b2)
                {
                    int new_pixel= (d_10_5 <= d_10_11) ? kernel[5] : kernel[11];
                    int blended_pixel = blend(new_pixel, kernel[10], 0.5);
                    destData.set(x * scale + 1, y * scale, blended_pixel);
                }
                else
                {
                    destData.set(x * scale + 1, y * scale, kernel[10]);
                }

                // Bottom Left Edge Detection Rule
                double a3 = (d_10_4 + d_10_16 +  d_14_8 + d_14_19 + (4 * d_9_15));
                double b3 = ( d_9_5 +  d_9_13 + d_11_15 + d_15_18 + (4 * d_10_14));
                if (a3 < b3)
                {
                    int new_pixel= (d_10_9 <= d_10_15) ? kernel[9] : kernel[15];
                    int blended_pixel = blend(new_pixel, kernel[10], .5);
                    destData.set(x * scale, y * scale + 1, blended_pixel);
                }
                else
                {
                    destData.set(x * scale, y * scale + 1, kernel[10]);
                }

                // Bottom Right Edge Detection Rule
                double a4 = (d_10_6 + d_10_14 + d_16_12 + d_16_19 + (4 * d_11_15));
                double b4 = (d_9_15 + d_15_20 + d_15_17 +  d_5_11 + (4 * d_10_16));
                if (a4 < b4)
                {
                    int new_pixel= (d_10_11 <= d_10_15) ? kernel[11] : kernel[15];
                    int blended_pixel = blend(new_pixel, kernel[10], .5);
                    destData.set(x * scale + 1, y * scale + 1, blended_pixel);
                }
                else
                {
                    destData.set(x * scale + 1, y * scale + 1, kernel[10]);
                }
            }
        }

        return destData.toBufferedImage();
    }

    protected static void createKernel(int[] srcData, int[] kernel, getIndex coord2index, int x, int y)
    {
        // kernel is centred at 10
        // [ ]  [ 0] [ 1] [ 2] [  ]
        // [ 3] [ 4] [ 5] [ 6] [ 7]
        // [ 8] [ 9] [10] [11] [12]
        // [13] [14] [15] [16] [17]
        //[  ] [18] [19] [20] [  ]

        kernel[ 0] = srcData[coord2index.apply(x-1, y-2)];
        kernel[ 1] = srcData[coord2index.apply(  x, y-2)];
        kernel[ 2] = srcData[coord2index.apply(x+1, y-2)];
        kernel[ 3] = srcData[coord2index.apply(x-2, y-1)];
        kernel[ 4] = srcData[coord2index.apply(x-1, y-1)];
        kernel[ 5] = srcData[coord2index.apply(  x, y-1)];
        kernel[ 6] = srcData[coord2index.apply(x+1, y-1)];
        kernel[ 7] = srcData[coord2index.apply(x+2, y-1)];
        kernel[ 8] = srcData[coord2index.apply(x-2,   y)];
        kernel[ 9] = srcData[coord2index.apply(x-1,   y)];
        kernel[10] = srcData[coord2index.apply(  x,   y)];
        kernel[11] = srcData[coord2index.apply(x+1,   y)];
        kernel[12] = srcData[coord2index.apply(x+2,   y)];
        kernel[13] = srcData[coord2index.apply(x-2, y+1)];
        kernel[14] = srcData[coord2index.apply(x-1, y+1)];
        kernel[15] = srcData[coord2index.apply(  x, y+1)];
        kernel[16] = srcData[coord2index.apply(x+1, y+1)];
        kernel[17] = srcData[coord2index.apply(x+2, y+1)];
        kernel[18] = srcData[coord2index.apply(x-1, y+2)];
        kernel[19] = srcData[coord2index.apply(  x, y+2)];
        kernel[20] = srcData[coord2index.apply(x+1, y+2)];
    }
}
