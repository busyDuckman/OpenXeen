package mamFiles;

import Game.IGameEntity;
import Rendering.AnimationSettings;
import Rendering.IRenderableGameObject;
import Toolbox.Direction;

import mamFiles.WOX.WOXSpriteFile;

import java.awt.*;
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
    }


    @Override
    public IRenderableGameObject getView( Point mapPosRelative, Direction viewDir) {
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
        //TODO
        return false;
    }
}
