package Rendering.SimpleCanvas;

import Game.IMaMGame;
import Game.Monsters.MaMMonster;
import Rendering.*;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 20/05/2016.
 */
public class GraphicsRenderer implements IMaMRenderer<Graphics>
{
    IMaMGame game;

    double scale = 4;

    @Override
    public void setGame(IMaMGame game) {
        this.game = game;
    }

    @Override
    public IMaMGame getGame() {
        return game;
    }

    @Override
    public void refresh(Graphics g, long timeMS)
    {
        MaM3DSceneComposition view = this.getCurrentView();

        if(view != null)
        {
            render3DView(g, timeMS, view);
        }

        ISceneComposition hudView = this.game.renderHUDForWorld();
        if(view != null)
        {
            renderView(g, timeMS, hudView);
        }

    }

    private void renderView(Graphics g, long timeMS, ISceneComposition view) {
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
            //Rectangle bounds =

            g.drawImage(
                    frame,
                    (int)Math.round(bounds.x*scale),
                    (int)Math.round(bounds.y*scale),
                    (int)Math.round(bounds.width*scale),
                    (int)Math.round(bounds.height*scale),
                    null);
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
            //Rectangle bounds =

            g.drawImage(
                    frame,
                    (int)Math.round(bounds.x*scale),
                    (int)Math.round(bounds.y*scale),
                    (int)Math.round(bounds.width*scale),
                    (int)Math.round(bounds.height*scale),
                    null);
        }
    }
}
