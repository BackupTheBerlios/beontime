package fr.umlv.smoreau.beontime.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


/* DESS CRI - projet BeOnTime */
/* Created on 12 janv. 2005 */

/**
 * @author abrunete
 */
public class TestLDAP {

	private String host  = "ldapetud.univ-mlv.fr";

	private Connection conn;

	public TestLDAP() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void connect() {
		
		try{
			Class.forName("com.octetstring.jdbcLdap.sql.JdbcLdapDriver").newInstance();
			conn = java.sql.DriverManager.getConnection(
					"jdbc:ldap://"+host+"/ou=Etudiant,dc=univ-mlv,dc=fr");
		} catch(Exception e){
			System.out.println("Connection ratée: "+e);
		//	e.printStackTrace();
			System.exit(-1);  
		}
		
	}
	
	protected ResultSet request() {
		ResultSet res = null;
		
		
		String req = "SELECT * FROM ou=Users WHERE uid=abrunete";
		
		try {
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(req);
		} catch (SQLException e) {
			System.err.println("Erreur de requête");
			e.printStackTrace();
		}
		return res;
				
		
	}
	
	public void essai() {
		try {
			DirContext ctx = new InitialDirContext();
			//String[] attrIDs = ()
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestLDAP ldap = new TestLDAP();
		
		ldap.connect();
		System.out.println("--- connection OK");
		
		ResultSet res = ldap.request();
		System.out.println(res);
		
	}
}
