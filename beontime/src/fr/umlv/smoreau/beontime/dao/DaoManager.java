package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;


/**
 * The Manager of the DAOs
 * @author BeOnTime team
 */
public class DaoManager {
	/* Configuration du serveur RMI par les properties */
	private static String host;
    private static final String CONFIG_BOT = "beontime.properties";
    private static final String DEFAULT_HOST = "localhost";

    static {
        try {
            String configDirectory = System.getProperty("config.directory");
            if (configDirectory != null) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(configDirectory + System.getProperty("file.separator") + CONFIG_BOT));
                host = properties.getProperty("rmi.host");
            } else {
                System.err.println("Le paramètre JVM 'config.directory' n'est pas positionner");
                System.err.println("Utilisation de l'hôte par défaut (" + DEFAULT_HOST + ")");
                host = DEFAULT_HOST;
            }
        } catch (Exception e) {
            throw new RuntimeException("Problème de lecture du fichier de configuration : " + e.getMessage(), e);
        }
    }
    
	/* instances des differents DAO */
	private static /*final*/ DatabaseConfiguration databaseConf;
	private static /*final*/ ElementDao elementDao;
	private static /*final*/ FormationDao formationDao;
	private static /*final*/ GroupDao groupDao;
	private static /*final*/ TimetableDao timetableDao;
	private static /*final*/ UnavailabilityDao unavailabilityDao;
	private static /*final*/ UserDao userDao;
	static {
		try {
			/* initialisation des DAO par RMI */
			databaseConf = (DatabaseConfiguration) Naming.lookup("rmi://"+host+"/DbConfiguration");
			elementDao = (ElementDao) Naming.lookup("rmi://"+host+"/ElementDao");
			formationDao = (FormationDao) Naming.lookup("rmi://"+host+"/FormationDao");
			groupDao = (GroupDao) Naming.lookup("rmi://"+host+"/GroupDao");
			timetableDao = (TimetableDao) Naming.lookup("rmi://"+host+"/TimeTableDao");
			unavailabilityDao = (UnavailabilityDao) Naming.lookup("rmi://"+host+"/UnavailabitityDao");
			userDao = (UserDao) Naming.lookup("rmi://"+host+"/UserDao");
		} catch (MalformedURLException e) {
			System.err.println("pb RMI");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("pb RMI");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("pb RMI");
			e.printStackTrace();
		} 
	}
	
	
    /**
     * @return Renvoie availabilityDao.
     */
    public static UnavailabilityDao getAvailabilityDao() {
        return unavailabilityDao;
    }
    /**
     * @return Renvoie databaseDao.
     */
    public static DatabaseConfiguration getDatabaseDao() {
        return databaseConf;
    }
    /**
     * @return Renvoie elementDao.
     */
    public static ElementDao getElementDao() {
        return elementDao;
    }
    /**
     * @return Renvoie formationDao.
     */
    public static FormationDao getFormationDao() {
        return formationDao;
    }
    /**
     * @return Renvoie groupDao.
     */
    public static GroupDao getGroupDao() {
        return groupDao;
    }
    /**
     * @return Renvoie timetableDao.
     */
    public static TimetableDao getTimetableDao() {
        return timetableDao;
    }
    /**
     * @return Renvoie userDao.
     */
    public static UserDao getUserDao() {
    	return userDao;
    }
}
