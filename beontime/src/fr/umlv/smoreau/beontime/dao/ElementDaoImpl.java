package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.MaterialFilter;
import fr.umlv.smoreau.beontime.filter.RoomFilter;
import fr.umlv.smoreau.beontime.model.element.*;
import fr.umlv.smoreau.beontime.model.user.User;

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
	
	public Room getRoom(Room room, String[] join) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            room = (Room) get(TABLE_ROOM, new RoomFilter(room), session).toArray()[0];
            join(room, join);
        } finally {
            Hibernate.closeSession();
        }
        return room;
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
	
	public Material getMaterial(Material material, String[] join) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            material = (Material) get(TABLE_MATERIAL, new MaterialFilter(material), session).toArray()[0];
            join(material, join);
        } finally {
            Hibernate.closeSession();
        }
        return material;
	}
	
	public Collection getRooms() throws RemoteException, HibernateException {
		return getRooms(null);
	}
	
	public Collection getMaterials() throws RemoteException, HibernateException {
		return getMaterials(null);
	}
	
	public Room addRoom(Room room) throws RemoteException, HibernateException {
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
        return room;
	}
	
	public Material addMaterial(Material material) throws RemoteException, HibernateException {
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
        return material;
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
	
	public Collection getBuildings() throws RemoteException, HibernateException {
		HashSet buildings = new HashSet();

		// ajout des batiments se trouvant dans la table 'local'
		Collection rooms = getRooms();
		for (Iterator i = rooms.iterator(); i.hasNext(); ) {
			Room room = (Room) i.next();
			String building = room.getBuildingName();
			if (building != null)
				buildings.add(building);
		}
		
		// ajout des batiments se trouvant dans la table 'personne'
		UserDao userDao = UserDaoImpl.getInstance();
		Collection users = userDao.getUsers(false);
		for (Iterator i = users.iterator(); i.hasNext(); ) {
			User user = (User) i.next();
			String building = user.getBuildingNameForOffice();
			if (building != null)
				buildings.add(building);
		}
		
		return buildings;
	}
}
