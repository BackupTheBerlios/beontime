package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ModifyDBParametersWindow;

/**
 * @author BeOnTime
 */
public class ModifyDBParameters extends Action {
    private static final String NAME = "Configurer les connexions aux bases de données";
    private static final String ICON = "configurer.png";
    private static final String SMALL_ICON = "configurer_small.png";
    
    public ModifyDBParameters(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ModifyDBParametersWindow window = new ModifyDBParametersWindow();
        window.show();
    }
}
