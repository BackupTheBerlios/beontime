package fr.umlv.smoreau.beontime.model;

import fr.umlv.smoreau.beontime.model.base.BaseGroup;

/**
 * This is the object class that relates to the groupe table.
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
	public Group (java.lang.Long _idGroup) {
		super(_idGroup);
	}

/*[CONSTRUCTOR MARKER END]*/
}