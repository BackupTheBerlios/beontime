package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * RMI interface for the Formation DAO
 * @author BeOnTime team
 */
public interface FormationDao extends Remote {
	public Collection getFormations(FormationFilter filter) throws RemoteException, HibernateException;
	
	public Collection getFormations() throws RemoteException, HibernateException;
	
	public void addFormation(Formation formation) throws RemoteException, HibernateException;
	
	public void modifyFormation(Formation formation) throws RemoteException, HibernateException;
}
