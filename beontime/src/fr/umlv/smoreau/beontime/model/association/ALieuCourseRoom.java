package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.base.BaseALieuCourseRoom;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * This is the object class that relates to the A_Lieu_Cours_Local table.
 * Any customizations belong here.
 */
public class ALieuCourseRoom extends BaseALieuCourseRoom {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ALieuCourseRoom () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ALieuCourseRoom (
		Room _idLocal,
		Course _idCours) {

		super (
			_idLocal,
			_idCours);
	}
/*[CONSTRUCTOR MARKER END]*/
}