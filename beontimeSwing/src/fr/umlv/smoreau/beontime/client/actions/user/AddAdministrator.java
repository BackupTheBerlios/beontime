package fr.umlv.smoreau.beontime.client.actions.user;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class AddAdministrator extends AddUser {
    private static final String NAME = "Ajouter un administrateur";
    private static final String ICON = "creer_user.png";


    public AddAdministrator(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame, UserDao.TYPE_ADMIN);
    }
    
    public AddAdministrator(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame, UserDao.TYPE_ADMIN);
    }
    
    public AddAdministrator(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame, UserDao.TYPE_ADMIN);
    }
    
    public AddAdministrator(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame, UserDao.TYPE_ADMIN);
    }
}
