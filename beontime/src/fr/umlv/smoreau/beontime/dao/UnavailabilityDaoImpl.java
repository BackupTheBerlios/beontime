package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.UnavailabilityType;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * RMI implementation of the Unavailability DAO
 * @author BeOnTime team
 */
public class UnavailabilityDaoImpl extends Dao implements UnavailabilityDao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

//	   private static final UnavailabilityDao INSTANCE = new UnavailabilityDao();
	   private static UnavailabilityDao INSTANCE;
	   static {
	   	try {
	   		INSTANCE = new UnavailabilityDaoImpl();
	   	} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du Unavalability DAO");
			//TODO gerer
	   	}
	   }
    
    private static final String TABLE      = "Unavailability";
    private static final String TABLE_TYPE = "UnavailabilityType";
    
    private String[] DEFAULT_TYPES = { "enseignant", "étudiant", "cours", "matériel", "local", "calendrier" };
    
    private UnavailabilityDaoImpl() throws RemoteException {
        Collection types = getTypesUnavailability();
        if (types != null && types.size() == 0) {
            for (int i = 0; i < DEFAULT_TYPES.length; ++i) {
                UnavailabilityType type = new UnavailabilityType();
                type.setNameUnavailabilityType(DEFAULT_TYPES[i]);
                addTypeUnavailability(type);
            }
        }
    }

    public static UnavailabilityDao getInstance() /*throws RemoteException*/ {
        return INSTANCE;
    }


	public Collection getUnavailabilities(UnavailabilityFilter filter) throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des indisponibilités : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getUnavailabilities() throws RemoteException {
		return getUnavailabilities(null);
	}
	
	public boolean addUnavailability(Unavailability unavailability) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            add(unavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyUnavailability(Unavailability unavailability) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            modify(unavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeUnavailability(Unavailability unavailability) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new UnavailabilityFilter(unavailability));
            TransactionManager.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la suppression d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	
	public Collection getTypesUnavailability() throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_TYPE, null);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des types d'indisponibilité : " + e.getMessage());
        }

		return result;
	}
	
	private boolean addTypeUnavailability(UnavailabilityType typeUnavailability) /*throws RemoteException*/ {
        try {
            TransactionManager.beginTransaction();
            add(typeUnavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout du type d'indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
}
