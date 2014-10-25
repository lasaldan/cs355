/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.GUIFunctions;
import cs355.ICS355Controller;
import cs355.IViewRefresher;
import cs355.solution.model.ModelFacade;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author [your name here]
 */
public class CS355 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Color defaultColor = Color.RED;
        ModelFacade modelFacade = new ModelFacade();

        CS355Controller controller = new CS355Controller();
        controller.setModel( modelFacade );

        IViewRefresher viewRefresher = new ViewRefresher();
        viewRefresher.setModel( modelFacade );
        viewRefresher.setController(controller);

        MouseListener mouseListener = new CS355MouseListener(controller);
        MouseMotionListener mouseMotionListener = new CS355MouseMotionListener(controller);

        controller.setScrollController( new CS355ScrollController() );
        controller.setZoomController( new CS355ZoomController() );

        // Fill in the parameters below with your controller, view,
        //   mouse listener, and mouse motion listener
        GUIFunctions.createCS355Frame(controller,viewRefresher,mouseListener,mouseMotionListener);

        controller.colorButtonHit(defaultColor);
        controller.lineButtonHit();
        controller.initView();

        GUIFunctions.refresh();        
    }
}