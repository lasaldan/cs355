package cs355.solution.view;

import cs355.solution.model.Point2D;
import cs355.solution.model.Shape;

import java.awt.*;

/**
 * Created by Daniel on 9/6/14.
 */
public interface DrawableShape {

    public void drawOn(Graphics2D g);

    public Point2D getCenter();

    public double getRotation();

    public void drawHandlesOn(Graphics2D g2d);
}
