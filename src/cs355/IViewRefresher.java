/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355;

import cs355.solution.CS355Controller;
import cs355.solution.model.ModelFacade;

import java.awt.Graphics2D;

/**
 *
 * @author Talonos
 */
public interface IViewRefresher
{
    void refreshView(Graphics2D g2d);

    void setModel(ModelFacade model);

    void setController(CS355Controller controller);
}
