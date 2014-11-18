package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.IViewRefresher;
import cs355.solution.model.*;
import cs355.solution.model.Rectangle;
import cs355.solution.view.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

/**
 * Created by Daniel on 9/5/14.
 */
public class ViewRefresher implements IViewRefresher, Observer {

    ModelFacade model;
    CS355Controller controller;
    DrawableShape tempShape;
    AffineTransform tempTransform;

    public ViewRefresher () {
        model = null;
    }

    public ViewRefresher (ModelFacade _model) {
        model = _model;
        model.addObserver(this);
        tempShape = null;
        tempTransform = null;
    }

    @Override
    public void refreshView(Graphics2D g2d_fresh) {
        Graphics2D g2d = (Graphics2D)g2d_fresh.create();
        List shapeList = new ArrayList( model.getShapes() );

        shapeList.add(controller.getTempShape());

        int selectedShape = controller.currentShapeIndex;

        shapeList.forEach((shape) -> {
            DrawableShape drawable = null;

            if(shape instanceof Line)
                drawable = new DrawableLine((Line) shape);

            if(shape instanceof Rectangle)
                drawable = new DrawableRectangle((Rectangle) shape);

            if(shape instanceof Square)
                drawable = new DrawableSquare((Square) shape);

            if(shape instanceof Ellipse)
                drawable = new DrawableEllipse((Ellipse) shape);

            if(shape instanceof Circle)
                drawable = new DrawableCircle((Circle) shape);

            if(shape instanceof Triangle)
                drawable = new DrawableTriangle((Triangle) shape);


            if( drawable != null) {
                // create a new transformation (defaults to identity)
                AffineTransform objToWorld = new AffineTransform();

                // Set variables for Translation Transformation
                double translation_m00 = 1.0;
                double translation_m10 = 0.0;
                double translation_m01 = 0.0;
                double translation_m11 = 1.0;
                double translation_m02 = drawable.getCenter().x;
                double translation_m12 = drawable.getCenter().y;

                // Set variables for Rotation Transformation
                double rotation_m00 = Math.cos(drawable.getRotation());
                double rotation_m10 = Math.sin(drawable.getRotation());
                double rotation_m01 = -Math.sin(drawable.getRotation());
                double rotation_m11 = Math.cos(drawable.getRotation());
                double rotation_m02 = 0.0;
                double rotation_m12 = 0.0;


                AffineTransform translation = new AffineTransform(translation_m00,
                                                                  translation_m10,
                                                                  translation_m01,
                                                                  translation_m11,
                                                                  translation_m02,
                                                                  translation_m12);

                AffineTransform rotation = new AffineTransform(rotation_m00,
                                                               rotation_m10,
                                                               rotation_m01,
                                                               rotation_m11,
                                                               rotation_m02,
                                                               rotation_m12);



                // reset Transform to identity
                g2d.setTransform(objToWorld);

				// Object to World
                objToWorld.concatenate(rotation);
				objToWorld.concatenate(translation);

				// World to View
				objToWorld.concatenate(controller.getTranslationTransformation());
				objToWorld.concatenate(controller.getScaleTransformation());


                // translate to its position in the world (last transformation)
                //objToWorld.translate(drawable.getCenter().x, drawable.getCenter().y);
                //objToWorld.concatenate(controller.getTranslationTransformation());

                // rotate to its orientation (first transformation)
                //objToWorld.rotate(drawable.getRotation());

                // set the drawing transformation
                g2d.setTransform(objToWorld);

                // and finally draw
                drawable.drawOn(g2d);

                if(selectedShape != -1 && selectedShape == shapeList.indexOf(shape)) {
                    tempShape = drawable;
                    tempTransform = objToWorld;
                }
            }
        });

        //System.out.println(selectedShape);
        if(selectedShape != -1 && tempShape != null) {
            g2d.setTransform(tempTransform);
            controller.setRotationHandle( tempShape.drawRotationHandle(g2d) );
            controller.setScaleHandles( tempShape.drawScaleHandles(g2d));
            //tempShape = null;
        }
    }

    @Override
    public void setModel(ModelFacade _model) {
        model = _model;
        model.addObserver(this);
    }

    @Override
    public void setController(CS355Controller _controller) {
        controller = _controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }
}
