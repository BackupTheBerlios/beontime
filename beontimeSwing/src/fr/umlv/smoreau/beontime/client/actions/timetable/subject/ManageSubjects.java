package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageElementsWindow;

/**
 * @author BeOnTime
 */
public class ManageSubjects extends Action {
    private static final String NAME = "Gérer les matières";
    private static final String ICON = "gerer_matiere.png";


    public ManageSubjects(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ManageSubjects(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ManageSubjects(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ManageSubjects(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ManageElementsWindow window = new ManageElementsWindow("TYPE_SUBJECTS");
        window.show();
    }
}
