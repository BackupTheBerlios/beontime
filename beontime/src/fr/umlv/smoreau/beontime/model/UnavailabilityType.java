package fr.umlv.smoreau.beontime.model;

import fr.umlv.smoreau.beontime.model.base.BaseUnavailabilityType;

/**
 * This is the object class that relates to the Type_indisponibilite table.
 * Any customizations belong here.
 */
public class UnavailabilityType extends BaseUnavailabilityType {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public UnavailabilityType () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UnavailabilityType (java.lang.Long _idUnavailabilityType) {
		super(_idUnavailabilityType);
	}

/*[CONSTRUCTOR MARKER END]*/
}