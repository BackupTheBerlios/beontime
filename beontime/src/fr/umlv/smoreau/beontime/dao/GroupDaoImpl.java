package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * RMI implementation of the Group DAO
 * @author BeOnTime team
 */
public class GroupDaoImpl extends Dao implements GroupDao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

//    private static final GroupDao INSTANCE = new GroupDao();
    private static GroupDao INSTANCE;
    static {
    	try {
			INSTANCE = new GroupDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du Group DAO");
			//TODO gerer
		}
    }
    
    private static final String TABLE = "Group";
    
    private GroupDaoImpl() throws RemoteException {
    }

    public static GroupDao getInstance() throws RemoteException {
        return INSTANCE;
    }


	public Collection getGroups(GroupFilter filter) throws RemoteException {
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
	
	public Collection getGroups() throws RemoteException {
		return getGroups(null);
	}
	
	public boolean addGroup(Group group) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            add(group);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un groupe : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyGroup(Group group) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            modify(group);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un groupe : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeGroup(Group group) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new GroupFilter(group));
            TransactionManager.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la suppression d'un groupe : " + e.getMessage());
            return false;
        }
        return true;
	}
}
