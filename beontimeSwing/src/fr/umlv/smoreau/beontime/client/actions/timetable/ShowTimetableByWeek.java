package fr.umlv.smoreau.beontime.client.actions.timetable;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * Manages the action for showing the Time Table by week
 * @author BeOnTime
 */
public class ShowTimetableByWeek extends Action{
	private static final String NAME = "Afficher l'emploi du temps par semaine";
    private static final String ICON = "affiche_semestre.png";
    private static final String SMALL_ICON = "affiche_semestre_small.png";


    public ShowTimetableByWeek(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    	mainFrame.setViewType(MainFrame.VIEW_WEEK);
    	ActionsList.getAction("ShowTimetableByWeek").setEnabled(false);
    	ActionsList.getAction("ShowTimetableBySixMonthPeriod").setEnabled(true);
    }
}
