package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.CourseFilter;
import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.filter._TakePartGroupSubjectCourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.filter._IsDirectedByCourseTeacherFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.*;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * RMI implementation of the TimeTable DAO
 * @author BeOnTime team
 */
public class TimetableDaoImpl extends Dao implements TimetableDao {
	//En cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static TimetableDao INSTANCE;
    static {
    	try {
			INSTANCE = new TimetableDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du TimeTable DAO");
			//TODO gerer
		} catch (HibernateException e) {
		    System.err.println("Erreur lors de l'instanciation : " + e.getMessage());
        }
    }

    private static final String TABLE_COURSE      = "Course";
    private static final String TABLE_SUBJECT     = "Subject";
    private static final String TABLE_TYPE_COURSE = "CourseType";
    private static final String TABLE_ASSOCIATION = "TakePartGroupSubjectCourse";
    private static final String TABLE_ISDIRECTING = "IsDirectedByCourseTeacher";


    private TimetableDaoImpl() throws RemoteException, HibernateException {
        Collection types = getTypesCourse();
        if (types != null && types.size() == 0) {
            for (int i = 0; i < TYPES_COURSES.length; ++i) {
                CourseType type = new CourseType();
                type.setNameCourseType(TYPES_COURSES[i]);
                addTypeCourse(type);
            }
        }
    }

    public static TimetableDao getInstance() {
        return INSTANCE;
    }


	public Collection getCourses(CourseFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_COURSE, filter, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public Collection getCoursesDirected(User user) throws RemoteException, HibernateException {
	    Collection result = new ArrayList();

	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            _IsDirectedByCourseTeacherFilter filter = new _IsDirectedByCourseTeacherFilter();
            filter.setIdTeacher(user.getIdUser());
            Collection isDirected = get(TABLE_ISDIRECTING, filter, session);
            for (Iterator i = isDirected.iterator(); i.hasNext(); )
                result.add(new Course(((IsDirectedByCourseTeacher) i.next()).getIdCourse().getIdCourse()));
        } finally {
            Hibernate.closeSession();
        }
        
        return result;
	}
	
	public Collection getSubjects(SubjectFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_SUBJECT, filter, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public Collection getSubjectsResponsible(User user) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            SubjectFilter filter = new SubjectFilter();
            filter.setIdTeacher(user.getIdUser());
            return get(TABLE_SUBJECT, filter, session);
        } finally {
            Hibernate.closeSession();
        }
	}

	public Timetable getTimetable(TimetableFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            Timetable timetable = new Timetable();
            FormationDao formationDao = FormationDaoImpl.getInstance();
            Collection c = formationDao.getFormations(new FormationFilter(filter.getFormation()));
            if (c == null || c.size() == 0)
                return null;
            timetable.setFormation((Formation) c.toArray()[0]);

            Subject subject = new Subject();
            subject.setIdFormation(filter.getFormation().getIdFormation());
            timetable.setSubjects(get(TABLE_SUBJECT, new SubjectFilter(subject), Hibernate.getCurrentSession()));
            
            Group group = new Group();
            group.setIdFormation(filter.getFormation().getIdFormation());
            GroupDao groupDao = GroupDaoImpl.getInstance();
            timetable.setGroups(groupDao.getGroups(new GroupFilter(group)));
            
            //TODO à voir plus précisemment
            timetable.setGroup((Group) timetable.getGroups().toArray()[0]);
            
            Course course = new Course();
            course.setIdFormation(filter.getFormation().getIdFormation());
            timetable.setCourses(get(TABLE_COURSE, new CourseFilter(course), Hibernate.getCurrentSession()));

            for (Iterator i = timetable.getCourses().iterator(); i.hasNext(); ) {
                Course tmp = (Course) i.next();
                _TakePartGroupSubjectCourseFilter f = new _TakePartGroupSubjectCourseFilter();
                f.setIdCourse(tmp.getIdCourse());
                Collection co = get(TABLE_ASSOCIATION, f, Hibernate.getCurrentSession());
                if (co != null && co.size() > 0) {
	                TakePartGroupSubjectCourse takePart = (TakePartGroupSubjectCourse) co.toArray()[0];
	                SubjectFilter s = new SubjectFilter(new Subject(takePart.getIdSubject()));
	                co = get(TABLE_SUBJECT, s, Hibernate.getCurrentSession());
	                tmp.setSubject((Subject) co.toArray()[0]);
                }
            }
            
            UserFilter userFilter = new UserFilter();
            userFilter.setIdUser(timetable.getFormation().getIdTeacher());
            userFilter.setUserType(UserDao.TYPE_TEACHER);
            UserDao userDao = UserDaoImpl.getInstance();
            c = userDao.getUsers(userFilter);
            timetable.setPersonInCharge((User) c.toArray()[0]);

            return timetable;
        } finally {
            Hibernate.closeSession();
        }
	}

	public Collection getCourses() throws RemoteException, HibernateException {
		return getCourses(null);
	}

	public Collection getSubjects() throws RemoteException, HibernateException {
		return getSubjects(null);
	}
	
	public Course addCourse(Course course) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(course, session);
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); ) {
	                TakePartGroupSubjectCourse takePart = (TakePartGroupSubjectCourse) i.next();
	                takePart.setIdCourse(course.getIdCourse());
	                add(takePart, session);
	            }
            }
            Collection c = course.getTeachersDirecting();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                IsDirectedByCourseTeacher isDirected = (IsDirectedByCourseTeacher) i.next();
	                add(isDirected, session);
	            }
            }
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
        return course;
	}
	
	public Subject addSubject(Subject subject) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(subject, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
        return subject;
	}
	
	public void modifyCourse(Course course) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(course, session);
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                addOrModify((TakePartGroupSubjectCourse)i.next(), session);
            }
            Collection c = course.getTeachersDirecting();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                IsDirectedByCourseTeacher isDirected = (IsDirectedByCourseTeacher) i.next();//new IsDirectedByCourseTeacher((User) i.next(), course);
                	addOrModify(isDirected, session);
	            }
            }
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void modifySubject(Subject subject) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(subject, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeCourse(Course course) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                remove(TABLE_ASSOCIATION, new _TakePartGroupSubjectCourseFilter((TakePartGroupSubjectCourse)i.next()), session);
            }
            Collection c = course.getTeachersDirecting();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                IsDirectedByCourseTeacher isDirected = (IsDirectedByCourseTeacher) i.next();
	                remove(TABLE_ISDIRECTING, new _IsDirectedByCourseTeacherFilter(isDirected), session);
	            }
            }
            remove(TABLE_COURSE, new CourseFilter(course), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeSubject(Subject subject) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            remove(TABLE_SUBJECT, new SubjectFilter(subject), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}


	public Collection getTypesCourse() throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            return get(TABLE_TYPE_COURSE, null, session);
        } finally {
            Hibernate.closeSession();
        }
	}
	
	private CourseType addTypeCourse(CourseType typeCourse) throws HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(typeCourse, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
        return typeCourse;
	}
	
	
	public void removeLinkBetweenCourseAndTeacher(IsDirectedByCourseTeacher link) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            remove(link, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
}
