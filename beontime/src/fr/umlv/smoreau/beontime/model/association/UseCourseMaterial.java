package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.base.BaseUseCourseMaterial;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * This is the object class that relates to the Utilise_Cours_Materiel table.
 * Any customizations belong here.
 */
public class UseCourseMaterial extends BaseUseCourseMaterial {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public UseCourseMaterial () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UseCourseMaterial (
		Material _idMateriel,
		Course _idCours) {

		super (
			_idMateriel,
			_idCours);
	}

/*[CONSTRUCTOR MARKER END]*/
}