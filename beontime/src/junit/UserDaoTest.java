package junit;


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
    private static final UserDao userDao = UserDao.getInstance();

    public UserDaoTest(String name) {
        super(name);
    }
    
    public void testGetUsers() {
        assertNotNull(userDao.getAdministrators());
    }
    
    public void testGetUsersWithFilter() {
        UserFilter filter = new UserFilter();
        filter.setName("toto");
        assertNotNull(userDao.getAdministrators(filter));
    }
    
    public void testAddRemoveUser() {
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
    }
}
