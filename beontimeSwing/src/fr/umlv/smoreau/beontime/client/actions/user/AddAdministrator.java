package fr.umlv.smoreau.beontime.client.actions.user;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class AddAdministrator extends AddUser {
    private static final String NAME = "Ajouter un administrateur";
    private static final String ICON = "creer_user.png";
    private static final String SMALL_ICON = "creer_user_small.png";


    public AddAdministrator(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame, UserDao.TYPE_ADMIN);
    }
}
