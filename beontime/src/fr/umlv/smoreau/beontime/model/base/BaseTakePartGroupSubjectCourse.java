package fr.umlv.smoreau.beontime.model.base;

import java.io.Serializable;

import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;


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
public abstract class BaseTakePartGroupSubjectCourse implements Serializable {

	public static String PROP_ID_MATIERE = "IdSubject";
	public static String PROP_ID_GROUPE = "IdGroup";
	public static String PROP_ID_COURS = "IdCourse";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long _idSubject;
	private java.lang.Long _idGroup;
	private java.lang.Long _idCourse;

	// constructors
	public BaseTakePartGroupSubjectCourse () {
	    initialize();
	}
	
	/**
	 * Constructor for primary key
	 */
	public BaseTakePartGroupSubjectCourse (
	        java.lang.Long _idSubject,
	        java.lang.Long _idGroup,
	        java.lang.Long _idCourse) {
	    
	    this.setIdSubject(_idSubject);
		this.setIdGroup(_idGroup);
		this.setIdCourse(_idCourse);
		initialize();
	}

	protected void initialize () {}


	public java.lang.Long getIdSubject () {
		return _idSubject;
	}

	public void setIdSubject (java.lang.Long _idSubject) {
		hashCode = Integer.MIN_VALUE;
		this._idSubject = _idSubject;
	}

	public java.lang.Long getIdGroup () {
		return _idGroup;
	}

	public void setIdGroup (java.lang.Long _idGroup) {
		hashCode = Integer.MIN_VALUE;
		this._idGroup = _idGroup;
	}

	public java.lang.Long getIdCourse () {
		return _idCourse;
	}

	public void setIdCourse (java.lang.Long _idCourse) {
		hashCode = Integer.MIN_VALUE;
		this._idCourse = _idCourse;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof TakePartGroupSubjectCourse)) return false;
		else {
			TakePartGroupSubjectCourse mObj = (TakePartGroupSubjectCourse) obj;
			if (null != this.getIdSubject() && null != mObj.getIdSubject()) {
				if (!this.getIdSubject().equals(mObj.getIdSubject())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getIdGroup() && null != mObj.getIdGroup()) {
				if (!this.getIdGroup().equals(mObj.getIdGroup())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getIdCourse() && null != mObj.getIdCourse()) {
				if (!this.getIdCourse().equals(mObj.getIdCourse())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuffer sb = new StringBuffer();
			if (null != this.getIdSubject()) {
				sb.append(this.getIdSubject().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getIdGroup()) {
				sb.append(this.getIdGroup().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getIdCourse()) {
				sb.append(this.getIdCourse().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}