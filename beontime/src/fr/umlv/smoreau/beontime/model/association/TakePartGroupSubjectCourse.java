package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.base.BaseTakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;

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
	        Subject _idSubject,
			Group _idGroup,
			Course _idCourse) {
		super(_idSubject, _idGroup, _idCourse);
	}

/*[CONSTRUCTOR MARKER END]*/
}