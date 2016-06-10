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

        }, UNKNOWN;


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


    public WoXWorld(MaMGame game, CCFileReader ccFileReader) throws CCFileFormatException {
        super(game, ccFileReader);
        String ccPath = FileHelpers.getParentDirectory(ccFile.getFilePath());
        variant = ccFileWox().getVariant().getWoxVariant();
        //todo? clouds?
        ccFileCur = CCFileReaderWOX.open(FileHelpers.join(ccPath, "DARK.CUR"));
        ccFileAnimations = CCFileReaderWOX.open(FileHelpers.join(ccPath, "INTRO.CC"));
    }

    @Override
    protected MaMPallet getDefaultPallate() throws CCFileFormatException {
        return ccFileWox().getPallet(ccFileWox().getVariant().getDefaultPallate());
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
    //ref Dictionary<int, string> mapNameLut, ref Dictionary<string, int> mapIDLut)
    {
        int missCount=0;
        for (int i = 0; (i < 500)&&(missCount<10); i++)
        {
            if(ccFile.fileExists(getMazeName(i)))
            {
                //temporary name
                String name = "maze {" + i +"}";

                if(ccFileCur.fileExists(getAreaNameFile(i)))
                {
                    name = ccFileCur.getText(getAreaNameFile(i)).getText();
                }

                if(ccFile.fileExists(getMazeName(i)))
                {
                    MazeFiles.put(i, ccFile.getMapFile(getMazeName(i), this));
                }

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
    protected static String makeMazeFileName(String prefix, String ext, int id)
    {
        return prefix + String.format("%1s%2$3d.", (id < 100) ? "0" : "X", id) + ext;
    }

    @Override
    public String getMazeName(int id)
    {
        return makeMazeFileName("MAZE", "DAT", id);
    }
    @Override
    public String getScriptedEventsName(int id)
    {
        return makeMazeFileName("MAZE", "EVT", id);
    }
    @Override
    public String getMonsterLayoutFile(int id)
    {
        return makeMazeFileName("MAZE", "MOB", id);
    }
    @Override
    public String getHeadingFile(int id)
    {
        //AAZE = wtf? I assume hashcode collisions in the cc file when too many similar names were used.
        return makeMazeFileName("AAZE", "HED", id);
    }
    @Override
    public String getAreaNameFile(int id)
    {
        //TODO: clouds?
        return makeMazeFileName("DARK", "TXT", id);
    }
    @Override
    public String getEventTextStringsFile(int id)
    {
        return makeMazeFileName("AAZE", "TXT", id);
    }
    @Override
    public String getMapNameFile(int id)
    {
        //TODO: clouds?
        return makeMazeFileName("DARK", "HED", id);
    }


    @Override
    public void close() throws Exception {
        super.close();
        this.ccFileCur.close();
    }
}
