package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.CourseFilter;
import fr.umlv.smoreau.beontime.filter.TakePartGroupSubjectCourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.*;

/**
 * RMI implementation of the TimeTable DAO
 * @author BeOnTime team
 */
public class TimetableDaoImpl extends Dao implements TimetableDao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

 //   private static final TimetableDao INSTANCE = new TimetableDao();
    private static TimetableDao INSTANCE;
    static {
    	try {
			INSTANCE = new TimetableDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du TimeTable DAO");
			//TODO gerer
		}
    }

    private static final String TABLE_COURSE      = "Course";
    private static final String TABLE_SUBJECT     = "Subject";
    private static final String TABLE_TYPE_COURSE = "CourseType";
    private static final String TABLE_ASSOCIATION = "TakePartGroupSubjectCourse";
    
    private String[] DEFAULT_TYPES = { "cours magistraux", "travaux dirigés", "travaux pratiques" };

    private TimetableDaoImpl() throws RemoteException {
        Collection types = getTypesCourse();
        if (types != null && types.size() == 0) {
            for (int i = 0; i < DEFAULT_TYPES.length; ++i) {
                CourseType type = new CourseType();
                type.setNameCourseType(DEFAULT_TYPES[i]);
                addTypeCourse(type);
            }
        }
    }

    public static TimetableDao getInstance() throws RemoteException {
        return INSTANCE;
    }


	public Collection getCourses(CourseFilter filter) throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_COURSE, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des cours : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getSubjects(SubjectFilter filter) throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_SUBJECT, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des matières : " + e.getMessage());
        }

		return result;
	}

	public Timetable getTimetable(TimetableFilter filter) throws RemoteException {
		//TODO à implémenter
		return null;
	}

	public Collection getCourses() throws RemoteException {
		return getCourses(null);
	}

	public Collection getSubjects() throws RemoteException {
		return getSubjects(null);
	}
	
	public boolean addCourse(Course course) {
        try {
            TransactionManager.beginTransaction();
            add(course);
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                add((TakePartGroupSubjectCourse)i.next());
            }
            TransactionManager.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'ajout d'un cours : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean addSubject(Subject subject) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            add(subject);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une matière : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyCourse(Course course) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            modify(course);
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                add((TakePartGroupSubjectCourse)i.next());
            }
            TransactionManager.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la modification d'un cours : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifySubject(Subject subject) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            modify(subject);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une matière : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeCourse(Course course) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                remove(TABLE_ASSOCIATION, new TakePartGroupSubjectCourseFilter((TakePartGroupSubjectCourse)i.next()));
            }
            remove(TABLE_COURSE, new CourseFilter(course));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un cours : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeSubject(Subject subject) throws RemoteException {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_SUBJECT, new SubjectFilter(subject));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'une matière : " + e.getMessage());
            return false;
        }
        return true;
	}


	public Collection getTypesCourse() throws RemoteException {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_TYPE_COURSE, null);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des types de cours : " + e.getMessage());
        }

		return result;
	}
	
	private boolean addTypeCourse(CourseType typeCourse) /*throws RemoteException*/ {
        try {
            TransactionManager.beginTransaction();
            add(typeCourse);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout du type de cours : " + e.getMessage());
            return false;
        }
        return true;
	}
}
