package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 24 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * RMI interface for the Unavailability DAO
 * @author BeOnTime team
 */
public interface UnavailabilityDao extends Remote {

	public Collection getUnavailabilities(UnavailabilityFilter filter) throws RemoteException;
	
	public Collection getUnavailabilities() throws RemoteException;
	
	public boolean addUnavailability(Unavailability unavailability) throws RemoteException;
	
	public boolean modifyUnavailability(Unavailability unavailability) throws RemoteException;
	
	public boolean removeUnavailability(Unavailability unavailability) throws RemoteException;
	
	public Collection getTypesUnavailability() throws RemoteException;
		
}
