package fr.umlv.smoreau.beontime.client.graphics.event;

import java.util.*;

/**
 * @author BeOnTime
 */
public interface BoTListener extends EventListener {
	public void refreshAll(BoTEvent e) throws InterruptedException;
	
	public void addCourse(BoTEvent e) throws InterruptedException;
	
	public void modifyCourse(BoTEvent e) throws InterruptedException;
	
	public void removeCourse(BoTEvent e) throws InterruptedException;
	
	public void addSubject(BoTEvent e) throws InterruptedException;
	
	public void modifySubject(BoTEvent e) throws InterruptedException;
	
	public void removeSubject(BoTEvent e) throws InterruptedException;
	
	public void addUser(BoTEvent e) throws InterruptedException;
	
	public void modifyUser(BoTEvent e) throws InterruptedException;
	
	public void removeUser(BoTEvent e) throws InterruptedException;
}
