/*
 * 
 */
package fr.umlv.smoreau.beontime;

import java.util.Collection;

import fr.umlv.smoreau.beontime.dao.FormationFilter;
import fr.umlv.smoreau.beontime.dao.UserFilter;

/**
 * @author BeOnTime
 */
public class LdapManager {
	public static Collection getUsers(UserFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public static Collection getAdministrators(UserFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public static Collection getStudents(UserFilter filter) {
		//TODO à implémenter
		return null;
	}
		
	public static Collection getTeachers(UserFilter filter) {
		//TODO à implémenter
		return null;
	}

	public static Collection getUsers() {
		return getUsers(null);
	}
	
	public static Collection getAdministrators() {
		return getAdministrators(null);
	}
	
	public static Collection getStudents() {
		return getStudents(null);
	}

	public static Collection getTeachers() {
		return getTeachers(null);
	}
	
	public static Collection getFormations(FormationFilter filter) {
		//TODO à implémenter
		return null;
	}

	public static Collection getFormations() {
		return getFormations(null);
	}
}
