/*
 * 
 */
package fr.umlv.smoreau.beontime.model;

/**
 * @author BeOnTime
 */
public class Database {
	public static final int LDAP = 0;
	public static final int SQL  = 1;
	
	private int type;
	private String baseName;
	private String host;
	private String port;
	private String login;
	private String password;
	
	public Database(String baseName, String host, String port, String login, String password) {
		this.type = SQL;
		this.baseName = baseName;
		this.host = host;
		this.port = port;
		this.login = login;
		this.password = password;
	}
	
	public Database(String DNBase, String host, String port) {
		this.type = LDAP;
		this.baseName = DNBase;
		this.host = host;
		this.port = port;
	}

	public int getType() {
		return type;
	}

	/**
	 * @return
	 */
	public String getBaseName() {
		if (type == SQL)
			return baseName;
		return null;
	}
	
	/**
	 * @return
	 */
	public String getDNBase() {
		if (type == LDAP)
			return baseName;
		return null;
	}

	/**
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return
	 */
	public String getLogin() {
		if (type == SQL)
			return login;
		return null;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		if (type == SQL)
			return password;
		return null;
	}

	/**
	 * @return
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param string
	 */
	public void setBaseName(String string) {
		baseName = string;
	}
	
	public void setDNBase(String string) {
		baseName = string;
	}

	/**
	 * @param string
	 */
	public void setHost(String string) {
		host = string;
	}

	/**
	 * @param string
	 */
	public void setLogin(String string) {
		login = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public void setPort(String string) {
		port = string;
	}

}