package fr.umlv.smoreau.beontime.model;

import fr.umlv.smoreau.beontime.model.base.BaseTypeUnvailability;

/**
 * This is the object class that relates to the Type_indisponibilite table.
 * Any customizations belong here.
 */
public class TypeUnavailability extends BaseTypeUnvailability {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TypeUnavailability () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TypeUnavailability (java.lang.Long _idTypeIndisponibilite) {
		super(_idTypeIndisponibilite);
	}

/*[CONSTRUCTOR MARKER END]*/
}