package fr.umlv.smoreau.beontime.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Created on 20 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestSql {

	public Connection getConnection(String url,String user, String passwd) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connexion = DriverManager.getConnection(url,user,passwd);
			return connexion;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
			return null;
		} catch (SQLException e) {
			System.out.println("Database or Login/passwd are wrong");
			return null;
		}
		
	}
	public String getIdentification(Connection connexion,int i){
		String name;
		name="";
		try {
			PreparedStatement pst = connexion.prepareStatement("SELECT nom,prenom FROM etudiant WHERE Id = ?");
			pst.setInt(1,i);
			ResultSet rs=pst.executeQuery();
			rs.next();
			name="Nom : "+rs.getString(1)+" ; Prenom : "+rs.getString(2);
			return name;
		} catch (SQLException e) {
			System.out.println("Query Error");
			return null;
		}	
	}
	public static void main(String[] args) {
		TestSql tSql=new TestSql();
		Connection connexion=tSql.getConnection("jdbc:postgresql://saadouni.dyndns.org:5432/bot","bot","bot");
		System.out.println(tSql.getIdentification(connexion,1));
		System.out.println(tSql.getIdentification(connexion,2));
		System.out.println(tSql.getIdentification(connexion,3));
		System.out.println(tSql.getIdentification(connexion,4));
		System.out.println(tSql.getIdentification(connexion,5));
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
