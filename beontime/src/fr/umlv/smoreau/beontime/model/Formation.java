package fr.umlv.smoreau.beontime.model;

import fr.umlv.smoreau.beontime.model.base.BaseFormation;
import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * This is the object class that relates to the Formation table.
 * Any customizations belong here.
 */
public class Formation extends BaseFormation {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Formation () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Formation (java.lang.Long _idFormation) {
		super(_idFormation);
	}

	/**
	 * Constructor for required fields
	 */
	public Formation (
		java.lang.Long _idFormation,
		Person _idSecretaire,
		java.lang.Long _idEnseignant) {

		super (
			_idFormation,
			_idSecretaire,
			_idEnseignant);
	}
/*[CONSTRUCTOR MARKER END]*/
}