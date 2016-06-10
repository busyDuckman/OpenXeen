package Rendering;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duckman on 5/06/2016.
 *
 * Used for mini-map and larger world maps.
 */
public class MaM2DMapComposition implements ISceneComposition
{
    protected List<Pair<RenderablePos,  IRenderableGameObject>> renderables;
    protected boolean sorted = false;

    public MaM2DMapComposition()
    {
        renderables = new ArrayList<>();
        sorted = false;
    }

    @Override
    public synchronized void depthSort()
    {
        if(!sorted)
        {
            //depth sort by depth, then y, then x, because the wall tiles overlap floor tiles.
            renderables.sort((p1, p2) -> {
                RenderablePos rp1 =  p1.getKey();
                RenderablePos rp2 =  p2.getKey();
                int c = Integer.compare(rp1.getDepth(), rp2.getDepth());
                if(c == 0)
                {
                    c = Integer.compare(rp1.getyPos(), rp2.getyPos());
                    if(c == 0)
                    {
                        c = Integer.compare(rp1.getxPos(), rp2.getxPos());
                    }
                }
                return c;
            }
            );
            sorted = true;
        }
    }

    @Override
    public synchronized List<Pair<RenderablePos, IRenderableGameObject>> getRenderables() {
        depthSort(); //ensure we are sorted
        return renderables;
    }

    @Override
    public synchronized void addRenderable(RenderablePos pos, IRenderableGameObject obj)
    {
        sorted = false;
        renderables.add(new Pair<>(pos, obj));
    }
}
