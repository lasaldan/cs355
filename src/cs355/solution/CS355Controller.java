package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.solution.model.*;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Daniel on 9/5/14.
 */
public class CS355Controller implements ICS355Controller {

    Color currentColor;
    CS355State currentState;
    int currentShapeIndex;
    Shape temp;
    ModelFacade model;
    Point2D first;
    Point2D second;
    double tolerance;

    List<Point2D> scaleHandles;
    Point2D rotationHandle;

    public CS355Controller() {
        currentColor = Color.WHITE;
        currentShapeIndex = -1;
        tolerance = 4;
    }

    public void setScaleHandles(List<Point2D> scaleHandles) {
        this.scaleHandles = scaleHandles;
    }

    public void setRotationHandle(Point2D rotationHandle) {
        //System.out.println(rotationHandle);
        this.rotationHandle = rotationHandle;
    }

    @Override
    public void colorButtonHit(Color c) {
        if(currentShapeIndex != -1) {
            IShape temp = model.getShape(currentShapeIndex);
            temp.setColor(c);
            model.setShape(currentShapeIndex, temp);
        }
        currentColor = c;
        GUIFunctions.changeSelectedColor(c);
    }

    @Override
    public void triangleButtonHit() {
        currentState = CS355State.DRAWING_TRIANGLE_FIRST_POINT;
        currentShapeIndex = -1;
        GUIFunctions.refresh();
    }

    @Override
    public void squareButtonHit() {
        currentState = CS355State.DRAWING_SQUARE_FIRST_POINT;
        currentShapeIndex = -1;
        GUIFunctions.refresh();
    }

    @Override
    public void rectangleButtonHit() {
        currentState = CS355State.DRAWING_RECTANGLE_FIRST_POINT;
        currentShapeIndex = -1;
        GUIFunctions.refresh();
    }

    @Override
    public void circleButtonHit() {
        currentState = CS355State.DRAWING_CIRCLE_FIRST_POINT;
        currentShapeIndex = -1;
        GUIFunctions.refresh();
    }

    @Override
    public void ellipseButtonHit() {
        currentState = CS355State.DRAWING_ELLIPSE_FIRST_POINT;
        currentShapeIndex = -1;
        GUIFunctions.refresh();
    }

    @Override
    public void lineButtonHit() {
        currentState = CS355State.DRAWING_LINE_FIRST_POINT;
        currentShapeIndex = -1;
        GUIFunctions.refresh();
    }

    @Override
    public void selectButtonHit() {
        currentState = CS355State.SELECTING;
        currentShapeIndex = -1;
    }

    @Override
    public void zoomInButtonHit() {

    }

    @Override
    public void zoomOutButtonHit() {

    }

    @Override
    public void hScrollbarChanged(int value) {

    }

    @Override
    public void vScrollbarChanged(int value) {

    }

