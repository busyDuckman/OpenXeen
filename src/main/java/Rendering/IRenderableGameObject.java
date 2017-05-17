package Rendering;

import Toolbox.Direction;
import Toolbox.IHasProperties;
import Toolbox.ImageHelpers;
//import com.sun.istack.internal.NotNull;
import mamFiles.CCFileFormatException;
import mamFiles.IHasProxy;
import mamFiles.MAMFile;
import mamFiles.MaMSprite;
import mamFiles.WOX.WOXSpriteFile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.function.Function;


/**
 * Created by duckman on 16/05/2016.
 */
public interface IRenderableGameObject
{
    BufferedImage getImage(int frame);
    AnimationSettings getAnimationSettings();

    /**
     * Useful in the debugger, set a watch on this and hit show image.
     */
    default BufferedImage asSpriteSheet()
    {
        AnimationSettings anim = getAnimationSettings();
        if(anim == null)
        {
            return getImage(0);
        }
        else
        {
            BufferedImage[] images = new BufferedImage[anim.numberOfFrames];
            for (int i = 0; i < images.length; i++) {
                images[i] = getImage(i);
            }
            return ImageHelpers.joinHorizontally(images);
        }
    }


    static IRenderableGameObject fromImage(BufferedImage image)
    {
        return new ImageWrapper(image);
    }

    /**
     * This is intended for rough text display (for debug purposes etc)
     * NOT suitable for game menus/signs/text etc.
     */
    static IRenderableGameObject fromText(String s, Color col, int width, int height)
    {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        //get the dpi..
        double dpi = g.getDeviceConfiguration().getNormalizingTransform().getScaleX() * 72;

        //"height" of image
        double inchesHigh = height / dpi;
        //"height" in points (1/72 inch per point) or 7.01e-5 rods per point, narf.
        double pointsHigh = inchesHigh * 72;

        int fontSize = (int)pointsHigh;
        Font font = new Font("Ariel", Font.PLAIN, fontSize);

//        g.setFont(font);
//        FontMetrics fm = g.getFontMetrics();
//        int sWidth = fm.stringWidth(s);
//        int sHeight = fm.getHeight();
//        g.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        g.setColor(col);
        g.drawString(s, 0, fm.getAscent());
        g.dispose();
        return IRenderableGameObject.fromImage(img);
        }

    default BufferedImage[] getRenderedFrames()
    {
        AnimationSettings anim = getAnimationSettings();
        if(anim != null)
        {
            BufferedImage[] images = new BufferedImage[anim.getNumberOfFrames()];
            for (int i = 0; i < anim.getNumberOfFrames(); i++) {
                images[i] = getImage(i);
            }
            return images;
        }
        else
        {
            return new BufferedImage[] {getImage(0)};
        }
    }

    default IRenderableGameObject[] eachFrameAsRenderable() throws CCFileFormatException {
        IRenderableGameObject[] renderables = new IRenderableGameObject[getRenderedFrames().length];
        for (int i = 0; i < renderables.length; i++) {
            renderables[i] = IRenderableGameObject.fromImage(getRenderedFrames()[i]);
        }
        return renderables;
    }

    default IHasProxy asIHasProxyObject()
    {
        String name = (this instanceof MAMFile) ? ((MAMFile)this).getName() : "via asIHasProxyObject()";
        String key = MAMFile.generateUniqueKey(name);
        if(this instanceof IHasProxy)
        {
            return (IHasProxy)this;
        }
        else
        {
            return new MaMSprite(name, key, this.getRenderedFrames());
        }
    }

    default IHasProperties asIHasPropertiesObject()
    {
        String name = (this instanceof MAMFile) ? ((MAMFile)this).getName() : "via asIHasProxyObject()";
        String key = MAMFile.generateUniqueKey(name);
        if(this instanceof IHasProperties)
        {
            return (IHasProperties)this;
        }
        else
        {
            return new MaMSprite(name, key, this.getRenderedFrames());
        }
    }

    /**
     * Sets the alpha values for every frame.
     * Assumes all frames are the same size.
     */
    default IRenderableGameObject applyMask(byte[] mask)
    {
        BufferedImage[] sourceImages = getRenderedFrames();
        BufferedImage[] maskedImages = new BufferedImage[sourceImages.length];
        for (int i = 0; i < maskedImages.length; i++) {
            if(maskedImages.length != (sourceImages[i].getWidth() * sourceImages[i].getHeight()))
            {
                maskedImages[i] = ImageHelpers.applyAlphaChannel(sourceImages[i], mask);
            }
        }

        String name = (this instanceof MAMFile) ? ((MAMFile)this).getName() : "via applyMask()";
        String key = MAMFile.generateUniqueKey(name);
        return new MaMSprite(name, key, maskedImages);

    }

