package fr.umlv.smoreau.beontime.model.timetable;

import fr.umlv.smoreau.beontime.model.base.BaseTypeCourse;

/**
 * This is the object class that relates to the Type_cours table.
 * Any customizations belong here.
 */
public class TypeCourse extends BaseTypeCourse {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TypeCourse () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TypeCourse (java.lang.Long _idTypeCours) {
		super(_idTypeCours);
	}

/*[CONSTRUCTOR MARKER END]*/
}