package fr.umlv.smoreau.beontime.client.actions.user;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class AddSecretary extends AddUser {
    private static final String NAME = "Ajouter une secrétaire";
    private static final String ICON = "creer_user.png";


    public AddSecretary(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame, UserDao.TYPE_SECRETARY);
    }
    
    public AddSecretary(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame, UserDao.TYPE_SECRETARY);
    }
    
    public AddSecretary(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame, UserDao.TYPE_SECRETARY);
    }
    
    public AddSecretary(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame, UserDao.TYPE_SECRETARY);
    }
}
