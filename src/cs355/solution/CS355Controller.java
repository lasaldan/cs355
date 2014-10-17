package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.solution.model.*;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;

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
    Point2D activeHandle;
    Point2D oppositeCorner;
    Point2D currentHandle;

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

                if(currentShapeIndex != -1)
                    temp = (Shape) model.getShape(currentShapeIndex);

                if(currentShapeIndex != -1 &&
                   isNear(worldToObject(loc,temp), rotationHandle, tolerance)) {
                    currentState = CS355State.ROTATING;
                    break;
                }

                if(currentShapeIndex != -1) {
                    for (Point2D handle : scaleHandles) {
                        if (isNear(worldToObject(loc, temp), handle, tolerance)) {
                            //System.out.println("Setting Current Handle");
                            currentHandle = handle;
                            oppositeCorner = objectToWorld(new Point2D (scaleHandles.get( (scaleHandles.indexOf(handle) +2) % 4)), temp);
                            break;
                        }
                    }
                }

                if(currentShapeIndex != -1 && currentHandle != null) {
                    currentState = CS355State.SCALING;
                    first = new Point2D(loc);
                    second = model.getShape(currentShapeIndex).getCenter();
                    temp = (Shape) model.getShape(currentShapeIndex);
                    //System.out.println("Setting Active");
                    activeHandle = new Point2D(currentHandle);
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
                    temp = (Shape) model.getShape(currentShapeIndex);
                    double x = second.getX() + xdiff;
                    double y = second.getY() + ydiff;

                    temp.setCenter(new Point2D(x, y));

                    model.setShape(currentShapeIndex, temp);
                }
                break;
            case ROTATING:

                temp = (Shape) model.getShape(currentShapeIndex);

                double diffX = temp.getCenter().getX() - loc.getX();
                double diffY = temp.getCenter().getY() - loc.getY();

                temp.setRotation(Math.atan2(-diffX, diffY));

                model.setShape(currentShapeIndex, temp);

                break;
            case SCALING:

                IShape currentShape = model.getShape(currentShapeIndex);

                double diffx = loc.getX() - first.getX();
                double diffy = loc.getY() - first.getY();

                Point2D newCenter;
                Point2D currentInLocal = worldToObject(loc, currentShape);

                if(currentShape instanceof Line) {
                    System.out.println(scaleHandles.indexOf(activeHandle));
                    if(scaleHandles.indexOf(activeHandle) == 0)
                        ((Line) currentShape).setStartPoint(loc);
                    if(scaleHandles.indexOf(activeHandle) == 1)
                        ((Line) currentShape).setEndPoint(loc);
                }

                else if(currentShape instanceof Square || currentShape instanceof Circle) {

                    if(currentShape instanceof Square) {
                        Point2D mousePosInLocal = worldToObject(loc, currentShape);
                        Point2D localFixedCorner = worldToObject(oppositeCorner, currentShape);

                        newCenter = ((Square)currentShape).getCenterFromCorner(mousePosInLocal, localFixedCorner);
                        currentShape.setCenter(objectToWorld(newCenter,currentShape));
                    }
                    else if(currentShape instanceof Circle) {
                        Point2D mousePosInLocal = worldToObject(loc, currentShape);
                        Point2D localFixedCorner = worldToObject(oppositeCorner, currentShape);

                        newCenter = ((Circle)currentShape).getCenterFromCorner(mousePosInLocal, localFixedCorner);
                        currentShape.setCenter(objectToWorld(newCenter,currentShape));
                    }
                    /*
                    Point2D oppositeWorldCoords = objectToWorld(oppositeCorner,currentShape);
                    Point2D originalHandleLocation = first;
                    Point2D originalShapeCenter = second;
                    Point2D currentMousePosition = loc;

                    diffx = currentMousePosition.x - originalHandleLocation.x;
                    diffy = currentMousePosition.y - originalHandleLocation.y;
                    System.out.println("Mouse Moved: " + diffx + "," + diffy);

                    double centerAdjustX = diffx / 2.0;
                    double centerAdjustY = diffy / 2.0;

                    newCenter = new Point2D( originalShapeCenter.x + centerAdjustX, originalShapeCenter.y + centerAdjustY);

                    double newSizeX = newCenter.x - oppositeWorldCoords.x;
                    double newSizeY = newCenter.y - oppositeWorldCoords.y;

                    System.out.println("New Size: " + newSizeX + "," + newSizeY);

                    int signX = (newSizeX < 0)? -1 : 1;
                    int signY = (newSizeY < 0)? -1 : 1;

                    newCenter.x = originalShapeCenter.x + (min(newSizeX, newSizeY)*signX);
                    newCenter.y = originalShapeCenter.y + (min(newSizeX, newSizeY)*signY);

                    System.out.println("Moving Center to: " + newCenter);

                    currentShape.setCenter(newCenter);

                    System.out.println();

*/


                    //System.out.println((second.x - diffx) + " " + (second.y - diffy));

 /*
                    if(currentInLocal.x > 0 && currentInLocal.y > 0)
                        System.out.println("BR");
                    else if(currentInLocal.x >= 0)
                        System.out.println("UR");
                    else if(currentInLocal.y >= 0)
                        System.out.println("BL");
                    else
                        System.out.println("UL");
                        */
                    /*
                    System.out.println(scaleHandles.size() + " " + scaleHandles.indexOf(currentHandle));
                    System.out.println(currentHandle);

                    //System.out.println(currentInLocal);
                    double size = min(abs(diffx), abs(diffy));

                    if (currentInLocal.getX() >= 0 && currentInLocal.getY() >= 0){
                        //System.out.println("LowerRight");
                        diffx = size;
                        diffy = size;
                    }
                    else if (currentInLocal.getX() > 0 && currentInLocal.getY() < 0) {
                        //System.out.println("UpperRight");
                        diffx = size;
                        diffy = -size;
                    }
                    else if (currentInLocal.getX() < 0 && currentInLocal.getY() > 0){
                        //System.out.println("LowerLeft");
                        diffx = -size;
                        diffy = size;
                    }
                    else {
                        //System.out.println("UpperLeft");
                        diffx = -size;
                        diffy = -size;
                    }

                    newCenter = new Point2D(second.x + diffx / 2.0, second.y + diffy / 2.0);
                    /*
                    double diffxSize = min(abs(diffx),abs(diffy)) * (diffx/abs(diffx));
                    double diffySize = min(abs(diffx),abs(diffy)) * (diffy/abs(diffy));

                    * //System.out.println(diffx + " " + diffy + " " + diffxSize + " " + diffySize);
                    newCenter = new Point2D(second.x + diffxSize / 2.0, second.y + diffySize / 2.0);


                    currentShape = new Square();
                    currentShape.setCenter(oppositeCorner);
                    currentShape.setCorner(loc);
                    */

/*
                    Point2D current = worldToObject(loc,currentShape);
                    System.out.println(current);
                    */
                    //currentShape.setCenter(newCenter);

                    //Point2D corner = new Point2D(current.x + newCenter.x, current.y + newCenter.y);

                    //currentShape.setCorner(corner);

                    /*

                    newCenter = new Point2D(second.x + diffx / 2.0, second.y + diffy / 2.0);
                    Point2D centerInLocal = worldToObject(newCenter,currentShape);
                    Point2D mousePosition = worldToObject(loc,currentShape);

                    if(centerInLocal)
                    */

                    /*
                    System.out.println("Original Corner Loc: " + currentHandle);
                    diffx = -first.x + worldToObject(loc,temp).x;
                    diffy = -first.y + worldToObject(loc,temp).y;

                    diffx = worldToObject(loc,temp).x - worldToObject(first,temp).x;
                    diffy = worldToObject(loc,temp).y - worldToObject(first,temp).y;

                    System.out.println("Local Drag Offset:" + diffx + ","+diffy);

                    double diffxCenter = min(abs(diffx), abs(diffy));
                    double diffyCenter = min(abs(diffx), abs(diffy));

                    int signx = (diffx < 0)? -1 : 1;
                    int signy = (diffy < 0)? -1 : 1;

                    diffxCenter *= signx;
                    diffyCenter *= signy;

                    newCenter = new Point2D(second.x + diffxCenter/2.0, second.y+diffyCenter/2.0);

                    double shortestSide = min(first.x + diffx, first.y + diffy);
                    System.out.println(shortestSide);

                    currentShape.setCenter(newCenter);
*/
/*
                    double sizeDiffx = min(abs(diffx),abs(diffy)) * (diffx/abs(diffx));
                    double sizeDiffy = min(abs(diffx),abs(diffy)) * (diffy/abs(diffy));
                    System.out.println("Size Difference: "+sizeDiffx + "," + sizeDiffy);

                    double size = abs(currentHandle.getX())*2;
                    System.out.println("Size of Square: " + size);
                    double newcornerx = size + diffx;
                    double newcornery = size + diffy;

                    double shortestSide = min(abs(newcornerx), abs(newcornery));

                    newCenter = new Point2D(second.x + sizeDiffx/2.0, second.y + sizeDiffy/2.0);
                    currentShape.setCenter(newCenter);
                    //currentShape.setCorner(new Point2D(shortestSide/2.0, shortestSide/2.0));
/*
                    System.out.println("Shortest side of new Square: " + shortestSide);

                    double centerOffsetX = (shortestSide - currentHandle.x) / 2.0;
                    double centerOffsetY = (shortestSide - currentHandle.y) / 2.0;

                    double xsize = shortestSide * (newcornerx/abs(newcornerx));
                    double ysize = shortestSide * (newcornery/abs(newcornery));

                    newCenter = new Point2D(second.x+centerOffsetX, second.y+centerOffsetY);
                    //System.out.println(xsize);
                    //System.out.println(ysize);
                    System.out.println(newCenter);
*/



                }
                else {
                    newCenter = new Point2D(second.x + diffx / 2.0, second.y + diffy / 2.0);

                    currentShape.setCenter(newCenter);

                    Point2D current = worldToObject(loc,currentShape);
                    currentShape.setCorner(current);
                }

                model.setShape(currentShapeIndex, currentShape);

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

            case SCALING:
            case ROTATING:
                currentState = CS355State.SELECTING;
                currentShapeIndex = -1;
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

    public Point2D objectToWorld(Point2D loc, IShape shape) {
        Point2D worldCoordinates = new Point2D();

        AffineTransform objToWorld = new AffineTransform();

        // translate to its position in the world (last transformation)
        objToWorld.translate(shape.getCenter().x, shape.getCenter().y);

        // rotate to its orientation (first transformation)
        objToWorld.rotate(shape.getRotation());

        // set the drawing transformation
        objToWorld.transform(loc, worldCoordinates);

        return worldCoordinates;
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
