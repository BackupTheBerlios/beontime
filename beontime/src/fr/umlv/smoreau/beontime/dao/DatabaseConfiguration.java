package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 25 febr. 2005 */


import java.rmi.Remote;
import java.rmi.RemoteException;

import fr.umlv.smoreau.beontime.model.Database;

/**
 * RMI interface for the Database Configuration
 * @author BeOnTime team
 */
public interface DatabaseConfiguration extends Remote {
 //   private static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration();
    
//    public static DatabaseConfiguration getInstance() throws RemoteException;

	public Database getLDAPDatabase() throws RemoteException;
	
	public Database getSQLDatabase() throws RemoteException;
	
	public void modifyDatabase(Database database) throws RemoteException;
	
	public boolean testDatabase(Database database) throws RemoteException;
}
