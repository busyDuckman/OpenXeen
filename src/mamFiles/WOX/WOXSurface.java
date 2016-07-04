package mamFiles.WOX;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Grayscale;
import Catalano.Imaging.Filters.Threshold;
import Rendering.*;
import Toolbox.Direction;
import Toolbox.ImageHelpers;
import Toolbox.MaMGameException;
import com.google.common.collect.Multimap;
import mamFiles.*;
import mamFiles.SpriteHelpers.RenderPosHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duckman on 19/05/2016.
 *
 * NB: xeen used a sprite with 25 frames, where 1 frame was an on screen tile.
 *     I broke from this, and am using 1 frame and 25 masks, one for each screen tile.
 *     This means pre-compiling surface files to one image.
 *     Why:
 *       - Enables resizing of the surface, without interpolating to clear pixels
 *       - Allows for me to blur the mask, creating a terrain blending on screen.
 *       - Allows for easier modding, without cutting up an image
 *       - Allows for sane approach to animating things like lava
 *
 */
public class WOXSurface extends MaMSurface
{
    private static final int numFrames = 25;

    /**
     * One mask per area
     */
    protected byte[][] renderMasks =null;

    protected IRenderableGameObject fullSurface;

    public WOXSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        this(new WOXSpriteFile(name, MAMFile.generateUniqueKey(key), data, pal), key);
    }

    public WOXSurface(MaMSprite aSprite, String key) {
        super(aSprite, key);

        fullSurface = IRenderableGameObject.fromImage(compileToOneImage(aSprite));
        renderMasks = compileToMask(aSprite);
    }

    public static MaMSurface fromPng(String path) throws CCFileFormatException {
        MaMSprite sprite = WOXSpriteFile.fromPNGFile(path);
        return new WOXSurface(sprite, MAMFile.generateKeyFromPath(path));
    }

    @Override
    public IRenderableGameObject getSurfaceOverlay(Point mapPosRelative, Direction viewDirection) {
        if(mapPosRelative == null)
        {
            return fullSurface;
        }
//        int tileNum = relativePos2TileNumTable.getOrDefault(mapPosRelative, -1);
        int tileNum =RenderPosHelper.getGlobalHelper().getTileIndex(mapPosRelative);
        if(tileNum != -1)
        {
            return fullSurface.applyMask(renderMasks[tileNum]);
        }
        return null;
    }

    protected static void renderNthFrameToScreen(ISceneComposition view, MaMSprite mamStyleSprite, int frame)
    {
        try {
            IRenderableGameObject rSurf = IRenderableGameObject.fromImage(mamStyleSprite.getImage(frame));
            Point pos = RenderPosHelper.getGlobalHelper().getSurfaceEnvPos(frame);
            view.addRenderable(new RenderablePos(pos.x, pos.y, 1.0, 3), rSurf);
        } catch (MaMGameException e) {
            e.printStackTrace();
        }
    }
    
    protected BufferedImage compileToOneImage(MaMSprite mamStyleSprite)
    {
        MaM2DInsertionOrderComposition view = new MaM2DInsertionOrderComposition();

        //render all frames
        for(int i = 0; i< numFrames; i++)
        {
            renderNthFrameToScreen(view, mamStyleSprite, i);
        }

        //render to image
        BufferedImage img = new BufferedImage(216, 73, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        //offset for the view position and render
        view.render2Graphics(g, 0, -8, -67, 1.0);

        return img;
    }

    protected byte[][] compileToMask(MaMSprite mamStyleSprite)
    {
        byte[][] masks = new byte[numFrames][];
        for(int i = 0; i< numFrames; i++)
        {
            //render to view
            MaM2DInsertionOrderComposition view = new MaM2DInsertionOrderComposition();
            renderNthFrameToScreen(view, mamStyleSprite, i);

            //render to image
            BufferedImage img = new BufferedImage(216, 73, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.getGraphics();
            view.render2Graphics(g, 0, -8, -67, 1.0); //offset for the view position and render

            //get alpha channel
            masks[i] = ImageHelpers.getAlphaChannel(img);
        }

        return masks;
    }

    @Override
    public BufferedImage getImage(int frame) {
        return fullSurface.getRenderedFrames()[frame];
    }


}
