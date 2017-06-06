package Rendering.SimpleCanvas;

import Rendering.IRenderableGameObject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by duckman on 6/06/17.
 *
 * Tweaking JButton for graphics.
 */
public class MaMButton extends JButton {
    Rendering.IRenderableGameObject btnFrames;
    String tag;

    public MaMButton(String tag, IRenderableGameObject btnFrames) {
        this(tag, btnFrames, null);
    }

    public MaMButton(String tag, IRenderableGameObject btnFrames, String text) {
        super((text == null) ? "" : text);
        this.btnFrames = btnFrames;
        this.setFocusPainted(false);
        this.setFocusable(false);

        this.setFocusPainted(false);
        this.setFocusable(false);
        this.setBackground(new Color(255, 255, 255, 0));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.tag = tag;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        if(btnFrames != null) {
            g.drawImage(btnFrames.getRenderedFrames()[0], 0, 0, this.getWidth(), this.getHeight(), null);
        }
        super.paint(g);

        if(hasFocus()) {
            super.paint(g);
        }
        if(isSelected()) {
            super.paint(g);
        }

        //getIcon().paintIcon(this, g, 0, 0);
    }
};