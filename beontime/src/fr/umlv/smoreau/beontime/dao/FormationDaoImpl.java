package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	// Attention : en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static FormationDao INSTANCE;
    static {
    	try {
			INSTANCE = new FormationDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du Formation DAO");
			System.exit(1);
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
    	ArrayList result = new ArrayList();
        
        try {
            result.addAll(ldapManager.getFormations());
        } catch (NamingException e) {
            System.err.println("LDAP est inaccessible : " + e.getMessage());
        }
        
        Session session = null;
        try {
            session = Hibernate.getCurrentSession();

            Collection tmp = get(TABLE, null, session);
            for (Iterator i = tmp.iterator(); i.hasNext(); ) {
                Formation formation = (Formation) i.next();
                result.remove(formation);
                if (formation.getIdSecretary() == null)
                    result.add(formation);
            }
        } finally {
            Hibernate.closeSession();
        }
        
        Collections.sort(result);
        
        return result;
    }

	public Collection getFormations(FormationFilter filter) throws RemoteException, HibernateException {
		ArrayList result = new ArrayList();
        
        Session session = null;
        try {
            session = Hibernate.getCurrentSession();

            result.addAll(get(TABLE, null, session));
        } finally {
            Hibernate.closeSession();
        }
        
        Collections.sort(result);

		return result;
	}
	
	public Formation getFormation(Formation formation, String[] join) throws RemoteException, HibernateException {
        try {
            Session session = Hibernate.getCurrentSession();
            formation = (Formation) get(TABLE, new FormationFilter(formation), session).toArray()[0];
            join(formation, join);
        } finally {
            Hibernate.closeSession();
        }
        return formation;
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
            notifyListeners(formation, ChangeListener.TYPE_ADD);
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
            notifyListeners(formation, ChangeListener.TYPE_MODIFY);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	
	private ArrayList list = new ArrayList();
	
    public void addChangeListener(ChangeListener listener) throws RemoteException {
        list.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) throws RemoteException {
        list.remove(listener);
    }
    
    private void notifyListeners(Formation formation, int type) {
        for (Iterator i = list.iterator(); i.hasNext(); ) {
            ChangeListener listener = (ChangeListener) i.next();
            try {
                listener.formationChanged(formation, type);
            } catch(RemoteException re) {
                list.remove(listener);
            }
        }
    }
}
