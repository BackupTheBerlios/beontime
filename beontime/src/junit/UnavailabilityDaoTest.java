package junit;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.model.UnavailabilityType;
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
    
    public void testGetTypesUnavailability() {
        assertNotNull(unavailabilityDao.getTypesUnavailability());
    }
    
    public void testAddRemoveUnavailability() {
        Unavailability unavailability = new Unavailability();
        Collection t = unavailabilityDao.getTypesUnavailability();
        UnavailabilityType[] types = (UnavailabilityType[]) t.toArray(new UnavailabilityType[t.size()]);
        unavailability.setIdUnavailabilityType(types[0]);
        unavailability.setIdUnavailabilitySubject(new Long(1));
        assertTrue(unavailabilityDao.addUnavailability(unavailability));
        unavailability.setDescription("voici l'essai pour l'indisponibilité");
        assertTrue(unavailabilityDao.modifyUnavailability(unavailability));
        assertTrue(unavailabilityDao.removeUnavailability(unavailability));
    }

    public static Test suite() {
        return new TestSuite(UnavailabilityDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
