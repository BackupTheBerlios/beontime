package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AuthenticationWindow;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class Connect extends Action {
    private static final String NAME = "Se connecter";
    private static final String ICON = "";
    
    public Connect(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public Connect(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public Connect(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public Connect(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
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
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Authentification incorrecte", "Erreur", JOptionPane.ERROR_MESSAGE);
                actionPerformed(null);
            } else {
                mainFrame.setUser(user);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            actionPerformed(null);
        } catch (NullPointerException e) {
            //TODO supprimer ce catch pour le rendu
            JOptionPane.showMessageDialog(null, "Serveur non démarré, mais je démarre quand même, rien que pour toi ! ;op", "Attention", JOptionPane.WARNING_MESSAGE);
            User user = new User();
            user.setUserType(UserDao.TYPE_SECRETARY);
            mainFrame.setUser(user);
        }
        
        mainFrame.open();
    }
}
