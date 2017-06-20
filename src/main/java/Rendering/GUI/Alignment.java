package Rendering.GUI;

import javax.swing.*;

/**
 * Created by duckman on 14/06/2017.
 */
public enum Alignment {
    LEFT {
        @Override
        int getSwingConstant() {
            return SwingConstants.LEFT;
        }
    },
    CENTER {
        @Override
        int getSwingConstant() {
            return SwingConstants.CENTER;
        }
    },
    RIGHT {
        @Override
        int getSwingConstant() {
            return SwingConstants.RIGHT;
        }
    };

    abstract int getSwingConstant();
}
