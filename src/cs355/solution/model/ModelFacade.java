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
    Image background;

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

    public void loadImage(int[][] data) {
        background = new Image(data);
        setChanged();
        notifyObservers();
    }

    public Image getImage() {
        return background;
    }

    public void adjustBrightness( int val ) {
        ImageTools.brighten(background, val);
        setChanged();
        notifyObservers();
    }

    public void adjustContrast( int val ) {
        ImageTools.contrast(background, val);
        setChanged();
        notifyObservers();
    }

    public void uniformBlur() {
        ImageTools.blur(background);
        setChanged();
        notifyObservers();
    }

    public void medianBlur() {
        ImageTools.median(background);
        setChanged();
        notifyObservers();
    }

    public void sharpen() {
        ImageTools.sharpen(background);
        setChanged();
        notifyObservers();
    }

    public void detectEdges() {
        ImageTools.edgeDetect(background);
        setChanged();
        notifyObservers();
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
