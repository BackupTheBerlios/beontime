package fr.umlv.smoreau.beontime.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.LdapManager;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.model.Formation;


/**
 * @author BeOnTime
 */
public class FormationDao extends Dao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;
	
 //   private static final FormationDao INSTANCE = new FormationDao();
    private static FormationDao INSTANCE;
    static {
    	try {
			INSTANCE = new FormationDao();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du Formation DAO");
			//TODO gerer
		}
    }
    private static final LdapManager ldapManager = LdapManager.getInstance();
    
    private static final String TABLE = "Formation";
    
    private FormationDao() throws RemoteException {
    }

    public static FormationDao getInstance() {
        return INSTANCE;
    }


	public Collection getFormations(FormationFilter filter) {
	    Collection result = new ArrayList();

        try {
            Session session = Hibernate.getCurrentSession();
            TransactionManager.beginTransaction();
            result.addAll(get(TABLE, filter));
            TransactionManager.commit();
        } catch (HibernateException e) {
            System.err.println("Erreur lors de la récupération des formations sur la base de données SQL : " + e.getMessage());
        }
        
        try {
            result.addAll(ldapManager.getFormations());
        } catch (NamingException e) {
            System.err.println("Erreur lors de la récupération des formations sur la base de données LDAP : " + e.getMessage());
            //TODO à supprimer plus tard, mais permet de tester l'application en dehors de la fac
            Formation formation = new Formation(new Long(66));
            formation.setHeading("formation de test sans LDAP");
            result.add(formation);
            //finTODO
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
