package fr.umlv.smoreau.beontime.dao;


import fr.umlv.smoreau.beontime.DatabasesProperties;
import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.LdapManager;
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
	    String dnBase = DatabasesProperties.getLdapDNBase();
	    String host = DatabasesProperties.getLdapHost();
	    String port = DatabasesProperties.getLdapPort();

		return new Database(dnBase, host, port);
	}
	
	public Database getSQLDatabase() {
	    String baseName = DatabasesProperties.getSqlDatabaseName();
	    String host = DatabasesProperties.getSqlHost();
	    String port = DatabasesProperties.getSqlPort();
	    String login = DatabasesProperties.getSqlUserName();
	    String password = DatabasesProperties.getSqlPassword();

		return new Database(baseName, host, port, login, password);
	}
	
	public void modifyDatabase(Database database) {
	    if (database.getHost() != null)
	        DatabasesProperties.setLdapHost(database.getHost());
	    if (database.getPort() != null)
	        DatabasesProperties.setLdapPort(database.getPort());

		if (database.getType() == Database.LDAP) {
		    if (database.getDNBase() != null)
		        DatabasesProperties.setLdapDNBase(database.getDNBase());
		} else if (database.getType() == Database.SQL) {
		    if (database.getLogin() != null)
		        DatabasesProperties.setSqlUserName(database.getLogin());
		    if (database.getPassword() != null)
		        DatabasesProperties.setSqlPassword(database.getPassword());
		    if (database.getBaseName() != null)
		        DatabasesProperties.setSqlDatabaseName(database.getBaseName());
		}

		DatabasesProperties.save();
	}
	
	public boolean testDatabase(Database database) {
		if (database == null)
			return false;

		if (database.getType() == Database.LDAP) {
			return LdapManager.testConnection(database.getHost(), database.getPort(),
					database.getDNBase());			
		} else if (database.getType() == Database.SQL) {
			return Hibernate.testConnection(database.getHost(), database.getPort(),
					database.getBaseName(), database.getLogin(), database.getPassword());
		}

		return false;
	}
}
