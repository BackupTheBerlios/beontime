package fr.umlv.smoreau.beontime.client.actions;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AboutWindow;

/**
 * @author BeOnTime
 */
public class About extends Action {
    private static final String NAME = "A propose de BeOnTime";
    private static final String ICON = "Help24.gif";


    public About(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public About(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public About(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public About(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AboutWindow window = new AboutWindow();
        window.show(null);
    }
}