/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.model.element.*;

/**
 * @author BeOnTime
 */
public class ElementDao extends Dao {
    private static final ElementDao INSTANCE = new ElementDao();
    
    private static final String TABLE_MATERIAL = "Material";
    private static final String TABLE_ROOM     = "Room";
    
    private ElementDao() {
    }

    public static ElementDao getInstance() {
        return INSTANCE;
    }


	public Collection getRooms(RoomFilter filter) {
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
	
	public Collection getMaterials(MaterialFilter filter) {
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
	
	public Collection getRooms() {
		return getRooms(null);
	}
	
	public Collection getMaterials() {
		return getMaterials(null);
	}
	
	public void addRoom(Room room) {
        try {
            TransactionManager.beginTransaction();
            add(room);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un local : " + e.getMessage());
        }
	}
	
	public void addMaterial(Material material) {
        try {
            TransactionManager.beginTransaction();
            add(material);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un matériel : " + e.getMessage());
        }
	}
	
	public void modifyRoom(Room room) {
        try {
            TransactionManager.beginTransaction();
            modify(room);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un local : " + e.getMessage());
        }
	}
	
	public void modifyMaterial(Material material) {
        try {
            TransactionManager.beginTransaction();
            modify(material);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un matériel : " + e.getMessage());
        }
	}
	
	public void removeRoom(Room room) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_ROOM, new RoomFilter(room));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un local : " + e.getMessage());
        }
	}
	
	public void removeMaterial(Material material) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_MATERIAL, new MaterialFilter(material));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un matériel : " + e.getMessage());
        }
	}
}
