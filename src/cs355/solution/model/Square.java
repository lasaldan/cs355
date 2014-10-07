package cs355.solution.model;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Created by Daniel on 9/5/14.
 */
public class Square extends Shape {

    private double size;

    public Square() {
        size = 0;
        color = Color.WHITE;
    }

    @Override
    public boolean containsHitPoint(Point2D hitLoc, double tolerance) {
        if(abs(hitLoc.x) <= size/2 &&
           abs(hitLoc.y) <= size/2 )
            return true;

        return false;
    }

    public Square(Point2D _center, double _size, Color _color) {
        center = new Point2D(_center.x, _center.y);
        size = _size;
        color = _color;
    }

    public double getSize() {
        return size;
    }

    public void setDimensions(Point2D initial, Point2D mousePos) {


        double height = mousePos.y - initial.y;
        double width = mousePos.x - initial.x;

        this.size = min(abs(height), abs(width));

        if(height < 0 && width < 0) {
            center.y = initial.y - size/2;
            center.x = initial.x - size/2;
        }
        else if(height < 0) {
            center.y = initial.y - size/2;
            center.x = initial.x + size/2;
        }
        else if(width < 0) {
            center.y = initial.y + size/2;
            center.x = initial.x - size/2;
        }
        else {
            center.y = initial.y + size/2;
            center.x = initial.x + size/2;
        }

    }

}
