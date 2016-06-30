package Game;

import Game.Map.MaMTile;
import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import Game.Monsters.MaMMonster;
import GameMechanics.Adventurers.Adventurer;
import GameMechanics.Adventurers.CharClass;
import GameMechanics.Adventurers.CharGender;
import GameMechanics.Adventurers.CharRace;
import GameMechanics.Magic.PartyEnchantments.IPartyEnchantment;
import Rendering.*;
import Toolbox.*;
import mamFiles.*;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;
import mamFiles.WOX.WOXSurface;
import mamFiles.WOX.WOXccFileReader;
import org.joda.time.DateTime;
import static Toolbox.PointHelper.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by duckman on 15/05/2016.
 */
public class MaMGame implements IMaMGame
{
    //-------------------------------------------------------------------------------------------------
    // Instance data
    //-------------------------------------------------------------------------------------------------
    int gold;
    int food;
    DateTime dateTime;
    MaMGameStates gameState;
    List<Adventurer> party;


    //everything that is not the party
    protected MaMWorld world;
    private List<IPartyEnchantment> activePartyEnchantments;
    Point partyPos;
    Direction partyDir;
    private boolean debugInfo;

    //-------------------------------------------------------------------------------------------------
    // Sprites
    //-------------------------------------------------------------------------------------------------
    protected IRenderableGameObject[] mapArrow;


    //-------------------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------------------
    public MaMGame(MaMCCFileReader ccFile) throws CCFileFormatException
    {
        loadWorld(ccFile);

        activePartyEnchantments = new ArrayList<>();

        setupDefaultGameState();
    }

    public MaMGame(String ccFilePath) throws CCFileFormatException
    {
        MaMCCFileReader ccFile = WOXccFileReader.open(ccFilePath);
        loadWorld(ccFile);

        activePartyEnchantments = new ArrayList<>();
        setupDefaultGameState();
    }

    //-------------------------------------------------------------------------------------------------
    // World
    //-------------------------------------------------------------------------------------------------
    protected void loadWorld(MaMCCFileReader ccFile) throws CCFileFormatException
    {
        world = new WoXWorld(this, ccFile);
        world.loadMaps();
        world.setCurrentMap(1);

        // load sprites
        //------------------

        //arrow frames happen to be in the same order as the Direction enum.
        mapArrow = ccFile.getSprite("GLOBAL.ICN")
                        .subSetOfFrames("MapArrow", 1, 4)
                .applyAlphaTransform(128, ImageHelpers.AlphaTransforms.SUB)
                        //.applyAlphaTransform(64, ImageHelpers.AlphaTransforms.ADD_NOISE)
                        .eachFrameAsRenderable();
    }

    private void setupDefaultGameState()
    {
        party = new ArrayList<>();
        party.add(new Adventurer("", CharGender.Male, CharRace.HUMAN, new CharClass(), 1));
        partyPos = new Point(4, 4);
        partyDir = Direction.UP;
    }

    public void doAction(MaMActions action) {
        switch (action) {
            case WalkForward:
                //testMonsterID++;
                this.partyPos = navigate(partyPos, partyDir, 1);
                break;
            case WalkBackWard:
                //testMonsterID--;
                this.partyPos = navigate(partyPos, partyDir, -1);
                break;
            case WalkLeft:
                this.partyPos = navigate(partyPos, partyDir.turnLeft(), 1);
                break;
            case WalkRight:
                this.partyPos = navigate(partyPos, partyDir.turnRight(), 1);
                break;
            case TurnLeft:
                partyDir = partyDir.turnLeft();
                break;
            case TurnRight:
                partyDir = partyDir.turnRight();
                break;
            case Inspect:
                break;
            case Shoot:
                break;
            case Bash:
                break;
            case Run:
                break;
            case Spell:
                break;
            case MelleAttack:
                break;
            case MelleShield:
                break;
            case Sleep:
                break;
        }
        System.out.println(getDebugInfo());
    }


    //-------------------------------------------------------------------------------------------------
    // Getters and Setters
    //-------------------------------------------------------------------------------------------------
    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public MaMGameStates getGameState() {
        return gameState;
    }

    @Override
    public MaMWorld getWorld() {
        return world;
    }

