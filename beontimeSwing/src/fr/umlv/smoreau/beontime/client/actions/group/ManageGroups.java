package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageElementsWindow;

/**
 * @author BeOnTime
 */
public class ManageGroups extends Action {
    private static final String NAME = "Gérer les groupes";
    private static final String ICON = "gerer_groupe.png";
    private static final String SMALL_ICON = "gerer_groupe_small.png";
    private static final String KEY_STROKE = "ctrl G";


    public ManageGroups(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    	ManageElementsWindow window = new ManageElementsWindow(ManageElementsWindow.TYPE_GROUPS);
        window.show();
    }
}
