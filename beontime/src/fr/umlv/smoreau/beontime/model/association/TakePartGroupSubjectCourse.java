package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.base.BaseTakePartGroupSubjectCourse;

/**
 * This is the object class that relates to the Participe_Groupe_Matiere_Cours table.
 * Any customizations belong here.
 */
public class TakePartGroupSubjectCourse extends BaseTakePartGroupSubjectCourse {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TakePartGroupSubjectCourse () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TakePartGroupSubjectCourse (
	        java.lang.Long _idSubject,
	        java.lang.Long _idGroup,
	        java.lang.Long _idCourse) {
		super(_idSubject, _idGroup, _idCourse);
	}

/*[CONSTRUCTOR MARKER END]*/
}