    /**
     * Handy tool to manipulate image alpha values.
     */
    default IRenderableGameObject applyAlphaTransform(int level,  ImageHelpers.AlphaTransforms transform)
    {
        BufferedImage[] sourceImages = getRenderedFrames();
        BufferedImage[] destImages= new BufferedImage[sourceImages.length];
        for (int i = 0; i < destImages.length; i++) {
            destImages[i] = ImageHelpers.applyAlphaTransform(sourceImages[i], level, transform, true);
        }
        String name = (this instanceof MAMFile) ? ((MAMFile)this).getName() : "via applyMask()";
        String key = MAMFile.generateUniqueKey(name);
        return new MaMSprite(name, key, destImages);
    }

    /**
     * Flip about vertical axis.
     */
    default IRenderableGameObject mirror()
    {
        BufferedImage[] sourceImages = getRenderedFrames();
        BufferedImage[] destImages= new BufferedImage[sourceImages.length];

        for (int i = 0; i < destImages.length; i++) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            //tx.translate(0, -sourceImages[i].getHeight(null));
            tx.translate(-sourceImages[i].getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            destImages[i] = op.filter(sourceImages[i], null);
        }
        String name = (this instanceof MAMFile) ? ("mirrored_"+((MAMFile)this).getName()) : "via mirror()";
        String key = MAMFile.generateUniqueKey(name);
        return new MaMSprite(name, key, destImages);
    }

    /**
     * Sometimes simple sprites need to be upgraded act like one of the more powerful
     * versions. This is backward to the normal way of doing things, but it is done
     * to enable the modding engine to work in a graceful manor. Especially in respect
     * to upgrading sprites.
     */
    default IRelativeToLocationSprite asIRelativeToLocationSprite()
    {
        return new RelativeLocationWrapper(this);
    }

    default MaMSprite asSprite()
    {
        //TODO: AnimationSettings should be preserved
        return (this instanceof MaMSprite) ? (MaMSprite)this : new MaMSprite("asSprite", MAMFile.generateUniqueKey("asSprite"), getRenderedFrames());
    }

    default IRenderableGameObject unifyDimensions()
    {
        BufferedImage[] frames = this.getRenderedFrames();
        if(frames.length == 0) {
            return this;
        }

        //find max size, and some stats
        int maxWidth = 0, maxHeight = 0;
        boolean homogenous = true;

        for (int i = 0; i < frames.length; i++) {
            BufferedImage frame = frames[i];
            int width = frame.getWidth();
            int height = frame.getHeight();

            if(i > 0) {
                if((width != maxWidth) || (height != maxHeight)) {
                    homogenous = false;
                }
            }

            maxWidth = Math.max(maxWidth, width);
            maxHeight = Math.max(maxHeight, height);
        }

        if(homogenous) {
            return this;
        }

        BufferedImage[] newFrames = new BufferedImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            newFrames[i] = ImageHelpers.centreOnNewCanvas(frames[i], maxWidth, maxHeight);
        }

        //return a sprite
        String name = (this instanceof MAMFile) ? ("unified_"+((MAMFile)this).getName()) : "via unifyDimensions()";
        String key = MAMFile.generateUniqueKey(name);
        return new MaMSprite(name, key, newFrames);
    }
}

/**
 * Sometimes simple sprites need to be upgraded act like one of the more powerful
 * versions. This is backward to the normal way of doing things, but it is done
 * to enable the modding engine to work in a graceful manor. Especially in respect
 * to upgrading sprites.
 */
final class RelativeLocationWrapper implements IRelativeToLocationSprite
{
    private final IRenderableGameObject rgo;

    public RelativeLocationWrapper(IRenderableGameObject rgo) {
        this.rgo = rgo;
    }

    @Override
    public IRenderableGameObject getView(Point mapPosRelative, Direction viewDir) {
        return rgo;
    }

    @Override
    public AnimationSettings getAnimationSettings() {
        return rgo.getAnimationSettings();
    }
}

/**
 * Used by IRenderableGameObject::fromImage(...)
 */
final class ImageWrapper implements IRenderableGameObject
{
    private final BufferedImage image;

    public ImageWrapper(BufferedImage image) {
        this.image = image;
    }

    @Override
    public final BufferedImage getImage(int frame) {
        return image;
    }

    @Override
    public final AnimationSettings getAnimationSettings() {
        return null;
    }
}
