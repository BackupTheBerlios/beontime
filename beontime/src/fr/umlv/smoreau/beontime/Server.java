package fr.umlv.smoreau.beontime;
/* DESS CRI - BeOnTime - timetable project */


import java.net.InetAddress;
import java.rmi.Naming;

import fr.umlv.smoreau.beontime.dao.*;

/**
 * The main class to run the server
 * @author BeOnTime team 
 */
public class Server {
	public static void main(String[] args) {
		//	userDaoThread.start();
		
		try {
			System.out.println("-- starting RMI server");
			java.rmi.registry.LocateRegistry.createRegistry(1099); // remplace le rmiregistry
			
			// Creation de l'instance de l'objet qui sera accedé à distance
			UserDao user = UserDaoImpl.getInstance();
			// Exposition de l'objet
//			UnicastRemoteObject.exportObject(user); // pas besoin si les implementations extends de UnicastRemoteObject
			//publication de l'objet auprès du serveur de noms
//			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/UserDao", user);
			Naming.rebind("/UserDao", user);
			System.out.println(" -> user Dao available");
			
			UnavailabilityDao unavailability = UnavailabilityDaoImpl.getInstance();
			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/UnavailabitityDao", unavailability);
			System.out.println(" -> unavailability Dao available");
			
			TimetableDao timetable = TimetableDaoImpl.getInstance();
			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/TimeTableDao", timetable);
			System.out.println(" -> timetable Dao available");
			
			GroupDao group = GroupDaoImpl.getInstance();
			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/GroupDao", group);
			System.out.println(" -> group Dao available");
			
			FormationDao formation = FormationDaoImpl.getInstance();
			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/FormationDao", formation);
			System.out.println(" -> formation Dao available");
			
			ElementDao element = ElementDaoImpl.getInstance();
			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/ElementDao", element);
			System.out.println(" -> element Dao available");
			
			DatabaseConfiguration dbConf = DatabaseConfigurationImpl.getInstance();
			Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/DbConfiguration", dbConf);
			System.out.println(" -> database configuration available");
			
			System.out.println("-- RMI server started");
			
		} catch (Exception e) {
			System.out.println("Probleme serveur RMI : " + e);
			e.printStackTrace();
		}
	}
}
