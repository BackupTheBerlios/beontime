package junit;

import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.model.Unavailability;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class UnavailabilityDaoTest extends TestCase {
    private static final UnavailabilityDao unavailabilityDao = UnavailabilityDao.getInstance();
    
    public UnavailabilityDaoTest(String name) {
        super(name);
    }

    public void testGetUnavailabilities() {
        assertNotNull(unavailabilityDao.getUnavailabilities());
    }
    
    /*public void testAddRemoveGroup() {
        Unavailability unavailability = new Unavailability();
        group.setIdFormation(new Long(1));
        group.setIntitule("groupe pour essayer");
        assertTrue(groupDao.addGroup(group));
        group.setIntitule("groupe pour ressayer");
        assertTrue(groupDao.modifyGroup(group));
        assertTrue(groupDao.removeGroup(group));
    }*/
    
    public static Test suite() {
        return new TestSuite(UnavailabilityDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
