package Rendering;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by duckman on 5/06/2016.
 */
public interface ISceneComposition
{
    void depthSort();

    List<Pair<RenderablePos, IRenderableGameObject>> getRenderables();

    void addRenderable(RenderablePos pos, IRenderableGameObject obj);
}
