package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.model.timetable.*;

/**
 * @author BeOnTime
 */
public class TimetableDao extends Dao {
    private static final TimetableDao INSTANCE = new TimetableDao();

    private static final String TABLE_COURSE  = "Course";
    private static final String TABLE_SUBJECT = "Subject";

    private TimetableDao() {
    }

    public static TimetableDao getInstance() {
        return INSTANCE;
    }


	public Collection getCourses(CourseFilter filter) {
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
	
	public Collection getSubjects(SubjectFilter filter) {
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

	public Timetable getTimetable(TimetableFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public Collection getCourses() {
		return getCourses(null);
	}

	public Collection getSubjects() {
		return getSubjects(null);
	}
	
	public void addCourse(Course course) {
        try {
            TransactionManager.beginTransaction();
            add(course);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'un cours : " + e.getMessage());
        }
	}
	
	public void addSubject(Subject subject) {
        try {
            TransactionManager.beginTransaction();
            add(subject);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une matière : " + e.getMessage());
        }
	}
	
	public void modifyCourse(Course course) {
        try {
            TransactionManager.beginTransaction();
            modify(course);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'un cours : " + e.getMessage());
        }
	}
	
	public void modifySubject(Subject subject) {
        try {
            TransactionManager.beginTransaction();
            modify(subject);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une matière : " + e.getMessage());
        }
	}
	
	public void removeCourse(Course course) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_COURSE, new CourseFilter(course));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un cours : " + e.getMessage());
        }
	}
	
	public void removeSubject(Subject subject) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE_SUBJECT, new SubjectFilter(subject));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'une matière : " + e.getMessage());
        }
	}
}
