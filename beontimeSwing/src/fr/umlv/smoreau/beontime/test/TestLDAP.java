package fr.umlv.smoreau.beontime.test;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class TestLDAP {

    public static String CONTEXT = "com.sun.jndi.ldap.LdapCtxFactory";
    public static String HOST = "ldap://ldapetud.univ-mlv.fr:389";
    public static String BASE = "ou=Etudiant,dc=univ-mlv,dc=fr";

    public static void main(String [] args) {

        String filter = "(uid=forax)";

        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT);
            env.put(Context.PROVIDER_URL, HOST);
           
            DirContext ctx = new InitialDirContext(env);

            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
            
            NamingEnumeration items = ctx.search(BASE, filter, sc);

            while (items != null && items.hasMore()) {

                SearchResult sr = (SearchResult)items.next();
                String dn = sr.getName();
                System.out.println("DN : " + dn);
                
                Attributes attrs = sr.getAttributes();
                NamingEnumeration attributs = attrs.getAll();

                while (attributs != null && attributs.hasMore()) {
                    
                    Attribute attr = (Attribute)attributs.next();
                    String attrId = attr.getID();
                    System.out.println("Attribut : " + attrId + " : ");
                    Enumeration values = attr.getAll();

                    while (values != null && values.hasMoreElements()) {
                        
                        System.out.println("\t" + values.nextElement() + "\n");
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
