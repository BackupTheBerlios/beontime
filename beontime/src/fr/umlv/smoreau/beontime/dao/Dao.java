package fr.umlv.smoreau.beontime.dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.filter.Filter;

/**
 * @author BeOnTime
 */
public abstract class Dao extends UnicastRemoteObject {

    /**
	 * @throws RemoteException
	 */
	protected Dao() throws RemoteException {
		super();
		// TODO virer ?
	}

	protected Collection get(String databaseName, Filter filter) throws HibernateException {
        String f = "";
        if (filter != null) {
            f = filter.getHQLQuery();
            if (f.length() != 0)
                f = " where " + f;
        }
		return Hibernate.getCurrentSession().find("from " + databaseName + f);
    }
    
    protected void add(Object object) throws HibernateException {
        Hibernate.getCurrentSession().save(object);
    }
    
    protected void modify(Object object) throws HibernateException {
        Hibernate.getCurrentSession().update(object);
    }
    
    protected void remove(String databaseName, Filter filter) throws HibernateException {
        String f = filter.getHQLQuery();
        if (f.length() != 0)
            f = " where " + f;
        Hibernate.getCurrentSession().delete("from " + databaseName + f);
    }

}
