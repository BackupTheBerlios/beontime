package fr.umlv.smoreau.beontime.model.base;

import java.io.Serializable;

import fr.umlv.smoreau.beontime.model.user.User;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Formation table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Formation"
 */
public abstract class BaseFormation implements Serializable {

	public static String PROP_ID_SECRETAIRE = "IdSecretary";
	public static String PROP_ID_FORMATION = "IdFormation";
	public static String PROP_ID_ENSEIGNANT = "IdTeacher";
	public static String PROP_INTITULE = "Heading";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idFormation;

	// fields
	private java.lang.String _heading;
	private java.lang.Long _idTeacher;


	// many to one
	private User _idSecretary;


	// collections
	private java.util.Set _groups;
	private java.util.Set _subjects;


	// constructors
	public BaseFormation () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseFormation (java.lang.Long _idFormation) {
		this.setIdFormation(_idFormation);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseFormation (
		java.lang.Long _idFormation,
		User _idSecretary,
		java.lang.Long _idTeacher) {

		this.setIdFormation(_idFormation);
		this.setIdSecretary(_idSecretary);
		this.setIdTeacher(_idTeacher);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="vm"
     *  column="ID_formation"
     */
	public java.lang.Long getIdFormation () {
		return _idFormation;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _idFormation the new ID
	 */
	public void setIdFormation (java.lang.Long _idFormation) {
		this._idFormation = _idFormation;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: Intitule
	 */
	public java.lang.String getHeading () {
		return _heading;
	}

	/**
	 * Set the value related to the column: Intitule
	 * @param _heading the Intitule value
	 */
	public void setHeading (java.lang.String _heading) {
		this._heading = _heading;
	}


	/**
     * @hibernate.property
     *  column=ID_secretaire
	 * not-null=true
	 */
	public User getIdSecretary () {
		return this._idSecretary;
	}

	/**
	 * Set the value related to the column: ID_secretaire
	 * @param _idSecretary the ID_secretaire value
	 */
	public void setIdSecretary (User _idSecretary) {
		this._idSecretary = _idSecretary;
	}


	/**
     * @hibernate.property
     *  column=ID_enseignant
	 * not-null=true
	 */
	public java.lang.Long getIdTeacher () {
		return this._idTeacher;
	}

	/**
	 * Set the value related to the column: ID_enseignant
	 * @param _idTeacher the ID_enseignant value
	 */
	public void setIdTeacher (java.lang.Long _idTeacher) {
		this._idTeacher = _idTeacher;
	}


	/**
	 * Return the value associated with the column: GroupeSet
	 */
	public java.util.Set getGroups () {
		return this._groups;
	}

	/**
	 * Set the value related to the column: GroupeSet
	 * @param _groups the GroupeSet value
	 */
	public void setGroups (java.util.Set _groups) {
		this._groups = _groups;
	}
	
	public void addGroup (Object obj) {
		if (null == this._groups) this._groups = new java.util.HashSet();
		this._groups.add(obj);
	}



	/**
	 * Return the value associated with the column: MatiereSet
	 */
	public java.util.Set getSubjects () {
		return this._subjects;
	}

	/**
	 * Set the value related to the column: MatiereSet
	 * @param _subjects the MatiereSet value
	 */
	public void setSubjects (java.util.Set _subjects) {
		this._subjects = _subjects;
	}
	
	public void addSubject (Object obj) {
		if (null == this._subjects) this._subjects = new java.util.HashSet();
		this._subjects.add(obj);
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof fr.umlv.smoreau.beontime.model.base.BaseFormation)) return false;
		else {
			fr.umlv.smoreau.beontime.model.base.BaseFormation mObj = (fr.umlv.smoreau.beontime.model.base.BaseFormation) obj;
			if (null == this.getIdFormation() || null == mObj.getIdFormation()) return false;
			else return (this.getIdFormation().equals(mObj.getIdFormation()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdFormation()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdFormation().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}