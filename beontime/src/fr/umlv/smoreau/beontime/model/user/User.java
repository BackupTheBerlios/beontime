package fr.umlv.smoreau.beontime.model.user;

import fr.umlv.smoreau.beontime.model.base.BaseUser;

/**
 * This is the object class that relates to the Personne table.
 * Any customizations belong here.
 */
public class User extends BaseUser implements Comparable {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public User () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public User (java.lang.Long _idUser) {
		super(_idUser);
	}

	/**
	 * Constructor for required fields
	 */
	public User (
		java.lang.Long _idUser,
		java.lang.String _userType) {

		super (
			_idUser,
			_userType);
	}

/*[CONSTRUCTOR MARKER END]*/
	
	private Long _idFormation;
	
	public void setIdFormation(Long _idFormation) {
	    this._idFormation = _idFormation;
	}
	
	public Long getIdFormation() {
	    return _idFormation;
	}


	public int compareTo(Object obj) {
		User user = (User) obj;
		int comp = getName().toLowerCase().compareTo(user.getName().toLowerCase());
		if (comp == 0)
			return getFirstName().toLowerCase().compareTo(user.getFirstName().toLowerCase());
		return comp;
	}
}