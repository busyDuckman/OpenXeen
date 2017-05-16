package Rendering.SimpleCanvas;

import Game.GlobalSettings;
import Game.IMaMGame;
import Game.MaMActions;
import Game.Map.IoTWorld;
import Rendering.RenderablePos;
import Toolbox.HackMe;
import mamFiles.CCFileCache;
import mamFiles.WOX.WOXSpriteFile;
import net.miginfocom.swing.MigLayout;
import org.joda.time.DateTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by duckman on 16/05/2016.
 */


public class MaMPanel extends JPanel implements  KeyListener, ComponentListener//implements WindowListener // implements MouseListener
{
    //------------------------------------------------------------------------------------------------------------------
    // Instance Data
    //------------------------------------------------------------------------------------------------------------------
    protected String title;

    protected IMaMGame game;

    protected double scale = 2.0;
    protected Dimension mamNativeSize = new Dimension(320, 200);
    protected Font messageFont = new Font("TimesRoman", Font.PLAIN, 20);

    protected Timer timer;
    protected GraphicsRenderer renderer = null;



    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public MaMPanel(IMaMGame game)
    {
        this(null, game);
    }

    public MaMPanel(String title, IMaMGame game)
    {
        // frame in GLOBAL.ICN
        super();

        this.title = title;
        this.setLayout(new MigLayout("","",""));

        //this.addMouseMotionListener(this);
        //this.addMouseListener(this);
        this.setFocusable(true);
        this.addKeyListener(this);
        Dimension size = new Dimension((int)(mamNativeSize.width*scale), (int)(mamNativeSize.height*scale));
        this.setPreferredSize(size);
        this.addComponentListener(this);

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                //code goes here
                try {
                    game.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.game = game;

        renderer = new GraphicsRenderer(scale);
        renderer.setGame(game);

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setInitialDelay(190);
        timer.start();

    }


    //------------------------------------------------------------------------------------------------------------------
    // Drawing
    //------------------------------------------------------------------------------------------------------------------
    int frame = 0;

    public void paint(Graphics g)
    {
        paint(g, false);
    }

    public void paint(Graphics g, boolean screenShotMode)
    {
        g.setColor(this.getBackground());
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        long ms = DateTime.now().getMillis();
        renderer.refresh(g, ms);

        //draw title
        if(this.title != null)
        {
            g.setFont(messageFont);
            DrawOutlinedString(g, title, 0, 20, Color.black, Color.white);
        }

        frame++;

    }

    private void DrawOutlinedString(Graphics g, String text, int x, int y, Color _fillColor, Color _borderColor)
    {
        //draw border
        g.setColor(_borderColor);
        g.drawString(text, x-1, y-1);
        g.drawString(text, x-1, y);
        g.drawString(text, x-1, y+1);
        g.drawString(text, x-1, y-1);
        g.drawString(text, x, y);
        g.drawString(text, x+1, y+1);

        //draw fill
        g.setColor(_fillColor);
        g.drawString(text, x, y);

    }

    //------------------------------------------------------------------------------------------------------------------
    // ComponentListener
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void componentResized(ComponentEvent e) {
        scale = Math.min((this.getWidth() / (double)mamNativeSize.width),
                         (this.getHeight() / (double)mamNativeSize.height));

        renderer.setScale(scale);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }


    //------------------------------------------------------------------------------------------------------------------
    // KeyListener
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                game.doAction(MaMActions.WalkForward);
                break;

            case KeyEvent.VK_DOWN:
                game.doAction(MaMActions.WalkBackWard);
                break;

            case KeyEvent.VK_LEFT:
                game.doAction(MaMActions.TurnLeft);
                break;

            case KeyEvent.VK_RIGHT:
                game.doAction(MaMActions.TurnRight);
                break;

            case KeyEvent.VK_H:
                GlobalSettings.INSTANCE.setDisableHUD(!GlobalSettings.INSTANCE.isHUDDisabled());
                break;

            //hack render pos offset
            case KeyEvent.VK_I:
                //Flush the cache, because I use this to hack away at file format tweaks as well
                CCFileCache.INSTANCE.flush();
                //RenderablePos.decYHack();
                HackMe.GlobalInstance.moveToPreviousHack();
                break;
            case KeyEvent.VK_J:
                CCFileCache.INSTANCE.flush();
                //RenderablePos.decXHack();
                HackMe.GlobalInstance.decCurrentHack();
                break;
            case KeyEvent.VK_K:
                CCFileCache.INSTANCE.flush();
                //RenderablePos.incYHack();
                HackMe.GlobalInstance.moveToNextHack();
                break;
            case KeyEvent.VK_L:
                CCFileCache.INSTANCE.flush();
                //RenderablePos.incXHack();
                HackMe.GlobalInstance.incCurrentHack();
                break;
            case KeyEvent.VK_P:
                CCFileCache.INSTANCE.flush();
                HackMe.GlobalInstance.reset();
                break;
            case KeyEvent.VK_O:
                CCFileCache.INSTANCE.flush();
                HackMe.GlobalInstance.reportAll();
                break;
            case KeyEvent.VK_PAGE_UP:
                IoTWorld.currentTestSprite++;
                System.out.println("Test sprite = " + IoTWorld.knownSprites[IoTWorld.currentTestSprite]);
                CCFileCache.INSTANCE.flush();
                break;
            case KeyEvent.VK_PAGE_DOWN:
                IoTWorld.currentTestSprite--;
                System.out.println("Test sprite = " + IoTWorld.knownSprites[IoTWorld.currentTestSprite]);
                CCFileCache.INSTANCE.flush();
                break;
            case KeyEvent.VK_END:
                IoTWorld.currentTestSpriteFrame++;
                System.out.println("Test sprite frame = " + IoTWorld.currentTestSpriteFrame);
                CCFileCache.INSTANCE.flush();
                break;
            case KeyEvent.VK_HOME:
                IoTWorld.currentTestSpriteFrame = -1;
                System.out.println("Test sprite animated" + IoTWorld.currentTestSpriteFrame);
                CCFileCache.INSTANCE.flush();
                break;

            case KeyEvent.VK_0:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD0 is purple");
                break;
            case KeyEvent.VK_1:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD1 is purple");
                break;
            case KeyEvent.VK_2:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD2 is purple");
                break;
            case KeyEvent.VK_3:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD3 is purple");
                break;
            case KeyEvent.VK_4:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD4 is purple");
                break;
            case KeyEvent.VK_5:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD5 is purple");
                break;
            case KeyEvent.VK_6:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD6 is purple");
                break;
            case KeyEvent.VK_7:
                WOXSpriteFile.opcodePurpleHack.toggleHack("CMD7 is purple");
                break;



        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
