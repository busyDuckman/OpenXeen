package Toolbox;

import java.awt.image.BufferedImage;

/**
 * Created by duckman on 1/08/2016.
 */
//@FunctionalInterface
public interface IImageWorker extends IHasProperties
{
    BufferedImage apply(BufferedImage image);

    static IImageWorker NO_OPERATION = new IImageWorker() {
        @Override
        public boolean getProperties(HProperties p) { return true;}

        @Override
        public boolean setProperties(HProperties p) { return true; }

        @Override
        public BufferedImage apply(BufferedImage image) { return image; }
    };
}
