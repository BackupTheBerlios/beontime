package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.CourseFilter;
import fr.umlv.smoreau.beontime.filter._TakePartGroupSubjectCourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.filter._IsDirectedByCourseTeacherFilter;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.*;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * RMI implementation of the TimeTable DAO
 * @author BeOnTime team
 */
public class TimetableDaoImpl extends Dao implements TimetableDao {
	//TODO en cas de modif refaire le rmic et rebalancer cot� client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static TimetableDao INSTANCE;
    static {
    	try {
			INSTANCE = new TimetableDaoImpl();
		} catch (RemoteException e) {
			System.err.println("probl�me RMI � l'instanciation du TimeTable DAO");
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
    
    private String[] DEFAULT_TYPES = { "cours magistraux", "travaux dirig�s", "travaux pratiques" };

    private TimetableDaoImpl() throws RemoteException, HibernateException {
        Collection types = getTypesCourse();
        if (types != null && types.size() == 0) {
            for (int i = 0; i < DEFAULT_TYPES.length; ++i) {
                CourseType type = new CourseType();
                type.setNameCourseType(DEFAULT_TYPES[i]);
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
            Collection c = get(TABLE_COURSE, filter, session);
            UserDao userDao = UserDaoImpl.getInstance();
            for (Iterator i = c.iterator(); i.hasNext(); ) {
                Course course = (Course) i.next();
                Set teachers = course.getTeachersDirecting();
                if (teachers != null) {
	                course.setTeachersDirecting(new HashSet());
	                for (Iterator j = teachers.iterator(); j.hasNext(); ) {
	                    IsDirectedByCourseTeacher isDirected = (IsDirectedByCourseTeacher) j.next();
	                    Collection tmp = userDao.getTeachers(new UserFilter(new User(isDirected.getIdTeacher())));
	                    course.addTeacherDirecting(tmp.toArray()[0]);
	                }
                }
            }
            return c;
        } finally {
            Hibernate.closeSession();
        }
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

	public Timetable getTimetable(TimetableFilter filter) throws RemoteException, HibernateException {
		//TODO � impl�menter
		return null;
	}

	public Collection getCourses() throws RemoteException, HibernateException {
		return getCourses(null);
	}

	public Collection getSubjects() throws RemoteException, HibernateException {
		return getSubjects(null);
	}
	
	public void addCourse(Course course) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(course, session);
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                add((TakePartGroupSubjectCourse)i.next(), session);
            }
            Collection c = course.getTeachersDirecting();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                IsDirectedByCourseTeacher isDirected = new IsDirectedByCourseTeacher((User) i.next(), course);
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
	}
	
	public void addSubject(Subject subject) throws RemoteException, HibernateException {
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
	                IsDirectedByCourseTeacher isDirected = new IsDirectedByCourseTeacher((User) i.next(), course);
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
	                IsDirectedByCourseTeacher isDirected = new IsDirectedByCourseTeacher((User) i.next(), course);
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
	
	private void addTypeCourse(CourseType typeCourse) throws HibernateException {
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
	}
}
