package cs355.solution.view;

import cs355.solution.model.Line;
import cs355.solution.model.Point2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by Daniel on 9/6/14.
 */
public class DrawableLine implements DrawableShape {

    double x1;
    double y1;
    double x2;
    double y2;

    Color color;

    public DrawableLine(Line l) {
        x1 = l.getStartPoint().x;
        y1 = l.getStartPoint().y;

        x2 = l.getEndPoint().x;
        y2 = l.getEndPoint().y;

        color = l.getColor();
    }

    @Override
    public void drawOn( Graphics2D g) {
        g.setPaint(color);
        g.draw(new Line2D.Double(x1,y1,x2,y2));
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((x1-x2)/2,((y1-y2)/2));
    }

    @Override
    public double getRotation() {
        return 0;
    }
}
