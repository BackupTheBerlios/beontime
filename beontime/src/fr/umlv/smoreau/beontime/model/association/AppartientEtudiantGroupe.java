package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.base.BaseAppartientEtudiantGroupe;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * This is the object class that relates to the Appartient_Etudiant_Groupe table.
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
	public AppartientEtudiantGroupe (java.lang.Long _idGroupe) {
		super(_idGroupe);
	}

	/**
	 * Constructor for required fields
	 */
	public AppartientEtudiantGroupe (
		java.lang.Long _idGroupe,
		Formation _idFormation) {

		super (
			_idGroupe,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
}