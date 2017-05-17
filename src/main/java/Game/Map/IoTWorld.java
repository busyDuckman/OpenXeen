package Game.Map;

import Game.MaMGame;
import Rendering.*;
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

import java.awt.*;

/**
 * Created by duckman on 14/07/2016.
 */
public class IoTWorld extends MaMWorld
{
    protected IoTIndoorEnvironmentSet[] indoorEnvironmentSets;
    protected IoTOutdoorEnvironmentSet[] outdoorEnvironmentSets;
    IoTccFileReader ccFileIoT;

    public static volatile int[] knownSprites = new int[] {10660, 12350, 13634, 14284, 14641, 15134, 15630, 17378, 17712, 17799, 17895, 18230, 18356, 19938, 20578, 21009, 2110, 21350, 21365, 21406, 21413, 21563, 23076, 23124, 23268, 23522, 23615, 23631, 23663, 24247, 24407, 24439, 24455, 24471, 24492, 25991, 26724, 26807, 26810, 2779, 27886, 2795, 3018, 30293, 30745, 31563, 32236, 32583, 32867, 33886, 3475, 3495, 35153, 35606, 3568, 37092, 38836, 38971, 39195, 39211, 39227, 39243, 40005, 40553, 42071, 42864, 42884, 43344, 43419, 43499, 44430, 44604, 45998, 46071, 4820, 48295, 4832, 50744, 51550, 5176, 51818, 52366, 52893, 53967, 54613, 54820, 55213, 55687, 55861, 56927, 56943, 5717, 57653, 59018, 59465, 60227, 60703, 61011, 61078, 61094, 61110, 61142, 61555, 61918, 62691, 63255, 63857, 64585, 65146, 6586, 6855, 9655, 9751};
    public static volatile int currentTestSprite = -1;
    public static volatile int currentTestSpriteFrame = -1;

    public IoTWorld(MaMGame game, MaMCCFileReader ccFileReader) throws CCFileFormatException {
        super(game, ccFileReader);

        //cast a copy to make code easier
        ccFileIoT = (IoTccFileReader) ccFileReader;

        indoorEnvironmentSets = new IoTIndoorEnvironmentSet[1];
        outdoorEnvironmentSets = new IoTOutdoorEnvironmentSet[1];

        outdoorEnvironmentSets[0] = new IoTOutdoorEnvironmentSet();

    }

//    @Override
//    protected MaMPallet getDefaultPallate() throws CCFileFormatException
//    {
//
//        //return MaMPallet.getDefaultMaMPallate();
//    }

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

            if((currentTestSprite >= 0) && (currentTestSprite < knownSprites.length))
            {
                if(currentTestSpriteFrame < 0)
                {
                    scene.addRenderable(new RenderablePos(0,0,2,0), ccFile.getSprite(knownSprites[currentTestSprite]));
                }
                else
                {
                    MaMSprite sprite = ccFile.getSprite(knownSprites[currentTestSprite]);
                    int frameNum = currentTestSpriteFrame%sprite.getRenderedFrames().length;
                    IRenderableGameObject frame = sprite.subSetOfFrames("test sprite", frameNum, 1);
                    scene.addRenderable(new RenderablePos(0,0,2.0,0), frame);
                }
            }

        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }

        return scene;
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