    @Override
    public void toggle3DModelDisplay() {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {

    }

    @Override
    public void doEdgeDetection() {

    }

    @Override
    public void doSharpen() {

    }

    @Override
    public void doMedianBlur() {

    }

    @Override
    public void doUniformBlur() {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {

    }

    @Override
    public void doLoadImage(BufferedImage openImage) {

    }

    @Override
    public void toggleBackgroundDisplay() {

    }

    public void handleClick(Point2D loc) {

        switch (currentState) {
            case DRAWING_TRIANGLE_FIRST_POINT:
                first = new Point2D(loc);
                currentState = CS355State.DRAWING_TRIANGLE_SECOND_POINT;
                break;

            case DRAWING_TRIANGLE_SECOND_POINT:
                second = new Point2D(loc);
                currentState = CS355State.DRAWING_TRIANGLE_THIRD_POINT;
                break;

            case DRAWING_TRIANGLE_THIRD_POINT:
                temp = new Triangle(first, second, new Point2D(loc), currentColor);
                model.addShape(temp);
                currentState = CS355State.DRAWING_TRIANGLE_FIRST_POINT;
                break;

        }
    }

    @Override
    public void handleMousePressed(Point2D loc) {

        switch (currentState) {
            case DRAWING_LINE_FIRST_POINT:
                first = new Point2D( loc );
                temp = new Line( loc, loc, currentColor );
                currentState = CS355State.DRAWING_LINE_SECOND_POINT;
                break;

            case DRAWING_RECTANGLE_FIRST_POINT:
                first = new Point2D( loc );
                temp = new Rectangle(loc, 0, 0, currentColor);
                currentState = CS355State.DRAWING_RECTANGLE_SECOND_POINT;
                break;

            case DRAWING_SQUARE_FIRST_POINT:
                first = new Point2D( loc );
                temp = new Square(loc, 0, currentColor);
                currentState = CS355State.DRAWING_SQUARE_SECOND_POINT;
                break;

            case DRAWING_ELLIPSE_FIRST_POINT:
                first = new Point2D( loc );
                temp = new Ellipse(loc, 0, 0, currentColor);
                currentState = CS355State.DRAWING_ELLIPSE_SECOND_POINT;
                break;

            case DRAWING_CIRCLE_FIRST_POINT:
                first = new Point2D( loc );
                temp = new Circle(loc, 0, currentColor);
                currentState = CS355State.DRAWING_CIRCLE_SECOND_POINT;
                break;

            case SELECTING:
                first = loc;

                if(currentShapeIndex != -1 &&
                   isNear(worldToObject(loc,model.getShape(currentShapeIndex)), rotationHandle, tolerance)) {
                    currentState = CS355State.ROTATING;
                    break;
                }

                currentShapeIndex = getShapeIndexAt(loc, tolerance);

                if(currentShapeIndex != -1) {
                    GUIFunctions.changeSelectedColor(model.getShape(currentShapeIndex).getColor());
                    second = model.getShape(currentShapeIndex).getCenter();
                }

                GUIFunctions.refresh();
                currentState = CS355State.DRAGGING_SELECTION;
                break;
        }
    }

    public boolean isNear(Point2D one, Point2D two, double tolerance) {

        if(one == null || two == null)
            return false;

        double diffX = one.getX() - two.getX();
        double diffY = one.getY() - two.getY();

        //System.out.println("is near?");
        //System.out.println(one);
        //System.out.println(two);
        //System.out.println(tolerance);

        if(Math.sqrt((diffX * diffX) + (diffY * diffY)) <= tolerance) {
            //System.out.println("Near");
            return true;
        }
        //System.out.println("Not Near");
        return false;
    }

    @Override
    public void handleMouseDrag(Point2D loc) {

        switch (currentState) {
            case DRAWING_LINE_SECOND_POINT:
                ((Line) temp).setEndPoint(loc);
                break;

            case DRAWING_RECTANGLE_SECOND_POINT:
                ((Rectangle) temp).setDimensions(first, loc);
                break;

            case DRAWING_SQUARE_SECOND_POINT:
                ((Square) temp).setDimensions(first, loc);
                break;

            case DRAWING_ELLIPSE_SECOND_POINT:
                ((Ellipse) temp).setDimensions(first, loc);
                break;

            case DRAWING_CIRCLE_SECOND_POINT:
                ((Circle) temp).setDimensions(first, loc);
                break;

            case DRAGGING_SELECTION:
                double xdiff = loc.getX() - first.getX();
                double ydiff = loc.getY() - first.getY();

                if(currentShapeIndex != -1) {
                    IShape temp = model.getShape(currentShapeIndex);
                    double x = second.getX() + xdiff;
                    double y = second.getY() + ydiff;

                    temp.setCenter(new Point2D(x, y));

                    model.setShape(currentShapeIndex, temp);
                }
                break;
            case ROTATING:

                IShape temp = model.getShape(currentShapeIndex);

                double diffX = temp.getCenter().getX() - loc.getX();
                double diffY = temp.getCenter().getY() - loc.getY();

                temp.setRotation(Math.atan2(-diffX, diffY));

                model.setShape(currentShapeIndex, temp);

                break;

        }
        GUIFunctions.refresh();
    }

    @Override
    public void handleMouseReleased(Point2D point2D) {

        switch (currentState) {
            case DRAWING_LINE_SECOND_POINT:
                model.addShape(temp);
                currentState = CS355State.DRAWING_LINE_FIRST_POINT;
                temp = null;
                break;

            case DRAWING_RECTANGLE_SECOND_POINT:
                model.addShape(temp);
                currentState = CS355State.DRAWING_RECTANGLE_FIRST_POINT;
                temp = null;
                break;

            case DRAWING_SQUARE_SECOND_POINT:
                model.addShape(temp);
                currentState = CS355State.DRAWING_SQUARE_FIRST_POINT;
                temp = null;
                break;

            case DRAWING_ELLIPSE_SECOND_POINT:
                model.addShape( temp );
                currentState = CS355State.DRAWING_ELLIPSE_FIRST_POINT;
                temp = null;
                break;

            case DRAWING_CIRCLE_SECOND_POINT:
                model.addShape( temp );
                currentState = CS355State.DRAWING_CIRCLE_FIRST_POINT;
                temp = null;
                break;

            case DRAGGING_SELECTION:
                currentState = CS355State.SELECTING;
                break;

            case ROTATING:
                currentState = CS355State.SELECTING;
                break;

        }
    }

    @Override
    public void setModel( ModelFacade _model ) {
        this.model = _model;
    }

    @Override
    public Shape getTempShape() {
        return temp;
    }

    public Point2D worldToObject(Point2D loc, IShape shape) {

        Point2D localCoordinates = new Point2D();

        // create a new transformation (defaults to identity)
        AffineTransform worldToObj = new AffineTransform();

        // rotate back from its orientation (last transformation)
        worldToObj.rotate(-shape.getRotation());

        // translate back from its position in the world (first transformation)
        worldToObj.translate(-shape.getCenter().x,-shape.getCenter().y);

        // and transform point from world to object
        worldToObj.transform(loc, localCoordinates);

        return localCoordinates;
    }

    public int getShapeIndexAt( Point2D loc, double tolerance ) {
        List<IShape> shapeList = model.getShapes();
        final int[] index = new int[1];
        index[0] = -1;

        shapeList.forEach((shape) -> {

            //transform clickPoint to local coordinates
            Point2D localCoordinates = worldToObject(loc, shape);

            if(shape.containsHitPoint(localCoordinates, tolerance ))
                index[0] = shapeList.indexOf(shape);

        });

        return index[0];
    }
}
