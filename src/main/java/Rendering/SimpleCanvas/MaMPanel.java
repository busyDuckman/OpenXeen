package Rendering.SimpleCanvas;

import Game.GlobalSettings;
import Game.IMaMGame;
import Game.MaMActions;
import Game.Map.IoTWorld;
import Rendering.GUI.PapyrusMessageBox;
import Rendering.ISScalableGUI;
import Toolbox.HackMe;
import mamFiles.CCFileCache;
import mamFiles.WOX.WOXSpriteFile;
import net.miginfocom.swing.MigLayout;
import org.joda.time.DateTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Toolbox.SwingHelpers.makeClearPanel;

/**
 * Created by duckman on 16/05/2016.
 */


public class MaMPanel extends JPanel implements  KeyListener, ComponentListener, ISScalableGUI//implements WindowListener // implements MouseListener
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

    int frame = 0;

    JPanel pnlView, pnlControl, pnlNavigateBtnPad, pnlChars,
            pnlControlBtnPad;


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

        scale = GlobalSettings.INSTANCE.getRenderingScale();
        this.title = title;
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


        // NB: the 73.5% is calculated from original screen pixels (147/200).
        this.setLayout(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "0%![72.5%!]0%![25%!]2.5%!",   // col
                "0%![73.5%!]0%![22.5%!]4%!"));  // row

        // panels
        pnlView = makeClearPanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "0![5%]0![90%]0![5%]0!",   // col
                "0![5%]0![90%]0![5%]0!")); // row
        this.add(pnlView, "cell 0 0, grow");

        pnlNavigateBtnPad = makeClearPanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "0.5%![33%]0![33%]0![33%]0.5%!",   // col
                "0![50%]0![50%]0!")); // row
        this.add(pnlNavigateBtnPad, "cell 1 1, grow");

        pnlChars = makeClearPanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "4%![14%]1.5%![14%]1.5%![14%]1.5%![14%]1.5%![14%]1.5%![14%]3.5%!",   // col
                "5%![71%]0![24%]0!")); // row
        this.add(pnlChars, "cell 0 1, grow");

        pnlControl = makeClearPanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "0![100%]0!",   // col
                "5%![45%]1%[45%]4%!")); // row
        this.add(pnlControl, "cell 1 0, grow");

        pnlControlBtnPad = makeClearPanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "3%![30%]2%![30%]2%![30%]3%!",   // col
                "3%![30%]2%![30%]2%![30%]3%!")); // row
        // this sits in pnlControl, as I need to put an odd dialog over the whole thing (eg shop options).
        pnlControl.add(pnlControlBtnPad, "cell 0 1, grow");


        // items
        pnlView.add(PapyrusMessageBox.fromModalOKMessage(game.getWorld().getCcFile(),
                "OpenXeen",
                "Welcome adventurer. You have entered the " + game.getWorld().getWorldName() + "."),
                "cell 1 1, gapleft 10%, gapright 10%");



        pnlChars.add(makeTestButton("C1"), "cell 0 0, grow");
        pnlChars.add(makeTestButton("C2"), "cell 1 0, grow");
        pnlChars.add(makeTestButton("C3"), "cell 2 0, grow");
        pnlChars.add(makeTestButton("C4"), "cell 3 0, grow");
        pnlChars.add(makeTestButton("C5"), "cell 4 0, grow");
        pnlChars.add(makeTestButton("C6"), "cell 5 0, grow");

        pnlNavigateBtnPad.add(makeTestButton("\\"), "cell 0 0, grow");
        pnlNavigateBtnPad.add(makeTestButton("U"), "cell 1 0, grow");
        pnlNavigateBtnPad.add(makeTestButton("/"), "cell 2 0, grow");
        pnlNavigateBtnPad.add(makeTestButton("L"), "cell 0 1, grow");
        pnlNavigateBtnPad.add(makeTestButton("D"), "cell 1 1, grow");
        pnlNavigateBtnPad.add(makeTestButton("R"), "cell 2 1, grow");

        pnlControl.add(makeTestButton("map"), "cell 0 0, grow");
        pnlControlBtnPad.add(makeTestButton("s"), "cell 0 0, grow");
        pnlControlBtnPad.add(makeTestButton("c"), "cell 1 0, grow");
        pnlControlBtnPad.add(makeTestButton("r"), "cell 2 0, grow");
        pnlControlBtnPad.add(makeTestButton("b"), "cell 0 1, grow");
        pnlControlBtnPad.add(makeTestButton("d"), "cell 1 1, grow");
        pnlControlBtnPad.add(makeTestButton("q"), "cell 2 1, grow");
        pnlControlBtnPad.add(makeTestButton("m"), "cell 0 2, grow");
        pnlControlBtnPad.add(makeTestButton("t"), "cell 1 2, grow");
        pnlControlBtnPad.add(makeTestButton("p"), "cell 2 2, grow");



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

    protected JComponent makeTestButton(String s) {
        JButton btnTest = new JButton(s);
        btnTest.setBackground(new Color(64, 128, 32, 128));
        return btnTest;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * We ignore this because this does not scale relative to a parent container.
     */
    @Override
    public Rectangle getUnscaledBounds() {
        return this.getBounds();
    }

    /**
     * We ignore this because this does not scale relative to a parent container.
     */
    @Override
    public void setUnscaledBounds(Rectangle r) {
        // unused
    }

    //------------------------------------------------------------------------------------------------------------------
    // Drawing
    //------------------------------------------------------------------------------------------------------------------
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

//        for(Component c : this.getComponents()) {
//            //c.paintAll(g);
//        }
        this.paintChildren(g);
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
//        scale = Math.min((this.getWidth() / (double)mamNativeSize.width),
//                         (this.getHeight() / (double)mamNativeSize.height));
//
//        renderer.setScale(scale);
//
//        this.setPreferredSize(getScaledBounds().getSize());
//        this.setMinimumSize(getScaledBounds().getSize());
//        this.setMaximumSize(getScaledBounds().getSize());

        for(Component c : this.getComponents()) {
            if(c instanceof ISScalableGUI) {
                ((ISScalableGUI)c).setScale(scale);
                c.setSize(((ISScalableGUI) c).getScaledBounds().getSize());
                c.setLocation(((ISScalableGUI) c).getScaledBounds().getLocation());
                //c.setBounds(((ISScalableGUI) c).getScaledBounds());
                c.invalidate();
            }
        }
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
