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
    private static final String ICON = "groupe.png";
    private static final String SMALL_ICON = "groupe_small.png";


    public ManageIdentitiesToGroups(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    	ManageGroupsWindow window = new ManageGroupsWindow();  
        window.show();
    }
}
