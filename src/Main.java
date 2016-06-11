import Game.MaMGame;
import Rendering.SimpleCanvas.MaMPanel;
import mamFiles.CCFileFormatException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

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
            //Create game
            //game = new MaMGame("xeen.cc");
            game = new MaMGame("dark.cc");

            //Create a renderer for the game (renderer is embedded in a JPanel)
            MaMPanel window = new MaMPanel(game);

            //Create a window to hold the JPanel
            JFrame f = new JFrame("openXeen");
            f.getContentPane().setPreferredSize(window.getPreferredSize()); //set internal size, not window size.
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            f.add(window);
            f.pack();
            f.setVisible(true);
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }

    }
}
