package junit;

import fr.umlv.smoreau.beontime.dao.TimetableDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class TimetableDaoTest extends TestCase {
    private static final TimetableDao timetableDao = TimetableDao.getInstance();
    
    public TimetableDaoTest(String name) {
        super(name);
    }

    public void testGetCourses() {
        assertNotNull(timetableDao.getCourses());
    }
    
    public void testGetSubjects() {
        assertNotNull(timetableDao.getSubjects());
    }
    
    public static Test suite() {
        return new TestSuite(TimetableDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
