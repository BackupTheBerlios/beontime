package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AuthenticationWindow;

/**
 * @author BeOnTime
 */
public class Connect extends Action {
    private static final String NAME = "Se connecter";
    private static final String ICON = "";
    
    public Connect(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public Connect(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public Connect(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public Connect(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AuthenticationWindow window = new AuthenticationWindow();
        window.show();
    }
}
