package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUnavailabilityWindow;

/**
 * @author BeOnTime
 */
public class AddUnavailability extends Action {
    private static final String NAME = "Ajouter une indisponibilité";
    private static final String ICON = "ajouter_indisponibilite.png";
    
    public AddUnavailability(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddUnavailability(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddUnavailability(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddUnavailability(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyUnavailabilityWindow window = new AddModifyUnavailabilityWindow();
        window.show();
    }
}
