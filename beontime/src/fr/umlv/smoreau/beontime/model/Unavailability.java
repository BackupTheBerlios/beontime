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
	public Unavailability (java.lang.Long _idUnavailability) {
		super(_idUnavailability);
	}

	/**
	 * Constructor for required fields
	 */
	public Unavailability (
		java.lang.Long _idUnavailability,
		UnavailabilityType _idUnavailabilityType,
		java.lang.Long _idUnavailabilitySubject) {

		super (
			_idUnavailability,
			_idUnavailabilityType,
			_idUnavailabilitySubject);
	}

/*[CONSTRUCTOR MARKER END]*/
}