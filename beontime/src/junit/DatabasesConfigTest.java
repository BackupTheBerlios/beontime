package junit;

import fr.umlv.smoreau.beontime.dao.DatabaseConfiguration;
import fr.umlv.smoreau.beontime.model.Database;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class DatabasesConfigTest extends TestCase {
	private static final DatabaseConfiguration databaseConfig = DatabaseConfiguration.getInstance();
	
    public DatabasesConfigTest(String name) {
        super(name);
    }
    
    public static void testTestDatabase() {
    	Database database = new Database("ou=Etudiant,dc=univ-mlv,dc=fr","ldapetud.univ-mlv.fr","389");
    	assertTrue(databaseConfig.testDatabase(database));
    	
    	database = new Database("ou=Etudiant,dc=univ-mlv,dc=fr","ldapetudiant.univ-mlv.fr","389");
    	assertFalse(databaseConfig.testDatabase(database));
    	
    	database = new Database("ou=Etudiants,dc=univ-mlv,dc=fr","ldapetud.univ-mlv.fr","389");
    	assertFalse(databaseConfig.testDatabase(database));
    	
    	database = new Database("BoT_db2","saadouni.dyndns.org","5432","bot","bot");
    	assertTrue(databaseConfig.testDatabase(database));
    	
    	database = new Database("BoT_db2","msaadouni.dyndns.org","5432","bot","bot");
    	assertFalse(databaseConfig.testDatabase(database));
    	
    	database = new Database("BoT_db3","saadouni.dyndns.org","5432","bot","bot");
    	assertFalse(databaseConfig.testDatabase(database));
    	
    	database = new Database("BoT_db2","saadouni.dyndns.org","5432","beontime","bot");
    	assertFalse(databaseConfig.testDatabase(database));
    }
    
    public static Test suite() {
        return new TestSuite(DatabasesConfigTest.class);
    }


	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
