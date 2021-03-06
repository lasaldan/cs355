package cs355.solution.view;

import cs355.solution.model.Circle;
import cs355.solution.model.Ellipse;
import cs355.solution.model.Point2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableCircle implements DrawableShape {

    double x;
    double y;
    double size;

    double rotation;
    double handleSize;

    Color color;

    public DrawableCircle(Circle c) {

        size = c.getSize();

        x = c.getCenter().x;
        y = c.getCenter().y;

        color = c.getColor();

        rotation = c.getRotation();

        handleSize = 8;
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Ellipse2D.Double(-(size/2), -(size/2), size, size));
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(x,y);
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public Point2D drawRotationHandle(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Ellipse2D.Double(-4, -16-(size / 2), 8, 8));

        return new Point2D(0, -12-(size / 2));
    }

    @Override
    public List drawScaleHandles(Graphics2D g) {

        ArrayList<Point2D> corners = new ArrayList();
        corners.add(new Point2D(-size/2,-size/2)); // TL
        corners.add(new Point2D( size/2,-size/2)); // TR
        corners.add(new Point2D( size/2, size/2)); // BR
        corners.add(new Point2D(-size/2, size/2)); // BL

        g.setPaint(Color.WHITE);

        // Draw Handles at corners
        for(Point2D corner : corners) {
            g.draw(new Rectangle2D.Double(-handleSize/2+corner.getX(), -handleSize/2+corner.getY(),handleSize,handleSize));
        }

        // Outline Shape
        g.setPaint(Color.GRAY);
        g.draw(new Rectangle2D.Double(-(size / 2), -(size / 2), size, size));
        g.draw(new Ellipse2D.Double(-(size/2), -(size/2), size, size));

        return corners;
    }
}
