package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;

/**
 * @author BeOnTime
 */
public class Quit extends Action {
    private static final String NAME = "Quitter";
    private static final String ICON = "quitter.png";
    private static final String SMALL_ICON = "quitter_small.png";
    
    public Quit(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        try {
            DaoManager.removeChangeListener(mainFrame.getMonitor());
        } catch (RemoteException e) {
            System.err.println("Erreur lors du désenregistrement du client sur le serveur");
        }
        System.exit(0);
    }
}
