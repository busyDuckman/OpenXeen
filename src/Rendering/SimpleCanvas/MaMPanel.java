package Rendering.SimpleCanvas;

import Game.IMaMGame;
import Game.MaMActions;
import Rendering.RenderablePos;
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

    protected double scale = 4.0;
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
                game.doAction(MaMActions.WalkLeft);
                break;

            case KeyEvent.VK_RIGHT:
                game.doAction(MaMActions.WalkRight);
                break;

            //hack render pos offset
            case KeyEvent.VK_I:
                RenderablePos.decYHack();
                break;
            case KeyEvent.VK_J:
                RenderablePos.decXHack();
                break;
            case KeyEvent.VK_K:
                RenderablePos.incYHack();
                break;
            case KeyEvent.VK_L:
                RenderablePos.incXHack();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
