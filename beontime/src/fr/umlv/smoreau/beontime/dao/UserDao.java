/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import fr.umlv.smoreau.beontime.model.user.*;

/**
 * @author BeOnTime
 */
public class UserDao {
    private static final UserDao INSTANCE = new UserDao();
    
    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }


	public Collection getUsers(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getAdministrators(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getSecretaries(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getStudents(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getTeachers(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}

	public Collection getUsers() {
		return getUsers(null);
	}
	
	public Collection getAdministrators() {
		return getAdministrators(null);
	}
	
	public Collection getSecretaries() {
		return getSecretaries(null);
	}
	
	public Collection getStudents() {
		return getStudents(null);
	}
	
	public Collection getTeachers() {
		return getTeachers(null);
	}
	
	public void addUser(User user) {
		//TODO � impl�menter
	}
	
	public void addSecretary(Secretary secretary) {
		//TODO � impl�menter
	}
	
	public void addTeacher(Teacher teacher) {
		//TODO � impl�menter
	}
	
	public void modifyUser(User user) {
		//TODO � impl�menter
	}
	
	public void modifySecretary(Secretary secretary) {
		//TODO � impl�menter
	}
	
	public void modifyTeacher(Teacher teacher) {
		//TODO � impl�menter
	}
	
	public void removeUser(User user) {
		//TODO � impl�menter
	}
	
	public void removeSecretary(Secretary secretary) {
		//TODO � impl�menter
	}
	
	public void removeTeacher(Teacher teacher) {
		//TODO � impl�menter
	}
}
