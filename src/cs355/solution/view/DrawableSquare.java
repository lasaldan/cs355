package cs355.solution.view;

import cs355.solution.model.Point2D;
import cs355.solution.model.Rectangle;
import cs355.solution.model.Square;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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
        g.fill(new Rectangle2D.Double(-(size/2), -(size/2), size, size));
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(x,y);
    }

    @Override
    public double getRotation() {
        return rotation;
    }
}
