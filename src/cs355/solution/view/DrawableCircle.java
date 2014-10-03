package cs355.solution.view;

import cs355.solution.model.Circle;
import cs355.solution.model.Ellipse;
import cs355.solution.model.Point2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableCircle implements DrawableShape {

    double x;
    double y;
    double size;

    double rotation;

    Color color;

    public DrawableCircle(Circle c) {

        size = c.getSize();

        x = c.getCenter().x;
        y = c.getCenter().y;

        color = c.getColor();

        rotation = c.getRotation();
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
    public void drawHandlesOn(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Rectangle2D.Double(-4-(size / 2), -4-(size / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4+(size / 2), -4-(size / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4-(size / 2), -4+(size / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4+(size / 2), -4+(size / 2), 8, 8));
        g.setPaint(Color.GRAY);
        g.draw(new Rectangle2D.Double(-(size/2), -(size/2), size, size));
    }
}
