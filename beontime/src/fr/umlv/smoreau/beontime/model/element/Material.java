package fr.umlv.smoreau.beontime.model.element;

import fr.umlv.smoreau.beontime.base.BaseMaterial;

/**
 * This is the object class that relates to the Materiel table.
 * Any customizations belong here.
 */
public class Material extends BaseMaterial {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Material () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Material (java.lang.Long _idMateriel) {
		super(_idMateriel);
	}

/*[CONSTRUCTOR MARKER END]*/
}