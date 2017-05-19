package Game.Map;

import Game.MaMGame;
import Rendering.ISceneComposition;
import Rendering.MaM2DInsertionOrderComposition;
import Rendering.RenderablePos;
import Toolbox.Direction;
import Toolbox.FileHelpers;
import Toolbox.PointHelper;
import Toolbox.Tag;
import mamFiles.*;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.WOX.WoXIndoorEnvironmentSet;
import mamFiles.SpriteHelpers.EnvironmentSet.WOX.WoXOutdoorEnvironmentSet;
import mamFiles.WOX.WOXccFileReader;

import java.awt.*;
import java.util.*;

/**
 * Created by duckman on 5/06/2016.
 */
public class WoXWorld extends MaMWorld
{
    public enum WoxVariant
    {
        DARK_SIDE {
            @Override
            public String getIntroCCFileName() {
                return "intro.cc";
            }

            @Override
            public String getCurCCFileName() {
                return "dark.cur";
            }
        },
        CLOUDS {
            @Override
            public String getIntroCCFileName() {
                return "intro.cc";
            }

            @Override
            public String getCurCCFileName() {
                return "xeen.cur";
            }
        },
        SWORDS {
            @Override
            public String getIntroCCFileName() {
                return null;
            }

            @Override
            public String getCurCCFileName() {
                return null;
            }
        },
        UNKNOWN {
            @Override
            public String getIntroCCFileName() {
                return null;
            }

            @Override
            public String getCurCCFileName() {
                return null;
            }
        };


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

        public abstract String getIntroCCFileName();
        public abstract String getCurCCFileName();
    }

    protected WOXccFileReader ccFileCur;
    protected final WOXccFileReader ccFileWox() {return (WOXccFileReader)ccFile;}
    protected final WOXccFileReader ccFileAnimationsWox() {return (WOXccFileReader)ccFileAnimations;}
    protected WoxVariant variant;
    protected WoXIndoorEnvironmentSet[] indoorEnvironmentSets;
    protected WoXOutdoorEnvironmentSet[] outdoorEnvironmentSets;

    public WoXWorld(MaMGame game, MaMCCFileReader ccFileReader) throws CCFileFormatException {
        super(game, ccFileReader);
        String ccPath = FileHelpers.getParentDirectory(ccFile.getFilePath());
        variant = ccFileWox().getVariant().getWoxVariant();
        //todo? clouds?
        ccFileCur = WOXccFileReader.open(FileHelpers.join(ccPath, variant.getCurCCFileName()));
        ccFileAnimations = WOXccFileReader.open(FileHelpers.join(ccPath, variant.getIntroCCFileName()));
        outdoorEnvironmentSets = new WoXOutdoorEnvironmentSet[] { new WoXOutdoorEnvironmentSet(variant, ccFile) };
        indoorEnvironmentSets = WoXIndoorEnvironmentSet.getEnvironmentSets(variant, ccFile);

        //prepare the world (done here, because ccFileCur is not ready in the parent constructor
        loadMaps();
        loadMazeViews();
}

//    @Override
//    protected MaMPallet getDefaultPallate() throws CCFileFormatException {
//        try
//        {
//            return ccFileWox().getPallet(ccFileWox().getVariant().getDefaultPallate());
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Problem getting default pallate [getDefaultPallate()], using standard.");
//            return MaMPallet.getDefaultMaMPallate();
//        }
//    }

    public WOXccFileReader getCCFileCur() {
        return ccFileCur;
    }

    public WoxVariant getVariant() {
        return variant;
    }

    public WoXIndoorEnvironmentSet[] getIndoorEnvironmentSets() {
        return indoorEnvironmentSets;
    }

