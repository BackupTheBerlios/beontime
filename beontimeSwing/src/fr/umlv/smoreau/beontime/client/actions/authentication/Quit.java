package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class Quit extends Action {
    private static final String NAME = "Quitter";
    private static final String ICON = "quitter.png";
    
    public Quit(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public Quit(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public Quit(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public Quit(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
