/*
 * 
 */
package fr.umlv.smoreau.beontime;


import java.net.InetAddress;
import java.rmi.Naming;

import fr.umlv.smoreau.beontime.dao.UserDaoImpl;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class Server {
	
	private static Thread userDaoThread = new Thread() {
		public void run() {
			try{
				System.out.println("-- starting RMI server");
			//	IrmiUserDao dbm = new UserDao();
				UserDao dbm = UserDaoImpl.getInstance();
				java.rmi.registry.LocateRegistry.createRegistry(1099); // remplace le rmiregistry
				
				Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1099/UserDao", dbm);
				System.out.println(" -> user Dao available");
				
				System.out.println("-- RMI server started");
			} catch (Exception e) {
				System.out.println("Probleme serveur RMI : " + e);
			}
		}
	};
	
	public static void main(String[] args) {
		userDaoThread.start();
	}
}
