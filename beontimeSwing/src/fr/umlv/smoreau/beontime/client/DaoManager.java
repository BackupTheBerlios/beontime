package fr.umlv.smoreau.beontime.client;
/* DESS CRI - BeOnTime - timetable project */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import fr.umlv.smoreau.beontime.dao.*;


/**
 * @author BeOnTime team
 */
public class DaoManager {
	private static String host="localhost";
//	private static String host="saadouni.dyndns.org";
	//TODO gerer une ip fixe ? en param ? en properties ?
	private static /*final*/ DatabaseConfiguration databaseConf;
	private static /*final*/ ElementDao elementDao;
	private static /*final*/ FormationDao formationDao;
	private static /*final*/ GroupDao groupDao;
	private static /*final*/ TimetableDao timetableDao;
	private static /*final*/ UnavailabilityDao unavailabilityDao;
	private static /*final*/ UserDao userDao;
	static {
		try {
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

    /** un main juste pour tester un client RMI */
    public static void main (String[] args) {
    	// juste pour les tests
    	UserDao ud = getUserDao(); 
    	System.out.println(ud.toString());
    }
}
