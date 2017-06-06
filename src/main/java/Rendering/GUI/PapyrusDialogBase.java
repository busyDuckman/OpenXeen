package Rendering.GUI;

import Game.GlobalSettings;
import Rendering.IRenderableGameObject;
import Rendering.ISScalableGUI;
import Toolbox.FileHelpers;
import Toolbox.ImageHelpers;
import mamFiles.MaMCCFileReader;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Map;

/**
 * Created by duckman on 31/05/17.
 */
public class PapyrusDialogBase extends JPanel implements ComponentListener, ISScalableGUI
{
    /**
     * Scale graphics and items
     */
    protected double scale = 2.0;

    protected IRenderableGameObject papyrus;

    IRenderableGameObject topLeftImage, topImage, topRightImage,
                          leftImage, rightImage,
                          lowerLeftImage, lowerImage, lowerRightImage,
                          textureImage;

    MaMCCFileReader ccFile;

    private int thinBorderWidth = 8;
    private int longBorderWidth = 32;
    private Rectangle unscaledBounds;

    protected Font messageFont;
    protected Font boldFont;

    public PapyrusDialogBase(MaMCCFileReader ccFile) {
        this.ccFile = ccFile;
        scale = GlobalSettings.INSTANCE.getRenderingScale();


        String imgPath = FileHelpers.resolveFile(FileHelpers.join(ccFile.getAbsPathForProxies("png"), "mam_papyrus.png"),
                false,
                true);

        if (imgPath != null) {
            papyrus = IRenderableGameObject.fromImage(ImageHelpers.loadOrNull(imgPath));


            int width = papyrus.getImage(0).getWidth();
            int height = papyrus.getImage(0).getHeight();

            int xCorner = width - thinBorderWidth ;
            int yCorner = height - thinBorderWidth ;

            //extract image areas (that tessellate) for constructing the border.
            topLeftImage = papyrus.getRegion(new Rectangle(0, 0, thinBorderWidth, thinBorderWidth));
            topRightImage = papyrus.getRegion(new Rectangle(xCorner, 0, thinBorderWidth, thinBorderWidth));
            lowerLeftImage = papyrus.getRegion(new Rectangle(0, yCorner, thinBorderWidth, thinBorderWidth));
            lowerRightImage = papyrus.getRegion(new Rectangle(xCorner, yCorner, thinBorderWidth, thinBorderWidth));

            leftImage = papyrus.getRegion(new Rectangle(0, thinBorderWidth, thinBorderWidth, longBorderWidth));
            rightImage = papyrus.getRegion(new Rectangle(xCorner, thinBorderWidth, thinBorderWidth, longBorderWidth));

            topImage = papyrus.getRegion(new Rectangle(thinBorderWidth, 0, longBorderWidth, thinBorderWidth));
            lowerImage = papyrus.getRegion(new Rectangle(thinBorderWidth, yCorner, longBorderWidth, thinBorderWidth));

            textureImage = papyrus.getRegion(new Rectangle(18, 17, 32, 32));
        }

        messageFont = this.getFont();
        boldFont = new Font(messageFont.getFontName(), Font.BOLD, messageFont.getSize()+2);


    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public double getScale() {
        return scale;
    }

    @Override
    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public Rectangle getUnscaledBounds() {
        return unscaledBounds;
    }

    @Override
    public void setUnscaledBounds(Rectangle r) {
        unscaledBounds = r;
    }

    public int getScaledThinBorderWidth() {
        return (int)(thinBorderWidth * scale);
    }

    public int getScaledLongBorderWidth() {
        return (int)(longBorderWidth * scale);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Events
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void componentResized(ComponentEvent e) {
//        this.setPreferredSize(getScaledBounds().getSize());
//        this.setMinimumSize(getScaledBounds().getSize());
//        this.setMaximumSize(getScaledBounds().getSize());
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
    // Rendering
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void paint(Graphics g)
    {
        //super.paint(g);
        paint(g, false);
    }

    public void paint(Graphics g, boolean screenShotMode)
    {
        int thinW = getScaledThinBorderWidth();
        int longW = getScaledLongBorderWidth();

        g.drawImage(topLeftImage.getRenderedFrames()[0], 0, 0, thinW, thinW,null);
        g.drawImage(topRightImage.getRenderedFrames()[0], this.getWidth()-thinW, 0, thinW, thinW, null);
        g.drawImage(lowerLeftImage.getRenderedFrames()[0], 0, this.getHeight()-thinW, thinW, thinW,null);
        g.drawImage(lowerRightImage.getRenderedFrames()[0], this.getWidth()-thinW, this.getHeight()-thinW, thinW, thinW, null);

        //clip drawing the border
        g.setClip(thinW, 0, this.getWidth()-(thinW*2), this.getHeight());
        int xSteps = (this.getWidth()/longW) + 1;
        for(int x=0; x<xSteps; x++)
        {
            int pos = thinW + (x*longW);
            g.drawImage(topImage.getRenderedFrames()[0], pos, 0, longW, thinW,null);
            g.drawImage(lowerImage.getRenderedFrames()[0], pos, this.getHeight()-thinW, longW, thinW,null);
        }

        g.setClip(0, thinW,  this.getWidth(), this.getHeight()-(thinW*2));
        int ySteps = (this.getHeight()/longW) + 1;
        for(int y=0; y<ySteps; y++)
        {
            int pos = thinW + (y*longW);
            g.drawImage(leftImage.getRenderedFrames()[0], 0, pos, thinW, longW, null);
            g.drawImage(rightImage.getRenderedFrames()[0], this.getWidth()-thinW, pos, thinW, longW, null);
        }

        //texture
        g.setClip(thinW, thinW, this.getWidth()-(thinW*2), this.getHeight()-(thinW*2));
        for(int y=0; y<ySteps; y++)
        {
            int yPos = thinW + (y*longW);
            for(int x=0; x<xSteps; x++)
            {
                int xPos = thinW + (x * longW);
                g.drawImage(textureImage.getRenderedFrames()[0], xPos, yPos, longW, longW, null);
            }
        }

        this.paintChildren(g);
    }
}
