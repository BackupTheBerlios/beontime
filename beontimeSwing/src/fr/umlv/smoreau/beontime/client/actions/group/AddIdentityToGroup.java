package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class AddIdentityToGroup extends Action {
    private static final String NAME = "Ajouter des étudiants au groupe";
    private static final String ICON = "New24.gif";


    public AddIdentityToGroup(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddIdentityToGroup(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddIdentityToGroup(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddIdentityToGroup(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
