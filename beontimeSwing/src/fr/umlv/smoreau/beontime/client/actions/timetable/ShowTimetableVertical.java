package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ShowTimetableVertical extends Action{
    private static final String NAME = "Afficher l'emploi du temps verticalement";
    private static final String ICON = "affiche_vertical.png";
    private static final String SMALL_ICON = "affiche_vertical_small.png";


    public ShowTimetableVertical(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
