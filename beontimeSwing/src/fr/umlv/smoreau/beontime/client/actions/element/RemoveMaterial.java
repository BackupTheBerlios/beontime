package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class RemoveMaterial extends Action {
    private static final String NAME = "Supprimer le matériel";
    private static final String ICON = "supprimer_materiel.png";


    public RemoveMaterial(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveMaterial(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveMaterial(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveMaterial(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
