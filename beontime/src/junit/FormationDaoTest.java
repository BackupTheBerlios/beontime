package junit;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.FormationDao;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.user.Person;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class FormationDaoTest extends TestCase {
    private static final FormationDao formationDao = FormationDao.getInstance();
    
    public FormationDaoTest(String name) {
        super(name);
    }

    public void testGetFormations() {
        assertNotNull(formationDao.getFormations());
    }
    
    public void testModifyFormation() {
        Formation formation = new Formation();
        UserDao userDao = UserDao.getInstance();
        Collection c = userDao.getSecretaries();
        Person[] persons = (Person[]) c.toArray(new Person[c.size()]);
        formation.setIdSecretaire(persons[0]);
        formation.setIdTeacher(new Long(6));
        assertTrue(formationDao.addFormation(formation));
        formation.setIntitule("formation de test");
        assertTrue(formationDao.modifyFormation(formation));
    }
    
    public static Test suite() {
        return new TestSuite(FormationDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
