package fr.umlv.smoreau.beontime.model.timetable;

import fr.umlv.smoreau.beontime.model.base.BaseCourse;

/**
 * This is the object class that relates to the Cours table.
 * Any customizations belong here.
 */
public class Course extends BaseCourse {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Course () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Course (java.lang.Long _idCourse) {
		super(_idCourse);
	}

	/**
	 * Constructor for required fields
	 */
	public Course (
		java.lang.Long _idCourse,
		CourseType _idCourseType,
		java.lang.Long _idFormation) {

		super (
			_idCourse,
			_idCourseType,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
	
	private Subject _subject;
	
	public Subject getSubject() {
	    return _subject;
	}
	
	public void setSubject(Subject subject) {
	    this._subject = subject;
	}
}