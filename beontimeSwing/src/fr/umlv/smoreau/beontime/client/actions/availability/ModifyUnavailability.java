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
    private static final String ICON = "Edit24.gif";
    
    public ModifyUnavailability(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyUnavailability(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyUnavailability(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyUnavailability(String name, boolean showIcon, MainFrame mainFrame) {
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
