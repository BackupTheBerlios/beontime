package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */
/* Created on 19 febr. 2005 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import fr.umlv.smoreau.beontime.filter.MaterialFilter;
import fr.umlv.smoreau.beontime.filter.RoomFilter;
import fr.umlv.smoreau.beontime.model.element.*;

/**
 * RMI interface for the Element DAO
 * @author BeOnTime team
 */
public interface ElementDao extends Remote {

//	private static final String TABLE_MATERIAL = "Material";
//    private static final String TABLE_ROOM     = "Room";
    
 //   public static ElementDao getInstance() throws RemoteException;


	public Collection getRooms(RoomFilter filter) throws RemoteException;
	
	public Collection getMaterials(MaterialFilter filter) throws RemoteException;
	
	public Collection getRooms() throws RemoteException;
	
	public Collection getMaterials() throws RemoteException;
	
	public boolean addRoom(Room room) throws RemoteException;
	
	public boolean addMaterial(Material material) throws RemoteException;
	
	public boolean modifyRoom(Room room) throws RemoteException;
	
	public boolean modifyMaterial(Material material) throws RemoteException;
	
	public boolean removeRoom(Room room) throws RemoteException;
	
	public boolean removeMaterial(Material material) throws RemoteException;
	
}
