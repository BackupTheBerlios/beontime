package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.model.user.*;

/**
 * @author BeOnTime
 */
public class UserDao extends Dao {
    private static final UserDao INSTANCE = new UserDao();
    
    private static final String TABLE = "Person";
    private static final String TYPE_TEACHER   = "enseignant";
    private static final String TYPE_STUDENT   = "etudiant";
    private static final String TYPE_SECRETARY = "secretaire";
    private static final String TYPE_ADMIN     = "administrateur";
    
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
            System.err.println("Erreur lors de la récupération des personnes : " + e.getMessage());
        }
        
        //TODO ajouter les users de la base LDAP

		return result;
	}
	
	public Collection getAdministrators(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setTypePersonne(TYPE_ADMIN);

		return getUsers(f);
	}
	
	public Collection getSecretaries(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setTypePersonne(TYPE_SECRETARY);

		return getUsers(f);
	}
	
	public Collection getStudents(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setTypePersonne(TYPE_STUDENT);

		return getUsers(f);
	}
	
	public Collection getTeachers(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    if (f == null)
	        f = new UserFilter();
	    f.setTypePersonne(TYPE_TEACHER);

		return getUsers(f);
	}
	
	public Collection getAdministrators() {
	    UserFilter filter = new UserFilter();
	    filter.setTypePersonne(TYPE_ADMIN);
		return getUsers(filter);
	}
	
	public Collection getSecretaries() {
	    UserFilter filter = new UserFilter();
	    filter.setTypePersonne(TYPE_SECRETARY);
		return getUsers(filter);
	}
	
	public Collection getStudents() {
	    UserFilter filter = new UserFilter();
	    filter.setTypePersonne(TYPE_STUDENT);
		return getUsers(filter);
	}
	
	public Collection getTeachers() {
	    UserFilter filter = new UserFilter();
	    filter.setTypePersonne(TYPE_TEACHER);
		return getUsers(filter);
	}
	
	public void addUser(Person user) {
        try {
            TransactionManager.beginTransaction();
            add(user);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une personne : " + e.getMessage());
        }
	}
	
	public void modifyUser(Person user) {
        try {
            TransactionManager.beginTransaction();
            modify(user);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une personne : " + e.getMessage());
        }
	}
	
	public void removeUser(Person user) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new UserFilter(user));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'une personne : " + e.getMessage());
        }
	}
}