package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;

/**
 * @author BeOnTime
 */
public class ModifyCourse extends Action {
    private static final String NAME = "Modifier le cours";
    private static final String ICON = "modifier_cours.png";
    private static final String SMALL_ICON = "modifier_cours_small.png";


    public ModifyCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyCourseWindow window = new AddModifyCourseWindow();
        window.show();
    }
}
