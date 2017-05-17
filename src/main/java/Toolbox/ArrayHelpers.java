package Toolbox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by duckman on 22/05/2016.
 */
public class ArrayHelpers
{

    public static Color[] toColorArray(List<Color> cols)
    {
        Color[] c = new Color[cols.size()];
        return cols.toArray(c);
    }

    public static BufferedImage[] toBufferedImageArray(List<BufferedImage> images)
    {
        BufferedImage[] array = new BufferedImage[images.size()];
        return images.toArray(array);
    }
}
