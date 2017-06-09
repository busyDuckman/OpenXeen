package Game;

import Game.Map.MaMWorld;
import GameMechanics.Adventurers.Adventurer;
import GameMechanics.Magic.PartyEnchantments.IPartyEnchantment;
import Rendering.ISceneComposition;
import Rendering.MaM2DMapComposition;
import Rendering.MaM3DSceneComposition;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by duckman on 24/05/2016.
 */
public interface IMaMGame extends AutoCloseable
{
    //-------------------------------------------------------------------------------------------------
    // Internal classes
    //-------------------------------------------------------------------------------------------------
    enum MaMGameStates
    {
        Explore,
        Combat
    }



    //-------------------------------------------------------------------------------------------------
    // Interface
    //-------------------------------------------------------------------------------------------------
    int getGold();
    int getFood();
    DateTime getDateTime();

    MaMGameStates getGameState();

    MaMWorld getWorld();
    void doAction(MaMActions action);

    MaM3DSceneComposition render();
    MaM2DMapComposition renderMap(int mapX, int mapY, int mapWidth, int mapHeight);
    MaM2DMapComposition renderWizardEyeView(int width, int height);
    ISceneComposition renderHUDForWorld();

    /**
     * Renders the player characters and their
     */
    ISceneComposition renderParty();

    /**
     * 0 = no danger; to 1.0 = full danger.
     * 2.0 = holy shit.
     */
    double getDangerLevel();

    /**
     * Space for no compass
     */
    char getCompassValue();

    /**
     * Is the party facing a secret door.
     */
    boolean getSecretDoor();

    List<IPartyEnchantment> getActivePartyEnchantments();

    List<Adventurer> getParty();




}
