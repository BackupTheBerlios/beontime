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
	public Course (java.lang.Long _idCours) {
		super(_idCours);
	}

	/**
	 * Constructor for required fields
	 */
	public Course (
		java.lang.Long _idCours,
		TypeCourse _idTypeCours,
		java.lang.Long _idFormation) {

		super (
			_idCours,
			_idTypeCours,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
}