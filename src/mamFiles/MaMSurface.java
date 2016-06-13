package mamFiles;

import Rendering.AnimationSettings;
import Toolbox.HProperties;
import mamFiles.WOX.SpriteFileWOX;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 19/05/2016.
 *
 * More info [http://xeen.wikia.com/wiki/SRF_File_Format]
 * Bigger picture [http://xeen.wikia.com/wiki/MAZExxxx.DAT_File_Format]
 */
public class MaMSurface extends MAMFile implements Rendering.IRelativeToLocationSprite
{
    protected final static int[] viewWidthLut = {3,5,7,7};
    protected final static int[] ySumLut = {0,3,8,15};

    MaMSprite sprite;

    /**
     * @param aSprite A sprite, with a key not equal to the key parameter
     */
    public MaMSurface(MaMSprite aSprite, String key)
    {
        super(aSprite.name, key);
    }

    @Override
    public BufferedImage getImage(Point mapPosRelative, int frame)
    {
        int x = mapPosRelative.x;
        int y = mapPosRelative.y;
        if((y>=0)&&(y<viewWidthLut.length))
        {
            int divergence = viewWidthLut[y] / 2;
            if((x >= -divergence)&&(x <= divergence))
            {
                int relativeFrame = ySumLut[y]+x+divergence;
                return this.sprite.getRenderedFrames()[relativeFrame];
            }
        }

        //bad location
        return null;
    }

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
        return sprite.saveProxy(path);
    }

    public MaMSurface fromPng(String path) throws CCFileFormatException {
        MaMSprite sprite = SpriteFileWOX.fromPNGFile(path);
        return new MaMSurface(sprite, MAMFile.generateKeyFromPath(path));
    }


    @Override
    public boolean setProperties(HProperties p) {
        //TODO: sprite key and this key are saved to the same property
        p = p.push("Sprite");
        sprite.setProperties(p);
        p = p.pop();
        return super.setProperties(p);
    }

    @Override
    public boolean getProperties(HProperties p) {
        p = p.push("Sprite");
        sprite.getProperties(p);
        p = p.pop();
        return super.getProperties(p);
    }
}
