package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * RMI interface for the Group DAO
 * @author BeOnTime team
 */
public interface GroupDao extends Remote {
    public static final String JOIN_STUDENTS = "Students";
    public static final String JOIN_SUBJECTS_COURSES_TAKEPART = "SubjectsCoursesTakePart";

	public Collection getGroups(GroupFilter filter) throws RemoteException, HibernateException;
	
	public Collection getGroups() throws RemoteException, HibernateException;
	
	public Group getGroup(Group group, String[] join) throws RemoteException, HibernateException;
	
	public Group addGroup(Group group) throws RemoteException, HibernateException;
	
	public void modifyGroup(Group group) throws RemoteException, HibernateException;
	
	public void removeGroup(Group group) throws RemoteException, HibernateException;

    
    public void addChangeListener(ChangeListener listener) throws RemoteException;

    public void removeChangeListener(ChangeListener listener) throws RemoteException;
}
