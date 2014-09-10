package cs355.solution.model;

import java.awt.*;

/**
 * Created by Daniel on 9/5/14.
 */
public class Triangle extends Shape {
    private Point2D pt1, pt2, pt3;

    public Triangle() {
        pt1 = new Point2D();
        pt2 = new Point2D();
        pt3 = new Point2D();
        color = Color.WHITE;
    }

    public Triangle(Point2D pt1, Color _color) {
        this.pt1 = pt1;
        this.pt2 = pt1;
        this.pt3 = pt1;
        color = _color;
    }

    public Point2D getPt1() {
        return pt1;
    }

    public Point2D getPt2() {
        return pt2;
    }

    public Point2D getPt3() {
        return pt3;
    }

    public void setPt1(Point2D pt1) {
        this.pt1 = pt1;
    }

    public void setPt2(Point2D pt2) {
        this.pt2 = pt2;
    }

    public void setPt3(Point2D pt3) {
        this.pt3 = pt3;
    }
}
