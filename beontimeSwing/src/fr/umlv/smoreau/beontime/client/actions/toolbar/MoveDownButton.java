package fr.umlv.smoreau.beontime.client.actions.toolbar;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class MoveDownButton extends Action {
    private static final String NAME = "Descendre le bouton";
    private static final String ICON = "bas.png";


    public MoveDownButton(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public MoveDownButton(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public MoveDownButton(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public MoveDownButton(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}