package fr.umlv.smoreau.beontime.client.actions;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AboutWindow;

/**
 * @author BeOnTime
 */
public class About extends Action {
    private static final String NAME = "A propos de BeOnTime";
    private static final String ICON = "aide.png";
    private static final String SMALL_ICON = "aide_small.png";


    public About(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AboutWindow window = new AboutWindow();
        window.show(null);
    }
}