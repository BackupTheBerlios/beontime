package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class Disconnect extends Action {
    private static final String NAME = "Se déconnecter";
    private static final String ICON = "";
    
    public Disconnect(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public Disconnect(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public Disconnect(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public Disconnect(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        mainFrame.close();
        (new Connect(mainFrame)).actionPerformed(null);
    }
}
