package Game.Map;

import Game.MaMGame;
import Rendering.ISceneComposition;
import Rendering.MaM2DInsertionOrderComposition;
import Rendering.RenderablePos;
import Toolbox.FileHelpers;
import mamFiles.CCFileFormatException;
import mamFiles.CCFileReader;
import mamFiles.MaMPallet;
import mamFiles.MaMRawImage;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.WOX.WoXIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.WOX.WoXOutdoorEnvironmentSet;
import mamFiles.WOX.CCFileReaderWOX;

/**
 * Created by duckman on 5/06/2016.
 */
public class WoXWorld extends MaMWorld
{
    public enum WoxVariant
    {
        DARK_SIDE {

        },
        CLOUDS {

        },
        SWORDS {

        },
        UNKNOWN;


        MaMRawImage hud=null;

        public MaMRawImage getHUD(WoXWorld world) throws CCFileFormatException {
            if(hud == null) {
                //adding transparency to pallet
                String palFile = world.ccFileWox().getVariant().getDefaultPallate();
                MaMPallet pal = world.ccFile.getPallet(palFile).withTransperency(0);
                hud = world.ccFile.getRawImage("BACK.RAW", pal);
            }
            return hud;
        }
    }

    protected CCFileReaderWOX ccFileCur;
    protected final CCFileReaderWOX ccFileWox() {return (CCFileReaderWOX)ccFile;}
    protected final CCFileReaderWOX ccFileAnimationsWox() {return (CCFileReaderWOX)ccFileAnimations;}
    protected WoxVariant variant;
    protected WoXIndoorEnvironmentSet[] indoorEnvironmentSets;
    protected WoXOutdoorEnvironmentSet[] outdoorEnvironmentSets;

    public WoXWorld(MaMGame game, CCFileReader ccFileReader) throws CCFileFormatException {
        super(game, ccFileReader);
        String ccPath = FileHelpers.getParentDirectory(ccFile.getFilePath());
        variant = ccFileWox().getVariant().getWoxVariant();
        //todo? clouds?
        ccFileCur = CCFileReaderWOX.open(FileHelpers.join(ccPath, "DARK.CUR"));
        ccFileAnimations = CCFileReaderWOX.open(FileHelpers.join(ccPath, "INTRO.CC"));
        outdoorEnvironmentSets = new WoXOutdoorEnvironmentSet[] { new WoXOutdoorEnvironmentSet(variant, ccFile) };
        indoorEnvironmentSets = WoXIndoorEnvironmentSet.getEnvironmentSets(variant, ccFile);
    }

    @Override
    protected MaMPallet getDefaultPallate() throws CCFileFormatException {
        return ccFileWox().getPallet(ccFileWox().getVariant().getDefaultPallate());
    }

    public CCFileReaderWOX getCCFileCur() {
        return ccFileCur;
    }

    @Override
    public IMaMIndoorEnvironmentSet getIndoorEnvironmentSet(int index) {
        return indoorEnvironmentSets[index];
    }

    @Override
    public IMaMEnvironmentSet getOutdoorEnvironmentSet(int index) {
        return outdoorEnvironmentSets[index];
    }

    @Override
    public ISceneComposition renderHUDForWorld()
    {
        MaM2DInsertionOrderComposition scene = new MaM2DInsertionOrderComposition();
        try {
            scene.addRenderable(new RenderablePos(0,0, 1.0, RenderablePos.ScalePosition.TopLeft, 0),
                    variant.getHUD(this));
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }

        return scene;
    }

    @Override
    public void loadMaps() throws CCFileFormatException
    {
        int missCount=0;
        for (int i = 0; (i < 500)&&(missCount<10); i++)
        {
            String mazeName = getMazeName(i);
            if(ccFileCur.fileExists(mazeName))
            {
                //temporary name
                String name = "maze {" + i +"}";

                if(ccFile.fileExists(getAreaNameFile(i)))
                {
                    name = ccFile.getText(getAreaNameFile(i)).getText();
                }

                MazeFiles.put(i, ccFileCur.getMapFile(mazeName, this, i));

                //reset miss count
                missCount = 0;
            }
            else
            {
                missCount++;
            }
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    // Map names etc
    //------------------------------------------------------------------------------------------------------------------
    protected static String makeMazeFileName(String prefix, String ext, int id) throws CCFileFormatException
    {
        CCFileFormatException.assertFalse(id < 0);
        CCFileFormatException.assertFalse(id >= 1000);
        String s = "00000"+id;
        s = s.substring(s.length() - 4);
        if(id >= 100)
        {
            s = "X" + s.substring(1);
        }
        s = (prefix != null)? (prefix + s) : s;
        s = (ext != null)? (s + "." + ext) : s;
        return s;
    }

    @Override
    public String getMazeName(int id) throws CCFileFormatException {
        return makeMazeFileName("MAZE", "DAT", id);
    }
    @Override
    public String getScriptedEventsName(int id) throws CCFileFormatException {
        return makeMazeFileName("MAZE", "EVT", id);
    }
    @Override
    public String getMonsterLayoutFile(int id) throws CCFileFormatException {
        return makeMazeFileName("MAZE", "MOB", id);
    }
    @Override
    public String getHeadingFile(int id) throws CCFileFormatException {
        //AAZE = wtf? I assume hashcode collisions in the cc file when too many similar names were used.
        return makeMazeFileName("AAZE", "HED", id);
    }
    @Override
    public String getAreaNameFile(int id) throws CCFileFormatException {
        //TODO: clouds?
        return makeMazeFileName("DARK", "TXT", id);
    }
    @Override
    public String getEventTextStringsFile(int id) throws CCFileFormatException {
        return makeMazeFileName("AAZE", "TXT", id);
    }
    @Override
    public String getMapNameFile(int id) throws CCFileFormatException {
        //TODO: clouds?
        return makeMazeFileName("DARK", "HED", id);
    }


    @Override
    public void close() throws Exception {
        super.close();
        this.ccFileCur.close();
    }
}
