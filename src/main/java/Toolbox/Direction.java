package Toolbox;

import java.awt.*;

public enum Direction
{
    UP(0, -1) {
        @Override
        public Direction turnLeft() {
            return LEFT;
        }},
    RIGHT(1, 0) {
        @Override
        public Direction turnLeft() {
            return UP;
        }},
    DOWN(0, 1) {
        @Override
        public Direction turnLeft() {
            return RIGHT;
        }},
    LEFT(-1, 0) {
        @Override
        public Direction turnLeft() {
            return DOWN;
        }};

    Point vector;
    Direction(int x, int y)
    {
        vector = new Point(x, y);
    }

    public Point getVector() {
        return vector;
    }

    public abstract Direction turnLeft();

    //something about this is so wrong, its right.
    public final Direction turnRight() {return turnLeft().turnLeft().turnLeft();}
    public final Direction turnAround() {return turnLeft().turnLeft();}
}
