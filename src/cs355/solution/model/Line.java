package cs355.solution.model;

import java.awt.Color;

/**
 * Created by Daniel on 9/5/14.
 */
public class Line extends Shape {
    private Point2D startPoint, endPoint;

    public Line() {
        startPoint = new Point2D();
        endPoint = new Point2D();
    }

    @Override
    public boolean containsWorldPoint(Point2D hitLoc) {
        return false;
    }

    public Line(Point2D _start, Point2D _end, Color _color) {
        startPoint = new Point2D(_start);
        endPoint = new Point2D(_end);
        color = _color;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
    }
}
