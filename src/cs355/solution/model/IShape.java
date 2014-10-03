package cs355.solution.model;

import java.awt.Color;

/**
 * Created by Daniel on 9/6/14.
 */
public interface IShape {

    public void setColor(Color color);

    public Color getColor();

    public void setCenter(Point2D center);

    public Point2D getCenter();

    public void setRotation(double rotation);

    public double getRotation();

    public boolean containsHitPoint(Point2D hitLoc);
}
