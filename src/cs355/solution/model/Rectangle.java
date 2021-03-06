package cs355.solution.model;

import java.awt.Color;

import static java.lang.Math.abs;

/**
 * Created by Daniel on 9/5/14.
 */
public class Rectangle extends Shape {

    private double height;
    private double width;

    public Rectangle() {
        height = 0;
        width = 0;
    }

    @Override
    public boolean containsHitPoint(Point2D hitLoc, double tolerance) {
        if(abs(hitLoc.x) <= width/2.0 &&
            abs(hitLoc.y) <= height/2.0 )
            return true;

        return false;
    }

    @Override
    public void setCorner(Point2D corner) {
        this.height = abs(corner.getY()*2.0);
        this.width =  abs(corner.getX()*2.0);
    }

    public Rectangle(Point2D _center, double _width, double _height, Color _color) {
        width = _width;
        height = _height;
        color = _color;
        center = _center;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setDimensions(Point2D initial, Point2D mousePos) {

        double height = mousePos.y - initial.y;
        double width = mousePos.x - initial.x;

        center.y = initial.y + ( height / 2.0);
        center.x = initial.x + ( width / 2.0);

        this.height = abs(height);
        this.width = abs(width);

    }

}
