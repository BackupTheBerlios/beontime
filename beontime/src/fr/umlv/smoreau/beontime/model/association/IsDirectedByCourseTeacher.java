package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.base.BaseIsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * This is the object class that relates to the Est_Dirige_par_Cours_Enseignant table.
 * Any customizations belong here.
 */
public class IsDirectedByCourseTeacher extends BaseIsDirectedByCourseTeacher {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public IsDirectedByCourseTeacher () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public IsDirectedByCourseTeacher (
	        java.lang.Long _idTeacher,
			Course _idCourse) {

			super (
				_idTeacher,
				_idCourse);
		}

	public IsDirectedByCourseTeacher (
		User _idTeacher,
		Course _idCourse) {

		super (
			_idTeacher.getIdUser(),
			_idCourse);
	}

/*[CONSTRUCTOR MARKER END]*/
}