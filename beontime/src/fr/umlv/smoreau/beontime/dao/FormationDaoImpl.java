package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.naming.NamingException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.LdapManager;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.model.Formation;


/**
 * RMI implementation of the User DAO
 * @author BeOnTime team
 */
public class FormationDaoImpl extends Dao implements FormationDao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static FormationDao INSTANCE;
    static {
    	try {
			INSTANCE = new FormationDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du Formation DAO");
			//TODO gerer
		}
    }
    
    private static final LdapManager ldapManager = LdapManager.getInstance();
    
    private static final String TABLE = "Formation";
    
    private FormationDaoImpl() throws RemoteException {
    }

    public static FormationDao getInstance() {
        return INSTANCE;
    }

    public Collection getNotAllottedFormations() throws RemoteException, HibernateException {
        Collection result = new HashSet();
        
        try {
            result.addAll(ldapManager.getFormations());
        } catch (NamingException e) {
            System.err.println("LDAP est inaccessible : " + e.getMessage());
            //TODO à supprimer plus tard, mais permet de tester l'application en dehors de la fac
            Formation formation = new Formation(new Long(66));
            formation.setHeading("dslg00");
            result.add(formation);
            //finTODO
        }
        
        Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            Collection tmp = get(TABLE, null, session);
            for (Iterator i = tmp.iterator(); i.hasNext(); ) {
                Formation formation = (Formation) i.next();
                if (formation.getIdSecretary() != null)
                    result.remove(formation);
            }
        } finally {
            Hibernate.closeSession();
        }
        
        return result;
    }

	public Collection getFormations(FormationFilter filter) throws RemoteException, HibernateException {
	    Collection result = new ArrayList();
        
        /*try {
            result.addAll(ldapManager.getFormations());
        } catch (NamingException e) {
            System.err.println("LDAP est inaccessible : " + e.getMessage());
            //TODO à supprimer plus tard, mais permet de tester l'application en dehors de la fac
            Formation formation = new Formation(new Long(66));
            formation.setHeading("dslg00");
            result.add(formation);
            //finTODO
        }*/
        
        Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            result.addAll(get(TABLE, filter, session));
        } finally {
            Hibernate.closeSession();
        }

		return result;
	}

	public Collection getFormations() throws RemoteException, HibernateException {
		return getFormations(null);
	}

	public Formation addFormation(Formation formation) throws RemoteException, HibernateException {
	    Session session = null;
	    try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(formation, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
        return formation;
	}

	public void modifyFormation(Formation formation) throws RemoteException, HibernateException {
	    Session session = null;
	    try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(formation, session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
}
