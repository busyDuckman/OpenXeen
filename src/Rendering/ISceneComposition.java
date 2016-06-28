package Rendering;

import Toolbox.MaMGameException;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Function;

/**
 * Created by duckman on 5/06/2016.
 */
public interface ISceneComposition
{
    void depthSort();

    List<Pair<RenderablePos, IRenderableGameObject>> getRenderables();

    void addRenderable(RenderablePos pos, IRenderableGameObject obj) throws MaMGameException;

    default void render2Graphics(Graphics g,
                                 long timeMS,
                                 int x, int y,
                                 double scale)
    {
        depthSort();
        for (Pair<RenderablePos, IRenderableGameObject> renderable : getRenderables())
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
            bounds = scaleRectangleNoTearing(bounds, scale);

            g.drawImage(frame, (int)x+bounds.x, (int)y+bounds.y, bounds.width, bounds.height, null);
        }
    }

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
    static Rectangle scaleRectangleNoTearing(Rectangle r, double scale) {
        int x = (int)Math.round(r.x * scale);
        int y = (int)Math.round(r.y * scale);
        int right = (int)Math.round((r.x+r.width) * scale);
        int bottom = (int)Math.round((r.y+r.height) * scale);
        return new Rectangle(x, y, right-x, bottom-y);
    }

}
