package fr.umlv.smoreau.beontime.model;

import fr.umlv.smoreau.beontime.model.base.BaseUnavailability;

/**
 * This is the object class that relates to the Indisponibilite table.
 * Any customizations belong here.
 */
public class Unavailability extends BaseUnavailability {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Unavailability () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Unavailability (java.lang.Long _idIndisponibilite) {
		super(_idIndisponibilite);
	}

	/**
	 * Constructor for required fields
	 */
	public Unavailability (
		java.lang.Long _idIndisponibilite,
		TypeUnvailability _idTypeIndisponibilite,
		java.lang.Long _idSujetIndisponibilite) {

		super (
			_idIndisponibilite,
			_idTypeIndisponibilite,
			_idSujetIndisponibilite);
	}

/*[CONSTRUCTOR MARKER END]*/
}