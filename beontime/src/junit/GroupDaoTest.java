package junit;


import fr.umlv.smoreau.beontime.dao.GroupDao;
import fr.umlv.smoreau.beontime.model.Group;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class GroupDaoTest extends TestCase {
    private static final GroupDao groupDao = GroupDao.getInstance();
    
    public GroupDaoTest(String name) {
        super(name);
    }

    public void testGetGroups() {
        assertNotNull(groupDao.getGroups());
    }
    
    public void testAddRemoveGroup() {
        Group group = new Group();
        group.setIdFormation(new Long(1));
        group.setIntitule("groupe pour essayer");
        assertTrue(groupDao.addGroup(group));
        group.setIntitule("groupe pour ressayer");
        assertTrue(groupDao.modifyGroup(group));
        assertTrue(groupDao.removeGroup(group));
    }
    
    public static Test suite() {
        return new TestSuite(GroupDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
