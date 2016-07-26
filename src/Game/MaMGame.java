package Game;

import Game.Map.*;
import Game.Monsters.MaMMonster;
import GameMechanics.Adventurers.Adventurer;
import GameMechanics.Adventurers.CharClass;
import GameMechanics.Adventurers.CharGender;
import GameMechanics.Adventurers.CharRace;
import GameMechanics.Magic.PartyEnchantments.IPartyEnchantment;
import Rendering.*;
import Toolbox.*;
import mamFiles.*;
import mamFiles.IOT.IoTccFileReader;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;
import mamFiles.SpriteHelpers.RenderPosHelper;
import mamFiles.WOX.WOXSurface;
import mamFiles.WOX.WOXccFileReader;
import org.joda.time.DateTime;
import static Toolbox.PointHelper.*;
import static mamFiles.SpriteHelpers.RenderPosHelper.RenderableType;

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
        if(ccFile instanceof WOXccFileReader)
        {
            world = new WoXWorld(this, ccFile);
        }
        else if (ccFile instanceof IoTccFileReader)
        {
            world = new IoTWorld(this, ccFile);
        }
        else
        {
        }

        //world.loadMaps();

        //world.setCurrentMap(1);
        world.setMazeView("xeen"); //TODO: real code


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
        partyPos = new Point(18, 53);
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

