package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import javax.naming.NamingException;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.LdapManager;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.user.*;

/**
 * @author BeOnTime
 */
public class UserDao extends Dao {
    private static final UserDao INSTANCE = new UserDao();
    private static final LdapManager ldapManager = LdapManager.getInstance();
    
    private static final String TABLE = "User";
    public static final String TYPE_TEACHER   = "enseignant";
    public static final String TYPE_STUDENT   = "etudiant";
    public static final String TYPE_SECRETARY = "secretaire";
    public static final String TYPE_ADMIN     = "administrateur";
    
    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }


	private Collection getUsers(UserFilter filter) {
	    Collection result = null;

        try {
            TransactionManager.beginTransaction();
            result = get(TABLE, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs sur la base de données SQL : " + e.getMessage());
        }
        
        try {
            result.addAll(ldapManager.getUsers(filter));
        } catch (NamingException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs sur la base de données LDAP : " + e.getMessage());
            //TODO à supprimer plus tard, mais permet de tester l'application en dehors de la fac
            User user = new User(new Long(33), filter.getUserType());
            user.setFirstName("Toto");
            user.setName("Nom2toto");
            user.setEMail("toto@toto.fr");
            result.add(user);
            //finTODO
        }

		return result;
	}
	
	public Collection getAdministrators(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_ADMIN);

		return getUsers(f);
	}
	
	public Collection getSecretaries(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_SECRETARY);

		return getUsers(f);
	}
	
	public Collection getStudents(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_STUDENT);

		return getUsers(f);
	}
	
	public Collection getTeachers(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_TEACHER);

		return getUsers(f);
	}
	
	public Collection getAdministrators() {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_ADMIN);
		return getUsers(filter);
	}
	
	public Collection getSecretaries() {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_SECRETARY);
		return getUsers(filter);
	}
	
	public Collection getStudents() {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_STUDENT);
		return getUsers(filter);
	}
	
	public Collection getTeachers() {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_TEACHER);
		return getUsers(filter);
	}
	
	public boolean addUser(User user) {
        try {
            TransactionManager.beginTransaction();
            add(user);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un utilisateur : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyUser(User user) {
        try {
            TransactionManager.beginTransaction();
            modify(user);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un utilisateur : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeUser(User user) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new UserFilter(user));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un utilisateur : " + e.getMessage());
            return false;
        }
        return true;
	}


	public boolean testLoginPwd(String login, String password) {
	    return ldapManager.testLoginPwd(login, password);
	}
}