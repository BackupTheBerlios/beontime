package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.MaterialFilter;
import fr.umlv.smoreau.beontime.filter.RoomFilter;
import fr.umlv.smoreau.beontime.model.element.*;

/**
 * RMI implementation of the Element DAO
 * @author BeOnTime team
 */
public class ElementDaoImpl extends Dao implements ElementDao {
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static ElementDao INSTANCE; 
    static {
    	try {
			INSTANCE= new ElementDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation de l'Element DAO");
			//TODO gerer
		}
    }
    
    private static final String TABLE_MATERIAL = "Material";
    private static final String TABLE_ROOM     = "Room";
    
    private ElementDaoImpl() throws RemoteException {
    }

    public static ElementDao getInstance() {
        return INSTANCE;
    }


	public Collection getRooms(RoomFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_ROOM, filter, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public Collection getMaterials(MaterialFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_MATERIAL, filter, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public Collection getRooms() throws RemoteException, HibernateException {
		return getRooms(null);
	}
	
	public Collection getMaterials() throws RemoteException, HibernateException {
		return getMaterials(null);
	}
	
	public void addRoom(Room room) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(room, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void addMaterial(Material material) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(material, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void modifyRoom(Room room) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(room, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void modifyMaterial(Material material) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(material, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeRoom(Room room) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            remove(TABLE_ROOM, new RoomFilter(room), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeMaterial(Material material) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            remove(TABLE_MATERIAL, new MaterialFilter(material), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
}
