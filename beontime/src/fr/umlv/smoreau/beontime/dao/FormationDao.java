package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * RMI interface for the Formation DAO
 * @author BeOnTime team
 */
public interface FormationDao extends Remote {
//    private static final LdapManager ldapManager = LdapManager.getInstance();  
//    private static final String TABLE = "Formation";
    
 //   public static FormationDao getInstance() throws RemoteException;

	public Collection getFormations(FormationFilter filter) throws RemoteException;
	
	public Collection getFormations() throws RemoteException;
	
	public boolean addFormation(Formation formation) throws RemoteException;
	
	public boolean modifyFormation(Formation formation) throws RemoteException;
	
}
