package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * RMI interface for the Group DAO
 * @author BeOnTime team
 */
public interface GroupDao extends Remote {

	public Collection getGroups(GroupFilter filter) throws RemoteException;
	
	public Collection getGroups() throws RemoteException;
	
	public boolean addGroup(Group group) throws RemoteException;
	
	public boolean modifyGroup(Group group) throws RemoteException;
	
	public boolean removeGroup(Group group) throws RemoteException;
	
}
