/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * @author BeOnTime
 */
public class FormationDao extends Dao {
    private static final FormationDao INSTANCE = new FormationDao();
    
    private static final String TABLE = "Formation";
    
    private FormationDao() {
    }

    public static FormationDao getInstance() {
        return INSTANCE;
    }


	public Collection getFormations(FormationFilter filter) {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des formations : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getFormations() {
		return getFormations(null);
	}
	
	public boolean addFormation(Formation formation) {
	    try {
            TransactionManager.beginTransaction();
            add(formation);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une formation : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyFormation(Formation formation) {
	    try {
            TransactionManager.beginTransaction();
            modify(formation);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une formation : " + e.getMessage());
            return false;
        }
        return true;
	}
}
