package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class CutCourse extends Action {
    private static final String NAME = "Couper le cours";
    private static final String ICON = "Cut24.gif";


    public CutCourse(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public CutCourse(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public CutCourse(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public CutCourse(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
    }
}
