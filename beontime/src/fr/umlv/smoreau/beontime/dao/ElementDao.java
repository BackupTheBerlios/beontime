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
		//TODO à implémenter
		return null;
	}
	
	public Collection getMaterials(ElementFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public Collection getRooms() {
		return getRooms(null);
	}
	
	public Collection getMaterials() {
		return getMaterials(null);
	}
	
	public void addRoom(Room room) {
		//TODO à implémenter
	}
	
	public void addMaterial(Material material) {
		//TODO à implémenter
	}
	
	public void modifyRoom(Room room) {
		//TODO à implémenter
	}
	
	public void modifyMaterial(Material material) {
		//TODO à implémenter
	}
	
	public void removeRoom(Room room) {
		//TODO à implémenter
	}
	
	public void removeMaterial(Material material) {
		//TODO à implémenter
	}
}
