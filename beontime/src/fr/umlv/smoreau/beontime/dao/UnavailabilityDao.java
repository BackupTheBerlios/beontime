package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class UnavailabilityDao extends Dao {
    private static final UnavailabilityDao INSTANCE = new UnavailabilityDao();
    
    private static final String TABLE = "Unavailability";
    
    private UnavailabilityDao() {
    }

    public static UnavailabilityDao getInstance() {
        return INSTANCE;
    }


	public Collection getUnavailabilities(UnavailabilityFilter filter) {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE, filter);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des indisponibilités : " + e.getMessage());
        }

		return result;
	}
	
	public Collection getUnavailabilities() {
		return getUnavailabilities(null);
	}
	
	public void addUnavailability(Unavailability unavailability) {
        try {
            TransactionManager.beginTransaction();
            add(unavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une indisponibilité : " + e.getMessage());
        }
	}
	
	public void modifyUnavailability(Unavailability unavailability) {
        try {
            TransactionManager.beginTransaction();
            modify(unavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une indisponibilité : " + e.getMessage());
        }
	}
	
	public void removeUnavailability(Unavailability unavailability) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new UnavailabilityFilter(unavailability));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la suppression d'une indisponibilité : " + e.getMessage());
        }
	}
}
