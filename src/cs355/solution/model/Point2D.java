package cs355.solution.model;

/**
 * Created by Daniel on 9/6/14.
 */
public class Point2D {

    public double x;
    public double y;

    public Point2D() {
        x = 0;
        y = 0;
    }

    public Point2D(double newX, double newY)
    {
        x = newX;
        y = newY;
    }

    public Point2D(int newX, int newY)
    {
        x = newX;
        y = newY;
    }

    public Point2D(Point2D loc) {
        this.x = loc.x;
        this.y = loc.y;
    }

    @Override
    public String toString()
    {
        return "X: "+x+", Y: "+y;
    }
}
