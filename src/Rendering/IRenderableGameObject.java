package Rendering;

import Toolbox.IHasProperties;
import Toolbox.ImageHelpers;
import mamFiles.CCFileFormatException;
import mamFiles.IHasProxy;
import mamFiles.MAMFile;
import mamFiles.MaMSprite;
import mamFiles.WOX.WOXSpriteFile;
import org.joda.time.DateTime;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


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
}

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
