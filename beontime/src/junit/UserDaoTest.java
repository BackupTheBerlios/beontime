package junit;


import java.rmi.RemoteException;

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
//    private static final UserDao userDao = UserDao.getInstance();
    private static UserDao userDao;
    static {
    	try {
			userDao = UserDaoImpl.getInstance();
		} catch (RemoteException e) {
			System.err.println("Problème RMI dans le test JUnit du user Dao");
		}
    }

    public UserDaoTest(String name) {
        super(name);
    }
    
    public void testGetUsers() throws RemoteException {
        assertNotNull(userDao.getAdministrators());
    }
    
    public void testGetUsersWithFilter() throws RemoteException {
        UserFilter filter = new UserFilter();
        filter.setName("toto");
        assertNotNull(userDao.getAdministrators(filter));
    }
    
    public void testAddRemoveUser() throws RemoteException {
        User person = new User();
        person.setName("toto");
        person.setUserType("secretaire");
        assertTrue(userDao.addUser(person));
        assertTrue(userDao.removeUser(person));
    }
    
    public static Test suite() {
        return new TestSuite(UserDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
        //TODO catcher la remote Exception ?
    }
}
