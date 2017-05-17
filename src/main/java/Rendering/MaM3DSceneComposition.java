package Rendering;

import Game.Monsters.MaMMonster;
import Toolbox.MaMGameException;
import javafx.util.Pair;
import mamFiles.CCFileFormatException;
import mamFiles.MaMSprite;
import mamFiles.MaMSurface;
import mamFiles.SpriteHelpers.RenderPosHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    public synchronized void addRenderable(RenderablePos pos, IRenderableGameObject obj) throws MaMGameException {
        MaMGameException.assertTrue(pos != null, "MaM3DSceneComposition::addRenderable(...) pos != null");
        MaMGameException.assertTrue(obj != null, "MaM3DSceneComposition::addRenderable(...) obj != null");
        sorted = false;
        renderables.add(new Pair<>(pos, obj));
    }

    public synchronized void setGround(IRenderableGameObject ground) throws MaMGameException {
        if(ground != null)
        {
            int depth = RenderPosHelper.getGlobalHelper().getDepth(RenderPosHelper.RenderableType.GROUND, 0);
            addRenderable(MaM3dScenePos.Ground.getRenderablePosition().atDepth(depth), ground);
        }
    }

    public synchronized void setSky(IRenderableGameObject sky) throws MaMGameException
    {
        if(sky != null)
        {
            int depth = RenderPosHelper.getGlobalHelper().getDepth(RenderPosHelper.RenderableType.SKY, 0);

            IRenderableGameObject skyTop = IRenderableGameObject.fromImage(sky.getImage(0));
            IRenderableGameObject skyBottom = IRenderableGameObject.fromImage(sky.getImage(1));

            addRenderable(MaM3dScenePos.TopHalfOfsky.getRenderablePosition().atDepth(depth), skyTop);
            addRenderable(MaM3dScenePos.BottomHalfOfsky.getRenderablePosition().atDepth(depth), skyBottom);
        }
    }

    public synchronized void addSurface(Point relativePos, MaMSurface surface) throws MaMGameException {
//        RenderablePos pos = relativePointToSurfaceRenderPos(relativePos);
//        if(pos != null)
//        {
//            BufferedImage img = surface.getImage(relativePos, 0);
//            if(img != null)
//            {
//                addRenderable(pos, IRenderableGameObject.fromImage(img));
//            }
//        }

        addRenderable(MaM3dScenePos.Ground.getRenderablePosition(), surface.getSurfaceOverlay(relativePos));
    }

    public RenderablePos relativePointToSurfaceRenderPos(Point relativePos)
    {
        MaM3dScenePos pos = relativePointToSurfaceScenePos(relativePos);
        if(pos != null)
        {
            return pos.getRenderablePosition();
        }
        return null;
    }

    protected MaM3dScenePos relativePointToSurfaceScenePos(Point relativePos)
    {
        switch (relativePos.x)
        {
            case 0:
                switch (relativePos.y)
                {
                    case 0: return MaM3dScenePos.SurfaceTileplayerIscurrentlyOn;
                    case 1: return MaM3dScenePos.SurfaceTileDirectly1StepForward;
                    case 2: return MaM3dScenePos.SurfaceTileDirectly2StepsForward;
                    case 3: return MaM3dScenePos.SurfaceTileDirectly3StepsForward;
                    case 4: return MaM3dScenePos.SurfaceTileDirectly4StepsForward;
                }
                break;
            case -1:
                switch (relativePos.y)
                {
                    case 0: return MaM3dScenePos.SurfaceTileDirectly1StepLeft;
                    case 1: return MaM3dScenePos.SurfaceTile1StepForward1Left;
                    case 2: return MaM3dScenePos.SurfaceTile2StepsForward1Left;
                    case 3: return MaM3dScenePos.SurfaceTile3StepsForward1Left;
                    case 4: return MaM3dScenePos.SurfaceTile4StepsForward1Left;
                }
                break;
            case -2:
                switch (relativePos.y)
                {
                    case 3: return MaM3dScenePos.WallTile3StepsForward2StepsLeft;
                    case 4: return MaM3dScenePos.WallTile3StepsForward2StepsLeft;
                }
                break;
        }

        return null;
    }

    public synchronized void addMonster(RenderablePos pos, MaMMonster monster) throws MaMGameException {
        //addRenderable(MaM3dScenePos.MonsterCurrentMiddle.getRenderablePosition(), monster.getAttackAnimation());
        addRenderable(pos, monster.getAttackAnimation());
    }



}
