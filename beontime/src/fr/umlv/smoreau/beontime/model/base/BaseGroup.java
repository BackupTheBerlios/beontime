package fr.umlv.smoreau.beontime.model.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Groupe table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Groupe"
 */
public abstract class BaseGroup  implements Serializable {

	public static String PROP_ID_GROUPE = "IdGroupe";
	public static String PROP_ID_FORMATION = "IdFormation";
	public static String PROP_INTITULE = "Intitule";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idGroupe;

	// fields
	private java.lang.String _intitule;

	// many to one
	private java.lang.Long _idFormation;

	// collections
	private java.util.Set _participeGroupeMatiereCoursSet;


	// constructors
	public BaseGroup () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseGroup (java.lang.Long _idGroupe) {
		this.setIdGroupe(_idGroupe);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseGroup (
		java.lang.Long _idGroupe,
		java.lang.Long _idFormation) {

		this.setIdGroupe(_idGroupe);
		this.setIdFormation(_idFormation);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="vm"
     *  column="ID_groupe"
     */
	public java.lang.Long getIdGroupe () {
		return _idGroupe;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _idGroupe the new ID
	 */
	public void setIdGroupe (java.lang.Long _idGroupe) {
		this._idGroupe = _idGroupe;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: Intitule
	 */
	public java.lang.String getIntitule () {
		return _intitule;
	}

	/**
	 * Set the value related to the column: Intitule
	 * @param _intitule the Intitule value
	 */
	public void setIntitule (java.lang.String _intitule) {
		this._intitule = _intitule;
	}


	/**
     * @hibernate.property
     *  column=ID_formation
	 * not-null=true
	 */
	public java.lang.Long getIdFormation () {
		return this._idFormation;
	}

	/**
	 * Set the value related to the column: ID_formation
	 * @param _idFormation the ID_formation value
	 */
	public void setIdFormation (java.lang.Long _idFormation) {
		this._idFormation = _idFormation;
	}


	/**
	 * Return the value associated with the column: ParticipeGroupeMatiereCoursSet
	 */
	public java.util.Set getParticipeGroupeMatiereCoursSet () {
		return this._participeGroupeMatiereCoursSet;
	}

	/**
	 * Set the value related to the column: ParticipeGroupeMatiereCoursSet
	 * @param _participeGroupeMatiereCoursSet the ParticipeGroupeMatiereCoursSet value
	 */
	public void setParticipeGroupeMatiereCoursSet (java.util.Set _participeGroupeMatiereCoursSet) {
		this._participeGroupeMatiereCoursSet = _participeGroupeMatiereCoursSet;
	}
	
	public void addToParticipeGroupeMatiereCoursSet (Object obj) {
		if (null == this._participeGroupeMatiereCoursSet) this._participeGroupeMatiereCoursSet = new java.util.HashSet();
		this._participeGroupeMatiereCoursSet.add(obj);
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof fr.umlv.smoreau.beontime.model.base.BaseGroup)) return false;
		else {
			fr.umlv.smoreau.beontime.model.base.BaseGroup mObj = (fr.umlv.smoreau.beontime.model.base.BaseGroup) obj;
			if (null == this.getIdGroupe() || null == mObj.getIdGroupe()) return false;
			else return (this.getIdGroupe().equals(mObj.getIdGroupe()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdGroupe()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdGroupe().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}