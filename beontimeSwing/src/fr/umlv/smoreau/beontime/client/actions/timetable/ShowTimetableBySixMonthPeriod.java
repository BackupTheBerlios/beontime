package fr.umlv.smoreau.beontime.client.actions.timetable;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * Manages the action for showing the Time Table by an Half-Year
 * @author BeOnTime
 */
public class ShowTimetableBySixMonthPeriod extends Action{
    private static final String NAME = "Afficher l'emploi du temps par semestre";
    private static final String ICON = "affiche_trimestre.png";
    private static final String SMALL_ICON = "affiche_trimestre_small.png";


    public ShowTimetableBySixMonthPeriod(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    public boolean isEnabled() {
    	return (mainFrame.getViewType() == MainFrame.VIEW_WEEK);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    	mainFrame.setViewType(MainFrame.VIEW_HALF_YEAR);
    	setEnabled(false);
    	ActionsList.getAction("ShowTimetableByWeek").setEnabled(true);
    	//TODO fire
    }
}
