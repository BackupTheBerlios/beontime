package fr.umlv.smoreau.beontime;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.TransactionException;

/**
 * @author BeOnTime
 */
public class TransactionManager {
    private static final ThreadLocal transaction = new ThreadLocal();
    
    public static void beginTransaction() throws TransactionException {
        try {
            Session s = Hibernate.getCurrentSession();
            Transaction tx = s.beginTransaction();
            transaction.set(tx);
        } catch (HibernateException e) {
            throw new TransactionException("Erreur lors du commencement de la transaction", e);
        }
    }

    public static void commit() throws TransactionException {
        try {
            ((Transaction) transaction.get()).commit();
            transaction.set(null);
        } catch (HibernateException e) {
            throw new TransactionException("Erreur lors du commit", e);
        }
    }

    public static void rollback() throws TransactionException {
        try {
            ((Transaction) transaction.get()).rollback();
            transaction.set(null);
        } catch (HibernateException e) {
            throw new TransactionException("Erreur lors du rollback", e);
        }
    }
}
