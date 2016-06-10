package Game;

import Game.Map.MaMTile;
import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import Game.Monsters.MaMMonster;
import GameMechanics.Magic.PartyEnchantments.IPartyEnchantment;
import Rendering.*;
import Toolbox.FileHelpers;
import Toolbox.Grid;
import mamFiles.*;
import mamFiles.WOX.CCFileReaderWOX;
import org.joda.time.DateTime;

import java.awt.*;
import java.awt.image.BufferedImage;
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


    //everything that is not the party
    protected MaMWorld world;
    private List<IPartyEnchantment> activePartyEnchantments;


    //-------------------------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------------------------
    public MaMGame(CCFileReader ccFile) throws CCFileFormatException
    {
        loadWorld(ccFile);

        activePartyEnchantments = new ArrayList<>();
    }

    public MaMGame(String ccFilePath) throws CCFileFormatException
    {
        CCFileReader ccFile = CCFileReaderWOX.open(ccFilePath);
        loadWorld(ccFile);

        activePartyEnchantments = new ArrayList<>();
    }

    //-------------------------------------------------------------------------------------------------
    // World
    //-------------------------------------------------------------------------------------------------
    protected void loadWorld(CCFileReader ccFile) throws CCFileFormatException
    {
        world = new WoXWorld(this, ccFile);
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
            view.addMonster(new Point(100,0), mon);
            view.setGround(world.getCcFile().getSprite("CAVE.GND"));
        } catch (CCFileFormatException e) {
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
            for(int x=0; x<mapWidth; x++)
            {
                for(int y=0; y<mapHeight; y++)
                {
                    Grid<MaMTile> grid = maze.getMap();
                    MaMTile t = grid.get(x+mapX, y+mapY);

                    RenderablePos tilePos = new RenderablePos(x*16, y*16, 1.0, RenderablePos.ScalePosition.TopLeft, 0);

                    int groundTile = t.getIndexBase();
                    int groundTileStart = 0;

                    //TODO: getTilesSprite returns null...
//                    MaMSprite tilesprite = maze.getTilesSprite();
//                    BufferedImage image = tilesprite.getRenderedFrames()[groundTileStart + groundTile];
//
//                    scene.addRenderable(tilePos, IRenderableGameObject.fromImage(image));
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
