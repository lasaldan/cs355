package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.solution.model.Circle;
import cs355.solution.model.Ellipse;
import cs355.solution.model.Line;
import cs355.solution.model.ModelFacade;
import cs355.solution.model.Point2D;
import cs355.solution.model.Rectangle;
import cs355.solution.model.Square;
import cs355.solution.model.Triangle;

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
    ModelFacade model;


    public CS355Controller() {
        currentColor = Color.WHITE;
        lineButtonHit();
    }

    @Override
    public void colorButtonHit(Color c) {
        currentColor = c;
        GUIFunctions.changeSelectedColor(c);
    }

    @Override
    public void triangleButtonHit() {
        currentState = CS355State.DRAWING_TRIANGLE_FIRST_POINT;
    }

    @Override
    public void squareButtonHit() {
        currentState = CS355State.DRAWING_SQUARE_FIRST_POINT;
    }

    @Override
    public void rectangleButtonHit() {
        currentState = CS355State.DRAWING_RECTANGLE_FIRST_POINT;
    }

    @Override
    public void circleButtonHit() {
        currentState = CS355State.DRAWING_CIRCLE_FIRST_POINT;
    }

    @Override
    public void ellipseButtonHit() {
        currentState = CS355State.DRAWING_ELLIPSE_FIRST_POINT;
    }

    @Override
    public void lineButtonHit() {
        currentState = CS355State.DRAWING_LINE_FIRST_POINT;

    }

    @Override
    public void selectButtonHit() {

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

        Triangle triangle;

        switch (currentState) {
            case DRAWING_TRIANGLE_FIRST_POINT:
                triangle = new Triangle(loc, currentColor);
                currentShapeIndex = model.addShape(triangle);
                currentState = CS355State.DRAWING_TRIANGLE_SECOND_POINT;
                break;

            case DRAWING_TRIANGLE_SECOND_POINT:
                triangle = (Triangle) model.getShape(currentShapeIndex);
                triangle.setPt2(loc);
                model.setShape(currentShapeIndex, triangle);
                currentState = CS355State.DRAWING_TRIANGLE_THIRD_POINT;
                break;

            case DRAWING_TRIANGLE_THIRD_POINT:
                triangle = (Triangle) model.getShape(currentShapeIndex);
                triangle.setPt3(loc);
                model.setShape(currentShapeIndex, triangle);
                currentState = CS355State.DRAWING_TRIANGLE_FIRST_POINT;
                break;
        }
    }

    @Override
    public void handleMousePressed(Point2D loc) {

        switch (currentState) {
            case DRAWING_LINE_FIRST_POINT:
                Line line = new Line( loc, loc, currentColor );
                currentShapeIndex = model.addShape( line );
                currentState = CS355State.DRAWING_LINE_SECOND_POINT;
                break;

            case DRAWING_RECTANGLE_FIRST_POINT:
                Rectangle rect = new Rectangle(loc, 0, 0, currentColor);
                currentShapeIndex = model.addShape( rect );
                currentState = CS355State.DRAWING_RECTANGLE_SECOND_POINT;
                break;

            case DRAWING_SQUARE_FIRST_POINT:
                Square square = new Square(loc, 0, currentColor);
                currentShapeIndex = model.addShape( square );
                currentState = CS355State.DRAWING_SQUARE_SECOND_POINT;
                break;

            case DRAWING_ELLIPSE_FIRST_POINT:
                Ellipse ellipse = new Ellipse(loc, 0, 0, currentColor);
                currentShapeIndex = model.addShape( ellipse );
                currentState = CS355State.DRAWING_ELLIPSE_SECOND_POINT;
                break;

            case DRAWING_CIRCLE_FIRST_POINT:
                Circle circle = new Circle(loc, 0, 0, currentColor);
                currentShapeIndex = model.addShape( circle );
                currentState = CS355State.DRAWING_CIRCLE_SECOND_POINT;
                break;
        }
    }

    @Override
    public void handleMouseDrag(Point2D point2D) {

        switch (currentState) {
            case DRAWING_LINE_SECOND_POINT:
                Line line = (Line) model.getShape(currentShapeIndex);
                line.setEndPoint(point2D);
                model.setShape(currentShapeIndex, line);
                break;

            case DRAWING_RECTANGLE_SECOND_POINT:
                Rectangle rect = (Rectangle) model.getShape(currentShapeIndex);
                rect.setDimensions(point2D);
                model.setShape(currentShapeIndex, rect);
                break;

            case DRAWING_SQUARE_SECOND_POINT:
                Square square = (Square) model.getShape(currentShapeIndex);
                square.setDimensions( point2D );
                model.setShape(currentShapeIndex, square);
                break;

            case DRAWING_ELLIPSE_SECOND_POINT:
                Ellipse ellipse = (Ellipse) model.getShape(currentShapeIndex);
                ellipse.setDimensions( point2D );
                model.setShape(currentShapeIndex, ellipse);
                break;

            case DRAWING_CIRCLE_SECOND_POINT:
                Circle circle = (Circle) model.getShape(currentShapeIndex);
                circle.setDimensions( point2D );
                model.setShape(currentShapeIndex, circle);
                break;


        }
    }

    @Override
    public void handleMouseReleased(Point2D point2D) {

        switch (currentState) {
            case DRAWING_LINE_SECOND_POINT:
                currentState = CS355State.DRAWING_LINE_FIRST_POINT;
                break;

            case DRAWING_RECTANGLE_SECOND_POINT:
                currentState = CS355State.DRAWING_RECTANGLE_FIRST_POINT;
                break;

            case DRAWING_SQUARE_SECOND_POINT:
                currentState = CS355State.DRAWING_SQUARE_FIRST_POINT;
                break;

            case DRAWING_ELLIPSE_SECOND_POINT:
                currentState = CS355State.DRAWING_ELLIPSE_FIRST_POINT;
                break;

            case DRAWING_CIRCLE_SECOND_POINT:
                currentState = CS355State.DRAWING_CIRCLE_FIRST_POINT;
                break;

        }
    }

    @Override
    public void setModel( ModelFacade _model ) {
        this.model = _model;
    }
}
