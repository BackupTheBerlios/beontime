package junit;


import java.rmi.RemoteException;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.dao.*;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * @author BeOnTime
 */
public class UserDaoTest extends TestCase {
    private static UserDao userDao = UserDaoImpl.getInstance();

    public UserDaoTest(String name) {
        super(name);
    }
    
    public void testGetUsers() {
        try {
            assertNotNull(userDao.getAdministrators());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testGetUsersWithFilter() {
        UserFilter filter = new UserFilter();
        filter.setName("toto");
        try {
            assertNotNull(userDao.getAdministrators(filter));
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testAddRemoveUser() {
        User person = new User();
        person.setName("toto");
        person.setUserType("secretaire");
        try {
            userDao.addUser(person);
            userDao.removeUser(person);
            assertTrue(true);
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public static Test suite() {
        return new TestSuite(UserDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
