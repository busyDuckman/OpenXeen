package Rendering.SimpleCanvas;

import Game.GlobalSettings;
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
        render3DView(g, timeMS, view);

        if(!GlobalSettings.INSTANCE.isHUDDisabled())
        {
            ISceneComposition hudView = this.game.renderHUDForWorld();
            renderView(g, timeMS, hudView);

            ISceneComposition partyView = this.game.renderParty();
            renderView(g, timeMS, partyView);
        }

        MaM2DMapComposition mapView = this.game.renderWizardEyeView(9,8);
        //MaM2DMapComposition mapView = this.game.renderMap(0,0,16,16);
        renderView(g, timeMS, mapView, 234, 9, 1);

    }

    //------------------------------------------------------------------------------------------------------------------
    // Helper methods
    //------------------------------------------------------------------------------------------------------------------

    protected void renderView(Graphics g,
                              long timeMS,
                              ISceneComposition view)
    {
        renderView(g, timeMS, view, 0, 0, 1);
    }

    protected void renderView(Graphics g,
                              long timeMS,
                              ISceneComposition view,
                              int x,
                              int y,
                              double localScale)
    {
        if(view == null) {
            return;
        }

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

            Rectangle bounds = renderable.getKey().getImageBoundsAfterOffset(0, 0,
                                                                frame.getWidth(),
                                                                frame.getHeight());

            double spriteScale = renderable.getKey().getScale();
            bounds = ISceneComposition.scaleRectangleNoTearing(bounds, spriteScale*scale*localScale);

            g.drawImage(frame, (int)(x*scale)+bounds.x, (int)(y*scale)+bounds.y, bounds.width, bounds.height, null);
        }
    }

    protected void render3DView(Graphics g, long timeMS, MaM3DSceneComposition view) {
        if(view == null) {
            return;
        }
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

//            double spriteScale = renderable.getKey().getScale();
//            if(spriteScale != 1.0)
//            {
//                //spriteScale *= 0.25;
//                System.out.println("scale="+spriteScale);
//            }

            Rectangle bounds = renderable.getKey().getImageBounds(frame.getWidth(), frame.getHeight());
            bounds = ISceneComposition.scaleRectangleNoTearing(bounds, scale);

            g.drawImage(frame,bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
    }
}
