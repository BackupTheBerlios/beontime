package fr.umlv.smoreau.beontime.client;

import java.rmi.RemoteException;

import fr.umlv.smoreau.beontime.client.actions.authentication.Connect;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;

/**
 * @author BeOnTime
 */
public class BeOnTime {
    public static void main(String[] args) {
    	MainFrame mainFrame = MainFrame.getInstance();
    	ClipboardManager.runClipboardThread();
    	try {
    	    ChangeMonitor monitor = mainFrame.getMonitor();
    	    if (monitor == null)
    	        throw new RemoteException();
            DaoManager.addChangeListener(monitor);
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement du client sur le serveur");
        }

    	Connect connect = new Connect(mainFrame);
    	connect.actionPerformed(null);
    }
}
