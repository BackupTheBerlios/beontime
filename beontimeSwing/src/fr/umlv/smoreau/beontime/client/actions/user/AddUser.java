package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUserWindow;

/**
 * @author BeOnTime
 */
public class AddUser extends Action {
    private static final String NAME = "Ajouter un utilisateur";
    private static final String ICON = "New24.gif";


    public AddUser(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddUser(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddUser(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddUser(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyUserWindow window = new AddModifyUserWindow(1);
        window.show();
    }
}
