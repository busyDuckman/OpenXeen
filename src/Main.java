import Game.MaMGame;
import GameMechanics.Dice;
import Rendering.RenderablePos;
import Rendering.SimpleCanvas.MaMPanel;
import mamFiles.CCFileCache;
import mamFiles.CCFileFormatException;
import mamFiles.IOT.IoTccFileReader;
import mamFiles.MaMSprite;
import mamFiles.SpriteHelpers.RenderPosHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by duckman on 2/05/2016.
 */
public class Main
{
    /**
     * Creates a MaM game with the basic Java Canvas renderer.
     */
    public static void main(String[] args)
    {
        MaMGame game = null;
        try {
            Random rand = new Random(System.currentTimeMillis());
            //Create game
            //game = new MaMGame("xeen.cc");
            game = new MaMGame("dark.cc");

            //CCFileCache.INSTANCE.setEnabled(false);
            //game = new MaMGame(IoTccFileReader.open("mm3.cc"));


            //Create a renderer for the game (renderer is embedded in a JPanel)
            MaMPanel window = new MaMPanel(game);

            //Create a window to hold the JPanel
            JFrame f = new JFrame("openXeen");
            f.getContentPane().setPreferredSize(window.getPreferredSize()); //set internal size, not window size.
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            try {
                f.setIconImage(game.getWorld().getPlayerFaceOrNull(15).getRenderedFrames()[0]);
            }
            catch (Exception ex) {
            }
            f.add(window);
            f.pack();
            f.setVisible(true);
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }

    }
}
