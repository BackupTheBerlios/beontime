package fr.umlv.smoreau.beontime.model.user;

import fr.umlv.smoreau.beontime.base.BasePerson;

/**
 * This is the object class that relates to the Personne table.
 * Any customizations belong here.
 */
public class Person extends BasePerson {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Person () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Person (java.lang.String _idPersonne) {
		super(_idPersonne);
	}

	/**
	 * Constructor for required fields
	 */
	public Person (
		java.lang.String _idPersonne,
		java.lang.String _typePersonne) {

		super (
			_idPersonne,
			_typePersonne);
	}

/*[CONSTRUCTOR MARKER END]*/
}