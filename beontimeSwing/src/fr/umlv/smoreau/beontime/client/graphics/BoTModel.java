package fr.umlv.smoreau.beontime.client.graphics;

import javax.swing.event.EventListenerList;

import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
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
		BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, timetable);
				((BoTListener) listeners[i + 1]).refreshAll(event);
			}
		}
	}
	
	public void fireRefreshCourse(Course course, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, course);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addCourse(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyCourse(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeCourse(event);
			}
		}
	}
	
	public void fireRefreshSubject(Subject subject, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, subject);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addSubject(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifySubject(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeSubject(event);
			}
		}
	}
}
