package fr.umlv.smoreau.beontime.base;

import java.io.Serializable;

import fr.umlv.smoreau.beontime.model.association.ParticipeGroupSubjectCoursePK;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Participe_Groupe_Matiere_Cours table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Participe_Groupe_Matiere_Cours"
 */
public abstract class BaseParticipeGroupSubjectCourse  implements Serializable {

	public static String PROP_TYPE_COURS = "TypeCours";
	public static String PROP_ID = "Id";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private ParticipeGroupSubjectCoursePK _id;

	// fields
	private java.lang.String _typeCours;


	// constructors
	public BaseParticipeGroupSubjectCourse () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseParticipeGroupSubjectCourse (ParticipeGroupSubjectCoursePK _id) {
		this.setId(_id);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public ParticipeGroupSubjectCoursePK getId () {
		return _id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _id the new ID
	 */
	public void setId (ParticipeGroupSubjectCoursePK _id) {
		this._id = _id;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: type_cours
	 */
	public java.lang.String getTypeCours () {
		return _typeCours;
	}

	/**
	 * Set the value related to the column: type_cours
	 * @param _typeCours the type_cours value
	 */
	public void setTypeCours (java.lang.String _typeCours) {
		this._typeCours = _typeCours;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof fr.umlv.smoreau.beontime.base.BaseParticipeGroupSubjectCourse)) return false;
		else {
			fr.umlv.smoreau.beontime.base.BaseParticipeGroupSubjectCourse mObj = (fr.umlv.smoreau.beontime.base.BaseParticipeGroupSubjectCourse) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return (this.getId().equals(mObj.getId()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}