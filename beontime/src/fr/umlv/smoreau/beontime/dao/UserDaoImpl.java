package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.naming.NamingException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.LdapManager;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.user.*;

/**
 * RMI implementation of the User DAO
 * @author BeOnTime team
 */
public class UserDaoImpl extends Dao implements UserDao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

	private static final LdapManager ldapManager = LdapManager.getInstance();
    
    private static final String TABLE = "User";

	private static UserDao INSTANCE;
	static {
		try {
			INSTANCE = new UserDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du User DAO");
			//TODO gerer
		}
	}
     
    private UserDaoImpl() throws RemoteException {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }


	private Collection getUsers(UserFilter filter, boolean ldap) throws RemoteException, HibernateException {
	    Collection result = null;

	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            result = get(TABLE, filter, session);
        } finally {
            Hibernate.closeSession();
        }
        
        if (ldap) {
	        try {
	            result.addAll(ldapManager.getUsers(filter));
	        } catch (NamingException e) {
	            System.err.println("LDAP est inaccessible : " + e.getMessage());
	        }
        }

		return result;
	}
	
	public Collection getUsers(UserFilter filter) throws RemoteException, HibernateException {
	    return getUsers(filter, true);
	}
	
	public Collection getAdministrators(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_ADMIN);

		return getUsers(f);
	}
	
	public Collection getSecretaries(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_SECRETARY);

		return getUsers(f);
	}
	
	public Collection getStudents(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_STUDENT);

		return getUsers(f);
	}
	
	public Collection getTeachers(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setUserType(TYPE_TEACHER);

		return getUsers(f);
	}
	
	public Collection getAdministrators() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_ADMIN);
		return getUsers(filter);
	}
	
	public Collection getSecretaries() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_SECRETARY);
		return getUsers(filter);
	}
	
	public Collection getStudents() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_STUDENT);
		return getUsers(filter);
	}
	
	public Collection getTeachers() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_TEACHER);
		return getUsers(filter);
	}
	
	public void addUser(User user) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(user, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void modifyUser(User user) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(user, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeUser(User user) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            remove(TABLE, new UserFilter(user), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}


	public User testLoginPwd(String login, String password) throws RemoteException {
	    boolean test = ldapManager.testLoginPwd(login, password);
	    if (!test) {
            try {
                UserFilter filter = new UserFilter();
    	        filter.setLogin(login);

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                filter.setPassword(new String(md.digest()));
                
                Collection c = getUsers(filter, false);
                if (c.size() == 1)
                    return (User) c.toArray()[0];
            } catch (NoSuchAlgorithmException e) {
                // impossible avec le paramètre 'MD5'
            } catch (RemoteException e) {
                System.err.println("Erreur lors de la vérification du login et mot de passe sur la base de données SQL");
            } catch (HibernateException e) {
                System.err.println("Erreur lors de la vérification du login et mot de passe sur la base de données SQL");
            }
	    } else {
	        try {
		        UserFilter filter = new UserFilter();
		        filter.setLogin(login);
		        Collection c = getUsers(filter);
		        return (User) c.toArray()[0];
	        } catch (RemoteException e) {
                System.err.println("Erreur lors de la vérification du login et mot de passe sur la base de données LDAP");
            } catch (HibernateException e) {
                System.err.println("Erreur lors de la vérification du login et mot de passe sur la base de données LDAP");
            }
	    }
	    return null;
	}
}