package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ShowTimetableBySixMonthPeriod extends Action{
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
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
