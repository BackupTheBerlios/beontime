package fr.umlv.smoreau.beontime.client.actions.timetable;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * Manages the action for showing the Time Table by week
 * @author BeOnTime team
 */
public class ShowTimetableByWeek extends Action{
    /** This class has to be serializable */
	private static final long serialVersionUID = 1L;
	
	private static final String NAME = "Afficher l'emploi du temps par semaine";
    private static final String ICON = "affiche_semestre.png";


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
    	mainFrame.setViewType(MainFrame.VIEW_WEEK);
    	setEnabled(false);
    	ActionsList.getAction("ShowTimetableBySixMonthPeriod").setEnabled(true);
    	//TODO fire
    }
}
