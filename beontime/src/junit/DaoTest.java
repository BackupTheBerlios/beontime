package junit;


import fr.umlv.smoreau.beontime.dao.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * @author BeOnTime
 */
public class DaoTest extends TestCase {
    private static final UserDao userDao = UserDao.getInstance();
    private static final GroupDao groupDao = GroupDao.getInstance();
    
    public DaoTest(String name) {
        super(name);
    }
    
    public void testGetUsers() {
        assertNotNull(userDao.getAdministrators());
    }
    
    public void testGetUsersWithFilter() {
        UserFilter filter = new UserFilter();
        filter.setNom("toto");
        assertNotNull(userDao.getAdministrators(filter));
    }
    
    public void testGetGroups() {
        assertNotNull(groupDao.getGroups());
    }
    
    public static Test suite() {
        return new TestSuite(DaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
