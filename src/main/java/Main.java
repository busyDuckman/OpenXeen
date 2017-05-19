import Game.MaMGame;
import Rendering.SimpleCanvas.MaMPanel;
import mamFiles.CCFileCache;
import mamFiles.CCFileFormatException;
import mamFiles.IOT.IoTccFileReader;
import org.apache.commons.cli.*;
import javax.swing.*;
import static Toolbox.Misc.ifNull;

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

        // parse CLI
        Options options = CMDOptions.getOptions();
        CommandLine cmd = null;
        try {
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Unable to parse command line.");
            printUsage(options);
            System.exit(1);
        }

        if(cmd.getOptions().length == 0) {
            printUsage(options);
            System.exit(0);
        }

        try {
            if(CMDOptions.GAME_MM3.isSet(cmd)) {
                game = new MaMGame(IoTccFileReader.open("mm3.cc"));
            }
            else if(CMDOptions.GAME_MM4.isSet(cmd)) {
                game = MaMGame.fromWoXData("xeen.cc");
            }
            else if(CMDOptions.GAME_MM5.isSet(cmd)) {
                game = MaMGame.fromWoXData("dark.cc");
            }
            else if(CMDOptions.GAME_WOX.isSet(cmd)) {
                game = MaMGame.fromWoXData("dark.cc"); //TODO
            }
            else {
                System.out.println("Error: No game specified.");
                printUsage(options);
                System.exit(1);
            }

            if(game == null) {
                System.out.println("Error loading resource files.");
                System.exit(1);
            }

            //MaMMonster mon = game.getWorld().getCcFile().getMonsterFactory().createMonster(game.getWorld(), r.nextInt(50));
            //game.getWorld().addMonster(mon, r.nextInt(256), r.nextInt(256));

            if(CMDOptions.NO_CACHE.isSet(cmd)) {
                CCFileCache.INSTANCE.setEnabled(false);
            }

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

    private static void printUsage(Options options)
    {
        System.out.println("OpenXeen, a M&M game engine rewrite.");
        if(Main.class.getPackage() != null) {
            System.out.println("  - ver: " + ifNull(Main.class.getPackage().getImplementationVersion(), "?"));
        }

        System.out.println("  - author: Dr Warren Creemers (busyDuckMan)");
        System.out.println("  - url: https://github.com/busyDuckman/OpenXeen");
        System.out.println("  - author blog: http://busyducks.com");
        System.out.println("  - doco: https://github.com/busyDuckman/OpenXeen/wiki");
        System.out.println("  - dev blog: https://github.com/busyDuckman/OpenXeen/wiki/A-Development-BLOG-(in-screenshots)");

        System.out.println();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("OpenXeen", options, true);

        System.out.println();
        System.out.println("example:");
        System.out.println("  OpenXeen -mm5 -dir \"c:\\games\\mm5\\\"");

    }
}
