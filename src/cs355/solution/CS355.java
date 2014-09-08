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
        ModelFacade modelFacade = new ModelFacade();

        IViewRefresher viewRefresher = new ViewRefresher();
        viewRefresher.setModel( modelFacade );

        ICS355Controller controller = new CS355Controller();
        controller.setModel( modelFacade );

        MouseListener mouseListener = new CS355MouseListener(controller);
        MouseMotionListener mouseMotionListener = new CS355MouseMotionListener(controller);

        // Fill in the parameters below with your controller, view,
        //   mouse listener, and mouse motion listener
        GUIFunctions.createCS355Frame(controller,viewRefresher,mouseListener,mouseMotionListener);
        
        GUIFunctions.refresh();        
    }
}