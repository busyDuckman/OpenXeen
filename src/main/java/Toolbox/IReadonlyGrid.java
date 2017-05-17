package Toolbox;

import java.awt.*;

/**
 * Created by duckman on 1/07/2016.
 */
public interface IReadonlyGrid<T> {
    T get(int x, int y);

    boolean isValidLocation(int x, int y);

    T get(Point pos);

    int getWidth();

    int getHeight();

    default int size() {return getWidth() * getHeight();}
}
