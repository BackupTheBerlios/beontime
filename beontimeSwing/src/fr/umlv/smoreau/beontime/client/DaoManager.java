package fr.umlv.smoreau.beontime.client;
/* DESS CRI - BeOnTime - timetable project */


/**
 * @author BeOnTime team
 */
public class DaoManager {
	private static String host="localhost";
	//TODO gerer une ip fixe ? en param ? en properties ?
/*	private static final AvailabilityDao availabilityDao;
	private static final DatabaseDao databaseDao;
	private static final ElementDao elementDao;
	private static final FormationDao formationDao;
	private static final GroupDao groupDao;
	private static final TimetableDao timetableDao;
//	private static final UserDao userDao;
	private static UserDao userDao;
		static {
		availabilityDao = null;
		databaseDao = null;
		elementDao = null;
		formationDao = null;
		groupDao = null;
		timetableDao = null;
		//userDao = null;
		try {
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

*/
	
	
    /**
     * @return Renvoie availabilityDao.
     */
/*    public static AvailabilityDao getAvailabilityDao() {
        return availabilityDao;
    }*/
    /**
     * @return Renvoie databaseDao.
     */
/*    public static DatabaseDao getDatabaseDao() {
        return databaseDao;
    }*/
    /**
     * @return Renvoie elementDao.
     */
/*    public static ElementDao getElementDao() {
        return elementDao;
    }*/
    /**
     * @return Renvoie formationDao.
     */
/*    public static FormationDao getFormationDao() {
        return formationDao;
    }*/
    /**
     * @return Renvoie groupDao.
     */
/*    public static GroupDao getGroupDao() {
        return groupDao;
    }*/
    /**
     * @return Renvoie timetableDao.
     */
/*    public static TimetableDao getTimetableDao() {
        return timetableDao;
    }*/
    /**
     * @return Renvoie userDao.
     */
/*    public static UserDao getUserDao() {
        return userDao;
    }*/

    /** un main juste pour tester un client RMI */
    public static void main (String[] args) {
    	// juste pour les tests
 //   	UserDao ud = getUserDao(); // ça plante
    	//TODO - importer le stub et l'interface dans un paquetage visible
    }
}
