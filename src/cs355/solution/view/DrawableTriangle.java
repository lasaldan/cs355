package cs355.solution.view;

import cs355.solution.model.Point2D;
import cs355.solution.model.Triangle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableTriangle implements DrawableShape {

    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    double centerX;
    double centerY;

    double rotation;
    double handleSize;

    Color color;

    public DrawableTriangle(Triangle t) {

        centerX = t.getCenter().x;
        centerY = t.getCenter().y;

        x1 = t.getPt1().x;
        y1 = t.getPt1().y;

        x2 = t.getPt2().x;
        y2 = t.getPt2().y;

        x3 = t.getPt3().x;
        y3 = t.getPt3().y;

        color = t.getColor();

        rotation = t.getRotation();

        handleSize = 8;
    }

    @Override
    public void drawOn( Graphics2D g) {

        //System.out.println(new Point2D(x1,y1));
        //System.out.println(new Point2D(x2,y2));
        //System.out.println(new Point2D(x3,y3));

        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
        path.moveTo(x1,y1);
        path.lineTo(x2,y2);
        path.lineTo(x3,y3);
        path.closePath();

        g.setPaint(color);
        g.fill(path);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(centerX, centerY);
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    private double maxHeight() {
        return Math.max(Math.max(Math.abs(y1), Math.abs(y2)),Math.abs(y3));
    }

    @Override
    public Point2D drawRotationHandle(Graphics2D g) {
        g.setPaint(Color.WHITE);
        g.draw(new Ellipse2D.Double(-4, -16 - maxHeight(), 8, 8));
        return new Point2D(0,-12-maxHeight());
    }
    @Override
    public List drawScaleHandles(Graphics2D g) {


        ArrayList<Point2D> corners = new ArrayList();
        corners.add(new Point2D(x1,y1));
        corners.add(new Point2D(x2,y2));
        corners.add(new Point2D(x3,y3));

        g.setPaint(Color.WHITE);

        // Draw Handles at corners
        for(Point2D corner : corners) {
            g.draw(new Rectangle2D.Double(-handleSize/2+corner.getX(), -handleSize/2+corner.getY(), handleSize, handleSize));
        }

        // Outline Shape
        g.setPaint(Color.GRAY);
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
        path.moveTo(x1,y1);
        path.lineTo(x2,y2);
        path.lineTo(x3,y3);
        path.closePath();

        g.setPaint(color);
        g.draw(path);

        return corners;

    }
}
