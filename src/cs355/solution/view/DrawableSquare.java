package cs355.solution.view;

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

    public DrawableSquare(Square s) {

        size = s.getSize();

        x = s.getCenter().x;
        y = s.getCenter().y;

        color = s.getColor();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Rectangle2D.Double(x-(size/2), y-(size/2), size, size));
    }
}
