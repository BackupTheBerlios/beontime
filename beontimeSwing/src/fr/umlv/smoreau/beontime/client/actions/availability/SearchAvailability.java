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
    private static final String ICON = "rechercher_indisponibilite.png";
    private static final String SMALL_ICON = "rechercher_indisponibilite_small.png";
    
    public SearchAvailability(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        SearchAvailabilityWindow window = new SearchAvailabilityWindow();
        window.show();
    }
}
