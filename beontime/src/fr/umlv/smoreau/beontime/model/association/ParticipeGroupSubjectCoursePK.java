package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.base.BaseParticipeGroupSubjectCoursePK;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.user.Person;

public class ParticipeGroupSubjectCoursePK extends BaseParticipeGroupSubjectCoursePK {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ParticipeGroupSubjectCoursePK () {}
	
	public ParticipeGroupSubjectCoursePK (
		Person _idEnseignant,
		Subject _idMatiere,
		Group _idGroupe,
		Course _idCours) {

		super (
		_idEnseignant,
		_idMatiere,
		_idGroupe,
		_idCours);
	}
/*[CONSTRUCTOR MARKER END]*/
}