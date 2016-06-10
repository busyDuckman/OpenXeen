import Game.MaMGame;
import Rendering.SimpleCanvas.MaMWindow;
import Toolbox.BinaryHelpers;
import Toolbox.ImageHelpers;
import mamFiles.CCFileFormatException;
import mamFiles.CCFileReader;
import mamFiles.WOX.CCFileReaderWOX;
import mamFiles.WOX.PalletWOX;
import mamFiles.WOX.SpriteFileWOX;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by duckman on 2/05/2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        MaMGame game = null;
        try {
            //game = new MaMGame("xeen.cc");
            game = new MaMGame("dark.cc");
            MaMWindow window = new MaMWindow(game);
            JFrame f = new JFrame("openXeen");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1200,800);
            f.setPreferredSize(f.getSize());
            f.add(window);
            f.pack();
            f.setVisible(true);
        } catch (CCFileFormatException e) {
            e.printStackTrace();
        }


/*
        try(CCFileReader ccFile = CCFileReaderWOX.open("xeen.cc"))
        {
            if(ccFile != null)
            {
                System.out.println("Number of files: " + ccFile.getNumberOfFiles());

                String testFile = "AAZE0001.TXT";
                System.out.println("Dumping " + testFile);
                byte[] data = ccFile.getFileRaw(testFile);
                System.out.println(new String(data));

                testFile = "MM4.PAL";
                data = ccFile.getFileRaw(testFile);
                PalletWOX pal = new PalletWOX(data);

                testFile = "000.MON";
                data = ccFile.getFileRaw(testFile);
                SpriteFileWOX sprite = new SpriteFileWOX(data, pal);
                BinaryHelpers.DebugDumpBinary(sprite.getFrames()[0].data, "sprite_rgb_frame.last");
                BufferedImage img = ImageHelpers.RGB2Image(sprite.getFrames()[0].data, sprite.getWidth(), sprite.getHeight());

                //ImageIO.write(img, "png", new File("000_MON.png"));
//
//                JLabel lbl = new JLabel(new ImageIcon(img));
//                JOptionPane.showMessageDialog(null, lbl, "ImageDialog",
//                        JOptionPane.PLAIN_MESSAGE, null);
//




            }
            else
            {
                System.out.println("ccFile failed to load.");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //return 0;
                */
    }
}
