package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyGroupWindow;

/**
 * @author BeOnTime
 */
public class AddGroup extends Action {
    private static final String NAME = "Créer un groupe";
    private static final String ICON = "New24.gif";


    public AddGroup(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddGroup(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddGroup(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddGroup(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyGroupWindow window = new AddModifyGroupWindow();
        window.show();
    }
}
