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
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.filter._TakePartGroupSubjectCourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.filter._IsDirectedByCourseTeacherFilter;
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
	    Session session = null;
        try {
            Timetable timetable = new Timetable();
            
            // toutes les matières
            Collection allSubjects = get(TABLE_SUBJECT, new SubjectFilter(), Hibernate.getCurrentSession());
            
            if (filter.getGroup() != null) {
                // caractéristiques du groupe
                GroupDao groupDao = GroupDaoImpl.getInstance();
                timetable.setGroup(groupDao.getGroup(filter.getGroup(), null));
                
                // cours du groupe
	            _TakePartGroupSubjectCourseFilter takePartGroupSubjectCourseFilter = new _TakePartGroupSubjectCourseFilter();
	            takePartGroupSubjectCourseFilter.setIdGroup(filter.getGroup().getIdGroup());
	            Collection list = get(TABLE_ASSOCIATION, takePartGroupSubjectCourseFilter, Hibernate.getCurrentSession());
	            timetable.setCourses(new ArrayList());
	            for (Iterator i = list.iterator(); i.hasNext(); ) {
	                TakePartGroupSubjectCourse takePart = (TakePartGroupSubjectCourse) i.next();
	                Course course = new Course(takePart.getIdCourse());
	                course = getCourse(course, null);
	                if (course.getBeginDate().getTimeInMillis() >= filter.getBeginPeriod().getTimeInMillis() && course.getEndDate().getTimeInMillis() <= filter.getEndPeriod().getTimeInMillis())
	                    timetable.addCourse(course);
	            }
	            
	            // caractéristiques de la formation du groupe
	            FormationDao formationDao = FormationDaoImpl.getInstance();
	            timetable.setFormation(formationDao.getFormation(filter.getFormation(), null));
	            
	            // matières de la formation
	            for (Iterator i = allSubjects.iterator(); i.hasNext(); ) {
	                Subject subject = (Subject) i.next();
	                if (subject.getIdFormation().equals(timetable.getFormation().getIdFormation()))
	                    timetable.addSubject(subject);
	            }
            }
            
            else if (filter.getFormation() != null) {
                // caractéristiques de la formation
	            FormationDao formationDao = FormationDaoImpl.getInstance();
	            timetable.setFormation(formationDao.getFormation(filter.getFormation(), null));

	            // matières de la formation
	            for (Iterator i = allSubjects.iterator(); i.hasNext(); ) {
	                Subject subject = (Subject) i.next();
	                if (subject.getIdFormation().equals(timetable.getFormation().getIdFormation()))
	                    timetable.addSubject(subject);
	            }

	            // groupes de la formation
	            Group group = new Group();
	            group.setIdFormation(filter.getFormation().getIdFormation());
	            GroupDao groupDao = GroupDaoImpl.getInstance();
	            timetable.setGroups(groupDao.getGroups(new GroupFilter(group)));
	            
	            // cours de la formation
	            Course course = new Course();
	            course.setIdFormation(filter.getFormation().getIdFormation());
	            course.setBeginPeriod(filter.getBeginPeriod());
	            course.setEndPeriod(filter.getEndPeriod());
	            timetable.setCourses(get(TABLE_COURSE, new CourseFilter(course), Hibernate.getCurrentSession()));
	            
	            // responsable de la formation
	            UserFilter userFilter = new UserFilter();
	            userFilter.setIdUser(timetable.getFormation().getIdTeacher());
	            userFilter.setUserType(UserDao.TYPE_TEACHER);
	            UserDao userDao = UserDaoImpl.getInstance();
	            Collection c = userDao.getUsers(userFilter);
	            timetable.setPersonInCharge((User) c.toArray()[0]);
            }
            
            else if (filter.getTeacher() != null) {
                // caractéristiques de l'enseignant
                UserDao userDao = UserDaoImpl.getInstance();
                timetable.setTeacher(userDao.getUser(filter.getTeacher(), null));

                // matières de l'enseignant
	            for (Iterator i = allSubjects.iterator(); i.hasNext(); ) {
	                Subject subject = (Subject) i.next();
	                if (subject.getIdTeacher().equals(timetable.getTeacher().getIdUser()))
	                    timetable.addSubject(subject);
	            }
	            
	            // cours de l'enseignant
	            _IsDirectedByCourseTeacherFilter isDirectedByCourseTeacherFilter = new _IsDirectedByCourseTeacherFilter();
	            isDirectedByCourseTeacherFilter.setIdTeacher(filter.getTeacher().getIdUser());
	            Collection isDirectedByCourseTeachers = get(TABLE_ISDIRECTING, isDirectedByCourseTeacherFilter, Hibernate.getCurrentSession());
	            timetable.setCourses(new ArrayList());
	            for (Iterator i = isDirectedByCourseTeachers.iterator(); i.hasNext(); ) {
	                Course course = ((IsDirectedByCourseTeacher) i.next()).getIdCourse();
	                if (course.getBeginDate().getTimeInMillis() >= filter.getBeginPeriod().getTimeInMillis() && course.getEndDate().getTimeInMillis() <= filter.getEndPeriod().getTimeInMillis())
	                    timetable.addCourse(course);
	            }
            }
            
            else if (filter.getRoom() != null) {
                // caractéristiques du local
                ElementDao elementDao = ElementDaoImpl.getInstance();
                timetable.setRoom(elementDao.getRoom(filter.getRoom(), null));
                
                // cours du local
                timetable.setCourses(elementDao.getCoursesInRoom(timetable.getRoom(), filter.getBeginPeriod(), filter.getEndPeriod()));
            }
            
            else if (filter.getMaterial() != null) {
                // caractéristiques du matériel
                ElementDao elementDao = ElementDaoImpl.getInstance();
                timetable.setMaterial(elementDao.getMaterial(filter.getMaterial(), null));
                
                // cours du matériel
                timetable.setCourses(elementDao.getCoursesWithMaterial(timetable.getMaterial(), filter.getBeginPeriod(), filter.getEndPeriod()));
            }

            UserDao userDao = UserDaoImpl.getInstance();
            for (Iterator i = timetable.getCourses().iterator(); i.hasNext(); ) {
                Course tmp = (Course) i.next();
                
                // matière correspondante pour chaque cours
                _TakePartGroupSubjectCourseFilter f = new _TakePartGroupSubjectCourseFilter();
                f.setIdCourse(tmp.getIdCourse());
                Collection co = get(TABLE_ASSOCIATION, f, Hibernate.getCurrentSession());
                if (co != null && co.size() > 0) {
	                TakePartGroupSubjectCourse takePart = (TakePartGroupSubjectCourse) co.toArray()[0];
	                for (Iterator j = allSubjects.iterator(); j.hasNext(); ) {
	                    Subject s = (Subject) j.next();
	                    if (s.getIdSubject().equals(takePart.getIdSubject()))
	                        tmp.setSubject(s);
	                }
                }
                
                // enseignants correspondants pour chaque cours
                Collection teachers = tmp.getTeachersDirecting();
                for (Iterator j = teachers.iterator(); j.hasNext(); ) {
                    IsDirectedByCourseTeacher isDirected = (IsDirectedByCourseTeacher) j.next();
                    tmp.addTeacher(userDao.getUser(new User(isDirected.getIdTeacher()), null));
                }
            }

            return timetable;
        } finally {
            Hibernate.closeSession();
        }
	}

	public Collection getCourses() throws RemoteException, HibernateException {
		return getCourses(null);
	}
	
	public Course getCourse(Course course, String[] join) throws RemoteException, HibernateException {
	    try {
            Session session = Hibernate.getCurrentSession();
            course = (Course) get(TABLE_COURSE, new CourseFilter(course), session).toArray()[0];
            join(course, join);
        } finally {
            Hibernate.closeSession();
        }
        return course;
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
            
            _TakePartGroupSubjectCourseFilter f1 = new _TakePartGroupSubjectCourseFilter();
            f1.setIdCourse(course.getIdCourse());
            remove(TABLE_ASSOCIATION, f1, session);
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                add((TakePartGroupSubjectCourse)i.next(), session);
            }
            
            _IsDirectedByCourseTeacherFilter f2 = new _IsDirectedByCourseTeacherFilter();
            f2.setIdCourse(course);
            remove(TABLE_ISDIRECTING, f2, session);
            Collection c = course.getTeachersDirecting();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                IsDirectedByCourseTeacher isDirected = (IsDirectedByCourseTeacher) i.next();//new IsDirectedByCourseTeacher((User) i.next(), course);
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
