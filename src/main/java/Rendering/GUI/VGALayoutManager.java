package Rendering.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duckman on 6/07/2017.
 */
public class VGALayoutManager implements LayoutManager2
{
    public static VGALayoutManager mode13h() {return new VGALayoutManager(320, 200);}

    int xPos;
    int yPos;
    int width;
    int height;

    double scale = 1.0;

    Map<String, Rectangle> layoutOverrides;
    Map<Component, VGALayoutPos> positions;

    private int minWidth = 0, minHeight = 0;
    private int preferredWidth = 0, preferredHeight = 0;
    private boolean sizeUnknown = true;

    public VGALayoutManager(int width, int height) {
        this(width, height, null);
    }

    public VGALayoutManager(int width, int height, String layoutOverrideFile) {
        this(0, 0, width, height, layoutOverrideFile);
    }

    public VGALayoutManager(int xPos, int yPos, int width, int height, String layoutOverrideFile) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        positions = new HashMap<>();

        layoutOverrides = new HashMap<>();
        if(layoutOverrideFile != null) {
            //TODO: scripting feature here
        }
    }

    protected VGALayoutManager(int xPos, int yPos, int width, int height, VGALayoutManager src) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        positions = new HashMap<>(src.positions);
        if(src != null) {
            layoutOverrides = new HashMap<>();
            if(src.layoutOverrides != null) {
                layoutOverrides.putAll(src.layoutOverrides);
            }
        }
    }

    public VGALayoutManager region(int xPos, int yPos, int width, int height)
    {
        return new VGALayoutManager(this.xPos+xPos, this.yPos+yPos, width, height, this);
    }

    //----------------------------------------------------------------------------------------------------
    // Getters and setters
    //----------------------------------------------------------------------------------------------------
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Map<String, Rectangle> getLayoutOverrides() {
        return layoutOverrides;
    }

    public void setLayoutOverrides(Map<String, Rectangle> layoutOverrides) {
        this.layoutOverrides = layoutOverrides;
    }


    //----------------------------------------------------------------------------------------------------
    // LayoutManager2
    //----------------------------------------------------------------------------------------------------

    @Override
    public void addLayoutComponent(String name, Component comp) {
        System.out.println("addLayoutComponent: " + NVL(name.toString()) + ", " + comp.toString());
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        System.out.println("removeLayoutComponent: " + comp.toString());

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        //return new Dimension(width, height);
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        setSizes(parent);

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = preferredWidth
                + insets.left + insets.right;
        dim.height = preferredHeight
                + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        //return new Dimension(1, 1);
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = minWidth
                + insets.left + insets.right;
        dim.height = minHeight
                + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth()
                - (insets.left + insets.right);
        int maxHeight = parent.getHeight()
                - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();
        int previousWidth = 0, previousHeight = 0;
        int x = 0, y = insets.top;
        int rowh = 0, start = 0;
        int xFudge = 0, yFudge = 0;
        boolean oneColumn = false;

        // Go through the components' sizes, if neither
        // preferredLayoutSize nor minimumLayoutSize has
        // been called.
        if (sizeUnknown) {
            setSizes(parent);
        }

        if (maxWidth <= minWidth) {
            oneColumn = true;
        }

        if (maxWidth != preferredWidth) {
            xFudge = (maxWidth - preferredWidth)/(nComps - 1);
        }

        if (maxHeight > preferredHeight) {
            yFudge = (maxHeight - preferredHeight)/(nComps - 1);
        }

        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();

                // increase x and y, if appropriate
                if (i > 0) {
                    if (!oneColumn) {
                        x += previousWidth/2 + xFudge;
                    }
                    y += previousHeight + 32 + yFudge;
                }

                // If x is too large,
                if ((!oneColumn) &&
                        (x + d.width) >
                                (parent.getWidth() - insets.right)) {
                    // reduce x to a reasonable number.
                    x = parent.getWidth()
                            - insets.bottom - d.width;
                }

                // If y is too large,
                if ((y + d.height)
                        > (parent.getHeight() - insets.bottom)) {
                    // do nothing.
                    // Another choice would be to do what we do to x.
                }

                // Set the component's size and position.
                c.setBounds(x, y, d.width, d.height);

                previousWidth = d.width;
                previousHeight = d.height;
            }
        }
    }


    private void setSizes(Container parent) {
        int nComps = parent.getComponentCount();
        Dimension d = null;

        //Reset preferred/minimum width and height.
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                d = c.getPreferredSize();

                if (i > 0) {
                    preferredWidth += d.width/2;
                    preferredHeight += 32;
                } else {
                    preferredWidth = d.width;
                }
                preferredHeight += d.height;

                minWidth = Math.max(c.getMinimumSize().width,
                        minWidth);
                minHeight = preferredHeight;
            }
        }
    }

    private <T> String NVL(T obj)
    {
        return NVL(obj.toString(), "<<NULL>>");
    }

    private <T> T NVL(T obj, T onNull)
    {
        return (obj == null) ? onNull : obj;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {

    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return null;
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }
}
