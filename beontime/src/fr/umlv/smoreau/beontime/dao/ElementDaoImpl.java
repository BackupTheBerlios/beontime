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
	
	//    private static final ElementDao INSTANCE = new ElementDao();
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

    public static ElementDao getInstance() throws RemoteException {
        return INSTANCE;
    }


	public Collection getRooms(RoomFilter filter) throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_ROOM, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des locaux : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getMaterials(MaterialFilter filter) throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_MATERIAL, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des matériels : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getRooms() throws RemoteException {
		return getRooms(null);
	}
	
	public Collection getMaterials() throws RemoteException {
		return getMaterials(null);
	}
	
	public boolean addRoom(Room room) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            add(room);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un local : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean addMaterial(Material material) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            add(material);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un matériel : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyRoom(Room room) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            modify(room);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un local : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyMaterial(Material material) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            modify(material);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un matériel : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeRoom(Room room) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_ROOM, new RoomFilter(room));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un local : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeMaterial(Material material) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_MATERIAL, new MaterialFilter(material));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un matériel : " + e.getMessage());
            return false;
        }
        return true;
	}
}
