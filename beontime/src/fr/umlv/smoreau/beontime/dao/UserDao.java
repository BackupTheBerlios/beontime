package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.model.user.*;

/**
 * @author BeOnTime
 */
public class UserDao {
    private static final UserDao INSTANCE = new UserDao();
    
    private static final String DB = "Person";
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
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            String f = "";
            if (filter != null) {
                f = filter.getHQLQuery();
                if (f.length() != 0)
                    f = " where " + f;
            }
            result = session.find("from " + DB + f);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des personnes : " + e.getMessage());
        }
        
        //TODO ajouter les users de la base LDAP

		return result;
	}
	
	public Collection getAdministrators(UserFilter filter) {
	    if (filter == null)
	        filter = new UserFilter();
	    filter.setTypePersonne(TYPE_ADMIN);

		return getUsers(filter);
	}
	
	public Collection getSecretaries(UserFilter filter) {
	    if (filter == null)
	        filter = new UserFilter();
	    filter.setTypePersonne(TYPE_SECRETARY);

		return getUsers(filter);
	}
	
	public Collection getStudents(UserFilter filter) {
	    if (filter == null)
	        filter = new UserFilter();
	    filter.setTypePersonne(TYPE_STUDENT);

		return getUsers(filter);
	}
	
	public Collection getTeachers(UserFilter filter) {
	    if (filter == null)
	        filter = new UserFilter();
	    filter.setTypePersonne(TYPE_TEACHER);

		return getUsers(filter);
	}
	
	public Collection getAdministrators() {
		return getAdministrators(null);
	}
	
	public Collection getSecretaries() {
		return getSecretaries(null);
	}
	
	public Collection getStudents() {
		return getStudents(null);
	}
	
	public Collection getTeachers() {
		return getTeachers(null);
	}
	
	public void addUser(Person user) {
        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            session.save(user);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une personne : " + e.getMessage());
        }
	}
	
	public void modifyUser(Person user) {
        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            session.update(user);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une personne : " + e.getMessage());
        }
	}
	
	public void removeUser(Person user) {
        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            UserFilter filter = new UserFilter(user);
            String f = filter.getHQLQuery();
            if (f.length() != 0)
                f = " where " + f;
            session.delete("from " + DB + f);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'une personne : " + e.getMessage());
        }
	}
}