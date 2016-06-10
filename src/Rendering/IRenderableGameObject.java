package Rendering;

import org.joda.time.DateTime;

import java.awt.image.BufferedImage;


/**
 * Created by duckman on 16/05/2016.
 */
public interface IRenderableGameObject
{
    BufferedImage getImage(int frame);
    AnimationSettings getAnimationSettings();



    static IRenderableGameObject fromImage(BufferedImage image)
    {
        return new ImageWrapper(image);
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
