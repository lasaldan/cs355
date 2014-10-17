package cs355.solution.model;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * Created by Daniel on 9/5/14. ;)
 */
public class Ellipse extends Shape {

    private double height;
    private double width;

    public Ellipse() {
        height = 0;
        width = 0;
        color = Color.WHITE;
    }

    @Override
    public boolean containsHitPoint(Point2D hitLoc, double tolerance) {

        double xComp = ((hitLoc.x)*(hitLoc.x)) / ((width/2.0)*(width/2.0));
        double yComp = ((hitLoc.y)*(hitLoc.y)) / ((height/2.0)*(height/2.0));

        return xComp + yComp <= 1;

    }

    @Override
    public void setCorner(Point2D corner) {
        this.height = abs(corner.getY() * 2.0);
        this.width = abs(corner.getX() * 2.0);
    }

    public Ellipse(Point2D _center, double _width, double _height, Color _color) {
        center = new Point2D(_center);
        width = _width;
        height = _height;
        color = _color;
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

        center.y = initial.y + ( height / 2.0 );
        center.x = initial.x + ( width / 2.0 );

        this.height = abs(height);
        this.width = abs(width);
    }

}
