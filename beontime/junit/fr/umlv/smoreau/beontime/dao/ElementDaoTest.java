package fr.umlv.smoreau.beontime.dao;

import java.rmi.RemoteException;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class ElementDaoTest extends TestCase {
    private static ElementDao elementDao = ElementDaoImpl.getInstance();
    
    public ElementDaoTest(String name) {
        super(name);
    }

    public void testGetMaterials() {
        try {
            assertNotNull(elementDao.getMaterials());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testGetRooms() {
        try {
            assertNotNull(elementDao.getRooms());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testAddRemoveMaterial() {
        try {
	        Material material = new Material();
	        material.setName("m1");
            elementDao.addMaterial(material);
	        material.setDescription("matériel n°1");
	        elementDao.modifyMaterial(material);
	        elementDao.removeMaterial(material);
	        assertTrue(true);
	    } catch (RemoteException e) {
        	assertTrue(false);
	    } catch (HibernateException e) {
	        assertTrue(false);
        }
    }
    
    public void testAddRemoveRoom() {
        try {
	        Room room = new Room();
	        room.setName("r1");
	        elementDao.addRoom(room);
	        room.setDescription("local n°1");
	        elementDao.modifyRoom(room);
	        elementDao.removeRoom(room);
	        assertTrue(true);
        } catch (RemoteException e) {
        	assertTrue(false);
	    } catch (HibernateException e) {
	        assertTrue(false);
        } 
    }
    
    public void testGetBuildings() {
    	try {
			assertNotNull(elementDao.getBuildings());
		} catch (RemoteException e) {
			assertTrue(false);
		} catch (HibernateException e) {
			assertTrue(false);
		}
    }
    
    public static Test suite() {
        return new TestSuite(ElementDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
