package fr.umlv.smoreau.beontime.model.element;

import fr.umlv.smoreau.beontime.model.base.BaseRoom;

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
	public Room (java.lang.Long _idRoom) {
		super(_idRoom);
	}

/*[CONSTRUCTOR MARKER END]*/
}