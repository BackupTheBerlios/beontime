package fr.umlv.smoreau.beontime.dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
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

	protected Collection get(String databaseName, Filter filter, Session session) throws HibernateException {
        String f = "";
        if (filter != null) {
            f = filter.getHQLQuery();
            if (f.length() != 0)
                f = " where " + f;
        }
		return session.find("from " + databaseName + f);
    }
    
    protected void add(Object object, Session session) throws HibernateException {
        session.save(object);
    }
    
    protected void addOrModify(Object object, Session session) throws HibernateException {
        session.saveOrUpdate(object);
    }
    
    protected void modify(Object object, Session session) throws HibernateException {
        session.update(object);
    }
    
    protected void remove(Object object, Session session) throws HibernateException {
        session.delete(object);
    }
    
    protected void remove(String databaseName, Filter filter, Session session) throws HibernateException {
        String f = filter.getHQLQuery();
        if (f.length() != 0)
            f = " where " + f;
        session.delete("from " + databaseName + f);
    }

    protected void join(Object object, String[] join) {
        if (join == null)
            return;

        Class clazz = object.getClass();
        for (int i = 0; i < join.length; ++i) {
            try {
                Object obj = clazz.getMethod("get"+join[i], (java.lang.Class[]) null).invoke(object, (java.lang.Object[]) null);
                Class cl = obj.getClass();
                cl.getMethod("size", (java.lang.Class[]) null).invoke(obj, (java.lang.Object[]) null);
            } catch (Exception e) {
                // ne rien faire ...
            }
        }
    }
}
