package fr.umlv.smoreau.beontime.model.timetable;

import fr.umlv.smoreau.beontime.model.base.BaseCourseType;

/**
 * This is the object class that relates to the Type_cours table.
 * Any customizations belong here.
 */
public class CourseType extends BaseCourseType {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CourseType () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CourseType (java.lang.Long _idCourseType) {
		super(_idCourseType);
	}

/*[CONSTRUCTOR MARKER END]*/
}