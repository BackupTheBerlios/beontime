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


    public ViewAllFunctionalities(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ViewAllFunctionalities(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ViewAllFunctionalities(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ViewAllFunctionalities(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        mainFrame.getMenuBar().setViewAll(true);
        mainFrame.refresh();
    }
}