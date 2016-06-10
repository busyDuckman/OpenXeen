package Rendering;

import Game.Monsters.MaMMonster;
import javafx.util.Pair;
import mamFiles.MaMSprite;
import mamFiles.MaMSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by duckman on 20/05/2016.
 *
 * This class holds a view of the world as a collage renderable objects.
 *
 */
public class MaM3DSceneComposition implements ISceneComposition {

    protected List<Pair<RenderablePos,  IRenderableGameObject>> renderables;
    protected boolean sorted = false;

    public MaM3DSceneComposition()
    {
        renderables = new ArrayList<>();
        sorted = false;
    }

    @Override
    public synchronized void depthSort()
    {
        if(!sorted)
        {
            renderables.sort((p1, p2) -> Integer.compare(p1.getKey().getDepth(), p2.getKey().getDepth()));
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

    public synchronized void setGround(MaMSprite ground)
    {
        addRenderable(MaM3dScenePos.Ground.getRenderablePosition(), ground);
    }

    public synchronized void setSky(MaMSprite sky)
    {
        addRenderable(MaM3dScenePos.TopHalfOfsky.getRenderablePosition(), sky);
    }

    public synchronized void addSurface(Point relativePos, MaMSurface surface)
    {
        addRenderable(MaM3dScenePos.SurfaceTile1StepForward1Left.getRenderablePosition(), surface);
    }

    public synchronized void addMonster(Point relativePos, MaMMonster monster)
    {
        addRenderable(MaM3dScenePos.Monsters2StepsForward.getRenderablePosition(), monster.getAttackAnimation());
    }



}
