package fr.umlv.smoreau.beontime.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author BeOnTime
 */
public class TestJunit extends TestCase {
    public TestJunit(String name) {
        super(name);
    }
    
    public void testLastComponent() {
        assertEquals("stdlib.h", "stdlib.h");
    }
    
    public void testLastComponentWithEmptyPath() {
        assertEquals("", "");
    }
    
    public static Test suite() {
        /*TestSuite suite= new TestSuite();
        suite.addTest(new TestJunit("testLastComponent"));
        suite.addTest(new TestJunit("testLastComponentWithEmptyPath"));
        return suite;*/
        return new TestSuite(TestJunit.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}
