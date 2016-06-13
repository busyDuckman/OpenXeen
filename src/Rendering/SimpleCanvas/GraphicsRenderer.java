package Rendering.SimpleCanvas;

import Game.IMaMGame;
import Game.Monsters.MaMMonster;
import Rendering.*;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 20/05/2016.
 *
 * This class implements IMaMRenderer, This is the requirement to render the openXeen game.
 * Any SFX or graphical enhancements, are done here.
 *
 * IMaMRenderer uses the IMaMGame object to perform render to sprite collection tasts:
 *  - game.render(...)
 *  - game.renderHUDForWorld(...)
 *  - game.renderMap(...)
 *
 *  It then takes the sprite collections (composed scenes) and renders them to the display.
 *
 *  This class in the vein of a reference implementation of IMaMRenderer.
 *  Fancy rendering should be done in a custom class implementing IMaMRenderer. This class
 *  is for testing and reference purposes and should not be polluted.
 */
public class GraphicsRenderer implements IMaMRenderer<Graphics>
{
    //------------------------------------------------------------------------------------------------------------------
    // Instance data
    //------------------------------------------------------------------------------------------------------------------
    IMaMGame game;
    protected double scale = 1;

    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------

    /**
     * @param scale The scale to which the scene will be drawn. Will be clamped to 0.1 - 100.
     */
    public GraphicsRenderer(double scale) {
        setScale(scale);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setGame(IMaMGame game) {
        this.game = game;
    }

    @Override
    public IMaMGame getGame() {
        return game;
    }

    public double getScale() {
        return scale;
    }

    /**
     * @param scale The scale to which the scene will be drawn. Will be clamped to 0.1 - 100.
     */
    public void setScale(double scale) {
        this.scale = Math.max(0.1, Math.min(scale, 100));
    }

    //------------------------------------------------------------------------------------------------------------------
    // Rendering
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void refresh(Graphics g, long timeMS)
    {
        MaM3DSceneComposition view = this.game.render();

        if(view != null)
        {
            render3DView(g, timeMS, view);
        }

        ISceneComposition hudView = this.game.renderHUDForWorld();
        if(view != null)
        {
            renderView(g, timeMS, hudView);
        }

        MaM2DMapComposition mapView = this.game.renderMap(0,0,16,16);
        if(mapView != null)
        {
            //renderView(g, timeMS, mapView);
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    // Helper methods
    //------------------------------------------------------------------------------------------------------------------
    /**
     * So...
     * If a sprite is rendered so its edge in world space is (X + W)
     * Then its edge in view space is (X*scale + W*scale).
     * BUT: Consider the Sprite adjacent o it (X2 + W2), such that X2 = (X + W)+1;
     *
     * Problem: X2*scale != (X*scale + W*scale)+1.
     *   This causes occasional tears when scaling sprites in a non integral fashion.
     *
     * I'm 90% sure scaling rectangles by outer dimensions fixes this, if not
     * the other code here can be used instead.
     */
    private static Rectangle scaleRectangleNoTearing(Rectangle r, double scale) {

        int x = (int)Math.round(r.x * scale);
        int y = (int)Math.round(r.y * scale);
        int right = (int)Math.round((r.x+r.width) * scale);
        int bottom = (int)Math.round((r.y+r.height) * scale);
        return new Rectangle(x, y, right-x, bottom-y);

//        if(scale > 1.0) //magnification must respect tearing
//        {
//            int x = (int)Math.round(r.x * scale);
//            int y = (int)Math.round(r.y * scale);
//
//            //scale to edge of next sprite (note the +1)
//            int right = (int)Math.round((r.x+r.width+1) * scale);
//            int bottom = (int)Math.round((r.y+r.height+1) * scale);
//
//            //rectangle to pixel before next bound
//            return new Rectangle(x, y, right-x-1, bottom-y-1);
//        }
//        else if(scale < 1.0) //minification should be fine
//        {
//            int x = (int)Math.round(r.x * scale);
//            int y = (int)Math.round(r.y * scale);
//            int right = (int)Math.round((r.x+r.width) * scale);
//            int bottom = (int)Math.round((r.y+r.height) * scale);
//            return new Rectangle(x, y, right-x, bottom-y);
//        }
//        else
//        {
//            //no scale
//            return r;
//        }
    }

    protected void renderView(Graphics g, long timeMS, ISceneComposition view) {
        view.depthSort();
        for (Pair<RenderablePos, IRenderableGameObject> renderable : view.getRenderables())
        {
            AnimationSettings anim = renderable.getValue().getAnimationSettings();

            BufferedImage frame;
            if(anim != null)
            {
                int frameNum = anim.resolveFrameNumber(timeMS);
                frame = renderable.getValue().getImage(frameNum % anim.getNumberOfFrames());
            }
            else
            {
                frame = renderable.getValue().getImage(0);
            }
            if(frame == null)
            {
                System.out.println("Sprite is corrupted?");
                continue;
            }

            Rectangle bounds = renderable.getKey().getImageBounds(frame.getWidth(), frame.getHeight());
            bounds = scaleRectangleNoTearing(bounds, scale);

            g.drawImage(frame,bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
    }

    protected void render3DView(Graphics g, long timeMS, MaM3DSceneComposition view) {
        //view.depthSort();
        //this should already be depth sorted.
        for (Pair<RenderablePos, IRenderableGameObject> renderable : view.getRenderables())
        {
            //IMaMSprite sprite = mon.getIdleAnimation();
            //BufferedImage[] frames = renderable.getRenderedFrames();
            AnimationSettings anim = renderable.getValue().getAnimationSettings();

            BufferedImage frame;
            if(anim != null)
            {
                int frameNum = anim.resolveFrameNumber(timeMS);
                frame = renderable.getValue().getImage(frameNum % anim.getNumberOfFrames());
            }
            else
            {
                frame = renderable.getValue().getImage(0);
            }
            if(frame == null)
            {
                System.out.println("Sprite is corrupted?");
                continue;
            }

            Rectangle bounds = renderable.getKey().getImageBounds(frame.getWidth(), frame.getHeight());
            bounds = scaleRectangleNoTearing(bounds, scale);

            g.drawImage(frame,bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
    }
}
