package Toolbox;

import java.awt.*;

/**
 * Created by duckman on 29/06/2016.
 *
 * Stuff the Point class seems to lack.
 * narf.
 */

public final class PointHelper
{
    public static Point add(Point a, Point b)
    {
        return new Point(a.x + b.x, a.y + b.y);
    }

    public static Point sub(Point minuend, Point subtrahend)
    {
        return new Point(minuend.x - subtrahend.x, minuend.y - subtrahend.y);
    }

    public static Point multiply(Point a, int v)
    {
        return new Point(a.x * v, a.y * v);
    }

    public static Point navigate(Point pos, Direction dir, int dist)
    {
        Point p = add(pos, multiply(dir.getVector(), dist));
        return p;
    }

    public static double length(Point a)
    {
        return Math.sqrt((a.x*a.x)+(a.y*a.y));
    }

    public static double dist(Point a, Point b)
    {
        return length(sub(a, b));
    }

    public static String point2String(Point p) {
        return "[" + p.x + ", " + p.y + "]";
    }

    public static boolean equalsXY(Point p, int x, int y)
    {
        return (p.x == x) && (p.y == y);
    }
}
