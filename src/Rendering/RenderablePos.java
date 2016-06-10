package Rendering;

import java.awt.*;

/**
 * Created by duckman on 24/05/2016.
 */
public class RenderablePos
{
    public enum ScalePosition
    {
        TopLeft(0,0), TopRight(0,1), LowerLeft(1,0), LowerRight(1,1),
        Centre(0.5, 0.5),
        Left(0, 0.5), Right(1, 0.5), Top (0.5, 0), Bottom(0.5, 1);

        //in object co-ordiante space
        double ocXOrigin;
        double ocYOrigin;

        ScalePosition(double ocXOrigin, double ocYOrigin)
        {
            this.ocXOrigin = ocXOrigin;
            this.ocYOrigin = ocYOrigin;
        }

        public final Rectangle scaleRectangle(Rectangle rec, double scale)
        {
            return scaleRectangle(rec, scale, scale);
        }

        public final Rectangle scaleRectangle(Rectangle rec, double sx, double sy)
        {
            //scaling width & height, scales the rec about the top left
            double newWidth = sx * rec.width;
            double newHeight = sy * rec.height;

            //now move the rectangles top left to a new position
            // using the "world space" co-ordinate origin.
            double wsXorigin = rec.x + (ocXOrigin * rec.width);
            double wsYorigin = rec.y + (ocYOrigin * rec.height);

            double newXpos = ((rec.x - wsXorigin) * sx) + wsXorigin;
            double newYpos = ((rec.y - wsYorigin) * sy) + wsYorigin;

            //return the new rectangle, im rounding given the context of this game.
            return new Rectangle((int)Math.round(newXpos), (int)Math.round(newYpos),
                                 (int)Math.round(newWidth), (int)Math.round(newHeight));
        }
    };

    int xPos;
    int yPos;
    double scale;
    ScalePosition scalePos;
    int depth;

    public RenderablePos(int xPos, int yPos, double scale, ScalePosition scalePos, int depth) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.scale = scale;
        this.scalePos = scalePos;
        this.depth = depth;
    }

    public RenderablePos(int xPos, int yPos, double scale, int depth) {
        this(xPos, yPos, scale, ScalePosition.Centre, depth);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public double getScale() {
        return scale;
    }

    public ScalePosition getScalePos() {
        return scalePos;
    }

    public int getDepth() {
        return depth;
    }

    public Rectangle getImageBounds(int width, int height)
    {
        return  new Rectangle(xPos, yPos, width, height);
        //return this.scalePos.scaleRectangle(new Rectangle(xPos, yPos, width, height), scale);
    }
}
