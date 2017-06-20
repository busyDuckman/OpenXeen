package Rendering.GUI;

import java.awt.*;

/**
 * Created by duckman on 14/06/2017.
 */
public class FontEffect
{
    private static final Color TRANSPARENT = new Color(0,0,0,0);

    public static final FontEffect HEADING = new FontEffect(Color.BLACK, TRANSPARENT, true, false, true);
    public static final FontEffect NORMAL = new FontEffect(Color.BLACK, false);

    protected Color innerCol;
    protected Color outlineCol;
    protected boolean bold;
    protected boolean italic;
    protected boolean heading;

    public FontEffect(Color innerCol, Color outlineCol, boolean bold, boolean italic, boolean heading) {
        this.innerCol = innerCol;
        this.outlineCol = outlineCol;
        this.bold = bold;
        this.italic = italic;
        this.heading = heading;
    }

    public FontEffect(Color innerCol, boolean bold, boolean italic) {
        this(innerCol, TRANSPARENT, bold, italic, false);
    }

    public FontEffect(Color innerCol, boolean bold) {
        this(innerCol, bold, false);
    }

    public Color getInnerCol() {
        return innerCol;
    }

    public void setInnerCol(Color innerCol) {
        this.innerCol = innerCol;
    }

    public Color getOutlineCol() {
        return outlineCol;
    }

    public void setOutlineCol(Color outlineCol) {
        this.outlineCol = outlineCol;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isHeading() {
        return heading;
    }

    public void setHeading(boolean heading) {
        this.heading = heading;
    }
}
