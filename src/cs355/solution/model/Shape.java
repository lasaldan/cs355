package cs355.solution.model;

import cs355.GUIFunctions;

import java.awt.Color;

/**
 * Created by Daniel on 9/6/14.
 */
public abstract class Shape implements IShape {

    protected Color color;
    protected Point2D center;
    protected double rotation;

    public Shape () {
        color = Color.WHITE;
        center = new Point2D(0,0);
        rotation = 0;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public abstract boolean containsHitPoint(Point2D hitLoc, double tolerance);
}
