package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.TypeUnavailability;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class UnavailabilityDao extends Dao {
    private static final UnavailabilityDao INSTANCE = new UnavailabilityDao();
    
    private static final String TABLE      = "Unavailability";
    private static final String TABLE_TYPE = "TypeUnavailability";
    
    private String[] DEFAULT_TYPES = { "enseignant", "étudiant", "cours", "matériel", "local", "calendrier" };
    
    private UnavailabilityDao() {
        Collection types = getTypesUnavailability();
        if (types != null && types.size() == 0) {
            for (int i = 0; i < DEFAULT_TYPES.length; ++i) {
                TypeUnavailability type = new TypeUnavailability();
                type.setNomTypeIndisponibilite(DEFAULT_TYPES[i]);
                addTypeUnavailability(type);
            }
        }
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
	
	public boolean addUnavailability(Unavailability unavailability) {
        try {
            TransactionManager.beginTransaction();
            add(unavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean modifyUnavailability(Unavailability unavailability) {
        try {
            TransactionManager.beginTransaction();
            modify(unavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la modification d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public boolean removeUnavailability(Unavailability unavailability) {
        try {
            TransactionManager.beginTransaction();
            remove(TABLE, new UnavailabilityFilter(unavailability));
            TransactionManager.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la suppression d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
	
	
	public Collection getTypesUnavailability() {
	    Collection result = null;

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result = get(TABLE_TYPE, null);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des indisponibilités : " + e.getMessage());
        }

		return result;
	}
	
	private boolean addTypeUnavailability(TypeUnavailability typeUnavailability) {
        try {
            TransactionManager.beginTransaction();
            add(typeUnavailability);
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de l'ajout d'une indisponibilité : " + e.getMessage());
            return false;
        }
        return true;
	}
}
