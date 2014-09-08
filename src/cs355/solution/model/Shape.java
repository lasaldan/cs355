package cs355.solution.model;

import java.awt.*;

/**
 * Created by Daniel on 9/6/14.
 */
public abstract class Shape {

    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
