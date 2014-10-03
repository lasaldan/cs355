package cs355.solution.model;

import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Daniel on 9/6/14.
 */
public class ModelFacade extends Observable {

    Shapes shapes = new Shapes();
    Semaphore modelLock = new Semaphore(1);

    public List getShapes() {
        return shapes.getShapes();
    }

    public IShape getShape(int index) {
        return shapes.get(index);
    }

    public void setShape(int index, IShape shape) {
        setChanged();
        notifyObservers();
        shapes.set(index, (Shape) shape);
    }

    public int addShape(Shape s) {

        int shapeIndex = 0;

        try {

            modelLock.acquire();

            shapes.add(s);
            shapeIndex = shapes.size();
            setChanged();

            modelLock.release();

        } catch (InterruptedException e) {

            System.out.println("Error Adding Shape (Using Semaphore)");
            e.printStackTrace();

        }

        notifyObservers();

        return shapeIndex - 1;
    }

    public int getShapeIndexAt( Point2D loc ) {
        List<IShape> shapeList = getShapes();
        final int[] index = new int[1];
        index[0] = -1;

        shapeList.forEach((shape) -> {

            //transform clickPoint to local coordinates
            Point2D localCoordinates = new Point2D();

            // create a new transformation (defaults to identity)
            AffineTransform worldToObj = new AffineTransform();

            // rotate back from its orientation (last transformation)
            worldToObj.rotate(-shape.getRotation());

            // translate back from its position in the world (first transformation)
            worldToObj.translate(-shape.getCenter().x,-shape.getCenter().y);

            // and transform point from world to object
            worldToObj.transform(loc, localCoordinates);

            if(shape.containsHitPoint(localCoordinates))
                index[0] = shapeList.indexOf(shape);

        });

        return index[0];
    }
}
