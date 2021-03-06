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
    }

    private boolean isDotPositive(Point2D one, Point2D two, Point2D three ) {
        if (((one.x - three.x) * (two.y - three.y) - (two.x - three.x) * (one.y - three.y)) > 0)
            return true;
        return false;
    }
    @Override
    public boolean containsHitPoint(Point2D hitLoc, double tolerance) {

        boolean test1 = isDotPositive(hitLoc, pt1, pt2);
        boolean test2 = isDotPositive(hitLoc, pt2, pt3);
        boolean test3 = isDotPositive(hitLoc, pt3, pt1);

        if((test1 == test2) && (test2 == test3))
            return true;

        return false;
    }

    @Override
    public void setCorner(Point2D corner) {

    }

    public Triangle(Point2D pt1, Point2D pt2, Point2D pt3, Color _color) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.pt3 = pt3;
        color = _color;
        setToLocal();
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
        setToLocal();
    }

    public void setPt2(Point2D pt2) {
        this.pt2 = pt2;
        setToLocal();
    }

    public void setPt3(Point2D pt3) {
        this.pt3 = pt3;
        setToLocal();
    }

    private void setToLocal() {
        center.x = (pt1.x + pt2.x + pt3.x) / 3;
        center.y = (pt1.y + pt2.y + pt3.y) / 3;

        pt1.x = pt1.x - center.x;
        pt1.y = pt1.y - center.y;

        pt2.x = pt2.x - center.x;
        pt2.y = pt2.y - center.y;

        pt3.x = pt3.x - center.x;
        pt3.y = pt3.y - center.y;
    }
}
