package junit;


import fr.umlv.smoreau.beontime.dao.GroupDao;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.user.Person;
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
        // ajout d'un groupe simple
        Group group = new Group();
        group.setIdFormation(new Long(1));
        group.setIntitule("groupe pour essayer");
        assertTrue(groupDao.addGroup(group));
        
        // assignation d'un étudiant à ce groupe
        Person person = new Person();
        person.setIdPersonne(new Long(1054));
        person.setTypePersonne("etudiant");
        group.addToStudentSet(person);
        assertTrue(groupDao.modifyGroup(group));
        
        // suppression du groupe
        assertTrue(groupDao.removeGroup(group));
    }
    
    public static Test suite() {
        return new TestSuite(GroupDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
