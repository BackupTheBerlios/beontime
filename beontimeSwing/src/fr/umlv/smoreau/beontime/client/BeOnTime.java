package fr.umlv.smoreau.beontime.client;

import fr.umlv.smoreau.beontime.client.actions.authentication.Connect;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class BeOnTime {

    public static void main(String[] args) {
    	MainFrame mainFrame = MainFrame.getInstance();
    	Connect connect = new Connect(mainFrame);
    	connect.actionPerformed(null);
    }
}
