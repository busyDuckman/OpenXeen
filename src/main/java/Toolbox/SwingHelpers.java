package Toolbox;

import javax.swing.*;
import java.awt.*;

/**
 * Created by duckman on 4/06/17.
 */
public class SwingHelpers
{
    public static void setToSize(JComponent component , Dimension size) {
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
    }

    public static void setToSize(JComponent component, int width, int height) {
        setToSize(component, new Dimension(width, height));
    }

    public static JPanel makeClearPanel(LayoutManager layout) {
        JPanel pnl = new JPanel(layout);
        pnl.setBackground(new Color(255, 255, 255, 0));
        return pnl;
    }
}
