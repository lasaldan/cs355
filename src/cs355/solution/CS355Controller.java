package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.solution.model.*;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Created by Daniel on 9/5/14.
 */
public class CS355Controller implements ICS355Controller {

    CS355ZoomController zoomData;
    CS355ScrollController scrollData;
    CS355Image image;
    CS355Image imageBuffer;

    Point2D viewCenter;

    Color currentColor;
    CS355State currentState;
    int currentShapeIndex;
    Shape temp;
    ModelFacade model;
    Point2D first;
    Point2D second;
    Point2D third;
    double tolerance;

    List<Point2D> scaleHandles;
    Point2D rotationHandle;
    Point2D activeHandle;
    Point2D oppositeCorner;
    Point2D currentHandle;
    String end;

    protected boolean hasBackground;

    public CS355Controller() {
        currentColor = Color.WHITE;
        currentShapeIndex = -1;
        tolerance = 4;
        end = "";
        viewCenter = new Point2D(256,256);
        hasBackground = false;
    }

    public void setScaleHandles(List<Point2D> scaleHandles) {
        this.scaleHandles = scaleHandles;
    }

    public void setRotationHandle(Point2D rotationHandle) {
        //System.out.println(rotationHandle);
        this.rotationHandle = rotationHandle;
    }

    public void initView() {
        adjustScrollBars();
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
        zoomData.zoomIn();
        adjustScrollBars();
    }

    @Override
    public void zoomOutButtonHit() {
        zoomData.zoomOut();
        adjustScrollBars();

    }

    private void adjustScrollBars() {
        scrollData.centerAt(viewCenter, zoomData.getZoomLevel());
    }

    @Override
    public void hScrollbarChanged(int value) {
        viewCenter.x = value + scrollData.getCurrentKnobSize() / 2.0;
        GUIFunctions.refresh();
    }

    @Override
    public void vScrollbarChanged(int value) {
        viewCenter.y = value + scrollData.getCurrentKnobSize() / 2.0;
        GUIFunctions.refresh();
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
        ImageTools.median(model.getImage());
        GUIFunctions.refresh();
    }

