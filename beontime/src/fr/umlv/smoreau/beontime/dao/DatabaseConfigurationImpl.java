package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import fr.umlv.smoreau.beontime.DatabasesProperties;
import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.LdapManager;
import fr.umlv.smoreau.beontime.model.Database;

/**
 * RMI implementation for the Database Configuration
 * @author BeOnTime team
 */
public class DatabaseConfigurationImpl extends UnicastRemoteObject implements DatabaseConfiguration {
	// Attention : en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static DatabaseConfiguration INSTANCE;
    static {
    	try {
			INSTANCE= new DatabaseConfigurationImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation de Database Configuration");
			System.exit(1);
		}
    }
    
    private DatabaseConfigurationImpl() throws RemoteException {
    }

    public static DatabaseConfiguration getInstance() {
        return INSTANCE;
    }


	public Database getLDAPDatabase() throws RemoteException {
	    String dnBase = DatabasesProperties.getLdapDNBase();
	    String host = DatabasesProperties.getLdapHost();
	    String port = DatabasesProperties.getLdapPort();

		return new Database(dnBase, host, port);
	}
	
	public Database getSQLDatabase() throws RemoteException {
	    String baseName = DatabasesProperties.getSqlDatabaseName();
	    String host = DatabasesProperties.getSqlHost();
	    String port = DatabasesProperties.getSqlPort();
	    String login = DatabasesProperties.getSqlUserName();
	    String password = DatabasesProperties.getSqlPassword();

		return new Database(baseName, host, port, login, password);
	}
	
	public void modifyDatabase(Database database) throws RemoteException {
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
	
	public boolean testDatabase(Database database) throws RemoteException {
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
