package junit;

import javax.naming.NamingException;

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
    	try {
            ldapManager.getFormations();
            assertTrue(true);
        } catch (NamingException e) {
            assertTrue(false);
        }
    }
    
    public void testGetTeachers() {
    	try {
            ldapManager.getTeachers();
            assertTrue(true);
        } catch (NamingException e) {
            assertTrue(false);
        }
    }
    
    public void testGetAdministrators() {
    	try {
            ldapManager.getAdministrators();
            assertTrue(true);
        } catch (NamingException e) {
            assertTrue(false);
        }
    }
    
    public void testGetStudents() {
    	try {
            ldapManager.getStudents();
            assertTrue(true);
        } catch (NamingException e) {
            assertTrue(false);
        }
    }
    
    public void testTestLoginPwd() {
    	assertFalse(ldapManager.testLoginPwd("forax","forax"));
    }

    public static Test suite() {
        return new TestSuite(LdapManagerTest.class);
    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
