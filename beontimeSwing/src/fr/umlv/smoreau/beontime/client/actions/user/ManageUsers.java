package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageElementsWindow;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class ManageUsers extends Action {
    private static final String NAME = "Gérer les utilisateurs";
    private static final String ICON = "gerer_user.png";
    private static final String SMALL_ICON = "gerer_user_small.png";


    public ManageUsers(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ManageElementsWindow window;
        if (UserDao.TYPE_ADMIN.equals(mainFrame.getUserConnected().getUserType()))
        	window = new ManageElementsWindow(ManageElementsWindow.TYPE_USERS_BY_ADMIN);
        else
            window = new ManageElementsWindow(ManageElementsWindow.TYPE_USERS_BY_SECRETARY);
        window.show();
    }
}
