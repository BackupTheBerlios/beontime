package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageGroupsWindow;

/**
 * @author BeOnTime
 */
public class ManageIdentitiesToGroups extends Action {
    private static final String NAME = "Gérer les étudiants du groupe";
    private static final String ICON = "Remove24.gif";


    public ManageIdentitiesToGroups(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ManageIdentitiesToGroups(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ManageIdentitiesToGroups(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ManageIdentitiesToGroups(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    	ManageGroupsWindow window = new ManageGroupsWindow();  
        window.show();
    }
}
