package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.CourseFilter;
import fr.umlv.smoreau.beontime.filter.ParticipeGroupSubjectCourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.*;

/**
 * @author BeOnTime
 */
public class TimetableDao extends Dao {
    private static final TimetableDao INSTANCE = new TimetableDao();

    private static final String TABLE_COURSE      = "Course";
    private static final String TABLE_SUBJECT     = "Subject";
    private static final String TABLE_TYPE_COURSE = "CourseType";
    private static final String TABLE_ASSOCIATION = "TakePartGroupSubjectCourse";
    
    private String[] DEFAULT_TYPES = { "cours magistraux", "travaux dirigés", "travaux pratiques" };

    private TimetableDao() {
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
	
	public boolean addSubject(Subject subject) {
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
	
	public boolean modifyCourse(Course course) {
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
	
	public boolean modifySubject(Subject subject) {
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
	
	public boolean removeCourse(Course course) {
        try {
            TransactionManager.beginTransaction();
            Set p = course.getGroupsSubjectsTakingPart();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); )
	                remove(TABLE_ASSOCIATION, new ParticipeGroupSubjectCourseFilter((TakePartGroupSubjectCourse)i.next()));
            }
            remove(TABLE_COURSE, new CourseFilter(course));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'un cours : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeSubject(Subject subject) {
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


	public Collection getTypesCourse() {
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
	
	private boolean addTypeCourse(CourseType typeCourse) {
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
