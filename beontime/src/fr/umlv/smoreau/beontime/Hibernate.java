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
    private static final String CONFIG_HIBERNATE = "config/hibernate.cfg.xml";
    private static final long TIMEOUT = 300000;

    private static SessionFactory sessionFactory;
    private static final ThreadLocal session = new ThreadLocal();
    private static long time;

    static {
        try {
            sessionFactory = getSessionFactory();
            time = System.currentTimeMillis();
        } catch (HibernateException e) {
            throw new RuntimeException("Problème de configuration Hibernate : " + e.getMessage(), e);
        }
    }
    
    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration cfg = new Configuration().configure(new File(CONFIG_HIBERNATE));
        StringBuffer url = new StringBuffer("jdbc:postgresql://");
        url.append(DatabasesProperties.getSqlHost()).append(":");
        url.append(DatabasesProperties.getSqlPort()).append("/");
        url.append(DatabasesProperties.getSqlDatabaseName());
        cfg.setProperty("hibernate.connection.url",url.toString());
        cfg.setProperty("hibernate.connection.username",DatabasesProperties.getSqlUserName());
        cfg.setProperty("hibernate.connection.password",DatabasesProperties.getSqlPassword());

        return cfg.buildSessionFactory();
    }

	public static Session getCurrentSession() throws HibernateException {
	    if (System.currentTimeMillis() - time > TIMEOUT)
	        sessionFactory = getSessionFactory();
	    time = System.currentTimeMillis();

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
	
	
	public static boolean testConnection(String host, String port, String dbName, String login, String password) {
		try {
            Configuration cfg = new Configuration().configure(new File(CONFIG_HIBERNATE));
            StringBuffer url = new StringBuffer("jdbc:postgresql://");
            url.append(host).append(":");
            url.append(port).append("/");
            url.append(dbName);
            cfg.setProperty("hibernate.connection.url",url.toString());
            cfg.setProperty("hibernate.connection.username",login);
            cfg.setProperty("hibernate.connection.password",password);

            SessionFactory sessionFactory = cfg.buildSessionFactory();
            Session s = sessionFactory.openSession();
            s.find("from Person");
        } catch (HibernateException e) {
            System.err.println("Connection échouée sur la base de données SQL : " + e.getMessage());
            return false;
        }
        
        return true;
	}
}
