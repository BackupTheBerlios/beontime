package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class GroupDao extends Dao {
    private static final GroupDao INSTANCE = new GroupDao();
    
    private static final String TABLE = "Group";
    
    private GroupDao() {
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }


	public Collection getGroups(GroupFilter filter) {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des groupes : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getGroups() {
		return getGroups(null);
	}
	
	public void addGroup(Group group) {
        try {
            TransactionManager.beginTransaction();
            add(group);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un groupe : " + e.getMessage());
        }
	}
	
	public void modifyGroup(Group group) {
        try {
            TransactionManager.beginTransaction();
            modify(group);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un groupe : " + e.getMessage());
        }
	}
	
	public void removeGroup(Group group) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new GroupFilter(group));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un groupe : " + e.getMessage());
        }
	}
}
