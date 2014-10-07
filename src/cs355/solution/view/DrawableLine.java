package cs355.solution.view;

import cs355.solution.model.Line;
import cs355.solution.model.Point2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableLine implements DrawableShape {

    double rotation;
    double length;
    Point2D center;
    Color color;

    public DrawableLine(Line l) {

        Point2D start = l.getStartPoint();
        Point2D end = l.getEndPoint();

        double diffX = start.getX() - end.getX();
        double diffY = start.getY() - end.getY();

        center = l.getCenter();

        length = Math.sqrt((diffX * diffX) + (diffY * diffY));

        rotation = l.getRotation();

        color = l.getColor();

    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.draw(new Line2D.Double(-length/2.0,0,length/2.0,0));
    }

    @Override
    public Point2D getCenter() {
        return center;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public Point2D drawRotationHandle(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Ellipse2D.Double(-4, -16, 8, 8));

        return new Point2D(0,-12);
    }

    @Override
    public List drawScaleHandles(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Rectangle2D.Double(-4-(length / 2.0), -4, 8, 8));
        g.draw(new Rectangle2D.Double(-4+(length / 2.0), -4, 8, 8));

        return null;
    }
}