package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.base.BaseBelongStudentGroup;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * This is the object class that relates to the appartient_etudiant_groupe table.
 * Any customizations belong here.
 */
public class BelongStudentGroup extends BaseBelongStudentGroup {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BelongStudentGroup () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BelongStudentGroup (
		User _idStudent,
		Group _idGroup) {

		super (
			_idStudent,
			_idGroup);
	}

/*[CONSTRUCTOR MARKER END]*/
}