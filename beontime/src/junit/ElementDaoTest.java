package junit;

import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class ElementDaoTest extends TestCase {
    private static final ElementDao elementDao = ElementDao.getInstance();
    
    public ElementDaoTest(String name) {
        super(name);
    }

    public void testGetMaterials() {
        assertNotNull(elementDao.getMaterials());
    }
    
    public void testGetRooms() {
        assertNotNull(elementDao.getRooms());
    }
    
    public void testAddRemoveMaterial() {
        Material material = new Material();
        material.setName("m1");
        assertTrue(elementDao.addMaterial(material));
        material.setDescription("matériel n°1");
        assertTrue(elementDao.modifyMaterial(material));
        assertTrue(elementDao.removeMaterial(material));
    }
    
    public void testAddRemoveRoom() {
        Room room = new Room();
        room.setName("r1");
        assertTrue(elementDao.addRoom(room));
        room.setDescription("local n°1");
        assertTrue(elementDao.modifyRoom(room));
        assertTrue(elementDao.removeRoom(room));
    }
    
    public static Test suite() {
        return new TestSuite(ElementDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
