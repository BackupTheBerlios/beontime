package fr.umlv.smoreau.beontime.dao;

import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class FormationDaoTest extends TestCase {
    private static FormationDao formationDao = FormationDaoImpl.getInstance();
    
    public FormationDaoTest(String name) {
        super(name);
    }

    public void testGetFormations() {
        try {
            assertNotNull(formationDao.getFormations());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testModifyFormation() {
    	try {
    		Formation formation = new Formation();
    		UserDao userDao = UserDaoImpl.getInstance();
    		Collection c = userDao.getSecretaries();
    		User[] persons = (User[]) c.toArray(new User[c.size()]);
    		formation.setIdSecretary(persons[0]);
    		formation.setIdTeacher(new Long(6));
    		formationDao.addFormation(formation);
    		formation.setHeading("formation de test");
    		formationDao.modifyFormation(formation);
    		assertTrue(true);
    	} catch (RemoteException e) {
    		assertTrue(false);
    	} catch (HibernateException e) {
    	    assertTrue(false);
        }
    }
    
    public static Test suite() {
        return new TestSuite(FormationDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
