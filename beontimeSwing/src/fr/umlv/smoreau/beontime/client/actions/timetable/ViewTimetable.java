package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ViewTimetableWindow;

/**
 * @author BeOnTime
 */
public class ViewTimetable extends Action{
    private static final String NAME = "Visualiser un emploi du temps";
    private static final String ICON = "visualiser_emploi.png";
    private static final String SMALL_ICON = "visualiser_emploi_small.png";


    public ViewTimetable(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ViewTimetableWindow window = new ViewTimetableWindow();
		window.show();
    }
}
