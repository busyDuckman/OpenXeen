package mamFiles;

import Rendering.AnimationSettings;
import Rendering.IRenderableGameObject;
import Toolbox.Direction;
import Toolbox.HProperties;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 19/05/2016.
 *
 * More info [http://xeen.wikia.com/wiki/SRF_File_Format]
 * Bigger picture [http://xeen.wikia.com/wiki/MAZExxxx.DAT_File_Format]
 *
 * This surface class is kept abstract. WoXSurface deals with paint by area
 * surfaces, I also envision perspective transform surfaces being a possibility.
 */
public abstract class MaMSurface extends MAMFile implements Rendering.IRelativeToLocationOverlay
{
    protected final static int[] viewWidthLut = {3,5,7,7};
    protected final static int[] ySumLut = {0,3,8,15};

    /**
     * The entire ground.
     */
    IRenderableGameObject groundSurface;

    /**
     * @param mamStyleSprite A sprite, with 25 frames
     */
    public MaMSurface(MaMSprite mamStyleSprite, String key)
    {
        super(mamStyleSprite.name, key);
    }

    protected abstract BufferedImage compileToOneImage(MaMSprite mamStyleSprite);




//    @Override
//    public BufferedImage getImage(Point mapPosRelative, int frame)
//    {
//        int x = mapPosRelative.x;
//        int y = mapPosRelative.y;
//        if((y>=0)&&(y<viewWidthLut.length))
//        {
//            int divergence = viewWidthLut[y] / 2;
//            if((x >= -divergence)&&(x <= divergence))
//            {
//                int relativeFrame = ySumLut[y]+x+divergence;
//                return this.sprite.getRenderedFrames()[relativeFrame];
//            }
//        }
//
//        //bad location
//        return null;
//    }

//    @Override
//    public BufferedImage getImage(int frame) {
//        return sprite.getRenderedFrames()[frame];
//    }

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
        return groundSurface.asIHasProxyObject().saveProxy(path);
    }

//    public MaMSurface fromPng(String path) throws CCFileFormatException {
//        MaMSprite sprite = WOXSpriteFile.fromPNGFile(path);
//        return new MaMSurface(sprite, MAMFile.generateKeyFromPath(path));
//    }


    @Override
    public boolean setProperties(HProperties p) {
        //TODO: sprite key and this key are saved to the same property
        p = p.push("Sprite");
        groundSurface.asIHasPropertiesObject().setProperties(p);
        p = p.pop();
        return super.setProperties(p);
    }

    @Override
    public boolean getProperties(HProperties p) {
        p = p.push("Sprite");
        groundSurface.asIHasPropertiesObject().getProperties(p);
        p = p.pop();
        return super.getProperties(p);
    }

    public IRenderableGameObject getSurfaceOverlay(Point point) {
        return this.getSurfaceOverlay(point, Direction.UP);
    }
}
