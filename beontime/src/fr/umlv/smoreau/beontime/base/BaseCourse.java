package fr.umlv.smoreau.beontime.base;

import java.io.Serializable;

import fr.umlv.smoreau.beontime.model.timetable.TypeCourse;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Cours table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Cours"
 */
public abstract class BaseCourse  implements Serializable {

	public static String PROP_DATE_DEBUT = "DateDebut";
	public static String PROP_ID_COURS = "IdCours";
	public static String PROP_ID_FORMATION = "IdFormation";
	public static String PROP_ID_TYPE_COURS = "IdTypeCours";
	public static String PROP_NB_SEMAINE = "NbSemaine";
	public static String PROP_DATE_FIN = "DateFin";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idCours;

	// fields
	private java.lang.Long _idFormation;
	private java.lang.Integer _nbSemaine;
	private java.util.Date _dateFin;
	private java.util.Date _dateDebut;

	// many to one
	private TypeCourse _idTypeCours;

	// collections
	private java.util.Set _participeGroupeMatiereCoursSet;
	private java.util.Set _personneSet;
	private java.util.Set _materielSet;
	private java.util.Set _localSet;


	// constructors
	public BaseCourse () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCourse (java.lang.Long _idCours) {
		this.setIdCours(_idCours);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCourse (
		java.lang.Long _idCours,
		TypeCourse _idTypeCours,
		java.lang.Long _idFormation) {

		this.setIdCours(_idCours);
		this.setIdTypeCourse(_idTypeCours);
		this.setIdFormation(_idFormation);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="vm"
     *  column="ID_cours"
     */
	public java.lang.Long getIdCours () {
		return _idCours;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _idCours the new ID
	 */
	public void setIdCours (java.lang.Long _idCours) {
		this._idCours = _idCours;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: ID_formation
	 */
	public java.lang.Long getIdFormation () {
		return _idFormation;
	}

	/**
	 * Set the value related to the column: ID_formation
	 * @param _idFormation the ID_formation value
	 */
	public void setIdFormation (java.lang.Long _idFormation) {
		this._idFormation = _idFormation;
	}


	/**
	 * Return the value associated with the column: Nb_semaine
	 */
	public java.lang.Integer getNbSemaine () {
		return _nbSemaine;
	}

	/**
	 * Set the value related to the column: Nb_semaine
	 * @param _nbSemaine the Nb_semaine value
	 */
	public void setNbSemaine (java.lang.Integer _nbSemaine) {
		this._nbSemaine = _nbSemaine;
	}


	/**
	 * Return the value associated with the column: Date_fin
	 */
	public java.util.Date getDateFin () {
		return _dateFin;
	}

	/**
	 * Set the value related to the column: Date_fin
	 * @param _dateFin the Date_fin value
	 */
	public void setDateFin (java.util.Date _dateFin) {
		this._dateFin = _dateFin;
	}


	/**
	 * Return the value associated with the column: Date_debut
	 */
	public java.util.Date getDateDebut () {
		return _dateDebut;
	}

	/**
	 * Set the value related to the column: Date_debut
	 * @param _dateDebut the Date_debut value
	 */
	public void setDateDebut (java.util.Date _dateDebut) {
		this._dateDebut = _dateDebut;
	}


	/**
     * @hibernate.property
     *  column=ID_type_cours
	 * not-null=true
	 */
	public TypeCourse getIdTypeCourse () {
		return this._idTypeCours;
	}

	/**
	 * Set the value related to the column: ID_type_cours
	 * @param _idTypeCours the ID_type_cours value
	 */
	public void setIdTypeCourse (TypeCourse _idTypeCours) {
		this._idTypeCours = _idTypeCours;
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



	/**
	 * Return the value associated with the column: PersonneSet
	 */
	public java.util.Set getPersonneSet () {
		return this._personneSet;
	}

	/**
	 * Set the value related to the column: PersonneSet
	 * @param _personneSet the PersonneSet value
	 */
	public void setPersonneSet (java.util.Set _personneSet) {
		this._personneSet = _personneSet;
	}
	
	public void addToPersonneSet (Object obj) {
		if (null == this._personneSet) this._personneSet = new java.util.HashSet();
		this._personneSet.add(obj);
	}



	/**
	 * Return the value associated with the column: MaterielSet
	 */
	public java.util.Set getMaterielSet () {
		return this._materielSet;
	}

	/**
	 * Set the value related to the column: MaterielSet
	 * @param _materielSet the MaterielSet value
	 */
	public void setMaterielSet (java.util.Set _materielSet) {
		this._materielSet = _materielSet;
	}
	
	public void addToMaterielSet (Object obj) {
		if (null == this._materielSet) this._materielSet = new java.util.HashSet();
		this._materielSet.add(obj);
	}



	/**
	 * Return the value associated with the column: LocalSet
	 */
	public java.util.Set getLocalSet () {
		return this._localSet;
	}

	/**
	 * Set the value related to the column: LocalSet
	 * @param _localSet the LocalSet value
	 */
	public void setLocalSet (java.util.Set _localSet) {
		this._localSet = _localSet;
	}
	
	public void addToLocalSet (Object obj) {
		if (null == this._localSet) this._localSet = new java.util.HashSet();
		this._localSet.add(obj);
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof fr.umlv.smoreau.beontime.base.BaseCourse)) return false;
		else {
			fr.umlv.smoreau.beontime.base.BaseCourse mObj = (fr.umlv.smoreau.beontime.base.BaseCourse) obj;
			if (null == this.getIdCours() || null == mObj.getIdCours()) return false;
			else return (this.getIdCours().equals(mObj.getIdCours()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdCours()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdCours().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}