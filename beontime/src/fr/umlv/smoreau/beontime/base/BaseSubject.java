package fr.umlv.smoreau.beontime.base;

import java.io.Serializable;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.user.Person;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Matiere table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Matiere"
 */
public abstract class BaseSubject  implements Serializable {

	public static String PROP_NB_GROUPE_TP = "NbGroupeTp";
	public static String PROP_NB_HEURE_TD = "NbHeureTd";
	public static String PROP_ID_MATIERE = "IdMatiere";
	public static String PROP_NB_GROUPE_TD = "NbGroupeTd";
	public static String PROP_NB_HEURE_MAG = "NbHeureMag";
	public static String PROP_NB_HEURE_TP = "NbHeureTp";
	public static String PROP_NB_GROUPE_MAG = "NbGroupeMag";
	public static String PROP_ID_FORMATION = "IdFormation";
	public static String PROP_ID_ENSEIGNANT = "IdEnseignant";
	public static String PROP_INTITULE = "Intitule";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idMatiere;

	// fields
	private java.lang.Integer _nbHeureTp;
	private java.lang.Integer _nbHeureMag;
	private java.lang.Integer _nbGroupeTd;
	private java.lang.Integer _nbGroupeMag;
	private java.lang.Integer _nbHeureTd;
	private java.lang.String _intitule;
	private java.lang.Integer _nbGroupeTp;

	// many to one
	private Person _idEnseignant;
	private Formation _idFormation;

	// collections
	private java.util.Set _participeGroupeMatiereCoursSet;


	// constructors
	public BaseSubject () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSubject (java.lang.Long _idMatiere) {
		this.setIdMatiere(_idMatiere);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSubject (
		java.lang.Long _idMatiere,
		Person _idEnseignant,
		Formation _idFormation) {

		this.setIdMatiere(_idMatiere);
		this.setIdTeacher(_idEnseignant);
		this.setIdFormation(_idFormation);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="vm"
     *  column="ID_matiere"
     */
	public java.lang.Long getIdMatiere () {
		return _idMatiere;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _idMatiere the new ID
	 */
	public void setIdMatiere (java.lang.Long _idMatiere) {
		this._idMatiere = _idMatiere;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: NB_heure_TP
	 */
	public java.lang.Integer getNbHeureTp () {
		return _nbHeureTp;
	}

	/**
	 * Set the value related to the column: NB_heure_TP
	 * @param _nbHeureTp the NB_heure_TP value
	 */
	public void setNbHeureTp (java.lang.Integer _nbHeureTp) {
		this._nbHeureTp = _nbHeureTp;
	}


	/**
	 * Return the value associated with the column: NB_heure_MAG
	 */
	public java.lang.Integer getNbHeureMag () {
		return _nbHeureMag;
	}

	/**
	 * Set the value related to the column: NB_heure_MAG
	 * @param _nbHeureMag the NB_heure_MAG value
	 */
	public void setNbHeureMag (java.lang.Integer _nbHeureMag) {
		this._nbHeureMag = _nbHeureMag;
	}


	/**
	 * Return the value associated with the column: NB_groupe_TD
	 */
	public java.lang.Integer getNbGroupeTd () {
		return _nbGroupeTd;
	}

	/**
	 * Set the value related to the column: NB_groupe_TD
	 * @param _nbGroupeTd the NB_groupe_TD value
	 */
	public void setNbGroupeTd (java.lang.Integer _nbGroupeTd) {
		this._nbGroupeTd = _nbGroupeTd;
	}


	/**
	 * Return the value associated with the column: NB_groupe_MAG
	 */
	public java.lang.Integer getNbGroupeMag () {
		return _nbGroupeMag;
	}

	/**
	 * Set the value related to the column: NB_groupe_MAG
	 * @param _nbGroupeMag the NB_groupe_MAG value
	 */
	public void setNbGroupeMag (java.lang.Integer _nbGroupeMag) {
		this._nbGroupeMag = _nbGroupeMag;
	}


	/**
	 * Return the value associated with the column: NB_heure_TD
	 */
	public java.lang.Integer getNbHeureTd () {
		return _nbHeureTd;
	}

	/**
	 * Set the value related to the column: NB_heure_TD
	 * @param _nbHeureTd the NB_heure_TD value
	 */
	public void setNbHeureTd (java.lang.Integer _nbHeureTd) {
		this._nbHeureTd = _nbHeureTd;
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
	 * Return the value associated with the column: NB_groupe_TP
	 */
	public java.lang.Integer getNbGroupeTp () {
		return _nbGroupeTp;
	}

	/**
	 * Set the value related to the column: NB_groupe_TP
	 * @param _nbGroupeTp the NB_groupe_TP value
	 */
	public void setNbGroupeTp (java.lang.Integer _nbGroupeTp) {
		this._nbGroupeTp = _nbGroupeTp;
	}


	/**
     * @hibernate.property
     *  column=ID_enseignant
	 * not-null=true
	 */
	public Person getIdTeacher () {
		return this._idEnseignant;
	}

	/**
	 * Set the value related to the column: ID_enseignant
	 * @param _idEnseignant the ID_enseignant value
	 */
	public void setIdTeacher (Person _idEnseignant) {
		this._idEnseignant = _idEnseignant;
	}


	/**
     * @hibernate.property
     *  column=ID_formation
	 * not-null=true
	 */
	public Formation getIdFormation () {
		return this._idFormation;
	}

	/**
	 * Set the value related to the column: ID_formation
	 * @param _idFormation the ID_formation value
	 */
	public void setIdFormation (Formation _idFormation) {
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
		if (!(obj instanceof fr.umlv.smoreau.beontime.base.BaseSubject)) return false;
		else {
			fr.umlv.smoreau.beontime.base.BaseSubject mObj = (fr.umlv.smoreau.beontime.base.BaseSubject) obj;
			if (null == this.getIdMatiere() || null == mObj.getIdMatiere()) return false;
			else return (this.getIdMatiere().equals(mObj.getIdMatiere()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdMatiere()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdMatiere().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}