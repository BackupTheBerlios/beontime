package junit;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.TypeCourse;
import fr.umlv.smoreau.beontime.model.user.Person;
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
        // ajout d'un cours simple
        Course course = new Course();
        Collection t = timetableDao.getTypesCourse();
        TypeCourse[] types = (TypeCourse[]) t.toArray(new TypeCourse[t.size()]);
        course.setIdTypeCourse(types[0]);
        course.setIdFormation(new Long(1));
        assertTrue(timetableDao.addCourse(course));
        
        // modification du cours, ajout d'un local
        Room room = new Room();
        room.setDescription("local d'essai ...");
        course.addToLocalSet(room);
        assertTrue(timetableDao.modifyCourse(course));
        
        // modification du cours, ajout d'un matériel
        Material material = new Material();
        material.setDescription("matériel d'essai ...");
        course.addToMaterielSet(material);
        assertTrue(timetableDao.modifyCourse(course));
        
        // modification du cours, ajout d'un enseignant
        Person person = new Person();
        person.setIdPersonne(new Long(10));
        person.setTypePersonne("enseignant");
        course.addToPersonneSet(person);
        assertTrue(timetableDao.modifyCourse(course));
        
        // suppression du cours
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
