package fr.umlv.smoreau.beontime.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author BeOnTime
 */
public class TestSql {

	public Connection getConnection(String url,String user, String passwd) 
{
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
	public String getById(Connection connexion,int i){
		String name;
		name="";
		try {
			PreparedStatement pst = connexion.prepareStatement("SELECT nom,prenom FROM etudiant WHERE Id = ?;");
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

	public String getAll(Connection connexion){
		String name;
		name="";
		try {
			PreparedStatement pst = connexion.prepareStatement("SELECT * FROM etudiant;");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				name=name+"Id : "+rs.getInt(1)+" ; Nom : "+rs.getString(2)+" ; Prenom : "+rs.getString(3)+"\n";
			}
			
			return name;
		} catch (SQLException e) {
			System.out.println("Query Error");
			return null;
		}	
	}
	
	public boolean addEtudiant(Connection connexion,String nom,String prenom){
		int i = 0;
		try {
			i=getMaxId(connexion)+1;
			Statement st = connexion.createStatement();
			st.execute("INSERT INTO etudiant VALUES ("+i+", '"+nom+"', '"+prenom+"');");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		

	}


	private int getMaxId(Connection connexion) {
		int i = 0;
		try {
			PreparedStatement pst = connexion.prepareStatement("SELECT max(*) FROM etudiant;");
			ResultSet rs=pst.executeQuery();
			rs.next();
			i = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	private boolean deleteByName(Connection connexion,String name) {
		
		try {
			Statement pst = connexion.createStatement();
			pst.execute("DELETE FROM etudiant WHERE nom = '"+name+"';");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		TestSql tSql=new TestSql();
		Connection connexion=tSql.getConnection("jdbc:postgresql://172.16.0.5:5432/bot","bot","bot");
		String name;
		System.out.println("\nAjout d'un étudiant :\n-------------------------\n");
		boolean ret=tSql.addEtudiant(connexion,"Doe","John");
		if (ret){
			System.out.println("L'étudiant John Doe à été rajouté avec succes");
		}
		else{
			System.out.println("L'étudiant John Doe n'a pas été rajouté a la base");
		}
		System.out.println("\nListe des Etudiants:\n-------------------------\n");
		name=tSql.getAll(connexion);
		System.out.println(name);
		System.out.println("\nSuppresion d'un étudiant :\n-------------------------\n");
		ret=tSql.deleteByName(connexion,"Doe");
		if (ret){
			System.out.println("L'étudiant John Doe à été supprimé avec succes");
		}
		else{
			System.out.println("L'étudiant John Doe n'a pas été supprimé de la base");
		}
		System.out.println("\nListe des Etudiants:\n-------------------------\n");
		name=tSql.getAll(connexion);
		System.out.println(name);
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}