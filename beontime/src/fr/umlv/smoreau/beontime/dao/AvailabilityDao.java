/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class AvailabilityDao {
    private static final AvailabilityDao INSTANCE = new AvailabilityDao();
    
    private AvailabilityDao() {
    }

    public static AvailabilityDao getInstance() {
        return INSTANCE;
    }


	public Collection getUnavailabilities(UnavailabilityFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getUnavailabilities() {
		return getUnavailabilities(null);
	}
	
	public void addUnavailability(Unavailability unavailability) {
		//TODO � impl�menter
	}
	
	public void modifyUnavailability(Unavailability unavailability) {
		//TODO � impl�menter
	}
	
	public void removeUnavailability(Unavailability unavailability) {
		//TODO � impl�menter
	}
}
