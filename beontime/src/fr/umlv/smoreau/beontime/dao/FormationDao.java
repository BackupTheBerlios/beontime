/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

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
		//TODO � impl�menter
		return null;
	}
	
	public Collection getFormations() {
		return getFormations(null);
	}
	
	public void modifyFormation(Formation formation) {
		//TODO � impl�menter
	}
}
