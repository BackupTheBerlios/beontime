package fr.umlv.smoreau.beontime.client.actions.user;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class AddTeacher extends AddUser {
    private static final String NAME = "Ajouter un enseignant";
    private static final String ICON = "creer_user.png";


    public AddTeacher(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame, UserDao.TYPE_TEACHER);
    }
    
    public AddTeacher(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame, UserDao.TYPE_TEACHER);
    }
    
    public AddTeacher(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame, UserDao.TYPE_TEACHER);
    }
    
    public AddTeacher(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame, UserDao.TYPE_TEACHER);
    }
}
