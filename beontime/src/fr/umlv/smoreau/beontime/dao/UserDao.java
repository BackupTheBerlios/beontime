package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.util.Collection;

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

 //   public static UserDao getInstance() throws java.rmi.RemoteException;

//	private Collection getUsers(UserFilter filter);
	
	public Collection getAdministrators(UserFilter filter) throws java.rmi.RemoteException;
	//TODO virer les throws ?
	
	public Collection getSecretaries(UserFilter filter) throws java.rmi.RemoteException;
	
	public Collection getStudents(UserFilter filter) throws java.rmi.RemoteException;
	
	public Collection getTeachers(UserFilter filter) throws java.rmi.RemoteException;
	
	public Collection getAdministrators() throws java.rmi.RemoteException;
	
	public Collection getSecretaries() throws java.rmi.RemoteException;
	
	public Collection getStudents() throws java.rmi.RemoteException;
	
	public Collection getTeachers() throws java.rmi.RemoteException;
	
	public boolean addUser(User user) throws java.rmi.RemoteException;
	
	public boolean modifyUser(User user) throws java.rmi.RemoteException;
	
	public boolean removeUser(User user) throws java.rmi.RemoteException;
	
	public boolean testLoginPwd(String login, String password) throws java.rmi.RemoteException;
	
}