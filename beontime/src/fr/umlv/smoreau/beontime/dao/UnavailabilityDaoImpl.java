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

	private static UnavailabilityDao INSTANCE;
	static {
	    try {
	        INSTANCE = new UnavailabilityDaoImpl();
	    } catch (RemoteException e) {
	        System.err.println("problème RMI à l'instanciation du Unavalability DAO");
	        //TODO gerer
	    } catch (HibernateException e) {
	        System.err.println("Erreur lors de l'instanciation : " + e.getMessage());
        }
	}
    
    private static final String TABLE      = "Unavailability";
    private static final String TABLE_TYPE = "UnavailabilityType";
    

    private UnavailabilityDaoImpl() throws RemoteException, HibernateException {
        Collection types = getTypesUnavailability();
        if (types != null && types.size() == 0) {
            for (int i = 0; i < ALL_TYPES.length; ++i) {
                UnavailabilityType type = new UnavailabilityType();
                type.setNameUnavailabilityType(ALL_TYPES[i]);
                addTypeUnavailability(type);
            }
        }
    }

    public static UnavailabilityDao getInstance() {
        return INSTANCE;
    }


	public Collection getUnavailabilities(UnavailabilityFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE, filter, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public Collection getUnavailabilities() throws RemoteException, HibernateException {
		return getUnavailabilities(null);
	}
	
	public void addUnavailability(Unavailability unavailability) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(unavailability, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void modifyUnavailability(Unavailability unavailability) throws RemoteException, HibernateException {
	    Session session = null;
	    try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(unavailability, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeUnavailability(Unavailability unavailability) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            remove(TABLE, new UnavailabilityFilter(unavailability), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	
	public Collection getTypesUnavailability() throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_TYPE, null, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	private void addTypeUnavailability(UnavailabilityType typeUnavailability) throws HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(typeUnavailability, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
}
