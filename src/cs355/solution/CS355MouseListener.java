package cs355.solution;

import cs355.ICS355Controller;
import cs355.solution.model.Point2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Daniel on 9/6/14.
 */
public class CS355MouseListener implements MouseListener {

    ICS355Controller controller;

    public CS355MouseListener() {
        controller = null;
    }

    public CS355MouseListener(ICS355Controller _controller) {
        controller = _controller;
    }

    public void setController(ICS355Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.handleClick( new Point2D(e.getX(), e.getY()) );
    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.handleMousePressed( new Point2D(e.getX(), e.getY()) );
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseReleased( new Point2D(e.getX(), e.getY()) );
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
