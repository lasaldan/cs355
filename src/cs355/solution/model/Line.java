package cs355.solution.model;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.awt.Color;

/**
 * Created by Daniel on 9/5/14.
 */
public class Line extends Shape {
    private Point2D startPoint, endPoint;
    private double length;

    public Line() {
        startPoint = new Point2D();
        endPoint = new Point2D();
        center = new Point2D();
        length = 0;
    }

    @Override
    public boolean containsHitPoint(Point2D hitLoc, double tolerance) {

        if( hitLoc.getX() > -(length / 2.0 + tolerance) &&
            hitLoc.getX() < (length / 2.0 + tolerance) &&
            hitLoc.getY() >= (-tolerance) &&
            hitLoc.getY() <= (tolerance))
            return true;

        return false;
    }

    public Line(Point2D _start, Point2D _end, Color _color) {
        startPoint = new Point2D(_start);
        endPoint = new Point2D(_end);
        color = _color;
        calculateDimensions();

    }

    private void calculateDimensions() {
        calculateCenter();
        calculateMetrics();
    }

    private void calculateCenter() {

        double sumX = startPoint.getX() + endPoint.getX();
        double sumY = startPoint.getY() + endPoint.getY();

        center = new Point2D();
        center.x = (sumX / 2.0);
        center.y = (sumY / 2.0);
    }

    private void calculateMetrics() {

        double diffX = startPoint.getX() - endPoint.getX();
        double diffY = startPoint.getY() - endPoint.getY();

        length = Math.sqrt((diffX * diffX) + (diffY * diffY));

        rotation = Math.atan2(diffY, diffX);
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
        calculateDimensions();
    }
}