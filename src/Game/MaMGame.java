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
import Toolbox.FileHelpers;
import Toolbox.Grid;
import Toolbox.MaMGameException;
import mamFiles.*;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;
import mamFiles.WOX.WOXSurface;
import mamFiles.WOX.WOXccFileReader;
import org.joda.time.DateTime;

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
    }

    public void doAction(MaMActions action)
    {
        switch (action)
        {
            case WalkForward:
                testMonsterID++;
                break;
            case WalkBackWard:
                testMonsterID--;
                break;
            case WalkLeft:
                break;
            case WalkRight:
                break;
            case TurnLeft:
                break;
            case TurnRight:
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
    }

    private void setupDefaultGameState()
    {
        party = new ArrayList<>();
        party.add(new Adventurer("", CharGender.Male, CharRace.HUMAN, new CharClass(), 1));
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

            MaMSurface surf = world.getCcFile().getSurface("DESERT.SRF");

            surf = world.getCcFile().getSurface("LAVA.SRF");
            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), ((WOXSurface)surf).getSurfaceOverlay(null));


            surf = world.getCcFile().getSurface("TFLR.SRF");
            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,0)));
            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,1)));
            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,2)));
            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,3)));
            view.addRenderable(new RenderablePos(8, 67, 1.0, 3), surf.getSurfaceOverlay(new Point(0,4)));

//            IRenderableGameObject rSurf = IRenderableGameObject.fromImage(surf.getImage(0));
//            view.addRenderable(new RenderablePos(8,109,1.0, 3), rSurf);
//
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(1));
//            view.addRenderable(new RenderablePos(8,109,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(2));
//            view.addRenderable(new RenderablePos(201,109,1.0, 3), rSurf);
//
//            //1 tile forward
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(3));
//            view.addRenderable(new RenderablePos(8,93,1.0, 3), rSurf);
//
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(4));
//            view.addRenderable(new RenderablePos(31,93,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(5));
//            view.addRenderable(new RenderablePos(169,93,1.0, 3), rSurf);
//
//            //2 tiles forward
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(6));
//            view.addRenderable(new RenderablePos(8,81,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(7));
//            view.addRenderable(new RenderablePos(8,81,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(8));
//            view.addRenderable(new RenderablePos(63,81,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(9));
//            view.addRenderable(new RenderablePos(145,81,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(10));
//            view.addRenderable(new RenderablePos(202,81,1.0, 3), rSurf);
//
//            //3 tiles forward
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(11));
//            view.addRenderable(new RenderablePos(8,73,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(12));
//            view.addRenderable(new RenderablePos(8,73,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(13));
//            view.addRenderable(new RenderablePos(30,73,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(14));
//            view.addRenderable(new RenderablePos(87,73,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(15));
//            view.addRenderable(new RenderablePos(129,73,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(16));
//            view.addRenderable(new RenderablePos(154,73,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(17));
//            view.addRenderable(new RenderablePos(181,73,1.0, 3), rSurf);
//
//            //4 tiles forward
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(18));
//            view.addRenderable(new RenderablePos(8,67,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(19));
//            view.addRenderable(new RenderablePos(38,67,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(20));
//            view.addRenderable(new RenderablePos(84,67,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(21));
//            view.addRenderable(new RenderablePos(103,67,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(22));
//            view.addRenderable(new RenderablePos(117,67,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(23));
//            view.addRenderable(new RenderablePos(117,67,1.0, 3), rSurf);
//
//            rSurf = IRenderableGameObject.fromImage(surf.getImage(24));
//            view.addRenderable(new RenderablePos(134,67,1.0, 3), rSurf);


            //view.addSurface(new  Point(0, 1), surf);
            for(int i=0; i<5; i++)
            {
                for(int j=-4; j<9; j++)
                {
                    Point p = new Point(i,j);
                    //IRenderableGameObject rSurf = IRenderableGameObject.fromImage(surf.getImage(p, 0));
                    //view.addRenderable(new RenderablePos(8,107-i*3,1.0, 3).hackMe(), rSurf);
                    //view.addSurface(p, surf);
                }
            }
            //CCFileFormatException.stub();

        } catch (CCFileFormatException e) {
            e.printStackTrace();
        } catch (MaMGameException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public MaM2DMapComposition renderMap(int mapX, int mapY, int mapWidth, int mapHeight) {
        MaMMazeFile maze = this.world.getCurrentMaze();
        MaM2DMapComposition scene = new MaM2DMapComposition();
        if(maze != null)
        {
            int i=0;
            for(int x=0; x<mapWidth; x++)
            {
                for(int y=0; y<mapHeight; y++)
                {
                    Grid<MaMTile> grid = maze.getMap();
                    MaMTile t = grid.get(x+mapX, y+mapY);

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
    public ISceneComposition renderHUDForWorld()
    {
        return world.renderHUDForWorld();
    }

    @Override
    public ISceneComposition renderParty() {
        MaM2DInsertionOrderComposition scene = new MaM2DInsertionOrderComposition();

        //  character faces
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
}
