package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ShowTimetableHorizontal extends Action{
    private static final String NAME = "Afficher l'emploi du temps horizontalement";
    private static final String ICON = "affiche_horizontal.png";


    public ShowTimetableHorizontal(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ShowTimetableHorizontal(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ShowTimetableHorizontal(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ShowTimetableHorizontal(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