    public WoXOutdoorEnvironmentSet[] getOutdoorEnvironmentSets() {
        return outdoorEnvironmentSets;
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
    public ISceneComposition renderHUDForWorld()
    {
        MaM2DInsertionOrderComposition scene = new MaM2DInsertionOrderComposition();
        try {
            scene.addRenderable(new RenderablePos(0,0, 1.0, RenderablePos.ScalePosition.TopLeft, 0),
                    variant.getHUD(this));

            //  screen helpers
            // ----------------------------------
            MaMSprite hudBorderGuys = ccFile.getSprite("BORDER.ICN");
            MaMSprite hudBorderBat = hudBorderGuys.subSetOfFrames("hudBorderBat", 40, 12);
            MaMSprite hudBorderFaceOnLefft = hudBorderGuys.subSetOfFrames("hudBorderFaceOnLeft", 0, 8);
            MaMSprite hudBorderFaceOnRight = hudBorderGuys.subSetOfFrames("hudBorderFaceOnRight", 8, 8);
            MaMSprite hudBorderWingedMonkey = hudBorderGuys.subSetOfFrames("hudBorderWingedMonkey", 16, 12);
            MaMSprite hudBorderGeko = hudBorderGuys.subSetOfFrames("hudBorderGeko", 28, 12);

            scene.addRenderable(new RenderablePos(107, 9,  1.0, RenderablePos.ScalePosition.Top, 1), hudBorderBat);
            scene.addRenderable(new RenderablePos(0,   32, 1.0, RenderablePos.ScalePosition.Top, 1), hudBorderFaceOnLefft);
            scene.addRenderable(new RenderablePos(215, 32, 1.0, RenderablePos.ScalePosition.Top, 1), hudBorderFaceOnRight);
            scene.addRenderable(new RenderablePos(0,   82, 1.0, RenderablePos.ScalePosition.Top, 1), hudBorderWingedMonkey);
            scene.addRenderable(new RenderablePos(194, 91, 1.0, RenderablePos.ScalePosition.Top, 1), hudBorderGeko);


        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }

        return scene;
    }

//    Map<Integer, MaMSprite> npcFaceSprites = null;
//    Map<Integer, MaMSprite> playeFaceSprites = null;

    @Override
    public MaMSprite getNPCFaceOrNull(int id) {
        return ccFile.getSpriteOrNull("FACE" + ((id < 10) ? "0" : "") + id + ".FAC");
//        if(npcFaceSprites == null)
//        {
//            npcFaceSprites = BlindIndexMaper.findMapping(I -> ccFile.getSpriteOrNull("FACE" + ((id < 10) ? "0" : "") + id + ".FAC"),
//                                                        0, 200, 10);
//        }
//        return npcFaceSprites.getOrDefault(id, null);
    }

    @Override
    public MaMSprite getPlayerFaceOrNull(int id) {
        return ccFile.getSpriteOrNull("CHAR" + ((id < 10) ? "0" : "") + id + ".FAC");
    }

    @Override
    public void loadMaps() throws CCFileFormatException
    {
        int missCount=0;
        //TODO: > 100 (500)
        for (int i = 0; (i < 100)&&(missCount<10); i++)
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

                mazeFiles.put(i, ccFileCur.getMapFile(mazeName, this, i));

                //reset miss count
                missCount = 0;
            }
            else
            {
                missCount++;
            }
        }
    }

    @Override
    public void loadMazeViews() throws CCFileFormatException {
        //TODO: Quick hack to just load the Xeen over-world
        mazeViews.put("xeen", growMazeViewFromMaze(mazeFiles.get(1)));
    }

    private MaMMazeView growMazeViewFromMaze(MaMMazeFile mazeFile) {
        //build a mapping of points to mazes
        Map<Point, MaMMazeFile> mazeLut = traverseMaps(mazeFile);

        //TODO: Get bounds from mazeLut
        MaMMazeView mazeView = new MaMMazeView(16, 16, 6, 4,
                                    P -> mazeLut.getOrDefault(P, null),
                                    P -> null,
                                    P-> null);

        return mazeView;
    }

    private Map<Point, MaMMazeFile> traverseMaps(MaMMazeFile mazeFile) {
        Map<Point, MaMMazeFile> mazeLut = new HashMap<>();
        //a stack and checklist, to manage a node traversal
        Stack<Tag<Point, MaMMazeFile>> todoSet = new Stack<>();
        Set<Point> doneSet = new HashSet<>();
        todoSet.push(new Tag<>(new Point(0, 0), mazeFile));

        //traverse nodes
        while(!todoSet.isEmpty())
        {
            Tag<Point, MaMMazeFile> map = todoSet.pop();
            int id = map.getTag().getMazeID();
            if(!doneSet.contains(map.getKey()))
            {
                doneSet.add(map.getKey());

                MaMMazeFile maze = this.mazeFiles.getOrDefault(id, null);
                if(maze != null)
                {
                    //add maze to collection
                    mazeLut.put(map.getKey(), map.getTag());

                    //explore the 4 adjoining notes
                    for(Direction dir : Direction.values())
                    {
                        Point nextPos = PointHelper.navigate(map.getKey(), dir, 1);
                        int nextID = maze.getJoiningMaps()[dir.ordinal()];
                        if(nextID >= 0)
                        {
                            MaMMazeFile nextMaze = this.mazeFiles.getOrDefault(nextID, null);
                            if(nextMaze != null)
                            {
                                todoSet.push(new Tag<>(nextPos, nextMaze));
                            }
                        }
                    }
                }
            }
        }

        return mazeLut;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Map names etc
    //------------------------------------------------------------------------------------------------------------------
    protected static String makeMazeFileName(String prefix, String ext, int id) throws CCFileFormatException
    {
        CCFileFormatException.assertFalse(id < 0, "makeMazeFileName() id < 0");
        CCFileFormatException.assertFalse(id >= 1000, "makeMazeFileName() id >= 1000");
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

    public String getMazeName(int id) throws CCFileFormatException {
        return makeMazeFileName("MAZE", "DAT", id);
    }

    public String getScriptedEventsName(int id) throws CCFileFormatException {
        return makeMazeFileName("MAZE", "EVT", id);
    }

    public String getMonsterLayoutFile(int id) throws CCFileFormatException {
        return makeMazeFileName("MAZE", "MOB", id);
    }

    public String getHeadingFile(int id) throws CCFileFormatException {
        //AAZE = wtf? I assume hashcode collisions in the cc file when too many similar names were used.
        return makeMazeFileName("AAZE", "HED", id);
    }

    public String getAreaNameFile(int id) throws CCFileFormatException {
        //TODO: clouds?
        return makeMazeFileName("DARK", "TXT", id);
    }

    public String getEventTextStringsFile(int id) throws CCFileFormatException {
        return makeMazeFileName("AAZE", "TXT", id);
    }

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
