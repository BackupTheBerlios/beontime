package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;

/**
 * @author BeOnTime
 */
public class ModifyCourse extends Action {
    private static final String NAME = "Placer un cours";
    private static final String ICON = "Edit24.gif";


    public ModifyCourse(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyCourse(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyCourse(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }

    public ModifyCourse(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyCourseWindow window = new AddModifyCourseWindow();
        window.show();
    }
}
