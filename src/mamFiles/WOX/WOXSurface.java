package mamFiles.WOX;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Grayscale;
import Catalano.Imaging.Filters.Threshold;
import Rendering.IRenderableGameObject;
import Rendering.ISceneComposition;
import Rendering.MaM2DInsertionOrderComposition;
import Rendering.RenderablePos;
import Toolbox.ImageHelpers;
import Toolbox.MaMGameException;
import com.google.common.collect.Multimap;
import mamFiles.*;

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

    /**
     * The surface position, as rendered in dos, vga mode 13h
     * Renders 25 frames to a rectangle(x,y,w,h) (8, 67, 216, 73)
     * NB: These are hand curated, by lining up the wooden floor surface.
     * There is a render struct at http://xeen.wikia.com/wiki/MAZExxxx.DAT_File_Format#Outdoor_DrawStruct_list
     *    - I don't know how that one was put together, it looks reverse engineered from code
     *    - I don't know whos is better, or by how much they differ.
     */
    protected static final Point[] surfacePositions = new Point[] {
            new Point(8,109),   //tile left
            new Point(8,109),   //tile currently on
            new Point(201,109), //tile right

            //1 tile forward
            new Point(8,93),    //left
            new Point(31,93),   //middle
            new Point(169,93),  //right

            //2 tiles forward
            new Point(8,81),
            new Point(8,81),
            new Point(63,81),
            new Point(145,81),
            new Point(202,81),

            //3 tiles forward
            new Point(8,73),
            new Point(8,73),
            new Point(30,73),
            new Point(87,73),
            new Point(129,73),
            new Point(154,73),
            new Point(181,73),

            //4 tiles forward
            new Point(8,67),
            new Point(38,67),
            new Point(84,67),
            new Point(103,67),
            new Point(117,67),
            new Point(117,67),
            new Point(134,67)};

    protected static Map<Point, Integer> relativePos2TileNumTable;

    //init static data
    static {
        relativePos2TileNumTable = new HashMap<>();
        relativePos2TileNumTable.put(new Point(-1,  0), 0); //left
        relativePos2TileNumTable.put(new Point( 0,  0), 1); //current
        relativePos2TileNumTable.put(new Point( 1,  0), 2); //right

        // 1 step forward
        relativePos2TileNumTable.put(new Point(-1,  1), 3);
        relativePos2TileNumTable.put(new Point( 0,  1), 4);
        relativePos2TileNumTable.put(new Point( 1,  1), 5);

        // 2 steps forward
        relativePos2TileNumTable.put(new Point(-2,  2), 6);
        relativePos2TileNumTable.put(new Point(-1,  2), 7);
        relativePos2TileNumTable.put(new Point( 0,  2), 8);
        relativePos2TileNumTable.put(new Point( 1,  2), 9);
        relativePos2TileNumTable.put(new Point( 2,  2), 10);

        // 3 steps forward
        relativePos2TileNumTable.put(new Point(-3,  3), 11);
        relativePos2TileNumTable.put(new Point(-2,  3), 12);
        relativePos2TileNumTable.put(new Point(-1,  3), 13);
        relativePos2TileNumTable.put(new Point( 0,  3), 14);
        relativePos2TileNumTable.put(new Point( 1,  3), 15);
        relativePos2TileNumTable.put(new Point( 2,  3), 16);
        relativePos2TileNumTable.put(new Point( 3,  3), 17);

        // 4 steps forward
        relativePos2TileNumTable.put(new Point(-3,  4), 18);
        relativePos2TileNumTable.put(new Point(-2,  4), 19);
        relativePos2TileNumTable.put(new Point(-1,  4), 20);
        relativePos2TileNumTable.put(new Point( 0,  4), 21);
        relativePos2TileNumTable.put(new Point( 1,  4), 22);
        relativePos2TileNumTable.put(new Point( 2,  4), 23);
        relativePos2TileNumTable.put(new Point( 3,  4), 24);
    }

    public WOXSurface(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        //super(new WOXSpriteFile(name, key, data, pal), MAMFile.generateKeyFromJoin();
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
    public IRenderableGameObject getSurfaceOverlay(Point mapPosRelative) {
        if(mapPosRelative == null)
        {
            return fullSurface;
        }
        int tileNum = relativePos2TileNumTable.getOrDefault(mapPosRelative, -1);
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
            view.addRenderable(new RenderablePos(surfacePositions[frame].x, surfacePositions[frame].y, 1.0, 3), rSurf);
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
