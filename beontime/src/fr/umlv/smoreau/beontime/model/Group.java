package fr.umlv.smoreau.beontime.model;

import fr.umlv.smoreau.beontime.model.base.BaseGroup;

/**
 * This is the object class that relates to the Groupe table.
 * Any customizations belong here.
 */
public class Group extends BaseGroup {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Group () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Group (java.lang.Long _idGroupe) {
		super(_idGroupe);
	}

	/**
	 * Constructor for required fields
	 */
	public Group (
		java.lang.Long _idGroupe,
		Formation _idFormation) {

		super (
			_idGroupe,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
}