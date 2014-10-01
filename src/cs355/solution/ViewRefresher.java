package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.IViewRefresher;
import cs355.solution.model.*;
import cs355.solution.view.*;

import java.awt.Graphics2D;
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

    public ViewRefresher () {
        model = null;
    }

    public ViewRefresher (ModelFacade _model) {
        model = _model;
        model.addObserver(this);
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        List shapeList = new ArrayList( model.getShapes() );

        shapeList.add( controller.getTempShape() );

        shapeList.forEach((shape) -> {
            // TODO FIX NULL INITIALIZATION
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

            // AffineTransform obToWorld = new AffineTransform();
            // objToWorld.rotate(Math.PI / 4);
            // objToWorld.translate(100,40);
            // g.setTransform(objToWorld);
            // g.fillRect(-width/w, -height/2, width, height);

            if( drawable != null)
            drawable.drawOn(g2d);
        });
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
