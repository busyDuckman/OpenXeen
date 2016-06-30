package Toolbox;

import mamFiles.MaMSprite;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by duckman on 15/05/2016.
 */
public class Grid<T>
{
    int numXValues, numYValues;
    T[] values;  // T[y*numXValues+x]

    public Grid(int xValues, int yValues, Function<Point, T> getValue)
    {
        this.numXValues = Math.max(xValues, 0);
        this.numYValues = Math.max(yValues, 0);
        //T foo = Crea T::new;
        //this.values = new T[numYValues][];
        this.values = (T[])new Object[size()];// s. [numYValues][];
        setAll(getValue);
    }

    public Grid(int xValues, int yValues)
    {
        this.numXValues = Math.max(xValues, 0);
        this.numYValues = Math.max(yValues, 0);
        //T foo = Crea T::new;
        //this.values = new T[numYValues][];
        this.values = (T[])new Object[size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
    }

    public T get(int x, int y)
    {
        return values[y*numXValues + x];
    }

    public boolean isValidLocation(int x, int y)
    {
        return (x >=0) && (x < numXValues) && (y >= 0) && (y < numYValues);
    }

    public T get(Point pos)
    {
        return values[pos.y*numXValues + pos.x];
    }

    public int getWidth() {
        return numXValues;
    }

    public int getHeight() {
        return numYValues;
    }

    public int size()
    {
        return numXValues * numYValues;
    }

    public void set(int x, int y, T value)
    {
        values[y*numXValues + x] = value;
    }

    public void set(Point pos, T value)
    {
        values[pos.y*numXValues + pos.x] = value;
    }

    public void setAll(Function<Point, T> getValue)
    {
        for(int y=0; y<numYValues; y++)
        {
            for(int x=0; x<numXValues; x++)
            {
                set(x, y, getValue.apply(new Point(x, y)));
            }
        }
    }
    
}
