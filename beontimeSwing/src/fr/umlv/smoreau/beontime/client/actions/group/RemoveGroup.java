package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveGroup extends Action {
    private static final String NAME = "Supprimer le groupe";
    private static final String ICON = "Remove24.gif";


    public RemoveGroup(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveGroup(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveGroup(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveGroup(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
