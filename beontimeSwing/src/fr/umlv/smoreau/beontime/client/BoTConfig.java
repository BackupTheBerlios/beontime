package fr.umlv.smoreau.beontime.client;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author BeOnTime
 */
public class BoTConfig {
    private static final SimpleDateFormat FORMAT_DATE  = new SimpleDateFormat("dd/MM");

    private static final String CONFIG_BOT = "beontime.properties";
    private static final String DEFAULT_BEGIN_FIRST_HALF_YEAR = "01/09";
    private static final String DEFAULT_END_FIRST_HALF_YEAR = "31/12";
    private static final String DEFAULT_BEGIN_SECOND_HALF_YEAR = "01/01";
    private static final String DEFAULT_END_SECOND_HALF_YEAR = "30/06";
    
	private static String beginFirstHalfYear;
	private static String endFirstHalfYear;
	private static String beginSecondHalfYear;
	private static String endSecondHalfYear;
    
	static {
		try {
	        String configDirectory = System.getProperty("config.directory");
	        if (configDirectory != null) {
	            Properties properties = new Properties();
	            properties.load(new FileInputStream(configDirectory + System.getProperty("file.separator") + CONFIG_BOT));
	            beginFirstHalfYear = properties.getProperty("begin.first.half.year");
	            endFirstHalfYear = properties.getProperty("end.first.half.year");
	            beginSecondHalfYear = properties.getProperty("begin.second.half.year");
	            endSecondHalfYear = properties.getProperty("end.second.half.year");
	        } else {
	            System.err.println("Le paramètre JVM 'config.directory' n'est pas positionné");
	            System.err.println("Utilisation des dates de semestre par défaut");
	            beginFirstHalfYear = DEFAULT_BEGIN_FIRST_HALF_YEAR;
	            endFirstHalfYear = DEFAULT_END_FIRST_HALF_YEAR;
	            beginSecondHalfYear = DEFAULT_BEGIN_SECOND_HALF_YEAR;
	            endSecondHalfYear = DEFAULT_END_SECOND_HALF_YEAR;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Problème de lecture du fichier de configuration : " + e.getMessage(), e);
	    }
	}
	
	
	public static Date getBeginFirstHalfYear() {
	    try {
            return FORMAT_DATE.parse(beginFirstHalfYear);
        } catch (ParseException e) {
            System.err.println("Format de date invalide : " + beginFirstHalfYear);
            return null;
        }
	}
	
	public static Date getEndFirstHalfYear() {
	    try {
		    return FORMAT_DATE.parse(endFirstHalfYear);
		} catch (ParseException e) {
	        System.err.println("Format de date invalide : " + beginFirstHalfYear);
	        return null;
	    }
	}
	
	public static Date getBeginSecondHalfYear() {
	    try {
		    return FORMAT_DATE.parse(beginSecondHalfYear);
		} catch (ParseException e) {
	        System.err.println("Format de date invalide : " + beginFirstHalfYear);
	        return null;
	    }
	}
	
	public static Date getEndSecondHalfYear() {
	    try {
		    return FORMAT_DATE.parse(endSecondHalfYear);
		} catch (ParseException e) {
	        System.err.println("Format de date invalide : " + beginFirstHalfYear);
	        return null;
	    }
	}
}
