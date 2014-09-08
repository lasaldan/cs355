package cs355.solution;

import cs355.ICS355Controller;
import cs355.solution.model.Point2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Daniel on 9/6/14.
 */
public class CS355MouseMotionListener implements MouseMotionListener {

    ICS355Controller controller;

    public CS355MouseMotionListener() {
        controller = null;
    }

    public CS355MouseMotionListener(ICS355Controller _controller) {
        controller = _controller;
    }

    public void setController(ICS355Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDrag(new Point2D(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
