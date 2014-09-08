package cs355.solution.model;

import java.awt.Color;

/**
 * Created by Daniel on 9/5/14.
 */
public class Rectangle extends Shape {

    private Point2D TLcorner;
    private Point2D initialCorner;
    private double height;
    private double width;

    public Rectangle() {
        initialCorner = new Point2D();
        TLcorner = new Point2D();
        height = 0;
        width = 0;
        color = Color.WHITE;
    }

    public Rectangle(Point2D _corner, double _width, double _height, Color _color) {
        initialCorner = new Point2D(_corner.x, _corner.y);
        TLcorner = new Point2D(_corner.x, _corner.y);
        width = _width;
        height = _height;
        color = _color;
    }

    public Point2D getCorner() {
        return TLcorner;
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
            this.TLcorner.y = this.initialCorner.y + height;
            this.TLcorner.x = this.initialCorner.x + width;
            this.height = height * -1;
            this.width = width * -1;
        }
        else if(height < 0) {
            this.TLcorner.y = this.initialCorner.y + height;
            this.TLcorner.x = this.initialCorner.x;
            this.height = height * -1;
            this.width = width;
        }
        else if(width < 0) {
            this.TLcorner.x = this.initialCorner.x + width;
            this.TLcorner.y = this.initialCorner.y;
            this.width = width * -1;
            this.height = height;
        }
        else {
            this.TLcorner.x = this.initialCorner.x;
            this.TLcorner.y = this.initialCorner.y;
            this.width = width;
            this.height = height;
        }

    }

}
