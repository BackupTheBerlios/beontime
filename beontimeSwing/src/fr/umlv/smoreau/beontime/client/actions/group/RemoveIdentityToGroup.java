package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveIdentityToGroup extends Action {
    private static final String NAME = "Supprimer des étudiants au groupe";
    private static final String ICON = "Remove24.gif";


    public RemoveIdentityToGroup(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveIdentityToGroup(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveIdentityToGroup(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveIdentityToGroup(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
