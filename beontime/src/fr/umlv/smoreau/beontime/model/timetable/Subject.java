package fr.umlv.smoreau.beontime.model.timetable;

import fr.umlv.smoreau.beontime.model.base.BaseSubject;

/**
 * This is the object class that relates to the Matiere table.
 * Any customizations belong here.
 */
public class Subject extends BaseSubject {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Subject () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Subject (java.lang.Long _idSubject) {
		super(_idSubject);
	}

	/**
	 * Constructor for required fields
	 */
	public Subject (
		java.lang.Long _idSubject,
		java.lang.Long _idTeacher,
		java.lang.Long _idFormation) {

		super (
			_idSubject,
			_idTeacher,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
}