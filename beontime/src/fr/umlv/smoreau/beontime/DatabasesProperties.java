package fr.umlv.smoreau.beontime;

import java.io.FileInputStream;
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

    public static String getSqlHost() {
        return properties.getProperty("sql.host");
    }

    public static String getSqlPort() {
        return properties.getProperty("sql.port");
    }

    public static String getSqlDatabaseName() {
        return properties.getProperty("sql.databaseName");
    }

    public static String getSqlUserName() {
        return properties.getProperty("sql.username");
    }

    public static String getSqlPassword() {
        return properties.getProperty("sql.password");
    }


    public static String getLdapContext() {
        return properties.getProperty("ldap.context");
    }

    public static String getLdapHost() {
        return properties.getProperty("ldap.host");
    }

    public static String getLdapPort() {
        return properties.getProperty("ldap.port");
    }

    public static String getLdapBase(String base) {
        return properties.getProperty("ldap.base."+base);
    }
    
    public static String getLdapBase() {
        return getLdapBase("etudiant");
    }
}
