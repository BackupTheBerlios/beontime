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
    private static final String ICON = "Preferences24.gif";
    
    public ModifyDBParameters(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyDBParameters(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyDBParameters(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyDBParameters(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ModifyDBParametersWindow window = new ModifyDBParametersWindow();
        window.show();
    }
}
