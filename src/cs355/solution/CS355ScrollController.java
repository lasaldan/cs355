package cs355.solution;

import cs355.GUIFunctions;
import cs355.solution.model.Point2D;

import java.awt.geom.AffineTransform;

/**
 * Created by Daniel on 10/24/14. ;)
 */
public class CS355ScrollController {

    private int maxH;
    private int maxV;

    private int minH;
    private int minV;

    private int defaultKnobSize;
    private int currentKnobSize;

    private int knobHPos;
    private int knobVPos;

    public CS355ScrollController() {
        maxH = maxV = 2048;
        minH = minV = 0;

        defaultKnobSize = 512;
        currentKnobSize = 512;

        knobHPos = knobVPos = 0;

    }

    public int getCurrentKnobSize() {
        return currentKnobSize;
    }

    public void centerAt(Point2D center, double zoomLevel) {
        currentKnobSize = (int) (defaultKnobSize * zoomLevel);
        setVScrollBar(center.getY());
        setHScrollBar(center.getX());
    }

    public double getHCenter() {
        return knobHPos + currentKnobSize / 2.0;
    }

    public double getVCenter() {
        return knobVPos + currentKnobSize / 2.0;
    }

    private void setVScrollBar(double vTarget) {

        knobVPos = (int) (vTarget - (currentKnobSize / 2.0));
        if(knobVPos > maxV - currentKnobSize)
            knobVPos = maxV - currentKnobSize;

        GUIFunctions.setVScrollBarKnob(currentKnobSize);
        GUIFunctions.setVScrollBarPosit(knobVPos);
        GUIFunctions.setVScrollBarKnob(currentKnobSize);

    }


    private void setHScrollBar(double hTarget) {

        knobHPos = (int) (hTarget - (currentKnobSize / 2.0));
        if(knobHPos > maxH - currentKnobSize)
            knobHPos = maxH - currentKnobSize;

        GUIFunctions.setHScrollBarKnob(currentKnobSize);
        GUIFunctions.setHScrollBarPosit(knobHPos);
        GUIFunctions.setHScrollBarKnob(currentKnobSize);

    }
}
