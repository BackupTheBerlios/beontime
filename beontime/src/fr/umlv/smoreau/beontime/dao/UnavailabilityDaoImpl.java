package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
    
    private static Collection unavailabilityTypes;
    

    private UnavailabilityDaoImpl() throws RemoteException, HibernateException {
        unavailabilityTypes = getTypesUnavailability();
        if (unavailabilityTypes != null && unavailabilityTypes.size() == 0) {
            for (int i = 0; i < ALL_TYPES.length; ++i) {
                UnavailabilityType type = new UnavailabilityType();
                type.setNameUnavailabilityType(ALL_TYPES[i]);
                unavailabilityTypes.add(addTypeUnavailability(type));
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
	
	public Unavailability addUnavailability(Unavailability unavailability) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(unavailability, session);
            notifyListeners(unavailability, ChangeListener.TYPE_ADD);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
        return unavailability;
	}
	
	public void modifyUnavailability(Unavailability unavailability) throws RemoteException, HibernateException {
	    Session session = null;
	    try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(unavailability, session);
            notifyListeners(unavailability, ChangeListener.TYPE_MODIFY);
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
            notifyListeners(unavailability, ChangeListener.TYPE_REMOVE);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	
	public Collection getTypesUnavailability() throws RemoteException, HibernateException {
	    if (unavailabilityTypes != null)
	        return unavailabilityTypes;
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_TYPE, null, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public UnavailabilityType getTypeUnavailability(String name) throws RemoteException {
	    for (Iterator i = unavailabilityTypes.iterator(); i.hasNext(); ) {
	        UnavailabilityType type = (UnavailabilityType) i.next();
	        if (type.getNameUnavailabilityType().equals(name))
	            return type;
	    }
	    return null;
	}
	
	private UnavailabilityType addTypeUnavailability(UnavailabilityType typeUnavailability) throws HibernateException {
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
        return typeUnavailability;
	}
	
	
	private ArrayList list = new ArrayList();
	
    public void addChangeListener(ChangeListener listener) throws RemoteException {
        list.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) throws RemoteException {
        list.remove(listener);
    }
    
    private void notifyListeners(Unavailability unavailability, int type) {
        for (Iterator i = list.iterator(); i.hasNext(); ) {
            ChangeListener listener = (ChangeListener) i.next();
            try {
                listener.unavailabilityChanged(unavailability, type);
            } catch(RemoteException re) {
                list.remove(listener);
            }
        }
    }
}
