package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifySubjectWindow;

/**
 * @author BeOnTime
 */
public class AddSubject extends Action {
    private static final String NAME = "Ajouter une matière";
    private static final String ICON = "ajouter_matiere.png";


    public AddSubject(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddSubject(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddSubject(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddSubject(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifySubjectWindow window = new AddModifySubjectWindow();
        window.show();
        
        if (window.isOk()) {
        }
    }
}
