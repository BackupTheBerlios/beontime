package fr.umlv.smoreau.beontime;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author BeOnTime
 */
public class DatabasesProperties {
    private static final String CONFIG_DATABASES = "config/databases.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_DATABASES));
        } catch (Exception e) {
            throw new RuntimeException("Problème de lecture du fichier de configuration : " + e.getMessage(), e);
        }
    }
    
    public static void save() {
        Properties ldap = new Properties();
        Properties sql = new Properties();
        for (Enumeration e = properties.keys(); e.hasMoreElements() ;) {
            String key = (String) e.nextElement();
            if (key.startsWith("ldap."))
                ldap.setProperty(key, properties.getProperty(key));
            else if (key.startsWith("sql."))
                sql.setProperty(key, properties.getProperty(key));
        }
        
        try {
            FileOutputStream out = new FileOutputStream(CONFIG_DATABASES);
            sql.store(out, " Configuration de la base de données SQL");
            out.write('\n');
            ldap.store(out, " Configuration de la base de données LDAP");
        } catch (Exception e) {
            throw new RuntimeException("Problème de l'écriture du fichier de configuration : " + e.getMessage(), e);
        }
    }

    public static String getSqlHost() {
        return properties.getProperty("sql.host");
    }
    
    public static void setSqlHost(String host) {
        properties.setProperty("sql.host", host);
    }

    public static String getSqlPort() {
        return properties.getProperty("sql.port");
    }
    
    public static void setSqlPort(String port) {
        properties.setProperty("sql.port", port);
    }

    public static String getSqlDatabaseName() {
        return properties.getProperty("sql.databaseName");
    }
    
    public static void setSqlDatabaseName(String databaseName) {
        properties.setProperty("sql.databaseName", databaseName);
    }

    public static String getSqlUserName() {
        return properties.getProperty("sql.username");
    }
    
    public static void setSqlUserName(String userName) {
        properties.setProperty("sql.username", userName);
    }

    public static String getSqlPassword() {
        return properties.getProperty("sql.password");
    }
    
    public static void setSqlPassword(String password) {
        properties.setProperty("sql.password", password);
    }


    public static String getLdapHost() {
        return properties.getProperty("ldap.host");
    }
    
    public static void setLdapHost(String host) {
        properties.setProperty("ldap.host", host);
    }

    public static String getLdapPort() {
        return properties.getProperty("ldap.port");
    }
    
    public static void setLdapPort(String port) {
        properties.setProperty("ldap.port", port);
    }

    public static String getLdapDNBase() {
        return properties.getProperty("ldap.dnbase");
    }
    
    public static void setLdapDNBase(String dnBase) {
        properties.setProperty("ldap.dnbase", dnBase);
    }
    
    public static String getLdapDNBase(String name) {
        return properties.getProperty("ldap.dnbase."+name) + getLdapDNBase();
    }
}
