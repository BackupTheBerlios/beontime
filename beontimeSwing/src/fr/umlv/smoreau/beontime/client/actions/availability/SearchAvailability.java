package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.SearchAvailabilityWindow;

/**
 * @author BeOnTime
 */
public class SearchAvailability extends Action {
    private static final String NAME = "Rechercher une disponibilité";
    private static final String ICON = "Find24.gif";
    
    public SearchAvailability(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public SearchAvailability(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public SearchAvailability(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public SearchAvailability(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        SearchAvailabilityWindow window = new SearchAvailabilityWindow();
        window.show();
    }
}