    public MaMPallet getCurrentPallate() {
        return world.getCurrentPallate();
    }

    @Override
    public double getDangerLevel() {
        return 0.0;
    }

    @Override
    public char getCompassValue() {
        return ' ';
    }

    @Override
    public boolean getSecretDoor() {
        return false;
    }

    @Override
    public List<IPartyEnchantment> getActivePartyEnchantments() {
        return activePartyEnchantments;
    }

    //-------------------------------------------------------------------------------------------------
    // Rendering
    //-------------------------------------------------------------------------------------------------

    volatile int testMonsterID = 0;

    @Override
    public MaM3DSceneComposition render()
    {
        MaM3DSceneComposition view = new MaM3DSceneComposition();

        try
        {
            MaMMonster mon = world.getMonsters()[testMonsterID%world.getMonsters().length];
            //view.addMonster(new Point(100,0), mon);

            view.setGround(world.getOutdoorEnvironmentSet(0).getGround());
            //.getSprite("CAVE.GND"));
            view.setSky(world.getOutdoorEnvironmentSet(0).getSky());

            //MaMSurface surf = world.getCcFile().getSurface("DESERT.SRF");

//            surf = world.getCcFile().getSurface("LAVA.SRF");
//            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), ((WOXSurface)surf).getSurfaceOverlay(null));
//
//
//            surf = world.getCcFile().getSurface("TFLR.SRF");
//            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,0)));
//            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,1)));
//            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,2)));
//            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,3)));
//            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,4)));


            //render the current view
            Grid<MaMTile> map = world.getCurrentMaze().getMap();
            Point viewNormal = partyDir.getVector();
            Point viewNormalOrth = partyDir.turnRight().getVector();
            for(int vsY=0; vsY<10; vsY++)
            {
                for(int vsX=-9; vsX<9; vsX++)
                {
                    //vsY steps forward
                    Point fwd = new Point(vsY * viewNormal.x, vsY * viewNormal.y);

                    //vsX steps right
                    Point right = new Point(vsX * viewNormalOrth.x, vsX * viewNormalOrth.y);

                    //get world position
                    int xPos = partyPos.x + fwd.x + right.x;
                    int yPos = partyPos.y + fwd.y + right.y;

                    //render item at xPos, yPos  to xsX, vsY
                    if(map.isValidLocation(xPos, yPos))
                    {
                        MaMTile tile =  map.get(xPos, yPos);
                        int surfaceNum = tile.getIndexBase();
                        MaMSurface surf = world.getOutdoorEnvironmentSet(0).getSurface(surfaceNum);

                        if(surf != null)
                        {

                            IRenderableGameObject surfaceLayer = surf.getSurfaceOverlay(new Point(vsX, vsY));

                            if(surfaceLayer != null)
                            {
                                // surfaceLayer is null if not renderable on screen, as the view sweep
                                // we make is deliberately too large so this is normal.
                                view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surfaceLayer);
                            }
                        }
                    }

                }
            }

        } catch (CCFileFormatException e) {
            e.printStackTrace();
        } catch (MaMGameException e) {
            e.printStackTrace();
        }

