package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageElementsWindow;

/**
 * @author BeOnTime
 */
public class ManageUsers extends Action {
    private static final String NAME = "Gérer les utilisateurs";
    private static final String ICON = "";


    public ManageUsers(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ManageUsers(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ManageUsers(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ManageUsers(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ManageElementsWindow window = new ManageElementsWindow();
        window.show();
    }
}
