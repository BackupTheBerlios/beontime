/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import fr.umlv.smoreau.beontime.model.timetable.*;

/**
 * @author BeOnTime
 */
public class TimetableDao {
    private static final TimetableDao INSTANCE = new TimetableDao();
    
    private TimetableDao() {
    }

    public static TimetableDao getInstance() {
        return INSTANCE;
    }


	public Collection getCourses(CourseFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getSubjects(SubjectFilter filter) {
		//TODO � impl�menter
		return null;
	}

	public Timetable getTimetable(TimetableFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getCourses() {
		return getCourses(null);
	}

	public Collection getSubjects() {
		return getSubjects(null);
	}
	
	public void addCourse(Course course) {
		//TODO � impl�menter
	}
	
	public void addSubject(Subject subject) {
		//TODO � impl�menter
	}
	
	public void modifyCourse(Course course) {
		//TODO � impl�menter
	}
	
	public void modifySubject(Subject subject) {
		//TODO � impl�menter
	}
	
	public void removeCourse(Course course) {
		//TODO � impl�menter
	}
	
	public void removeSubject(Subject subject) {
		//TODO � impl�menter
	}
}
