package fr.umlv.smoreau.beontime.client.graphics.event;

import java.util.EventObject;

import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class BoTEvent extends EventObject {
	private Timetable timetable;
	private Course course;
	private Subject subject;

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
	
	public Timetable getTimetable() {
	    return timetable;
	}
	
	public Course getCourse() {
	    return course;
	}
	
	public Subject getSubject() {
	    return subject;
	}
}