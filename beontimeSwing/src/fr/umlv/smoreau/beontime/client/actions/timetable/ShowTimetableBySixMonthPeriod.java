package fr.umlv.smoreau.beontime.client.actions.timetable;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * Manages the action for showing the Time Table by an Half-Year
 * @author BeOnTime team
 */
public class ShowTimetableBySixMonthPeriod extends Action{
    /** This class has to be serializable */
	private static final long serialVersionUID = 1L;
	
    private static final String NAME = "Afficher l'emploi du temps par semestre";
    private static final String ICON = "affiche_trimestre.png";


    public ShowTimetableBySixMonthPeriod(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ShowTimetableBySixMonthPeriod(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ShowTimetableBySixMonthPeriod(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ShowTimetableBySixMonthPeriod(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
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
