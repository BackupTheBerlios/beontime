package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifySubjectWindow;

/**
 * @author BeOnTime
 */
public class ModifySubject extends Action {
    private static final String NAME = "Modifier la matière";
    private static final String ICON = "Edit24.gif";


    public ModifySubject(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifySubject(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifySubject(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifySubject(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifySubjectWindow window = new AddModifySubjectWindow();
        window.show();
    }
}
