package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.solution.model.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

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


    public CS355Controller() {
        currentColor = Color.WHITE;
        currentShapeIndex = -1;
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

            case SELECTING:
                currentShapeIndex = model.getShapeIndexAt(loc);
                GUIFunctions.refresh();
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
        }
    }

    @Override
    public void handleMouseDrag(Point2D point2D) {

        switch (currentState) {
            case DRAWING_LINE_SECOND_POINT:
                ((Line) temp).setEndPoint(point2D);
                break;

            case DRAWING_RECTANGLE_SECOND_POINT:
                ((Rectangle) temp).setDimensions(first, point2D);
                break;

            case DRAWING_SQUARE_SECOND_POINT:
                ((Square) temp).setDimensions(first, point2D);
                break;

            case DRAWING_ELLIPSE_SECOND_POINT:
                ((Ellipse) temp).setDimensions(first, point2D);
                break;

            case DRAWING_CIRCLE_SECOND_POINT:
                ((Circle) temp).setDimensions(first, point2D);
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
}
