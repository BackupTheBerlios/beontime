package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.user.*;

/**
 * RMI interface for the User DAO
 * @author BeOnTime team
 */
public interface UserDao extends Remote {
    public static final String TYPE_TEACHER   = "enseignant";
    public static final String TYPE_STUDENT   = "etudiant";
    public static final String TYPE_SECRETARY = "secretaire";
    public static final String TYPE_ADMIN     = "administrateur";

    public static final String JOIN_FORMATIONS_RESPONSIBLE = "FormationsResponsible";
    public static final String JOIN_FORMATIONS_IN_CHARGE   = "FormationsInCharge";
    public static final String JOIN_SUBJECTS_RESPONSIBLE   = "SubjectsResponsible";
    public static final String JOIN_COURSES_DIRECTED       = "CoursesDirected";

    public Collection getUsers(UserFilter filter) throws RemoteException, HibernateException;
    
    public Collection getUsers(boolean ldap) throws RemoteException, HibernateException;
    
    public Collection getUsers() throws RemoteException, HibernateException;
    
    public User getUser(User user, String[] join) throws RemoteException, HibernateException;
	
	public Collection getAdministrators(UserFilter filter) throws RemoteException, HibernateException;
	
	public Collection getSecretaries(UserFilter filter) throws RemoteException, HibernateException;
	
	public Collection getStudents(UserFilter filter) throws RemoteException, HibernateException;
	
	public Collection getTeachers(UserFilter filter) throws RemoteException, HibernateException;
	
	public Collection getAdministrators() throws RemoteException, HibernateException;
	
	public Collection getSecretaries() throws RemoteException, HibernateException;
	
	public Collection getStudents() throws RemoteException, HibernateException;
	
	public Collection getTeachers() throws RemoteException, HibernateException;

	public Collection getTeachers(boolean ldap) throws RemoteException, HibernateException;
	
	public User addUser(User user) throws RemoteException, HibernateException;
	
	public void modifyUser(User user) throws RemoteException, HibernateException;
	
	public void removeUser(User user) throws RemoteException, HibernateException;
	
	public User testLoginPwd(String login, String password) throws RemoteException, HibernateException;
	
}