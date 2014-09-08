package cs355.solution.model;

import java.awt.*;

/**
 * Created by Daniel on 9/5/14.
 */
public class Ellipse extends Shape {

    private Point2D center;
    private Point2D initialCorner;
    private double height;
    private double width;

    public Ellipse() {
        initialCorner = new Point2D();
        center = new Point2D();
        height = 0;
        width = 0;
        color = Color.WHITE;
    }

    public Ellipse(Point2D _center, double _width, double _height, Color _color) {
        initialCorner = new Point2D(_center.x, _center.y);
        center = new Point2D(_center.x, _center.y);
        width = _width;
        height = _height;
        color = _color;
    }

    public Point2D getCenter() {
        return center;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setDimensions(Point2D mousePos) {

        double height = mousePos.y - this.initialCorner.y;
        double width = mousePos.x - this.initialCorner.x;

        if(height < 0 && width < 0) {
            this.center.y = this.initialCorner.y + height / 2;
            this.center.x = this.initialCorner.x + width / 2;
            this.height = height * -1;
            this.width = width * -1;
        }
        else if(height < 0) {
            this.center.y = this.initialCorner.y + height / 2;
            this.center.x = this.initialCorner.x + width / 2;
            this.height = height * -1;
            this.width = width;
        }
        else if(width < 0) {
            this.center.x = this.initialCorner.x + width / 2;
            this.center.y = this.initialCorner.y + height / 2;
            this.width = width * -1;
            this.height = height;
        }
        else {
            this.center.x = this.initialCorner.x + width / 2;
            this.center.y = this.initialCorner.y + height / 2;
            this.width = width;
            this.height = height;
        }

    }

}
