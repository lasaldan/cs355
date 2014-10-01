package cs355.solution.view;

import cs355.solution.model.Triangle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

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

    Color color;

    public DrawableTriangle(Triangle t) {

        centerX = t.getCenter().x;
        centerY = t.getCenter().y;

        x1 = t.getPt1().x + centerX;
        y1 = t.getPt1().y + centerY;

        x2 = t.getPt2().x + centerX;
        y2 = t.getPt2().y + centerY;

        x3 = t.getPt3().x + centerX;
        y3 = t.getPt3().y + centerY;

        color = t.getColor();
    }

    @Override
    public void drawOn( Graphics2D g) {

        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
        path.moveTo(x1,y1);
        path.lineTo(x2,y2);
        path.lineTo(x3,y3);
        path.closePath();

        g.setPaint(color);
        g.fill(path);
    }
}
