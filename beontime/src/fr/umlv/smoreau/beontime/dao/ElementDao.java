/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import fr.umlv.smoreau.beontime.model.element.*;

/**
 * @author BeOnTime
 */
public class ElementDao {
    private static final ElementDao INSTANCE = new ElementDao();
    
    private ElementDao() {
    }

    public static ElementDao getInstance() {
        return INSTANCE;
    }


	public Collection getRooms(ElementFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getMaterials(ElementFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getRooms() {
		return getRooms(null);
	}
	
	public Collection getMaterials() {
		return getMaterials(null);
	}
	
	public void addRoom(Room room) {
		//TODO � impl�menter
	}
	
	public void addMaterial(Material material) {
		//TODO � impl�menter
	}
	
	public void modifyRoom(Room room) {
		//TODO � impl�menter
	}
	
	public void modifyMaterial(Material material) {
		//TODO � impl�menter
	}
	
	public void removeRoom(Room room) {
		//TODO � impl�menter
	}
	
	public void removeMaterial(Material material) {
		//TODO � impl�menter
	}
}
