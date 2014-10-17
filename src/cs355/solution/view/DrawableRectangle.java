package cs355.solution.view;

import cs355.solution.model.Point2D;
import cs355.solution.model.Rectangle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableRectangle implements DrawableShape {

    double x;
    double y;
    double width;
    double height;
    double handleSize;

    double rotation;

    Color color;

    public DrawableRectangle(Rectangle r) {

        width = r.getWidth();
        height = r.getHeight();

        x = r.getCenter().x;
        y = r.getCenter().y;

        color = r.getColor();

        rotation = r.getRotation();
        handleSize = 8;
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
    public Point2D drawRotationHandle(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Ellipse2D.Double(-4, -16-(height / 2), 8, 8));

        return new Point2D(0,-12-(height /2));
    }
    @Override
    public List drawScaleHandles(Graphics2D g) {


        ArrayList<Point2D> corners = new ArrayList();
        corners.add(new Point2D(-width/2,-height/2)); // TL
        corners.add(new Point2D( width/2,-height/2)); // TR
        corners.add(new Point2D( width/2, height/2)); // BR
        corners.add(new Point2D(-width/2, height/2)); // BL

        g.setPaint(Color.WHITE);

        // Draw Handles at corners
        for(Point2D corner : corners) {
            g.draw(new Rectangle2D.Double(-handleSize/2+corner.getX(), -handleSize/2+corner.getY(),handleSize,handleSize));
        }

        // Outline Shape
        g.setPaint(Color.GRAY);
        g.draw(new Rectangle2D.Double(-(width / 2), -(height / 2), width, height));

        return corners;

    }
}
