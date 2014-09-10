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
    Color color;

    public DrawableTriangle(Triangle t) {
        x1 = t.getPt1().x;
        y1 = t.getPt1().y;

        x2 = t.getPt2().x;
        y2 = t.getPt2().y;

        x3 = t.getPt3().x;
        y3 = t.getPt3().y;

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
