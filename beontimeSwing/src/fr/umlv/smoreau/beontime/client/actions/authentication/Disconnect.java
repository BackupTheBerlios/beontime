package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class Disconnect extends Action {
    private static final String NAME = "Se déconnecter";
    private static final String ICON = "decon.png";
    private static final String SMALL_ICON = "decon_small.png";
    
    public Disconnect(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        mainFrame.close();
        (new Connect(mainFrame)).actionPerformed(null);
    }
}
