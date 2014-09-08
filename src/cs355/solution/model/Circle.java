package cs355.solution.model;

import java.awt.*;

import static java.lang.Math.min;
import static java.lang.Math.abs;

/**
 * Created by Daniel on 9/5/14.
 */
public class Circle extends Shape {

    private Point2D center;
    private Point2D initialCorner;
    private double radius;

    public Circle() {
        initialCorner = new Point2D();
        center = new Point2D();
        radius = 0;
        color = Color.WHITE;
    }

    public Circle(Point2D _center, double _width, double _height, Color _color) {
        initialCorner = new Point2D(_center.x, _center.y);
        center = new Point2D(_center.x, _center.y);
        radius = _width;
        color = _color;
    }

    public Point2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public void setDimensions(Point2D mousePos) {

        double height = mousePos.y - this.initialCorner.y;
        double width = mousePos.x - this.initialCorner.x;

        this.radius = min(abs(height/2), abs(width/2));

        if(height < 0 && width < 0) {
            this.center.y = this.initialCorner.y - this.radius;
            this.center.x = this.initialCorner.x - this.radius;
        }
        else if(height < 0) {
            this.center.y = this.initialCorner.y - this.radius;
            this.center.x = this.initialCorner.x + this.radius;
        }
        else if(width < 0) {
            this.center.x = this.initialCorner.x - this.radius;
            this.center.y = this.initialCorner.y + this.radius;
        }
        else {
            this.center.x = this.initialCorner.x + this.radius;
            this.center.y = this.initialCorner.y + this.radius;
        }

    }

}
