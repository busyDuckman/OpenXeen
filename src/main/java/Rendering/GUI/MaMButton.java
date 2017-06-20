package Rendering.GUI;

import Game.GlobalSettings;
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
    Dimension size;

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

        double scale = GlobalSettings.INSTANCE.getRenderingScale();
        if(this.btnFrames != null) {
            this.size = new Dimension((int)(this.btnFrames.getImage(0).getWidth() * scale),
                                      (int)(this.btnFrames.getImage(0).getHeight() * scale));
        }
        else {
            this.size = new Dimension((int)(16 * scale), (int)(16 * scale));
        }
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
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

    public String getTag() {
        return tag;
    }
};