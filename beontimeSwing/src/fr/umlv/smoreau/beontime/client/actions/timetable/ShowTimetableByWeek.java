package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ShowTimetableByWeek extends Action{
    private static final String NAME = "Afficher l'emploi du temps par semaine";
    private static final String ICON = "New24.gif";


    public ShowTimetableByWeek(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ShowTimetableByWeek(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ShowTimetableByWeek(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ShowTimetableByWeek(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
