package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.base.BaseAppartientEtudiantGroupe;
import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * This is the object class that relates to the appartient_etudiant_groupe table.
 * Any customizations belong here.
 */
public class AppartientEtudiantGroupe extends BaseAppartientEtudiantGroupe {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AppartientEtudiantGroupe () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AppartientEtudiantGroupe (
		Person _idEtudiant,
		Group _idGroupe) {

		super (
			_idEtudiant,
			_idGroupe);
	}

/*[CONSTRUCTOR MARKER END]*/
}