package fr.umlv.smoreau.beontime.model.timetable;

import java.awt.Color;
import java.util.HashSet;

import fr.umlv.smoreau.beontime.model.base.BaseSubject;

/**
 * This is the object class that relates to the Matiere table.
 * Any customizations belong here.
 */
public class Subject extends BaseSubject {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Subject () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Subject (java.lang.Long _idSubject) {
		super(_idSubject);
	}

	/**
	 * Constructor for required fields
	 */
	public Subject (
		java.lang.Long _idSubject,
		java.lang.Long _idTeacher,
		java.lang.Long _idFormation) {

		super (
			_idSubject,
			_idTeacher,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
	
	private static final SubjectColor color = new SubjectColor();

	public Color getColor() {
	    return color.getColorAt(getIdSubject().intValue());
	}


	private static class SubjectColor {
		private HashSet colors;
		
		public SubjectColor() {
		    colors = new HashSet();
			
			colors.add(new Color(255, 255, 153));
			colors.add(new Color(255, 255, 102));
			colors.add(new Color(255, 204, 102));
			colors.add(new Color(255, 153, 102));
			colors.add(new Color(255, 204, 153));
			colors.add(new Color(255, 204, 255));
			colors.add(new Color(255, 153, 255));
			colors.add(new Color(255, 153, 153));
			colors.add(new Color(204, 255, 255));
			colors.add(new Color(153, 204, 255));
			colors.add(new Color(204, 204, 255));
			colors.add(new Color(204, 153, 255));
			colors.add(new Color(204, 102, 255));
			colors.add(new Color(204, 255, 153));
			colors.add(new Color(153, 255, 102));
			colors.add(new Color(102, 255, 102));
			colors.add(new Color(204, 204, 102));
			colors.add(new Color(204, 153, 102));
			colors.add(new Color(204, 204, 153));
			colors.add(new Color(204, 204, 204));
		}

		public Color getColorAt(int index){
			if (index > colors.size())
			    return getColorAt(index - colors.size());
			
			return (Color) colors.toArray()[index];
		}
	}
}