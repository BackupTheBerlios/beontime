package junit;

import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.dao.GroupDao;
import fr.umlv.smoreau.beontime.dao.GroupDaoImpl;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.dao.TimetableDaoImpl;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.CourseType;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class TimetableDaoTest extends TestCase {
    private static GroupDao groupDao = GroupDaoImpl.getInstance();
    private static TimetableDao timetableDao = TimetableDaoImpl.getInstance();
    
    public TimetableDaoTest(String name) {
        super(name);
    }

    public void testGetCourses() {
        try {
            assertNotNull(timetableDao.getCourses());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testGetTypesCourse() {
        try {
            assertNotNull(timetableDao.getTypesCourse());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testGetSubjects() {
        try {
            assertNotNull(timetableDao.getSubjects());
        } catch (RemoteException e) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testAddRemoveCourse() {
        try {
            // ajout d'un cours simple
            Course course = new Course();
	        Collection t = timetableDao.getTypesCourse();
	        CourseType[] types = (CourseType[]) t.toArray(new CourseType[t.size()]);
	        course.setIdCourseType(types[0]);
	        course.setIdFormation(new Long(1));
	        timetableDao.addCourse(course);

	        // modification du cours, ajout d'un local
	        Room room = new Room();
	        room.setDescription("local d'essai ...");
	        course.addRoom(room);
            timetableDao.modifyCourse(course);

	        // modification du cours, ajout d'un matériel
	        Material material = new Material();
	        material.setDescription("matériel d'essai ...");
	        course.addMaterial(material);
            timetableDao.modifyCourse(course);

	        // modification du cours, ajout d'un enseignant
	        User person = new User();
	        person.setIdUser(new Long(10));
	        person.setUserType("enseignant");
	        course.addTeacherDirecting(new IsDirectedByCourseTeacher(person,course));
            timetableDao.modifyCourse(course);
        
        	// modification du cours, relation avec la matière et le groupe
	        TakePartGroupSubjectCourse participe = new TakePartGroupSubjectCourse();
	        participe.setIdCourse(course.getIdCourse());
	        Group group = new Group();
	        group.setIdFormation(new Long(1));
	        group.setHeading("groupe pour essayer");
            groupDao.addGroup(group);
	        participe.setIdGroup(group.getIdGroup());
	        Subject subject = new Subject();
	        subject.setIdFormation(new Long(2));
	        subject.setIdTeacher(new Long(5));
            timetableDao.addSubject(subject);
	        participe.setIdSubject(subject.getIdSubject());
	        course.addGroupSubjectTakingPart(participe);
            timetableDao.modifyCourse(course);

        	// suppression du cours et des éléments utilisés
            timetableDao.removeCourse(course);
            groupDao.removeGroup(group);
            timetableDao.removeSubject(subject);
            
            assertTrue(true);
        } catch (RemoteException e2) {
            assertTrue(false);
        } catch (HibernateException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testAddRemoveSubject() {
        try {
	        Subject subject = new Subject();
	        subject.setIdFormation(new Long(2));
	        subject.setIdTeacher(new Long(5));
	        timetableDao.addSubject(subject);
	        subject.setHeading("matière de test");
	        timetableDao.modifySubject(subject);
	        timetableDao.removeSubject(subject);
	        assertTrue(true);
        } catch (RemoteException e2) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }
    
    public void testGetTimetable() {
        try {
	        Timetable timetable = new Timetable(new Formation(new Long(1)));
	        TimetableFilter filter = new TimetableFilter(timetable);
	        assertNotNull(timetableDao.getTimetable(filter));
        } catch (RemoteException e2) {
            assertTrue(false);
        } catch (HibernateException e) {
            assertTrue(false);
        }
    }

    
    public static Test suite() {
        return new TestSuite(TimetableDaoTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
