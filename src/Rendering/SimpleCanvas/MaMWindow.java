package Rendering.SimpleCanvas;

import Game.IMaMGame;
import Game.MaMActions;
import Game.Monsters.MaMMonster;
import Rendering.IMaMSprite;
import org.joda.time.DateTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by duckman on 16/05/2016.
 */


public class MaMWindow extends JPanel implements  KeyListener //implements WindowListener // implements MouseListener
{
    //------------------------------------------------------------------------------------------------------------------
    // Instance Data
    //------------------------------------------------------------------------------------------------------------------
    protected String title;

    IMaMGame game;

    protected double scaleX = 1.0;
    protected double scaleY = 1.0;
    Font messageFont = new Font("TimesRoman", Font.PLAIN, 20);

    Timer timer;
    GraphicsRenderer renderer = null;



    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public MaMWindow(IMaMGame game)
    {
        this(null, game);
    }

    public MaMWindow(String title, IMaMGame game)
    {
        // frame in GLOBAL.ICN
        super();
        this.title = title;
        //this.addMouseMotionListener(this);
        //this.addMouseListener(this);
        this.setFocusable(true);
        this.addKeyListener(this);
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

        renderer = new GraphicsRenderer();
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

//        try {
//            //g.drawImage(game.getCcFile().loadMonsters(game)[0].getImage(0), 40, 40, null);
//            g.drawImage(game.getWorld().getMonsters()[0].getImage(0), 40, 40, null);
//        } catch (CCFileFormatException e) {
//            e.printStackTrace();
//        }

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
                game.doAction(MaMActions.WalkLeft);
                break;

            case KeyEvent.VK_RIGHT:
                game.doAction(MaMActions.WalkRight);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
