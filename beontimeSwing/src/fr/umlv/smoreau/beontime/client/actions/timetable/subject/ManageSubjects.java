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
    private static final String SMALL_ICON = "gerer_matiere_small.png";


    public ManageSubjects(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ManageElementsWindow window = new ManageElementsWindow(ManageElementsWindow.TYPE_SUBJECTS);
        window.show();
    }
}
