package fr.umlv.smoreau.beontime.model.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Local table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Local"
 */
public abstract class BaseRoom  implements Serializable {

	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_NOM = "Name";
	public static String PROP_ID_LOCAL = "IdRoom";
	public static String PROP_NOM_BATIMENT = "BuildingName";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idRoom;

	// fields
	private java.lang.String _buildingName;
	private java.lang.String _description;
	private java.lang.String _name;

	// collections
	private java.util.Set _courses;


	// constructors
	public BaseRoom () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRoom (java.lang.Long _idRoom) {
		this.setIdRoom(_idRoom);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="vm"
     *  column="ID_local"
     */
	public java.lang.Long getIdRoom () {
		return _idRoom;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _idRoom the new ID
	 */
	public void setIdRoom (java.lang.Long _idRoom) {
		this._idRoom = _idRoom;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: Nom_batiment
	 */
	public java.lang.String getBuildingName () {
		return _buildingName;
	}

	/**
	 * Set the value related to the column: Nom_batiment
	 * @param _buildingName the Nom_batiment value
	 */
	public void setBuildingName (java.lang.String _buildingName) {
		this._buildingName = _buildingName;
	}


	/**
	 * Return the value associated with the column: Description
	 */
	public java.lang.String getDescription () {
		return _description;
	}

	/**
	 * Set the value related to the column: Description
	 * @param _description the Description value
	 */
	public void setDescription (java.lang.String _description) {
		this._description = _description;
	}


	/**
	 * Return the value associated with the column: Nom
	 */
	public java.lang.String getName () {
		return _name;
	}

	/**
	 * Set the value related to the column: Nom
	 * @param _name the Nom value
	 */
	public void setName (java.lang.String _name) {
		this._name = _name;
	}


	/**
	 * Return the value associated with the column: CoursSet
	 */
	public java.util.Set getCourses () {
		return this._courses;
	}

	/**
	 * Set the value related to the column: CoursSet
	 * @param _courses the CoursSet value
	 */
	public void setCourses (java.util.Set _courses) {
		this._courses = _courses;
	}
	
	public void addCourse (Object obj) {
		if (null == this._courses) this._courses = new java.util.HashSet();
		this._courses.add(obj);
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof fr.umlv.smoreau.beontime.model.base.BaseRoom)) return false;
		else {
			fr.umlv.smoreau.beontime.model.base.BaseRoom mObj = (fr.umlv.smoreau.beontime.model.base.BaseRoom) obj;
			if (null == this.getIdRoom() || null == mObj.getIdRoom()) return false;
			else return (this.getIdRoom().equals(mObj.getIdRoom()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getIdRoom()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getIdRoom().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}