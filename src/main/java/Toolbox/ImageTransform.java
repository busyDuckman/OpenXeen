package Toolbox;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Created by duckman on 31/07/2016.
 *
 * Holds everything to do with basic transforming of an image.
 */
public class ImageTransform implements IImageWorker
{
    //------------------------------------------------------------------------------------------------------------------
    // Static constants
    //------------------------------------------------------------------------------------------------------------------
    public static final ImageTransform FLIP = new ImageTransform(true, false, 0, 1, 1,
                                                    ImageHelpers.AlphaTransforms.NO_OPERATION, 0, null);

    public static final ImageTransform MIRROR = new ImageTransform(false, true, 0, 1, 1,
                                                    ImageHelpers.AlphaTransforms.NO_OPERATION, 0, null);

    public static final ImageTransform ROTATE_RIGHT_90 = new ImageTransform(false, true, Math.PI*0.5, 1, 1,
                                                             ImageHelpers.AlphaTransforms.NO_OPERATION, 0, null);

    public static final ImageTransform ROTATE_180 = new ImageTransform(false, true, Math.PI, 1, 1,
            ImageHelpers.AlphaTransforms.NO_OPERATION, 0, null);

    public static final ImageTransform ROTATE_RIGHT_270 = new ImageTransform(false, true, Math.PI*1.5, 1, 1,
            ImageHelpers.AlphaTransforms.NO_OPERATION, 0, null);


    //------------------------------------------------------------------------------------------------------------------
    // Instance data
    //------------------------------------------------------------------------------------------------------------------
    boolean flip;
    boolean mirror;

    double scaleX;
    double scaleY;

    /**
     * Rotate right, in radians
     */
    double rotation;

    Function<Color, Color> pixelTransform;

    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public ImageTransform()
    {
        this(false, false, 0, 1, 1, ImageHelpers.AlphaTransforms.NO_OPERATION, 0, null);
    }

    public ImageTransform(boolean flip, boolean mirror, double rotation, double scaleX, double scaleY, ImageHelpers.AlphaTransforms alphaTransform, int newAplha, Function<Color, Color> pixelTransform) {
        this.flip = flip;
        this.mirror = mirror;
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.pixelTransform = pixelTransform;
    }

    public ImageTransform(ImageTransform other) {
        this.flip = other.flip;
        this.mirror = other.mirror;
        this.rotation = other.rotation;
        this.scaleX = other.scaleX;
        this.scaleY = other.scaleY;
        this.pixelTransform = other.pixelTransform;
    }

    public ImageTransform(boolean flip, boolean mirror, double scaleX, double scaleY) {
        this();
        this.flip = flip;
        this.mirror = mirror;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public ImageTransform(Function<Color, Color> pixelTransform) {
        this();
        this.pixelTransform = pixelTransform;
    }

    public ImageTransform(ImageHelpers.AlphaTransforms alphaTransform, int newAplha) {
        this();
        //TODO
    }

    //------------------------------------------------------------------------------------------------------------------
    // Object
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageTransform that = (ImageTransform) o;

        if (flip != that.flip) return false;
        if (mirror != that.mirror) return false;
        if (Double.compare(that.scaleX, scaleX) != 0) return false;
        if (Double.compare(that.scaleY, scaleY) != 0) return false;
        if (Double.compare(that.rotation, rotation) != 0) return false;
        return pixelTransform != null ? pixelTransform.equals(that.pixelTransform) : that.pixelTransform == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (flip ? 1 : 0);
        result = 31 * result + (mirror ? 1 : 0);
        temp = Double.doubleToLongBits(scaleX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(scaleY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rotation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (pixelTransform != null ? pixelTransform.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImageTransform{" +
                "flip=" + flip +
                ", mirror=" + mirror +
                ", rotation=" + rotation +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                ", pixelTransform=" + pixelTransform +
                '}';
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public boolean isFlip() {
        return flip;
    }

    public boolean isMirror() {
        return mirror;
    }

    public double getRotation() {
        return rotation;
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public Function<Color, Color> getPixelTransform() {
        return pixelTransform;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Apply transform
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public BufferedImage apply(BufferedImage image)
    {
        BufferedImage res = image;

        // Flip/mirror (use TYPE_NEAREST_NEIGHBOR)
        if(flip | mirror) {
            AffineTransform tx = AffineTransform.getScaleInstance(mirror ? -1 : 1, flip ? -1 : 1);
            if(flip) {
                tx.translate(0, -res.getHeight(null));
            }
            if(mirror) {
                tx.translate(-res.getWidth(null), 0);
            }
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            res = op.filter(res, null);
        }

        //scale and rotate
        if((scaleX != 1) || (scaleY != 1))
        {
            AffineTransform tx = AffineTransform.getScaleInstance(scaleX, scaleY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
            res = op.filter(res, null);
        }

        if(rotation != 0)
        {
            double rDiv = Math.abs(rotation / (0.5 * Math.PI));
            boolean rot90Type = Math.abs(((int)Math.round(rDiv)) - rDiv) < 0.0001;
            AffineTransform tx = AffineTransform.getRotateInstance(rotation, res.getWidth()/2.0, res.getHeight()/2.0);
            AffineTransformOp op = new AffineTransformOp(tx, rot90Type ? AffineTransformOp.TYPE_NEAREST_NEIGHBOR : AffineTransformOp.TYPE_BICUBIC);
            res = op.filter(res, null);
        }

        if(pixelTransform != null)
        {
            int[] pixels = ImageHelpers.image2RGBA(res);
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = pixelTransform.apply(new Color(pixels[i], true)).getRGB();
            }
            res = ImageHelpers.RGBA2Image(pixels, res.getWidth(), res.getHeight());
        }

        return res;
    }


    @Override
    public boolean getProperties(HProperties p) {
        this.flip = p.getBoolPropertyOrValue("Flip", false);
        this.mirror = p.getBoolPropertyOrValue("Mirror", false);
        p.getDoublePropertyOrValue("Rotate", 0);
        p.getDoublePropertyOrValue("ScaleX", 1);
        p.getDoublePropertyOrValue("ScaleY", 1);
        //this.pixelTransform = pixelTransform.getClass().getName();
        return true;
    }

    @Override
    public boolean setProperties(HProperties p) {
        if(flip) {
            p.setProperty("Flip",   ""+flip);
        }
        if(mirror) {
            p.setProperty("Mirror", ""+mirror);
        }
        if(rotation != 0) {
            p.setProperty("Rotate", ""+rotation);
        }
        if((scaleX != 1) || (scaleY != 1)) {
            p.setProperty("ScaleX", ""+scaleX);
            p.setProperty("ScaleY", ""+scaleY);
        }
        if(pixelTransform != null) {
            p.setProperty("pixelTransform", pixelTransform.getClass().getName());
        }
        return true;
    }
}
