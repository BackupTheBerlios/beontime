package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.filter.MaterialFilter;
import fr.umlv.smoreau.beontime.filter.RoomFilter;
import fr.umlv.smoreau.beontime.model.element.*;

/**
 * RMI interface for the Element DAO
 * @author BeOnTime team
 */
public interface ElementDao extends Remote {
	public static final int TYPE_ROOM     = 0;
    public static final int TYPE_MATERIAL = 1;
    
    public static final String JOIN_COURSES = "Courses";

	public Collection getRooms(RoomFilter filter) throws RemoteException, HibernateException;
	
	public Collection getMaterials(MaterialFilter filter) throws RemoteException, HibernateException;
	
	public Collection getRooms() throws RemoteException, HibernateException;
	
	public Room getRoom(Room room, String[] join) throws RemoteException, HibernateException;
	
	public Collection getMaterials() throws RemoteException, HibernateException;
	
	public Material getMaterial(Material material, String[] join) throws RemoteException, HibernateException;
	
	public Collection getCoursesInRoom(Room room, Calendar beginDate, Calendar endDate) throws RemoteException, HibernateException;
	
	public Collection getCoursesWithMaterial(Material material, Calendar beginDate, Calendar endDate) throws RemoteException, HibernateException;
	
	public Room addRoom(Room room) throws RemoteException, HibernateException;
	
	public Material addMaterial(Material material) throws RemoteException, HibernateException;
	
	public void modifyRoom(Room room) throws RemoteException, HibernateException;
	
	public void modifyMaterial(Material material) throws RemoteException, HibernateException;
	
	public void removeRoom(Room room) throws RemoteException, HibernateException;
	
	public void removeMaterial(Material material) throws RemoteException, HibernateException;
	
	public Collection getBuildings() throws RemoteException, HibernateException;

}
