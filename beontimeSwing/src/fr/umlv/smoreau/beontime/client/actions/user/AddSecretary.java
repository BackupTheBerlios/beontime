package fr.umlv.smoreau.beontime.client.actions.user;

import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class AddSecretary extends AddUser {
    private static final String NAME = "Ajouter une secrétaire";
    private static final String ICON = "creer_user.png";
    private static final String SMALL_ICON = "creer_user_small.png";
    private static final String KEY_STROKE = "ctrl S";


    public AddSecretary(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame, UserDao.TYPE_SECRETARY);
    }
}
