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
	public Subject (java.lang.Long _idMatiere) {
		super(_idMatiere);
	}

	/**
	 * Constructor for required fields
	 */
	public Subject (
		java.lang.Long _idMatiere,
		java.lang.Long _idEnseignant,
		java.lang.Long _idFormation) {

		super (
			_idMatiere,
			_idEnseignant,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
}