package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class CloseTimetable extends Action {
    private static final String NAME = "Fermer l'emploi du temps";
    private static final String ICON = "";


    public CloseTimetable(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public CloseTimetable(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public CloseTimetable(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public CloseTimetable(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
