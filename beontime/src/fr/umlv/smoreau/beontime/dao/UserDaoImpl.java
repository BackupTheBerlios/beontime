package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.naming.NamingException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.LdapManager;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.Formation;
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
	
	public User getUser(User user, String[] join) throws RemoteException, HibernateException {
        try {
            Session session = Hibernate.getCurrentSession();
            user = (User) get(TABLE, new UserFilter(user), session).toArray()[0];
            join(user, join);
        } finally {
            Hibernate.closeSession();
        }
        return user;
	}
	
	public Collection getUsers(UserFilter filter) throws RemoteException, HibernateException {
	    return getUsers(filter, true);
	}
	
	public Collection getUsers() throws RemoteException, HibernateException {
	    return getUsers(null, true);
	}
	
	public Collection getUsers(boolean ldap) throws RemoteException, HibernateException {
	    return getUsers(null, ldap);
	}
	
	public Collection getAdministrators(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(TYPE_ADMIN);
		return getUsers(f, true);
	}
	
	public Collection getSecretaries(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(TYPE_SECRETARY);
		return getUsers(f, false);
	}
	
	public Collection getStudents(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(TYPE_STUDENT);
		return getUsers(f, true);
	}
	
	public Collection getTeachers(UserFilter filter) throws RemoteException, HibernateException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(TYPE_TEACHER);
		return getUsers(f, true);
	}
	
	public Collection getAdministrators() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_ADMIN);
		return getUsers(filter, true);
	}
	
	public Collection getSecretaries() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_SECRETARY);
		return getUsers(filter, false);
	}
	
	public Collection getStudents() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_STUDENT);
		return getUsers(filter, true);
	}
	
	public Collection getTeachers() throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_TEACHER);
		return getUsers(filter, true);
	}
	
	public Collection getTeachers(boolean ldap) throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
	    filter.setUserType(TYPE_TEACHER);
		return getUsers(filter, ldap);
	}
	
	public User addUser(User user) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(user, session);
            Set formations = user.getFormationsInCharge();
            for (Iterator i = formations.iterator(); i.hasNext(); ) {
                Formation formation = (Formation) i.next();
                formation.setIdSecretary(user);
                add(formation, session);
            }
            notifyListeners(user, ChangeListener.TYPE_ADD);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
        return user;
	}
	
	public void modifyUser(User user) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(user, session);
            notifyListeners(user, ChangeListener.TYPE_MODIFY);
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
            notifyListeners(user, ChangeListener.TYPE_REMOVE);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}


	public User testLoginPwd(String login, String password) throws RemoteException, HibernateException {
	    UserFilter filter = new UserFilter();
        filter.setLogin(login);

	    boolean test = ldapManager.testLoginPwd(login, password);
	    if (!test) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                filter.setPassword(new String(md.digest()));

                Session session = Hibernate.getCurrentSession();
                Collection c = get(TABLE, filter, session);
                if (c.size() == 1) {
                    User user = (User) c.toArray()[0];
                    if (TYPE_SECRETARY.equals(user.getUserType()))
                        user.getFormationsInCharge().size();
                    return user;
                }
            } catch (NoSuchAlgorithmException e) {
                // impossible avec le paramètre 'MD5'
            } finally {
                Hibernate.closeSession();
            }
	    } else {
	        try {
	            Collection c = ldapManager.getUsers(filter);
		        return (User) c.toArray()[0];
	        } catch (NamingException e) {
	            // ne peut pas passer dans ce catch car la méthode testLoginPwd a fonctionné
	        }
	    }
	    return null;
	}


    private ArrayList list = new ArrayList();
    
    public void addChangeListener(ChangeListener listener) throws RemoteException {
        list.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) throws RemoteException {
        list.remove(listener);
    }
    
    private void notifyListeners(User user, int type) {
        for (Iterator i = list.iterator(); i.hasNext(); ) {
            ChangeListener listener = (ChangeListener) i.next();
            try {
                listener.userChanged(user, type);
            } catch(RemoteException re) {
                list.remove(listener);
            }
        }
    }
}