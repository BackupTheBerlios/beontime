package fr.umlv.smoreau.beontime.model.element;

import fr.umlv.smoreau.beontime.base.BaseRoom;

/**
 * This is the object class that relates to the Local table.
 * Any customizations belong here.
 */
public class Room extends BaseRoom {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Room () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Room (java.lang.Long _idLocal) {
		super(_idLocal);
	}

/*[CONSTRUCTOR MARKER END]*/
}