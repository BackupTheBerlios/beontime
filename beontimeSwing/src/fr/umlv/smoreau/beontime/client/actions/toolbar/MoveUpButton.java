package fr.umlv.smoreau.beontime.client.actions.toolbar;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class MoveUpButton extends Action {
    private static final String NAME = "Monter le bouton";
    private static final String ICON = "Up24.gif";


    public MoveUpButton(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public MoveUpButton(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public MoveUpButton(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public MoveUpButton(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}