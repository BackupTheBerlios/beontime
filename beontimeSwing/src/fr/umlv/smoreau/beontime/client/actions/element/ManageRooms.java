package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageElementsWindow;

/**
 * @author BeOnTime
 */
public class ManageRooms extends Action {
    private static final String NAME = "Gérer les locaux";
    private static final String ICON = "gerer_local.png";
    private static final String SMALL_ICON = "gerer_local_small.png";


    public ManageRooms(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ManageElementsWindow window = new ManageElementsWindow(ManageElementsWindow.TYPE_ROOMS);
        window.show();
    }
}
