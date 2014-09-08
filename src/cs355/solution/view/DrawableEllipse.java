package cs355.solution.view;

import cs355.solution.model.Ellipse;
import cs355.solution.model.Rectangle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableEllipse implements DrawableShape {

    double x;
    double y;
    double width;
    double height;
    Color color;

    public DrawableEllipse(Ellipse e) {

        width = e.getWidth();
        height = e.getHeight();

        x = e.getCenter().x - width / 2;
        y = e.getCenter().y - height / 2;

        color = e.getColor();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Ellipse2D.Double(x, y, width, height));
    }
}
