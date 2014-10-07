package cs355.solution.view;

import cs355.solution.model.Point2D;
import cs355.solution.model.Rectangle;
import cs355.solution.model.Square;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableSquare implements DrawableShape {

    double x;
    double y;
    double size;
    Color color;

    double rotation;

    public DrawableSquare(Square s) {

        size = s.getSize();

        x = s.getCenter().x;
        y = s.getCenter().y;

        color = s.getColor();

        rotation = s.getRotation();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Rectangle2D.Double(-(size / 2), -(size / 2), size, size));
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

        return new Point2D(0,-12-(size / 2));
    }

    @Override
    public List drawScaleHandles(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Rectangle2D.Double(-4-(size / 2), -4-(size / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4+(size / 2), -4-(size / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4-(size / 2), -4+(size / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4+(size / 2), -4+(size / 2), 8, 8));
        g.setPaint(Color.GRAY);
        g.draw(new Rectangle2D.Double(-(size / 2), -(size / 2), size, size));

        return null;
    }
}
