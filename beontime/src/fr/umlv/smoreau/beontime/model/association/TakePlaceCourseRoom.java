package fr.umlv.smoreau.beontime.model.association;

import fr.umlv.smoreau.beontime.model.base.BaseTakePlaceCourseRoom;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * This is the object class that relates to the A_Lieu_Cours_Local table.
 * Any customizations belong here.
 */
public class TakePlaceCourseRoom extends BaseTakePlaceCourseRoom {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TakePlaceCourseRoom () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TakePlaceCourseRoom (
		Room _idRoom,
		Course _idCourse) {

		super (
			_idRoom,
			_idCourse);
	}
/*[CONSTRUCTOR MARKER END]*/
}