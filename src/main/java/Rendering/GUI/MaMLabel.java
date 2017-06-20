package Rendering.GUI;

import Game.GlobalSettings;

import javax.swing.*;
import javax.xml.bind.JAXBElement;
import java.awt.*;

/**
 * Created by duckman on 14/06/2017.
 */
public class MaMLabel extends JLabel
{
    Alignment alignment;
    FontEffect fontEffect;
    Font font;

    public MaMLabel(String text, Alignment alignment, FontEffect fontEffect) {
        super(text);
        this.alignment = alignment;
        this.fontEffect = fontEffect;

        // alignment
        this.setHorizontalAlignment(alignment.getSwingConstant());

        // setup font
        Font _font = this.fontEffect.isHeading() ?
                GlobalSettings.INSTANCE.getHeadingFont():
                GlobalSettings.INSTANCE.getMessageFont();
        int style = 0;
        style |= (fontEffect.isBold()) ? Font.BOLD : Font.PLAIN;
        style |= (fontEffect.isItalic()) ? Font.ITALIC : Font.PLAIN;
        font = new Font(_font.getFontName(), style, _font.getSize());

        this.setFont(font);
    }

    public MaMLabel(String text, Alignment alignment) {
        this(text, alignment, FontEffect.NORMAL);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.GRAY);
        g.drawRect(0,0, this.getWidth(), this.getHeight());
    }
}
