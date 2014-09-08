package cs355.solution.view;

import cs355.solution.model.Rectangle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableRectangle implements DrawableShape {

    double x;
    double y;
    double width;
    double height;
    Color color;

    public DrawableRectangle(Rectangle r) {

        width = r.getWidth();
        height = r.getHeight();

        x = r.getCorner().x;
        y = r.getCorner().y;

        color = r.getColor();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
