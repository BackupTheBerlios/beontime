package fr.umlv.smoreau.beontime.client.graphics;

import javax.swing.event.EventListenerList;

import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTListener;
import fr.umlv.smoreau.beontime.model.Formation;
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
	
	public void fireCloseTimetable() throws InterruptedException {
	    fireCloseTimetable(true);
	}
	
	public void fireCloseTimetable(boolean initTimetableViewPanel) throws InterruptedException {
		BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, initTimetableViewPanel);
				((BoTListener) listeners[i + 1]).closeTimetable(event);
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
	
	public void fireRefreshUser(User user, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, user);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addUser(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyUser(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeUser(event);
			}
		}
	}
	
	public void fireRefreshRoom(Room room, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, room);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addRoom(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyRoom(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeRoom(event);
			}
		}
	}
	
	public void fireRefreshMaterial(Material material, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, material);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addMaterial(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyMaterial(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeMaterial(event);
			}
		}
	}
	
	public void fireRefreshGroup(Group group, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, group);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addGroup(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyGroup(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeGroup(event);
			}
		}
	}
	
	public void fireRefreshUnavailability(Unavailability unavailability, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, unavailability);
				if (type == TYPE_ADD)
				    ((BoTListener) listeners[i + 1]).addUnavailability(event);
				else if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyUnavailability(event);
				else if (type == TYPE_REMOVE)
				    ((BoTListener) listeners[i + 1]).removeUnavailability(event);
			}
		}
	}
	
	public void fireRefreshFormation(Formation formation, int type) throws InterruptedException {
	    BoTEvent event = null;

		Object[] listeners = list.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == BoTListener.class) {
				if (event == null)
					event = new BoTEvent(this, formation);
				if (type == TYPE_MODIFY)
				    ((BoTListener) listeners[i + 1]).modifyFormation(event);
			}
		}
	}
}
