package cs355.solution.model;

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

    public Shape getShape(int index) {
        return shapes.get(index);
    }

    public void setShape(int index, Shape shape) {
        setChanged();
        notifyObservers();
        shapes.set(index, shape);
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
}
