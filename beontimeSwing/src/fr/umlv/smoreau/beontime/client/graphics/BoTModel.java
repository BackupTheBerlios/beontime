/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics;

import javax.swing.event.EventListenerList;

import fr.umlv.smoreau.beontime.client.graphics.event.BoTListener;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;


/**
 * @author BeOnTime
 */
public class BoTModel {
	public static final int TYPE_ADD    = 0;
	public static final int TYPE_MODIFY = 1;
	public static final int TYPE_REMOVE = 2;

	private final EventListenerList list;
    private Timetable timetable;
    

	public BoTModel() {
		list = new EventListenerList();
	}

	public EventListenerList getEventListenerList() {
		return list;
	}

	public void addBoTListener(BoTListener l) {
		list.add(BoTListener.class, l);
	}

	public void removeBoTListener(BoTListener l) {
		list.remove(BoTListener.class, l);
	}

	
	public Timetable getTimetable() {
		return timetable;
	}
	
	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}


	public void fireShowTimetable(Timetable timetable) throws InterruptedException {
		
	}
	
	public void fireRefreshCourse(Course course, int type) throws InterruptedException {
		
	}
	
	public void fireRefreshSubject(Subject subject, int type) throws InterruptedException {
	
	}
}
