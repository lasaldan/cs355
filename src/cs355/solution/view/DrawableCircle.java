package cs355.solution.view;

import cs355.solution.model.Circle;
import cs355.solution.model.Ellipse;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableCircle implements DrawableShape {

    double x;
    double y;
    double width;
    double height;
    Color color;

    public DrawableCircle(Circle c) {

        width = c.getRadius() * 2;
        height = c.getRadius() * 2;

        x = c.getCenter().x - width / 2;
        y = c.getCenter().y - height / 2;

        color = c.getColor();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Ellipse2D.Double(x, y, width, height));
    }
}
