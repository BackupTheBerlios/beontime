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
		//TODO à implémenter
		return null;
	}
	
	public Database getSQLDatabase() {
		//TODO à implémenter
		return null;
	}
	
	public void modifyLDAPDatabase(Database database) {
		//TODO à implémenter
	}
	
	public void modifySQLDatabase(Database database) {
		//TODO à implémenter
	}
	
	public boolean testLDAPDatabase(Database database) {
		//TODO à implémenter
		return false;
	}

	public boolean testSQLDatabase(Database database) {
		//TODO à implémenter
		return false;
	}
}
