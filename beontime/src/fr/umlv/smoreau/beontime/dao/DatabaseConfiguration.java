/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.Database;

/**
 * @author BeOnTime
 */
public class DatabaseConfiguration {
    private static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration();
    
    private DatabaseConfiguration() {
    }

    public static DatabaseConfiguration getInstance() {
        return INSTANCE;
    }


	public Database getLDAPDatabase() {
		//TODO � impl�menter
		return null;
	}
	
	public Database getSQLDatabase() {
		//TODO � impl�menter
		return null;
	}
	
	public void modifyLDAPDatabase(Database database) {
		//TODO � impl�menter
	}
	
	public void modifySQLDatabase(Database database) {
		//TODO � impl�menter
	}
	
	public boolean testLDAPDatabase(Database database) {
		//TODO � impl�menter
		return false;
	}

	public boolean testSQLDatabase(Database database) {
		//TODO � impl�menter
		return false;
	}
}
