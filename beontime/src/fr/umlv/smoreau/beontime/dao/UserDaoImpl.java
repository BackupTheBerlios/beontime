package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.RemoteException;
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


	private Collection getUsers(UserFilter filter) throws RemoteException, HibernateException {
	    Collection result = null;

	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            result = get(TABLE, filter, session);
        } finally {
            Hibernate.closeSession();
        }
        
        try {
            result.addAll(ldapManager.getUsers(filter));
        } catch (NamingException e) {
            System.err.println("LDAP est inaccessible : " + e.getMessage());
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


	public boolean testLoginPwd(String login, String password) throws RemoteException {
	    return ldapManager.testLoginPwd(login, password);
	}
}