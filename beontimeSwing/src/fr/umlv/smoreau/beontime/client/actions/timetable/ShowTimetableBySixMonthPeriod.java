package fr.umlv.smoreau.beontime.client.actions.timetable;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

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
    private static final String KEY_STROKE = "F9";


    public ShowTimetableBySixMonthPeriod(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    	mainFrame.setViewType(MainFrame.VIEW_HALF_YEAR);
    	ActionsList.getAction("ShowTimetableBySixMonthPeriod").setEnabled(false);
    	ActionsList.getAction("ShowTimetableByWeek").setEnabled(true);
    }
}
