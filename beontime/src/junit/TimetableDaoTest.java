package junit;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.GroupDao;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.CourseType;
import fr.umlv.smoreau.beontime.model.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class TimetableDaoTest extends TestCase {
    private static final GroupDao groupDao = GroupDao.getInstance();
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
        CourseType[] types = (CourseType[]) t.toArray(new CourseType[t.size()]);
        course.setIdCourseType(types[0]);
        course.setIdFormation(new Long(1));
        assertTrue(timetableDao.addCourse(course));
        
        // modification du cours, ajout d'un local
        Room room = new Room();
        room.setDescription("local d'essai ...");
        course.addRoom(room);
        assertTrue(timetableDao.modifyCourse(course));
        
        // modification du cours, ajout d'un matériel
        Material material = new Material();
        material.setDescription("matériel d'essai ...");
        course.addMaterial(material);
        assertTrue(timetableDao.modifyCourse(course));
        
        // modification du cours, ajout d'un enseignant
        User person = new User();
        person.setIdUser(new Long(10));
        person.setUserType("enseignant");
        course.addTeacherDirecting(person);
        assertTrue(timetableDao.modifyCourse(course));
        
        // modification du cours, relation avec la matière et le groupe
        TakePartGroupSubjectCourse participe = new TakePartGroupSubjectCourse();
        participe.setIdCourse(course);
        Group group = new Group();
        group.setIdFormation(new Long(1));
        group.setHeading("groupe pour essayer");
        groupDao.addGroup(group);
        participe.setIdGroup(group);
        Subject subject = new Subject();
        subject.setIdFormation(new Long(2));
        subject.setIdTeacher(new Long(5));
        timetableDao.addSubject(subject);
        participe.setIdSubject(subject);
        course.addGroupSubjectTakingPart(participe);
        assertTrue(timetableDao.modifyCourse(course));
        
        // suppression du cours et des éléments utilisés
        assertTrue(timetableDao.removeCourse(course));
        groupDao.removeGroup(group);
        timetableDao.removeSubject(subject);
    }
    
    public void testAddRemoveSubject() {
        Subject subject = new Subject();
        subject.setIdFormation(new Long(2));
        subject.setIdTeacher(new Long(5));
        assertTrue(timetableDao.addSubject(subject));
        subject.setHeading("matière de test");
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
