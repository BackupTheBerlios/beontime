package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class PasteCourse extends Action {
    private static final String NAME = "Coller le cours";
    private static final String ICON = "coller.png";


    public PasteCourse(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public PasteCourse(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public PasteCourse(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public PasteCourse(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
