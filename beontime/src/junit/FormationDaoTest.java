package junit;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.FormationDao;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.user.User;
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
        User[] persons = (User[]) c.toArray(new User[c.size()]);
        formation.setIdSecretary(persons[0]);
        formation.setIdTeacher(new Long(6));
        assertTrue(formationDao.addFormation(formation));
        formation.setHeading("formation de test");
        assertTrue(formationDao.modifyFormation(formation));
    }
    
    public static Test suite() {
        return new TestSuite(FormationDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
