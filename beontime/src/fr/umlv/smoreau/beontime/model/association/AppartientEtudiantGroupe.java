package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.base.BaseAppartientEtudiantGroupe;

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
	public AppartientEtudiantGroupe (java.lang.Long _idGroupe) {
		super(_idGroupe);
	}

/*[CONSTRUCTOR MARKER END]*/
}