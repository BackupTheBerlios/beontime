package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUserWindow;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class ModifyUser extends Action {
    private static final String NAME = "Modifier l'utilisateur";
    private static final String ICON = "Edit24.gif";


    public ModifyUser(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyUser(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyUser(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyUser(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyUserWindow window = new AddModifyUserWindow(UserDao.TYPE_TEACHER);
        window.show();
    }
}
