/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * @author BeOnTime
 */
public class FormationDao {
    private static final FormationDao INSTANCE = new FormationDao();
    
    private FormationDao() {
    }

    public static FormationDao getInstance() {
        return INSTANCE;
    }


	public Collection getFormations(FormationFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public Collection getFormations() {
		return getFormations(null);
	}
	
	public boolean modifyFormation(Formation formation) {
		//TODO à implémenter
	    return true;
	}
}
