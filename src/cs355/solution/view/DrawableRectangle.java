package cs355.solution.view;

import cs355.solution.model.Point2D;
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

    double rotation;

    Color color;

    public DrawableRectangle(Rectangle r) {

        width = r.getWidth();
        height = r.getHeight();

        x = r.getCenter().x;
        y = r.getCenter().y;

        color = r.getColor();

        rotation = r.getRotation();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.fill(new Rectangle2D.Double(-(width/2), -(height/2), width, height));
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
        g.draw(new Rectangle2D.Double(-4-(width / 2), -4-(height / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4+(width / 2), -4-(height / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4-(width / 2), -4+(height / 2), 8, 8));
        g.draw(new Rectangle2D.Double(-4+(width / 2), -4+(height / 2), 8, 8));
    }
}
