package junit;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.TypeCourse;
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
    
    public void testGetTypesCourse() {
        assertNotNull(timetableDao.getTypesCourse());
    }
    
    public void testGetSubjects() {
        assertNotNull(timetableDao.getSubjects());
    }
    
    public void testAddRemoveCourse() {
        Course course = new Course();
        Collection t = timetableDao.getTypesCourse();
        TypeCourse[] types = (TypeCourse[]) t.toArray(new TypeCourse[t.size()]);
        course.setIdTypeCourse(types[0]);
        course.setIdFormation(new Long(1));
        assertTrue(timetableDao.addCourse(course));
        course.setNbSemaine(new Integer(3));
        assertTrue(timetableDao.modifyCourse(course));
        assertTrue(timetableDao.removeCourse(course));
    }
    
    public void testAddRemoveSubject() {
        Subject subject = new Subject();
        subject.setIdFormation(new Long(2));
        subject.setIdTeacher(new Long(5));
        assertTrue(timetableDao.addSubject(subject));
        subject.setIntitule("matière de test");
        assertTrue(timetableDao.modifySubject(subject));
        assertTrue(timetableDao.removeSubject(subject));
    }
    
    public static Test suite() {
        return new TestSuite(TimetableDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
