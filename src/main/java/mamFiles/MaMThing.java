package mamFiles;

import Game.GlobalSettings;
import Game.IGameEntity;
import Rendering.AnimationSettings;
import Rendering.IRenderableGameObject;
import Toolbox.Direction;
import Toolbox.FileHelpers;
import Toolbox.ImageHelpers;
import mamFiles.WOX.WOXSpriteFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by duckman on 3/07/2016.
 *
 * This represents any world object or "thing".
 * Things:
 *  - may have a direction (they look different when seen from a different side).
 *  - may be animated.
 *  - may be interacted with (are scriptable).
 *  - are positioned in the viewport, relative to their location.
 *
 *  Game entities that are not things:
 *   - monsters
 *   - wall decals
 */
public class MaMThing extends MAMFile implements Rendering.IRelativeToLocationSprite
{
    /**
     * Indexed by Direction::ordinal()
     */
    protected IRenderableGameObject[] directionalViews;

    /**
     * This constructor turns the frames of a sprite into directional views.
     */
    public MaMThing(MaMSprite mamStyleSprite, String key, Map<Direction, MaMSprite.SpriteView> views) throws CCFileFormatException {
        super(mamStyleSprite.name, key);

        directionalViews = new IRenderableGameObject[4];

        directionalViews[Direction.LEFT.ordinal()] = views.get(Direction.LEFT).apply(mamStyleSprite);
        directionalViews[Direction.RIGHT.ordinal()] = views.get(Direction.RIGHT).apply(mamStyleSprite);
        directionalViews[Direction.UP.ordinal()] = views.get(Direction.UP).apply(mamStyleSprite);
        directionalViews[Direction.DOWN.ordinal()] = views.get(Direction.DOWN).apply(mamStyleSprite);

    }
    /**
     * This constructor turns the frames of a sprite into directional views.
     */
    public MaMThing(MaMSprite mamStyleSprite, String key) throws CCFileFormatException {
        super(mamStyleSprite.name, key);

        directionalViews = new IRenderableGameObject[4];
        switch (mamStyleSprite.getRenderedFrames().length)
        {
            case 1:
                directionalViews[0] = mamStyleSprite;
                directionalViews[1] = mamStyleSprite;
                directionalViews[2] = mamStyleSprite;
                directionalViews[3] = mamStyleSprite;
                break;
            case 2:
                directionalViews[0] = mamStyleSprite.subSetOfFrames("ViewOfNorthSide_" + name, 0,1);
                directionalViews[1] = mamStyleSprite.subSetOfFrames("ViewOfEastSide_"  + name, 1,1);
                directionalViews[2] = mamStyleSprite.subSetOfFrames("ViewOfSouthSide_" + name, 0,1).mirror();
                directionalViews[3] = mamStyleSprite.subSetOfFrames("ViewOfWestSide_"  + name, 1,1).mirror();
                break;
            case 3:
                directionalViews[0] = mamStyleSprite.subSetOfFrames("ViewOfNorthSide_" + name, 0,1);
                directionalViews[1] = mamStyleSprite.subSetOfFrames("ViewOfEastSide_"  + name, 1,1);
                directionalViews[2] = mamStyleSprite.subSetOfFrames("ViewOfSouthSide_" + name, 2,1);
                directionalViews[3] = mamStyleSprite.subSetOfFrames("ViewOfWestSide_"  + name, 1,1).mirror();
                break;
            case 4:
                directionalViews[0] = mamStyleSprite.subSetOfFrames("ViewOfNorthSide_" + name, 0,1);
                directionalViews[1] = mamStyleSprite.subSetOfFrames("ViewOfEastSide_"  + name, 1,1);
                directionalViews[2] = mamStyleSprite.subSetOfFrames("ViewOfSouthSide_" + name, 0,1);
                directionalViews[3] = mamStyleSprite.subSetOfFrames("ViewOfWestSide_"  + name, 1,1);
                break;
            default:
                //CCFileFormatException.throwFeatureNotReady("Don't know how to make that sprite directional");
                directionalViews[0] = mamStyleSprite;
                directionalViews[1] = mamStyleSprite;
                directionalViews[2] = mamStyleSprite;
                directionalViews[3] = mamStyleSprite;

        }

        int debugAlpha = GlobalSettings.INSTANCE.getSpriteDebugAlpha();
        if(debugAlpha > 0) {
            for(int i=0; i<directionalViews.length; i++) {
                IRenderableGameObject vImg = directionalViews[i].applyAlphaTransform(debugAlpha, ImageHelpers.AlphaTransforms.SET_FOR_ALL);
                directionalViews[i] = vImg;
            }
        }

//        int debugAlpha = GlobalSettings.INSTANCE.getSceneryWash();
//        if(debugAlpha > 0) {
//            for(int i=0; i<directionalViews.length; i++) {
//                IRenderableGameObject vImg = directionalViews[i].a(debugAlpha, ImageHelpers.AlphaTransforms.SET_FOR_ALL);
//                directionalViews[i] = vImg;
//            }
//        }


    }

    @Override
    public void flush() {
        for (int i = 0; i < directionalViews.length; i++) {
            if(directionalViews[i] != null) {
                directionalViews[i].flush();
                directionalViews[i] = null;
            }
        }
    }

    @Override
    public IRenderableGameObject getView(Point mapPosRelative, Direction viewDir) {
        return directionalViews[viewDir.ordinal()];
    }

    @Override
    public AnimationSettings getAnimationSettings() {
        //TODO: see code cleanup notes, must go
        return null;
    }

    @Override
    public String suggestProxyFileName() {
        //TODO
        return null;
    }

    @Override
    public boolean saveProxy(String path) throws CCFileFormatException {

        //TODO: clean up
        //CCFileFormatException.assertFalse(width  <= 0, "MaMSprite::saveProxy() width <= 0");
        //CCFileFormatException.assertFalse(height <= 0, "MaMSprite::saveProxy() height <= 0");

        BufferedImage join = ImageHelpers.joinHorizontally(this.getRenderedFrames());
        //BufferedImage xBRJoin = ResizeXBR.xBR(join, 4);
        try {
            ImageIO.write(join, "png", new File(path));
            //ImageIO.write(xBRJoin, "png", new File(MaMGame.getModVersionOfPath(path, "xbr")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return saveProperties(FileHelpers.changeExtesion(path, "cfg"), "openXeen sprite configuration file");
    }
}
