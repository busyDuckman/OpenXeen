package Rendering.SimpleCanvas;

import Game.MaMGame;
import Rendering.ISScalableGUI;
import Toolbox.SwingHelpers;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by duckman on 4/06/17.
 * This frame holds a panel (MaMPanel) of the correct aspect ratio.
 */
public class OpenXeenFrame extends JFrame implements ComponentListener
{
    MaMPanel gamePanel;

    protected JPanel pnlRightBuffer, pnlLowerBuffer, pnlUpperBuffer, pnlLeftBuffer;

    MaMGame game;

    boolean centreGame = true;

    public OpenXeenFrame(String title, MaMGame game) throws HeadlessException {
        super(title);

        this.game = game;
        this.setLayout(new MigLayout("fill, debug 20",
                "0![]0![grow]0![]0!",
                "0![]0![grow]0![]0!"));

        pnlRightBuffer = new JPanel(false);
        SwingHelpers.setToSize(pnlRightBuffer, 0, 1);
        pnlRightBuffer.setBackground(Color.black);

        pnlLeftBuffer = new JPanel(false);
        SwingHelpers.setToSize(pnlLeftBuffer, 0, 1);
        pnlLeftBuffer.setBackground(Color.black);

        pnlLowerBuffer = new JPanel(false);
        SwingHelpers.setToSize(pnlLowerBuffer, 1, 0);
        pnlLowerBuffer.setBackground(Color.black);

        pnlUpperBuffer = new JPanel(false);
        SwingHelpers.setToSize(pnlUpperBuffer, 1, 0);
        pnlUpperBuffer.setBackground(Color.black);

//        this.add(pnlRightBuffer, "cell 2 1");
//        this.add(pnlLeftBuffer, "cell 0 1");
//        this.add(pnlLowerBuffer, "cell 1 2");
//        this.add(pnlUpperBuffer, "cell 1 0");

        gamePanel = new MaMPanel(game);
        this.getContentPane().setPreferredSize(gamePanel.getPreferredSize()); //set internal size, not window size.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            this.setIconImage(game.getWorld().getPlayerFaceOrNull(15).getRenderedFrames()[0]);
        }
        catch (Exception ex) {
        }
        this.add(gamePanel, "cell 1 1");

        //this.addComponentListener(this);
        this.pack();
    }

    //------------------------------------------------------------------------------------------------------------------
    // ComponentListener
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("IN MAIN RESIZE EVENT");
        // get the scale most constrained by the window
        double scale = Math.min((this.getWidth() / (double) gamePanel.mamNativeSize.width),
                (this.getHeight() / (double)gamePanel.mamNativeSize.height));

        // scale game window
        //gamePanel.setScale(scale);
        SwingHelpers.setToSize(gamePanel, gamePanel.getScaledBounds().getSize());
        gamePanel.invalidate();

        // setup gaps around game window
        int leftGap = 0;
        int rightGap = this.getWidth() - (int)gamePanel.getScaledBounds().getWidth();
        if(centreGame) {
            leftGap = rightGap - (rightGap/2);
            rightGap = (rightGap/2);
        }

        int topGap = 0;
        int lowerGap = this.getHeight() - (int)gamePanel.getScaledBounds().getHeight();
        if(centreGame) {
            topGap = lowerGap - (lowerGap/2);
            lowerGap = (lowerGap/2);
        }

        // resize buffers
        SwingHelpers.setToSize(pnlLeftBuffer, leftGap, 1);
        SwingHelpers.setToSize(pnlRightBuffer, rightGap, 1);

        SwingHelpers.setToSize(pnlUpperBuffer, 1, topGap);
        SwingHelpers.setToSize(pnlLowerBuffer, 1, lowerGap);

        this.pack();
        this.invalidate();
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
}