    @Override
    public void doUniformBlur() {
        ImageTools.blur(model.getImage());
        GUIFunctions.refresh();
    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {
        ImageTools.contrast(model.getImage(),contrastAmountNum);
        GUIFunctions.refresh();
    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {
        ImageTools.brighten(model.getImage(),brightnessAmountNum);
        GUIFunctions.refresh();
    }

    @Override
    public void doLoadImage(BufferedImage openImage) {
        hasBackground = true;

        int height = openImage.getHeight();
        int width = openImage.getWidth();

        int[][] imgData = new int[height][width];

        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                imgData[row][col] = openImage.getRGB(col,row)>>16&255;
            }
        }

        model.loadImage( imgData );
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

                            currentHandle = handle;

                            if(temp instanceof Line) {

                                if(scaleHandles.indexOf(currentHandle) == 0) {
                                    //System.out.println("Clicked Start Handle");
                                    end = "start";
                                    //second = objectToWorld(((Line)temp).getEndPoint(),temp);
                                    //System.out.println("Saving End Handle Location: " + second);
                                }

                                else if(scaleHandles.indexOf(currentHandle) == 1) {
                                    //System.out.println("Clicked End Handle");
                                    end = "end";
                                    //second = objectToWorld(((Line) temp).getStartPoint(), temp);
                                    //System.out.println("Saving Start Handle Location: " + second);
                                }
                            }

                            if(temp instanceof Triangle) {

                                if(scaleHandles.indexOf(currentHandle) == 0)
                                    end = "first";
                                else if(scaleHandles.indexOf(currentHandle) == 1)
                                    end = "second";
                                else
                                    end = "third";
                            }


                            if(temp instanceof Square || temp instanceof Circle)
                                oppositeCorner = objectToWorld(
                                    new Point2D (scaleHandles.get( (scaleHandles.indexOf(handle) +2) % 4)), temp);
                            break;
                        }
                    }
                }

                if(currentShapeIndex != -1 && currentHandle != null) {
                    currentState = CS355State.SCALING;
                    temp = (Shape) model.getShape(currentShapeIndex);
                    first = new Point2D(loc);
                    second = model.getShape(currentShapeIndex).getCenter();
                    if(end.equals("end"))
                        third = objectToWorld(((Line)temp).getEndPoint(),temp);
                    if(end.equals("start"))
                        third = objectToWorld(((Line)temp).getStartPoint(),temp);
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

        if(Math.sqrt((diffX * diffX) + (diffY * diffY)) <= tolerance) {
            return true;
        }

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
                temp = null;
                IShape currentShape = model.getShape(currentShapeIndex);

                double diffx = loc.getX() - first.getX();
                double diffy = loc.getY() - first.getY();

                Point2D newCenter;

                if(currentShape instanceof Line) {

                    Color color = currentShape.getColor();
                    if(end.equals("end")) {
                        currentShape = new Line(third,third,color);
                        ((Line) currentShape).setStartPoint(loc);
                    }

                    if(end.equals("start")) {
                        currentShape = new Line(third, third, color);
                        ((Line) currentShape).setEndPoint(loc);
                    }
                }

                else if (currentShape instanceof Triangle) {
                    Color color;
                    if(end.equals("first")) {
                        color = currentShape.getColor();
                        Point2D pt1 = loc;
                        Point2D pt2 = objectToWorld(((Triangle) currentShape).getPt2(), currentShape);
                        Point2D pt3 = objectToWorld(((Triangle) currentShape).getPt3(), currentShape);
                        currentShape = new Triangle(pt1, pt2, pt3, color);
                    }
                    else if(end.equals("second")) {
                        color = currentShape.getColor();
                        Point2D pt1 = objectToWorld(((Triangle) currentShape).getPt1(), currentShape);
                        Point2D pt2 = loc;
                        Point2D pt3 = objectToWorld(((Triangle) currentShape).getPt3(), currentShape);
                        currentShape = new Triangle(pt1, pt2, pt3, color);
                    }
                    else if(end.equals("third")) {
                        color = currentShape.getColor();
                        Point2D pt1 = objectToWorld(((Triangle) currentShape).getPt1(), currentShape);
                        Point2D pt2 = objectToWorld(((Triangle) currentShape).getPt2(), currentShape);
                        Point2D pt3 = loc;
                        currentShape = new Triangle(pt1, pt2, pt3, color);
                    }
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
                        currentShape.setCenter(objectToWorld(newCenter, currentShape));
                    }
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
            case SCALING:
            case ROTATING:
                currentState = CS355State.SELECTING;
                currentHandle = null;
                third = null;
                end = "";
                break;

        }
    }

    @Override
    public void setModel( ModelFacade _model ) {
        this.model = _model;
    }

    @Override
    public void setScrollController(CS355ScrollController scroller) {
        this.scrollData = scroller;
    }

    @Override
    public void setZoomController(CS355ZoomController zoomer) {
        this.zoomData = zoomer;
    }

    public AffineTransform getTranslationTransformation() {
        //return new AffineTransform(1.0d, 0, 0, 1.0d, -viewCenter.x, -viewCenter.y);

        double zoomLevel = zoomData.getZoomLevel();
        return new AffineTransform(1.0d, 0, 0, 1.0d, -viewCenter.x + 256*(1/zoomLevel), -viewCenter.y + 256*(1/zoomLevel));
    }

    public AffineTransform getScaleTransformation() {
        return new AffineTransform(1.0/zoomData.getZoomLevel(), 0, 0, 1.0/zoomData.getZoomLevel(), 0, 0);
    }
    @Override
    public Shape getTempShape() {
        return temp;
    }

    public Point2D worldToObject(Point2D loc, IShape shape) {




        AffineTransform rotation = new AffineTransform(Math.cos(shape.getRotation()), Math.sin(shape.getRotation()), -Math.sin(shape.getRotation()), Math.cos(shape.getRotation()), 0, 0);
        AffineTransform translation = new AffineTransform(1,0,0,1,shape.getCenter().x, shape.getCenter().y);



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

            Point2D localCoordinates = worldToObject(loc, shape);

            if(shape.containsHitPoint(localCoordinates, tolerance ))
                index[0] = shapeList.indexOf(shape);

        });

        return index[0];
    }
}
