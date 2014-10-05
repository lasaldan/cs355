package cs355.solution.model;

/**
 * Created by Daniel on 10/3/14.
 */
public class MatrixUtils {

    public static double dot(Point2D a,Point2D b) {
        return a.x * b.x + a.y * b.y;
    }

    public static double norm(Point2D a) {
        return Math.sqrt( dot(a,a) );
    }

    public static Point2D minus(Point2D a, Point2D b) {
        return new Point2D ( a.x-b.x, a.y-b.y );
    }

    public static double distance(Point2D a, Point2D b) {
        return norm( minus( a, b) );
    }
    /*
        #define dot(u,v)   ((u).x * (v).x + (u).y * (v).y + (u).z * (v).z)
        #define norm(v)     sqrt(dot(v,v))     // norm = length of  vector
        #define d(u,v)      norm(u-v)          // distance = norm of difference
    */

    double
    distFromLine( Point2D point, Point2D start, Point2D end)
    {
        //Vector v = S.P1 - S.P0;
        //Vector w = P - S.P0;
        Point2D v = minus (end, start);
        Point2D w = minus (point, start);

        double c1 = dot(w,v);
        if ( c1 <= 0 )
            return distance(point, start);

        double c2 = dot(v,v);
        if ( c2 <= c1 )
            return distance(point, end);

        double b = c1 / c2;

        //Point2D Pb = start + b * v;
        Point2D Pb = null;
        return distance(point, Pb);
    }
}
