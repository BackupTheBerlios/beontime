package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
import fr.umlv.smoreau.beontime.filter.CourseFilter;
import fr.umlv.smoreau.beontime.filter.SubjectFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.timetable.*;

/**
 * RMI interface for the TimeTable DAO
 * @author BeOnTime team
 */
public interface TimetableDao extends Remote {
    public static final String[] TYPES_COURSES = { "cours magistraux", "travaux dirigés", "travaux pratiques" };
    
    public static final String JOIN_GROUPS_SUBJECTS = "GroupsSubjectsTakingPart";
    public static final String JOIN_TEACHERS_DIRECTING = "TeachersDirecting";
    public static final String JOIN_MATERIALS = "Materials";
    public static final String JOIN_ROOMS = "Rooms";
	
	public Collection getCourses(CourseFilter filter) throws RemoteException, HibernateException;
	
	public Collection getSubjects(SubjectFilter filter) throws RemoteException, HibernateException;
	
	public Timetable getTimetable(TimetableFilter filter) throws RemoteException, HibernateException;

	public Collection getCourses() throws RemoteException, HibernateException;
	
	public Course getCourse(Course course, String[] join) throws RemoteException, HibernateException;

	public Collection getSubjects() throws RemoteException, HibernateException;
	
	public Course addCourse(Course course) throws RemoteException, HibernateException;
	
	public Subject addSubject(Subject subject) throws RemoteException, HibernateException;
	
	public void modifyCourse(Course course) throws RemoteException, HibernateException;
	
	public void modifySubject(Subject subject) throws RemoteException, HibernateException;
	
	public void removeCourse(Course course) throws RemoteException, HibernateException;
	
	public void removeSubject(Subject subject) throws RemoteException, HibernateException;


	public Collection getTypesCourse() throws RemoteException, HibernateException;
	
	public void removeLinkBetweenCourseAndTeacher(IsDirectedByCourseTeacher link) throws RemoteException, HibernateException;
}
