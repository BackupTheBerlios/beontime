package fr.umlv.smoreau.beontime.client.actions.authentication;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ModifyDBParametersWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Database;

/**
 * @author BeOnTime
 */
public class ModifyDBParameters extends Action {
    private static final String NAME = "Configurer les connexions aux bases de données";
    private static final String ICON = "configurer.png";
    private static final String SMALL_ICON = "configurer_small.png";
    
    public ModifyDBParameters(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        try {
            ModifyDBParametersWindow window = new ModifyDBParametersWindow();

            // remplissage des champs de la base de données SQL
            Database sql = DaoManager.getDatabaseDao().getSQLDatabase();
            window.setSqlBaseName(sql.getBaseName());
            window.setSqlHost(sql.getHost());
            window.setSqlPort(sql.getPort());
            window.setSqlLogin(sql.getLogin());
            window.setSqlPassword(sql.getPassword());
            
            // remplissage des champs de la base de données LDAP
            Database ldap = DaoManager.getDatabaseDao().getLDAPDatabase();
            window.setLdapBaseDN(ldap.getDNBase());
            window.setLdapHost(ldap.getHost());
            window.setLdapPort(ldap.getPort());

            window.show();
            
            if (window.isOk()) {
                // récupération des paramètres de la base de données SQL
                sql.setBaseName(window.getSqlBaseName());
                sql.setHost(window.getSqlHost());
                sql.setPort(window.getSqlPort());
                sql.setLogin(window.getSqlLogin());
                sql.setPassword(window.getSqlPassword());
                
                // récupération des paramètres de la base de données LDAP
                ldap.setDNBase(window.getLdapBaseDN());
                ldap.setHost(window.getLdapHost());
                ldap.setPort(window.getLdapPort());

                DaoManager.getDatabaseDao().modifyDatabase(sql);
                DaoManager.getDatabaseDao().modifyDatabase(ldap);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
