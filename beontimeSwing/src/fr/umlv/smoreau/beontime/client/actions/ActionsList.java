package fr.umlv.smoreau.beontime.client.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import fr.umlv.smoreau.beontime.client.actions.authentication.*;
import fr.umlv.smoreau.beontime.client.actions.availability.*;
import fr.umlv.smoreau.beontime.client.actions.element.*;
import fr.umlv.smoreau.beontime.client.actions.group.*;
import fr.umlv.smoreau.beontime.client.actions.timetable.*;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.*;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.*;
import fr.umlv.smoreau.beontime.client.actions.toolbar.ManageButtons;
import fr.umlv.smoreau.beontime.client.actions.user.*;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ActionsList {
	private static final HashMap ACTIONS;
	
	static {
	    ACTIONS = new HashMap();
	    ACTIONS.put("ViewTimetable", new ViewTimetable(null));
	    ACTIONS.put("CloseTimetable", new CloseTimetable(null));
		ACTIONS.put("PrintTimetable", new PrintTimetable(null));
		ACTIONS.put("ExportTimetable", new ExportTimetable(null));
		ACTIONS.put("Quit", new Quit(null));
		ACTIONS.put("CutCourse", new CutCourse(null));
		ACTIONS.put("CopyCourse", new CopyCourse(null));
		ACTIONS.put("PasteCourse", new PasteCourse(null));
		ACTIONS.put("ShowTimetableByWeek", new ShowTimetableByWeek(null));
		ACTIONS.put("ShowTimetableBySixMonthPeriod", new ShowTimetableBySixMonthPeriod(null));
		ACTIONS.put("AddUser", new AddUser(null));
		ACTIONS.put("ManageUsers", new ManageUsers(null));
		ACTIONS.put("AddSubject", new AddSubject(null));
		ACTIONS.put("ModifySubject", new ModifySubject(null));
		ACTIONS.put("RemoveSubject", new RemoveSubject(null));
		ACTIONS.put("ManageSubjects", new ManageSubjects(null));
		ACTIONS.put("AddCourse", new AddCourse(null));
		ACTIONS.put("ModifyCourse", new ModifyCourse(null));
		ACTIONS.put("RemoveCourse", new RemoveCourse(null));
		ACTIONS.put("AddGroup", new AddGroup(null));
		ACTIONS.put("ModifyGroup", new ModifyGroup(null));
		ACTIONS.put("RemoveGroup", new RemoveGroup(null));
		ACTIONS.put("ManageGroups", new ManageGroups(null));
		ACTIONS.put("GenerateGroups", new GenerateGroups(null));
		ACTIONS.put("AddRoom", new AddRoom(null));
		ACTIONS.put("ManageRooms", new ManageRooms(null));
		ACTIONS.put("AddMaterial", new AddMaterial(null));
		ACTIONS.put("ManageMaterials", new ManageMaterials(null));
		ACTIONS.put("AddUnavailability", new AddUnavailability(null));
		ACTIONS.put("ManageUnavailabilities", new ManageUnavailabilities(null));
		ACTIONS.put("SearchAvailability", new SearchAvailability(null));
		ACTIONS.put("ManageButtons", new ManageButtons(null));
	}
	
	public static ArrayList getActions(MainFrame mainFrame) {
	    ArrayList list = new ArrayList();
	    for (Iterator i = ACTIONS.values().iterator(); i.hasNext(); ) {
	        Action action = (Action) i.next();
	        action.setMainFrame(mainFrame);
	        list.add(action);
	    }
	    return list;
	}
	
	public static Action getAction(String name, MainFrame mainFrame) {
	    Action action = (Action) ACTIONS.get(name);
	    if (action != null)
	        action.setMainFrame(mainFrame);
	    return action;
	}
}
