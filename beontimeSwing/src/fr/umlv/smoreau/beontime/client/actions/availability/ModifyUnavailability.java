package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUnavailabilityWindow;

/**
 * @author BeOnTime
 */
public class ModifyUnavailability extends Action {
    private static final String NAME = "Modifier l'indisponibilité";
    private static final String ICON = "modifier_indisponibilite.png";
    private static final String SMALL_ICON = "modifier_indisponibilite_small.png";
    
    public ModifyUnavailability(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyUnavailabilityWindow window = new AddModifyUnavailabilityWindow();
        window.show();
    }
}
