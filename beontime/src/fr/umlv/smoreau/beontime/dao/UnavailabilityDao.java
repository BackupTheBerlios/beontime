package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 24 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * RMI interface for the Unavailability DAO
 * @author BeOnTime team
 */
public interface UnavailabilityDao extends Remote {

	public Collection getUnavailabilities(UnavailabilityFilter filter) throws RemoteException, HibernateException;
	
	public Collection getUnavailabilities() throws RemoteException, HibernateException;
	
	public void addUnavailability(Unavailability unavailability) throws RemoteException, HibernateException;
	
	public void modifyUnavailability(Unavailability unavailability) throws RemoteException, HibernateException;
	
	public void removeUnavailability(Unavailability unavailability) throws RemoteException, HibernateException;
	
	public Collection getTypesUnavailability() throws RemoteException, HibernateException;
		
}
