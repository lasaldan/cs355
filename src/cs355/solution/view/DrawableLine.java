package cs355.solution.view;

import cs355.solution.model.Line;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

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
        g.draw(new Line2D.Double(x1, y1, x2, y2));
    }
}
