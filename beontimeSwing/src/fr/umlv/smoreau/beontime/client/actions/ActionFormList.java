package fr.umlv.smoreau.beontime.client.actions;

import java.util.ArrayList;

import fr.umlv.smoreau.beontime.client.actions.element.AddMaterial;
import fr.umlv.smoreau.beontime.client.actions.element.AddRoom;
import fr.umlv.smoreau.beontime.client.actions.group.GenerateGroups;
import fr.umlv.smoreau.beontime.client.actions.timetable.ExportTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.PrintTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.ShowTimetableBySixMonthPeriod;
import fr.umlv.smoreau.beontime.client.actions.timetable.ShowTimetableByWeek;
import fr.umlv.smoreau.beontime.client.actions.timetable.ViewTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.AddCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.CopyCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.CutCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.PasteCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.RemoveCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.user.AddUser;

/**
 * @author BeOnTime
 */
public class ActionFormList {
	public static final ArrayList ACTIONS;
	
	static {
	    ACTIONS = new ArrayList();
	    ACTIONS.add(new ViewTimetable(null));
		ACTIONS.add(new PrintTimetable(null));
		ACTIONS.add(new ExportTimetable(null));
		ACTIONS.add(new CutCourse(null));
		ACTIONS.add(new CopyCourse(null));
		ACTIONS.add(new PasteCourse(null));
		ACTIONS.add(new AddUser(null));
		ACTIONS.add(new AddSubject(null));
		ACTIONS.add(new AddRoom(null));
		ACTIONS.add(new AddMaterial(null));
		ACTIONS.add(new GenerateGroups(null));
		ACTIONS.add(new AddCourse(null));
		ACTIONS.add(new RemoveCourse(null));
		ACTIONS.add(new ShowTimetableByWeek(null));
		ACTIONS.add(new ShowTimetableBySixMonthPeriod(null));
	}
}
