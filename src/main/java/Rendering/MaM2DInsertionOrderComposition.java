package Rendering;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duckman on 10/06/2016.
 */
public class MaM2DInsertionOrderComposition implements ISceneComposition
{
    protected List<Pair<RenderablePos,  IRenderableGameObject>> renderables;

    public MaM2DInsertionOrderComposition()
    {
        renderables = new ArrayList<>();
    }

    @Override
    public synchronized void depthSort()
    {
    }

    @Override
    public synchronized List<Pair<RenderablePos, IRenderableGameObject>> getRenderables()
    {
        return renderables;
    }

    @Override
    public synchronized void addRenderable(RenderablePos pos, IRenderableGameObject obj)
    {
        renderables.add(new Pair<>(pos, obj));
    }
}
