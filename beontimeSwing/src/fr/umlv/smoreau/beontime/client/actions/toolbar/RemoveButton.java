package fr.umlv.smoreau.beontime.client.actions.toolbar;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveButton extends Action {
    private static final String NAME = "Supprimer un bouton de la barre d'outils";
    private static final String ICON = "Back24.gif";


    public RemoveButton(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveButton(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveButton(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveButton(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}