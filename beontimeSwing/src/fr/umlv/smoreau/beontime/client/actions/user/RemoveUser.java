package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveUser extends Action {
    private static final String NAME = "Supprimer l'utilisateur";
    private static final String ICON = "Remove24.gif";


    public RemoveUser(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveUser(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveUser(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveUser(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
