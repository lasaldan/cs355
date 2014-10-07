package cs355.solution.view;

import cs355.solution.model.Ellipse;
import cs355.solution.model.Point2D;
import cs355.solution.model.Rectangle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableEllipse implements DrawableShape {

    double x;
    double y;
    double width;
    double height;
    double rotation;
    double handleSize;
    Color color;

    public DrawableEllipse(Ellipse e) {

        width = e.getWidth();
        height = e.getHeight();

        x = e.getCenter().x;
        y = e.getCenter().y;

        color = e.getColor();

        rotation = e.getRotation();

        handleSize = 8;
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        //g.fill(new Ellipse2D.Double(x, y, width, height));
        g.fill(new Ellipse2D.Double(-(width/2), -(height/2), width, height));
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
        g.draw(new Ellipse2D.Double(-(handleSize / 2), -(handleSize * 2)-(height / 2), handleSize, handleSize));

        return new Point2D(0,-12-(height / 2));
    }

    @Override
    public List drawScaleHandles(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Rectangle2D.Double(-(handleSize / 2) - (width / 2), -(handleSize / 2) - (height / 2), handleSize, handleSize));
        g.draw(new Rectangle2D.Double(-(handleSize / 2)+(width / 2), -(handleSize / 2)-(height / 2), handleSize, handleSize));
        g.draw(new Rectangle2D.Double(-(handleSize / 2)-(width / 2), -(handleSize / 2)+(height / 2), handleSize, handleSize));
        g.draw(new Rectangle2D.Double(-(handleSize / 2)+(width / 2), -(handleSize / 2)+(height / 2), handleSize, handleSize));
        g.setPaint(Color.GRAY);
        g.draw(new Rectangle2D.Double(-(width/2), -(height/2), width, height));

        g.draw(new Ellipse2D.Double(-(width/2), -(height/2), width, height));

        return null;
    }

    public boolean isRotationHandleClicked(Point2D hitLoc) {

        double distanceFromCenter = ((hitLoc.y)*(hitLoc.y) + (hitLoc.x)*(hitLoc.x));

        return distanceFromCenter <= (handleSize / 2) * (handleSize / 2);
    }
}
