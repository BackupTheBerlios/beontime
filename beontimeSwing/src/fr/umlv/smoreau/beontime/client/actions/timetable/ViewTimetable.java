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


    public ViewTimetable(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ViewTimetable(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ViewTimetable(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ViewTimetable(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ViewTimetableWindow window = new ViewTimetableWindow();
		window.show();
    }
}
