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
	private static final HashMap ACTIONS_ADMIN;
	
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
		ACTIONS.put("ShowTimetableHorizontal", new ShowTimetableHorizontal(null));
		ACTIONS.put("ShowTimetableVertical", new ShowTimetableVertical(null));
		ACTIONS.put("ShowTimetableByWeek", new ShowTimetableByWeek(null));
		ACTIONS.put("ShowTimetableBySixMonthPeriod", new ShowTimetableBySixMonthPeriod(null));
		ACTIONS.put("AddTeacher", new AddTeacher(null));
		ACTIONS.put("ModifyUser", new ModifyUser(null));
		ACTIONS.put("RemoveUser", new RemoveUser(null));
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
		ACTIONS.put("ModifyUnavailability", new ModifyUnavailability(null));
		ACTIONS.put("RemoveUnavailability", new RemoveUnavailability(null));
		ACTIONS.put("ManageUnavailabilities", new ManageUnavailabilities(null));
		ACTIONS.put("SearchAvailability", new SearchAvailability(null));
		ACTIONS.put("About", new About(null));
		ACTIONS.put("ManageButtons", new ManageButtons(null));
		
		ACTIONS_ADMIN = new HashMap();
		ACTIONS_ADMIN.put("AddAdministrator", new AddAdministrator(null));
		ACTIONS_ADMIN.put("AddSecretary", new AddSecretary(null));
		ACTIONS_ADMIN.put("ModifyDBParameters", new ModifyDBParameters(null));
		ACTIONS_ADMIN.put("ViewAllFunctionalities", new ViewAllFunctionalities(null));
	}
	
	public static void initActions(MainFrame mainFrame) {
	    for (Iterator i = ACTIONS.values().iterator(); i.hasNext(); ) {
	        Action action = (Action) i.next();
	        action.setMainFrame(mainFrame);
	    }
	    for (Iterator i = ACTIONS_ADMIN.values().iterator(); i.hasNext(); ) {
	        Action action = (Action) i.next();
	        action.setMainFrame(mainFrame);
	    }
	    
	    getAction("CloseTimetable").setEnabled(false);
	    getAction("PrintTimetable").setEnabled(false);
	    getAction("ExportTimetable").setEnabled(false);
	    getAction("CutCourse").setEnabled(false);
	    getAction("CopyCourse").setEnabled(false);
	    getAction("PasteCourse").setEnabled(false);
	    getAction("ShowTimetableVertical").setEnabled(false);
	    getAction("ShowTimetableHorizontal").setEnabled(false);
	    getAction("ShowTimetableByWeek").setEnabled(false);
	    getAction("ShowTimetableBySixMonthPeriod").setEnabled(false);
	    getAction("AddSubject").setEnabled(false);
	    getAction("ModifySubject").setEnabled(false);
	    getAction("RemoveSubject").setEnabled(false);
	    getAction("ManageSubjects").setEnabled(false);
	    getAction("AddCourse").setEnabled(false);
	    getAction("ModifyCourse").setEnabled(false);
	    getAction("RemoveCourse").setEnabled(false);
	    getAction("AddGroup").setEnabled(false);
	    getAction("ModifyGroup").setEnabled(false);
	    getAction("RemoveGroup").setEnabled(false);
	    getAction("ManageGroups").setEnabled(false);
	    getAction("GenerateGroups").setEnabled(false);
	    getAction("ModifyUser").setEnabled(false);
	    getAction("RemoveUser").setEnabled(false);
	}
	
	public static ArrayList getActions() {
	    return new ArrayList(ACTIONS.values());
	}
	
	public static Action getAction(String name) {
	    Action action = (Action) ACTIONS.get(name);
	    if (action == null)
	        action = (Action) ACTIONS_ADMIN.get(name);
	    return action;
	}
}
