package fr.umlv.smoreau.beontime.model.base;

import java.io.Serializable;

import fr.umlv.smoreau.beontime.model.timetable.CourseType;


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

	public static String PROP_DATE_DEBUT = "BeginDate";
	public static String PROP_ID_COURS = "IdCourse";
	public static String PROP_ID_FORMATION = "IdFormation";
	public static String PROP_ID_TYPE_COURS = "IdCourseType";
	public static String PROP_NB_SEMAINE = "NbWeeks";
	public static String PROP_DATE_FIN = "EndDate";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idCourse;

	// fields
	private java.lang.Long _idFormation;
	private java.lang.Integer _nbWeeks;
	private java.util.Calendar _endDate;
	private java.util.Calendar _beginDate;

	// many to one
	private CourseType _idCourseType;

	// collections
	private java.util.Set _groupsSubjectsTakingPart;
	private java.util.Set _teachersDirecting;
	private java.util.Set _materials;
	private java.util.Set _rooms;


	// constructors
	public BaseCourse () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCourse (java.lang.Long _idCourse) {
		this.setIdCourse(_idCourse);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCourse (
		java.lang.Long _idCourse,
		CourseType _idCourseType,
		java.lang.Long _idFormation) {

		this.setIdCourse(_idCourse);
		this.setIdCourseType(_idCourseType);
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
	public java.lang.Long getIdCourse () {
		return _idCourse;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _idCourse the new ID
	 */
	public void setIdCourse (java.lang.Long _idCourse) {
		this._idCourse = _idCourse;
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
	public java.lang.Integer getNbWeeks () {
		return _nbWeeks;
	}

	/**
	 * Set the value related to the column: Nb_semaine
	 * @param _nbWeeks the Nb_semaine value
	 */
	public void setNbWeeks (java.lang.Integer _nbWeeks) {
		this._nbWeeks = _nbWeeks;
	}


	/**
	 * Return the value associated with the column: Date_fin
	 */
	public java.util.Calendar getEndDate () {
		return _endDate;
	}

	/**
	 * Set the value related to the column: Date_fin
	 * @param _endDate the Date_fin value
	 */
	public void setEndDate (java.util.Calendar _endDate) {
		this._endDate = _endDate;
	}


	/**
	 * Return the value associated with the column: Date_debut
	 */
	public java.util.Calendar getBeginDate () {
		return _beginDate;
	}

	/**
	 * Set the value related to the column: Date_debut
	 * @param _beginDate the Date_debut value
	 */
	public void setBeginDate (java.util.Calendar _beginDate) {
		this._beginDate = _beginDate;
	}


	/**
     * @hibernate.property
     *  column=ID_type_cours
	 * not-null=true
	 */
	public CourseType getIdCourseType () {
		return this._idCourseType;
	}

	/**
	 * Set the value related to the column: ID_type_cours
	 * @param _idCourseType the ID_type_cours value
	 */
	public void setIdCourseType (CourseType _idCourseType) {
		this._idCourseType = _idCourseType;
	}


	/**
	 * Return the value associated with the column: ParticipeGroupeMatiereCoursSet
	 */
	public java.util.Set getGroupsSubjectsTakingPart () {
		return this._groupsSubjectsTakingPart;
	}

	/**
	 * Set the value related to the column: ParticipeGroupeMatiereCoursSet
	 * @param _groupsSubjectsTakingPart the ParticipeGroupeMatiereCoursSet value
	 */
	public void setGroupsSubjectsTakingPart (java.util.Set _groupsSubjectsTakingPart) {
		this._groupsSubjectsTakingPart = _groupsSubjectsTakingPart;
	}
	
	public void addGroupSubjectTakingPart (Object obj) {
		if (null == this._groupsSubjectsTakingPart) this._groupsSubjectsTakingPart = new java.util.HashSet();
		this._groupsSubjectsTakingPart.add(obj);
	}



	/**
	 * Return the value associated with the column: PersonneSet
	 */
	public java.util.Set getTeachersDirecting () {
		return this._teachersDirecting;
	}

	/**
	 * Set the value related to the column: PersonneSet
	 * @param _teachersDirecting the PersonneSet value
	 */
	public void setTeachersDirecting (java.util.Set _teachersDirecting) {
		this._teachersDirecting = _teachersDirecting;
	}
	
	public void addTeacherDirecting (Object obj) {
		if (null == this._teachersDirecting) this._teachersDirecting = new java.util.HashSet();
		this._teachersDirecting.add(obj);
	}



	/**
	 * Return the value associated with the column: MaterielSet
	 */
	public java.util.Set getMaterials () {
		return this._materials;
	}

	/**
	 * Set the value related to the column: MaterielSet
	 * @param _materials the MaterielSet value
	 */
	public void setMaterials (java.util.Set _materials) {
		this._materials = _materials;
	}
	
	public void addMaterial (Object obj) {
		if (null == this._materials) this._materials = new java.util.HashSet();
		this._materials.add(obj);
	}



	/**
	 * Return the value associated with the column: LocalSet
	 */
	public java.util.Set getRooms () {
		return this._rooms;
	}

	/**
	 * Set the value related to the column: LocalSet
	 * @param _rooms the LocalSet value
	 */
	public void setRooms (java.util.Set _rooms) {
		this._rooms = _rooms;
	}
	
	public void addRoom (Object obj) {
		if (null == this._rooms) this._rooms = new java.util.HashSet();
		this._rooms.add(obj);
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof fr.umlv.smoreau.beontime.model.base.BaseCourse)) return false;
		else {
			fr.umlv.smoreau.beontime.model.base.BaseCourse mObj = (fr.umlv.smoreau.beontime.model.base.BaseCourse) obj;
			if (null == this.getIdCourse() || null == mObj.getIdCourse()) return false;
			else return (this.getIdCourse().equals(mObj.getIdCourse()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdCourse()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdCourse().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}