        return view;
    }


    @Override
    public MaM2DMapComposition renderMap(int mapX, int mapY, int mapWidth, int mapHeight)
    {
        MaMMazeFile maze = this.world.getCurrentMaze();
        MaM2DMapComposition scene = new MaM2DMapComposition();
        if(maze != null)
        {
            int i=0;
            for(int x=0; x<mapWidth; x++)
            {
                for(int y=0; y<mapHeight; y++)
                {
                    //world space x and y
                    int wsX = x + mapX;
                    int wsY = y + mapY;

                    //tile info
                    Grid<MaMTile> grid = maze.getMap();
                    MaMTile t = grid.get(wsX, wsY);

                    RenderablePos tilePos = new RenderablePos(x*8, y*8, 1.0, RenderablePos.ScalePosition.TopLeft, 0);

                    //ground
                    int groundTile = t.getIndexBase() + 5;
                    IRenderableGameObject tileSprite = maze.getEnvironmentSet().getMapTile(groundTile);
                    scene.addRenderable(tilePos, tileSprite);

                    if(maze.isOutdoors())
                    {
                        //environ
                        tilePos = tilePos.above();
                        int environIndex = t.getIndexMiddle()+1;
                        IRenderableGameObject environSprite = ((IMaMOutdoorEnvironmentSet)maze.getEnvironmentSet())
                                                                .getMapEnviron(environIndex);
                        if(environIndex != 4)
                        {
                            scene.addRenderable(tilePos, environSprite);
                        }
                    }

                    //objects
                    tilePos = tilePos.above();
                    int objectIndex = t.getIndexTop()+3;
                    IRenderableGameObject overlaySprite = maze.getEnvironmentSet().getMapObject(objectIndex);
                    if(objectIndex != 8)
                    {
                        scene.addRenderable(tilePos, overlaySprite);
                    }

                    //draw arrow
                    if(equalsXY(partyPos, wsX, wsY))
                    {
                        tilePos = tilePos.above();
                        scene.addRenderable(tilePos, mapArrow[partyDir.ordinal()]);
                    }

                    if(GlobalSettings.INSTANCE.debugMode())
                    {
                        tilePos = tilePos.above();
                        //scene.addRenderable(tilePos, IRenderableGameObject.fromText("" + objectIndex, Color.BLUE, 8, 8));

                    }
                    i++;
                }
            }
        }

        return scene;
    }

    @Override
    public MaM2DMapComposition renderWizardEyeView(int width, int height) {
        int x = partyPos.x - (width  / 2);
        int y = partyPos.y - (height / 2);
        Grid<MaMTile> map = world.getCurrentMaze().getMap();

        //clamp x and y so we are not rendering off the map.
        x = Math.min(map.getWidth()-1-width, Math.max(x, 0));
        y = Math.min(map.getHeight()-1-height, Math.max(y, 0));
        return renderMap(x, y, width, height);
    }

    @Override
    public ISceneComposition renderHUDForWorld()
    {
        return world.renderHUDForWorld();
    }

    @Override
    public ISceneComposition renderParty() {
        MaM2DInsertionOrderComposition scene = new MaM2DInsertionOrderComposition();

        //  Character faces
        // ----------------------------------
        MaMSprite[] faces = new MaMSprite[6];

        //dimensions
        //int spacing = 36;
        int partyAreaStart = 8;
        int faceAreaLen = 216;
        int maxPartySize = 6;

        for (int i = 0; i < faces.length; i++) {
            try
            {
                faces[i] = world.getPlayerFaceOrNull(i+1).subSetOfFrames("normal face for " + i, 0, 1);

                //calculate pos
                int faceWidth = faces[i].getRenderedFrames()[0].getWidth();
                int faceSlotSize = faceAreaLen / maxPartySize;
                int faceIndent = (faceSlotSize - faceWidth) / 2;

                //face
                RenderablePos pos = new RenderablePos(
                        (i*faceSlotSize) + faceIndent + partyAreaStart,
                        150, 1.0, RenderablePos.ScalePosition.Top, 1);
                scene.addRenderable(pos, faces[i]);

                //hp bar
                IRenderableGameObject hpBar = world.getCcFile()
                                                   .getSpriteOrNull("HPBARS.ICN")
                                                   .subSetOfFrames("HP bar", i%4, 1);

                int hpWidth = hpBar.getImage(0).getWidth();
                int hpIndent = (faceSlotSize - hpWidth) / 2;
                scene.addRenderable(pos.translate(hpIndent-faceIndent, 32).above(), hpBar);

            }
            catch (CCFileFormatException e)
            {
                //e.printStackTrace();
            }
        }

        return scene;
    }

    public static String getModVersionOfPath(String path, String modName)
    {
        String ext = FileHelpers.getFileExtension(path);
        if((ext == null) || (ext.trim().isEmpty()))
        {
            return FileHelpers.changeExtesion(path, modName);
        }
        return FileHelpers.changeExtesion(path, modName + "." + ext);
    }

    @Override
    public void close() throws Exception {
        if(world != null) {
            world.close();
        }
    }

    public String getDebugInfo() {
        return "pos=" + point2String(partyPos) + ", dir=" + partyDir;
    }
}
