package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.base.BaseIsDirigeByCourseTeacher;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * This is the object class that relates to the Est_Dirige_par_Cours_Enseignant table.
 * Any customizations belong here.
 */
public class IsDirigeByCourseTeacher extends BaseIsDirigeByCourseTeacher {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public IsDirigeByCourseTeacher () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public IsDirigeByCourseTeacher (
		Person _idEnseignant,
		Course _idCours) {

		super (
			_idEnseignant,
			_idCours);
	}

/*[CONSTRUCTOR MARKER END]*/
}