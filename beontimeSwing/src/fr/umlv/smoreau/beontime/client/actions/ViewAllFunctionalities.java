package fr.umlv.smoreau.beontime.client.actions;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ViewAllFunctionalities extends Action {
    private static final String NAME = "Voir les fonctionnalités de la secrétaire";
    private static final String ICON = "";
    private static final String SMALL_ICON = "";


    public ViewAllFunctionalities(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        mainFrame.getMenuBar().setViewAll(true);
        mainFrame.refresh();
    }
}