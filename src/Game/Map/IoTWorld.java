package Game.Map;

import Game.MaMGame;
import Rendering.IRenderableGameObject;
import Rendering.ISceneComposition;
import Rendering.MaM2DInsertionOrderComposition;
import Rendering.RenderablePos;
import mamFiles.CCFileFormatException;
import mamFiles.IOT.IoTSpriteFile;
import mamFiles.IOT.IoTccFileReader;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMPallet;
import mamFiles.MaMSprite;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IOT.IoTIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IOT.IoTOutdoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.WOX.WoXIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.WOX.WoXOutdoorEnvironmentSet;

/**
 * Created by duckman on 14/07/2016.
 */
public class IoTWorld extends MaMWorld
{
    protected IoTIndoorEnvironmentSet[] indoorEnvironmentSets;
    protected IoTOutdoorEnvironmentSet[] outdoorEnvironmentSets;
    IoTccFileReader ccFileIoT;

    public IoTWorld(MaMGame game, MaMCCFileReader ccFileReader) throws CCFileFormatException {
        super(game, ccFileReader);

        //cast a copy to make code easier
        ccFileIoT = (IoTccFileReader) ccFileReader;

        indoorEnvironmentSets = new IoTIndoorEnvironmentSet[1];
        outdoorEnvironmentSets = new IoTOutdoorEnvironmentSet[1];

        outdoorEnvironmentSets[0] = new IoTOutdoorEnvironmentSet();

    }

    @Override
    protected MaMPallet getDefaultPallate() throws CCFileFormatException {
        return MaMPallet.getDefaultMaMPallate();
    }

    @Override
    public void loadMaps() throws CCFileFormatException {

    }

    @Override
    public void loadMazeViews() throws CCFileFormatException {

    }

    @Override
    public IMaMIndoorEnvironmentSet getIndoorEnvironmentSet(int index) {
        return indoorEnvironmentSets[index];
    }

    @Override
    public IMaMOutdoorEnvironmentSet getOutdoorEnvironmentSet(int index) {
        return outdoorEnvironmentSets[index];
    }

    @Override
    public ISceneComposition renderHUDForWorld() {
        MaM2DInsertionOrderComposition scene = new MaM2DInsertionOrderComposition();
        try {
            IRenderableGameObject background = ccFile.getRawImage(26810);
            scene.addRenderable(new RenderablePos(0,0,1,0), background);
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }

        return scene;
    }

    @Override
    public String getMazeName(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public String getScriptedEventsName(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public String getMonsterLayoutFile(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public String getHeadingFile(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public String getAreaNameFile(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public String getEventTextStringsFile(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public String getMapNameFile(int id) throws CCFileFormatException {
        return null;
    }

    @Override
    public MaMSprite getNPCFaceOrNull(int id) {
        return null;
    }

    @Override
    public MaMSprite getPlayerFaceOrNull(int id) {
        try {
            return  ccFile.getSprite(26807).subSetOfFrames("Face " + id, id%3, 1);
        } catch (CCFileFormatException e) {
            return null;
        }
    }
}
