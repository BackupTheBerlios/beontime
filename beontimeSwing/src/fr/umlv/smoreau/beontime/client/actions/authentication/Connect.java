package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AuthenticationWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class Connect extends Action {
    private static final String NAME = "Se connecter";
    private static final String ICON = "";
    private static final String SMALL_ICON = "";
    
    public Connect(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AuthenticationWindow window = new AuthenticationWindow();
        window.show();
        
        if (!window.isOk())
            System.exit(0);
        
        try {
            User user = DaoManager.getUserDao().testLoginPwd(window.getLogin(), window.getPassword());
            if (user == null ||
                    (!UserDao.TYPE_SECRETARY.equals(user.getUserType()) &&
                     !UserDao.TYPE_ADMIN.equals(user.getUserType()))) {
                JOptionPane.showMessageDialog(null, "Authentification incorrecte", "Erreur", JOptionPane.ERROR_MESSAGE);
                actionPerformed(null);
            } else {
                mainFrame.setUserConnected(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            actionPerformed(null);
        }

        mainFrame.open();
    }
}
