package junit;

import fr.umlv.smoreau.beontime.dao.FormationDao;
import fr.umlv.smoreau.beontime.model.Formation;
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
        assertTrue(formationDao.modifyFormation(formation));
    }
    
    public static Test suite() {
        return new TestSuite(FormationDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
