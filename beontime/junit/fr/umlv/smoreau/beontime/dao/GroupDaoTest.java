package fr.umlv.smoreau.beontime.dao;


import java.rmi.RemoteException;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class GroupDaoTest extends TestCase {
    private static GroupDao groupDao = GroupDaoImpl.getInstance();
    
    public GroupDaoTest(String name) {
        super(name);
    }

    public void testGetGroups() {
        try {
            assertNotNull(groupDao.getGroups());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testAddRemoveGroup() {
        try {
	        // ajout d'un groupe simple
	        Group group = new Group();
	        group.setIdFormation(new Long(1));
	        group.setHeading("groupe pour essayer");
            groupDao.addGroup(group);
        
	        // assignation d'un étudiant à ce groupe
	        User person = new User();
	        person.setIdUser(new Long(1054));
	        person.setUserType("enseignant");
	        group.addStudent(person);
	        groupDao.modifyGroup(group);

	        // suppression du groupe
            groupDao.removeGroup(group);
            
            assertTrue(true);
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public static Test suite() {
        return new TestSuite(GroupDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
