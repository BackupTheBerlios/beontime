package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import fr.umlv.smoreau.beontime.filter.CourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.timetable.*;

/**
 * RMI interface for the TimeTable DAO
 * @author BeOnTime team
 */
public interface TimetableDao extends Remote {
	
	public Collection getCourses(CourseFilter filter) throws RemoteException;
	
	public Collection getSubjects(SubjectFilter filter) throws RemoteException;

	public Timetable getTimetable(TimetableFilter filter) throws RemoteException;

	public Collection getCourses() throws RemoteException;

	public Collection getSubjects() throws RemoteException;
	
	public boolean addCourse(Course course) throws RemoteException;
	
	public boolean addSubject(Subject subject) throws RemoteException;
	
	public boolean modifyCourse(Course course) throws RemoteException;
	
	public boolean modifySubject(Subject subject) throws RemoteException;
	
	public boolean removeCourse(Course course) throws RemoteException;
	
	public boolean removeSubject(Subject subject) throws RemoteException;


	public Collection getTypesCourse() throws RemoteException;
	
}
