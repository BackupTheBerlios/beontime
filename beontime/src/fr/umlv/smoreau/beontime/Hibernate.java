package fr.umlv.smoreau.beontime;

import java.io.File;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * @author BeOnTime
 */
public class Hibernate {
    private static final File CONFIG_HIBERNATE = new File("config/hibernate.cfg.xml");
    private static SessionFactory sessionFactory;
    private static final ThreadLocal session = new ThreadLocal();

    static {
        try {
            sessionFactory = new Configuration().configure(CONFIG_HIBERNATE).buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException("Problème de configuration Hibernate : " + e.getMessage(), e);
        }
    }

	public static Session getCurrentSession() throws HibernateException {
	    Session s = (Session) session.get();
	    
	    // ouverture d'une nouvelle Session, si ce Thread n'en a aucune
	    if (s == null) {
	        s = sessionFactory.openSession();
	        session.set(s);
	    }
	    
	    return s;
	}
	
	public static void closeSession() throws HibernateException {
	    Session s = (Session) session.get();
	    session.set(null);
	    if (s != null) {
	        s.close();
	    }
	}
}
