package fr.umlv.smoreau.beontime.dao;

import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.model.UnavailabilityType;
import fr.umlv.smoreau.beontime.model.Unavailability;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class UnavailabilityDaoTest extends TestCase {
    private static final UnavailabilityDao unavailabilityDao = UnavailabilityDaoImpl.getInstance();
    
    public UnavailabilityDaoTest(String name) {
        super(name);
    }

    public void testGetUnavailabilities() {
        try {
            assertNotNull(unavailabilityDao.getUnavailabilities());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testGetTypesUnavailability() {
        try {
            assertNotNull(unavailabilityDao.getTypesUnavailability());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testAddRemoveUnavailability() {
        try {
	        Unavailability unavailability = new Unavailability();
	        Collection t = unavailabilityDao.getTypesUnavailability();
	        UnavailabilityType[] types = (UnavailabilityType[]) t.toArray(new UnavailabilityType[t.size()]);
	        unavailability.setIdUnavailabilityType(types[0]);
	        unavailability.setIdUnavailabilitySubject(new Long(1));
	        unavailabilityDao.addUnavailability(unavailability);
	        unavailability.setDescription("voici l'essai pour l'indisponibilité");
	        unavailabilityDao.modifyUnavailability(unavailability);
	        unavailabilityDao.removeUnavailability(unavailability);
	        assertTrue(true);
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }

    public static Test suite() {
        return new TestSuite(UnavailabilityDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
