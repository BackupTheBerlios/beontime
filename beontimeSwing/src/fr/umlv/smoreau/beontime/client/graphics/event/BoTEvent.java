package fr.umlv.smoreau.beontime.client.graphics.event;

import java.util.EventObject;

import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class BoTEvent extends EventObject {
	private Timetable timetable;
	private Course course;
	private Subject subject;
	private User user;
	private Room room;
	private Material material;
	private Group group;
	private Unavailability unavailability;
	private boolean initTimetableViewPanel;
	
	public BoTEvent(Object source, boolean initTimetableViewPanel) {
		super(source);
		this.initTimetableViewPanel = initTimetableViewPanel;
	}

	public BoTEvent(Object source, Timetable timetable) {
		super(source);
		this.timetable = timetable;
	}
	
	public BoTEvent(Object source, Course course) {
		super(source);
		this.course = course;
	}
	
	public BoTEvent(Object source, Subject subject) {
		super(source);
		this.subject = subject;
	}
	
	public BoTEvent(Object source, User user) {
		super(source);
		this.user = user;
	}
	
	public BoTEvent(Object source, Room room) {
		super(source);
		this.room = room;
	}
	
	public BoTEvent(Object source, Material material) {
		super(source);
		this.material = material;
	}
	
	public BoTEvent(Object source, Group group) {
		super(source);
		this.group = group;
	}
	
	public BoTEvent(Object source, Unavailability unavailability) {
		super(source);
		this.unavailability = unavailability;
	}
	
	public Timetable getTimetable() {
	    return timetable;
	}
	
	public Course getCourse() {
	    return course;
	}
	
	public Subject getSubject() {
	    return subject;
	}
	
	public User getUser() {
	    return user;
	}
	
	public Room getRoom() {
	    return room;
	}
	
	public Material getMaterial() {
	    return material;
	}
	
	public Group getGroup() {
	    return group;
	}
	
	public Unavailability getUnavailability() {
	    return unavailability;
	}
	
	public boolean isInitTimetableViewPanel() {
	    return initTimetableViewPanel;
	}
}