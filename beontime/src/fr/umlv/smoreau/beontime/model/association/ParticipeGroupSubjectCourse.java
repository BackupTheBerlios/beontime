package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.base.BaseParticipeGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;

/**
 * This is the object class that relates to the Participe_Groupe_Matiere_Cours table.
 * Any customizations belong here.
 */
public class ParticipeGroupSubjectCourse extends BaseParticipeGroupSubjectCourse {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ParticipeGroupSubjectCourse () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ParticipeGroupSubjectCourse (
	        Subject _idMatiere,
			Group _idGroupe,
			Course _idCours) {
		super(_idMatiere, _idGroupe, _idCours);
	}

/*[CONSTRUCTOR MARKER END]*/
}