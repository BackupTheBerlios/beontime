package junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import fr.umlv.smoreau.beontime.LdapManager;

/**
 * @author BeOnTime
 */
public class LdapManagerTest extends TestCase {
    private static final LdapManager ldapManager = LdapManager.getInstance();

    public LdapManagerTest(String name) {
        super(name);
    }

    public void testGetFormations() {
    	assertNotNull(ldapManager.getFormations());
    }
    
    public void testGetTeachers() {
    	assertNotNull(ldapManager.getTeachers());
    }

    public static Test suite() {
        return new TestSuite(LdapManagerTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
