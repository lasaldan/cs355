package cs355.solution.model;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Created by Daniel on 9/5/14.
 */
public class Square extends Shape {

    private Point2D TLcorner;
    private Point2D initialCorner;
    private double size;

    public Square() {
        initialCorner = new Point2D();
        TLcorner = new Point2D();
        size = 0;
        color = Color.WHITE;
    }

    public Square(Point2D _corner, double size, Color _color) {
        initialCorner = new Point2D(_corner.x, _corner.y);
        TLcorner = new Point2D(_corner.x, _corner.y);
        this.size = size;
        color = _color;
    }

    public Point2D getCorner() {
        return TLcorner;
    }

    public double getSize() {
        return size;
    }

    public void setDimensions(Point2D mousePos) {

        double ydiff = mousePos.y - this.initialCorner.y;
        double xdiff = mousePos.x - this.initialCorner.x;

        size = min(abs(ydiff), abs(xdiff));

        if(xdiff < 0 && ydiff < 0) {
            this.TLcorner.y = this.initialCorner.y - size;
            this.TLcorner.x = this.initialCorner.x - size;
        }
        else if(ydiff < 0) {
            this.TLcorner.y = this.initialCorner.y - size;
            this.TLcorner.x = this.initialCorner.x;
        }
        else if(xdiff < 0) {
            this.TLcorner.x = this.initialCorner.x - size;
            this.TLcorner.y = this.initialCorner.y;
        }
        else {
            this.TLcorner.x = this.initialCorner.x;
            this.TLcorner.y = this.initialCorner.y;
        }

    }

}
