package fr.umlv.smoreau.beontime.client.actions.toolbar;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class AddButton extends Action {
    private static final String NAME = "Ajouter un bouton à la barre d'outils";
    private static final String ICON = "plus_icone.png";


    public AddButton(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddButton(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddButton(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddButton(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}