package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.base.BaseParticipeGroupSubjectCourse;

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
	public ParticipeGroupSubjectCourse (ParticipeGroupSubjectCoursePK _id) {
		super(_id);
	}

/*[CONSTRUCTOR MARKER END]*/
}