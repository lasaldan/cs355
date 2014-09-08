package cs355.solution.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Daniel on 9/6/14.
 */
public class Shapes implements Iterable {
    private List<Shape> shapeList;

    public Shapes() {
        shapeList = new ArrayList<>();
    }

    public void add( Shape newShape ) {
        shapeList.add( newShape );
    }

    public List getShapes() {
        return shapeList;
    }

    public int size() {
        return shapeList.size();
    }

    public Shape get( int index ) {
        return shapeList.get( index );
    }

    public void set( int index, Shape shape ) {
        shapeList.set(index, shape);
    }

    @Override
    public Iterator iterator() {
        return shapeList.iterator();
    }
}
