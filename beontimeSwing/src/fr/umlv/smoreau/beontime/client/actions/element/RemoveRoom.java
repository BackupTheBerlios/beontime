package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveRoom extends Action {
    private static final String NAME = "Supprimer le local";
    private static final String ICON = "supprimer_local.png";


    public RemoveRoom(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveRoom(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveRoom(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveRoom(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