//    public MaMPallet getCurrentPallate() {
//        return world.getCurrentPallate();
//    }

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

            MaMMonster mon = (world.getMonsters().length > 0) ?
                    world.getMonsters()[testMonsterID%world.getMonsters().length]
                    : null;
            //view.addMonster(new Point(100,0), mon);

            view.setGround(world.getOutdoorEnvironmentSet(0).getGround());
            view.setSky(world.getOutdoorEnvironmentSet(0).getSky());

            //render the current view
            IReadonlyGrid<MaMTile> map = world.getCurrentMazeView();

            System.out.println("----------------------------------------------");
            if(map != null)
            {
                Point viewNormal = partyDir.getVector();
                Point viewNormalOrth = partyDir.turnRight().getVector();
                for(int vsY=0; vsY<10; vsY++)
                {
                    for(int vsX=-9; vsX<9; vsX++)
                    {
                        Point vsPos = new Point(vsX, vsY);

                        //vsY steps forward
                        Point fwd = new Point(vsY * viewNormal.x, vsY * viewNormal.y);

                        //vsX steps right
                        Point right = new Point(vsX * viewNormalOrth.x, vsX * viewNormalOrth.y);

                        //get world position
                        int xPos = partyPos.x + fwd.x + right.x;
                        int yPos = partyPos.y + fwd.y + right.y;

                        //render tile at xPos, yPos  to xsX, vsY
                        if(map.isValidLocation(xPos, yPos))
                        {
                            MaMTile tile =  map.get(xPos, yPos);
                            int surfaceNum = tile.getIndexBase();
                            MaMSurface surf = world.getOutdoorEnvironmentSet(0).getSurface(surfaceNum);

                            if(surf != null)
                            {
                                IRenderableGameObject surfaceLayer = surf.getSurfaceOverlay(vsPos);

                                if(surfaceLayer != null)
                                {
                                    // surfaceLayer is null if not renderable on screen, as the view sweep
                                    // is deliberately too large,  this is normal.
                                    int surfaceDepth = RenderPosHelper.getGlobalHelper().getDepth(RenderableType.SURFACE, vsY);
                                    view.addRenderable(new RenderablePos(8, 67, 1.0, surfaceDepth), surfaceLayer);
                                }
                                else
                                {
                                    //System.out.println("NULL surface overlay for view space :" + vsX + ", " + vsY);
                                }
                            }

                            int environNum = tile.getIndexMiddle();
                            IRenderableGameObject envobject = world.getOutdoorEnvironmentSet(0).getEnviron(environNum, 1);
                            if(envobject != null)
                            {
                                RenderablePos spPos = RenderPosHelper.getGlobalHelper().getOutdoorEnvPos(vsPos);
                                int frame = RenderPosHelper.getGlobalHelper().getOutdoorEnvFrame(vsPos);
                                if(spPos != null)
                                {
                                    RenderablePos deFutzed = deFutz(spPos);

                                    //that whole left/right side of screen frame thing
                                    String name = (envobject instanceof MaMSprite) ? ((MaMSprite)envobject).getName() : "?";
                                    view.addRenderable(deFutzed, envobject.asSprite().subSetOfFrames("frame of " + name, frame, 1));
                                    view.addRenderable(deFutzed.above().translate(0, 0),
                                            //IRenderableGameObject.fromText("<"+environNum+">" + name + ".", Color.white, 172,16));
                                    IRenderableGameObject.fromText(""+environNum, Color.white, 172,16));
                                }
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

    private RenderablePos deFutz(RenderablePos spPos)
    {
        int middle = (RenderPosHelper.screenSize.width/2) + 8;
        //return spPos.translate(middle-25, 0);//.scaleLocationOnly(1,-1).translate(0, 100);
        return spPos;
    }


    @Override
    public MaM2DMapComposition renderMap(int mapX, int mapY, int mapWidth, int mapHeight)
    {
        MaMMazeView mazeView= this.world.getCurrentMazeView();
        MaM2DMapComposition scene = new MaM2DMapComposition();
        if(mazeView != null)
        {
            int i=0;
            for(int x=0; x<mapWidth; x++)
            {
                for(int y=0; y<mapHeight; y++)
                {
                    MaMMazeFile maze = mazeView.getMazeFileForPoint(x, y);

                    //world space x and y
                    int wsX = x + mapX;
                    int wsY = y + mapY;

                    //tile info
                    MaMTile tile = mazeView.get(wsX, wsY);
                    if(tile==null)
                    {
                        continue;
                    }

                    RenderablePos tilePos = new RenderablePos(x*8, y*8, 1.0, RenderablePos.ScalePosition.TopLeft, 0);

                    //ground
                    int groundTile = tile.getIndexBase();
                    IRenderableGameObject tileSprite = maze.getEnvironmentSet().getMapTile(groundTile);
                    scene.addRenderable(tilePos, tileSprite);

                    if(maze.isOutdoors())
                    {
                        //environ
                        tilePos = tilePos.above();
                        int environIndex = tile.getIndexMiddle();
                        IRenderableGameObject environSprite = ((IMaMOutdoorEnvironmentSet)maze.getEnvironmentSet())
                                                                .getMapEnviron(environIndex);

                        if(environIndex != 0)
                        {
                            scene.addRenderable(tilePos, environSprite);
                        }
                    }

                    //objects
                    tilePos = tilePos.above();
                    int objectIndex = tile.getIndexTop();
                    IRenderableGameObject overlaySprite = maze.getEnvironmentSet().getMapObject(objectIndex);
                    if(objectIndex != 0)
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
                        //scene.addRenderable(tilePos, IRenderableGameObject.fromText("" + groundTile, Color.BLUE, 8, 8));

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
        IReadonlyGrid<MaMTile> map = world.getCurrentMazeView();

        if(map != null)
        {
            //clamp x and y so we are not rendering off the map.
            x = Math.min(map.getWidth()-width, Math.max(x, 0));
            y = Math.min(map.getHeight()-height, Math.max(y, 0));
            return renderMap(x, y, width, height);
        }
        else
        {
            return new MaM2DMapComposition();
        }
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
                if(!(world instanceof IoTWorld))
                {
                    IRenderableGameObject hpBar = world.getCcFile()
                                                   .getSpriteOrNull("HPBARS.ICN")
                                                   .subSetOfFrames("HP bar", i%4, 1);

                    int hpWidth = hpBar.getImage(0).getWidth();
                    int hpIndent = (faceSlotSize - hpWidth) / 2;
                    scene.addRenderable(pos.translate(hpIndent-faceIndent, 32).above(), hpBar);
                }

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
