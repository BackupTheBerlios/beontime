/*
 * 
 */
package fr.umlv.smoreau.beontime.client;

import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * @author BeOnTime
 */
public class AuthenticationManager {
	private Person user;
	
	public AuthenticationManager() {
		this.user = null;
	}
	
	public boolean connect(String login, String password) {
		
		return true;
	}
	
	public void disconnect() {
		
	}
}
