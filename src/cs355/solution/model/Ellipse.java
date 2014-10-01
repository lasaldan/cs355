package cs355.solution.model;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * Created by Daniel on 9/5/14.
 */
public class Ellipse extends Shape {

    private double height;
    private double width;

    public Ellipse() {
        height = 0;
        width = 0;
        color = Color.WHITE;
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

        center.y = initial.y + ( height / 2 );
        center.x = initial.x + ( width / 2 );

        this.height = abs(height);
        this.width = abs(width);
    }

}
