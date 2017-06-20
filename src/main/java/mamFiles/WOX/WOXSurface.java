package mamFiles.WOX;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.*;
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

import static Toolbox.BinaryHelpers.INT;

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
 * Essential concepts:
 *   - rpIndex (also r or rp): In index internal the the RenderPosHelper kernel (0-26)
 *   - spIndex (also s or sp): In index internal the the surface sprite (0-24)
 *
 *
 */
public class WOXSurface extends MaMSurface
{
    //------------------------------------------------------------------------------------------------------------------
    // static
    //------------------------------------------------------------------------------------------------------------------
    private static final int numFrames = 25;

    /**
     * One mask per area
     */
    protected byte[][] spRenderMasks =null;

    protected IRenderableGameObject fullSurface;
    private boolean blurMask = false;

    public WOXSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        this(new WOXSpriteFile(name, MAMFile.generateUniqueKey(key), data, pal), key);
    }

    public WOXSurface(MaMSprite aSprite, String key) {
        super(aSprite, key);

        fullSurface = IRenderableGameObject.fromImage(compileToOneImage(aSprite));
        spRenderMasks = compileToMask(aSprite);
    }

    public static MaMSurface fromPng(String path) throws CCFileFormatException {
        MaMSprite sprite = WOXSpriteFile.fromPNGFile(path);
        return new WOXSurface(sprite, MAMFile.generateKeyFromPath(path));
    }

    @Override
    public void flush() {
        if(fullSurface != null) {
            fullSurface.flush();
            fullSurface = null;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // MaMSurface
    //------------------------------------------------------------------------------------------------------------------
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
            //check this is a mapped surface
            //if(RenderPosHelper.getGlobalHelper().getSurfaceEnvPos(tileNum) != null)
            if(RenderPosHelper.getGlobalHelper().surfaceVisibleFor(tileNum))
            {
                int spIndex = mapRenderposIndexToSpriteFrame(tileNum);

//                System.out.println("Overlay for view space :" + mapPosRelative +
//                                   " is rp= " + tileNum +
//                                   " sp= " + spIndex);

                if(spIndex >=0)
                {
                    if(spRenderMasks[spIndex] != null)
                    {
                        return fullSurface.applyMask(spRenderMasks[spIndex]);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public BufferedImage getImage(int frame) {
        return fullSurface.getRenderedFrames()[frame];
    }

    //------------------------------------------------------------------------------------------------------------------
    // Frames
    //------------------------------------------------------------------------------------------------------------------
//    protected static void renderNthFrameToScreen(ISceneComposition view, MaMSprite mamStyleSprite, int rpIndex)
//    {
//        int spIndex = mapRenderposIndexToSpriteFrame(rpIndex);
//        if(spIndex < 0)
//        {
//            return;
//        }
//
//        try {
//            IRenderableGameObject rSurf = IRenderableGameObject.fromImage(mamStyleSprite.getImage(spIndex));
//            Point pos = RenderPosHelper.getGlobalHelper().getSurfaceEnvPos(rpIndex);
//            if(pos != null)
//            {
//                view.addRenderable(new RenderablePos(pos.x, pos.y, 1.0, 3), rSurf);
//            }
//            else
//            {
//                System.out.println("not supposed to be here");
//            }
//        } catch (MaMGameException e) {
//            e.printStackTrace();
//        }
//    }

    protected static void renderNthFrameToScreen(ISceneComposition view, MaMSprite mamStyleSprite, int frame)
    {
        try {
            IRenderableGameObject rSurf = IRenderableGameObject.fromImage(mamStyleSprite.getImage(frame));
            int rpIndex = mapSPIndexToRPIndex(frame);
            Point pos = RenderPosHelper.getGlobalHelper().getSurfaceEnvPos(rpIndex);
            view.addRenderable(new RenderablePos(pos.x, pos.y, 1.0, 3), rSurf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int mapSPIndexToRPIndex(int spIndex) {
        if(spIndex >= 18)
        {
            spIndex++;
        }
        return spIndex;
    }

    protected static int mapRenderposIndexToSpriteFrame(int rpIndex)
    {
        if((rpIndex == 18) || (rpIndex >= 26))
        {
            return -1;
        }
        else if(rpIndex > 18)
        {
            rpIndex--;
        }

        return rpIndex;
    }
    
    protected BufferedImage compileToOneImage(MaMSprite mamStyleSprite)
    {
        MaM2DInsertionOrderComposition view = new MaM2DInsertionOrderComposition();

        //render all frames
        for(int r = 0; r < numFrames; r++)
        {
            //System.out.println("n="+n + ", i="+i);
            //if(RenderPosHelper.getGlobalHelper().surfaceVisibleFor(r))
            {
                renderNthFrameToScreen(view, mamStyleSprite, r);
            }
        }

        // render to image
        BufferedImage img = new BufferedImage(216, 73, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        // offset for the view position and render
        view.render2Graphics(g, 0, -8, -67, 1.0);

        return img;
    }

//    protected byte[][] compileToMask(MaMSprite mamStyleSprite)
//    {
//        //byte[][] masks = new byte[RenderPosHelper.getGlobalHelper().getNumMappedPoints()][];
//        int spLen = mamStyleSprite.getRenderedFrames().length;
//        int rpLen = RenderPosHelper.getGlobalHelper().getNumMappedPoints();
//        byte[][] masks = new byte[spLen][];
//        for(int rp = 0; rp< rpLen; rp++)
//        {
//            int spIndex = mapRenderposIndexToSpriteFrame(rp);
//            if(spIndex >= 0)
//            //if(RenderPosHelper.getGlobalHelper().surfaceVisibleFor(i))
//            {
//                //render to view
//                MaM2DInsertionOrderComposition view = new MaM2DInsertionOrderComposition();
//                renderNthFrameToScreen(view, mamStyleSprite, spIndex);
//
//                //render to image
//                BufferedImage img = new BufferedImage(216, 73, BufferedImage.TYPE_INT_ARGB);
//                Graphics g = img.getGraphics();
//                view.render2Graphics(g, 0, -8, -67, 1.0); //offset for the view position and render
//
//                //get alpha channel
//                masks[spIndex] = ImageHelpers.getAlphaChannel(img);
//            }
//            else
//            {
//                //masks[i] = null;
//                System.out.println("skipping rp:" + rp);
//            }
//        }
//
//        return masks;
//    }

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


            if(blurMask)
            {
                Point p = RenderPosHelper.getGlobalHelper().getSurfaceEnvPos(mapSPIndexToRPIndex(i));

                //blur
                BufferedImage gsImage = ImageHelpers.RGB2Image(masks[i], masks[i], masks[i],
                                                                img.getWidth(), img.getHeight());
                //Load an image
                FastBitmap fb = new FastBitmap(gsImage);

                Grayscale gs = new Grayscale();
                gs.applyInPlace(fb);
//                Dilatation dl = new Dilatation(5-p.y);
//                dl.applyInPlace(fb);
//                ConservativeSmoothing cs = new ConservativeSmoothing(5-p.y);
//                cs.applyInPlace(fb);
                Blur bl = new Blur();
                bl.applyInPlace(fb);

                fb.saveAsPNG("c:\\temp\\dump_"+i+".png");
                byte[] temp = ImageHelpers.getRedChannel(fb.toBufferedImage());
                for(int q=0;q<temp.length;q++)
                {
                    masks[i][q] =  (masks[i][q] == ((byte)0xff)) ? masks[i][q] : temp[q];
                    //masks[i][q] =  (INT(masks[i][q]) >= 32) ? masks[i][q] : 0;
                }

                // Apply Bradley local

            }


        }

        return masks;
    }
}
