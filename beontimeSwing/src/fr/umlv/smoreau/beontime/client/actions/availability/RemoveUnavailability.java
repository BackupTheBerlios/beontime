package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveUnavailability extends Action {
    private static final String NAME = "Supprimer l'indisponibilité";
    private static final String ICON = "supprimer_indisponibilite.png";
    private static final String SMALL_ICON = "supprimer_indisponibilite_small.png";
    
    public RemoveUnavailability(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
