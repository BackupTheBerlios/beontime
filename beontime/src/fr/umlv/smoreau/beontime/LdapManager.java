/*
 * 
 */
package fr.umlv.smoreau.beontime;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

import fr.umlv.smoreau.beontime.dao.FormationFilter;
import fr.umlv.smoreau.beontime.dao.UserFilter;

/**
 * @author BeOnTime
 */
public class LdapManager {
    private static String CONTEXT = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String HOST = "ldap://ldapetud.univ-mlv.fr:389";
    private static String BASE_ETUDIANT = "ou=Etudiant,dc=univ-mlv,dc=fr";
    private static String BASE_USERS    = "ou=Users,ou=Etudiant,dc=univ-mlv,dc=fr";
    private static String BASE_GROUPS   = "ou=Groups,ou=Etudiant,dc=univ-mlv,dc=fr";

    private static NamingEnumeration search(String base, Attributes matchAttrs) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT);
        env.put(Context.PROVIDER_URL, HOST);
        env.put("java.naming.ldap.version", "3");
       
        DirContext ctx = new InitialDirContext(env);

        return ctx.search(base, matchAttrs);
    }
    
    
    public static void main(String[] args) {
    	getFormations(null);
    }


	public static Collection getUsers(UserFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public static Collection getAdministrators(UserFilter filter) {
		//TODO à implémenter
		return null;
	}
	
	public static Collection getStudents(UserFilter filter) {
		//TODO à implémenter
		return null;
	}
		
	public static Collection getTeachers(UserFilter filter) {
		try {
			Attributes matchAttrs = new BasicAttributes(true);
			matchAttrs.put(new BasicAttribute("gidNumber","801"));
			NamingEnumeration items = search(BASE_USERS,matchAttrs);
			
			while (items != null && items.hasMore()) {
				SearchResult sr = (SearchResult)items.next();
				
				Attributes attrs = sr.getAttributes();
				NamingEnumeration attributs = attrs.getAll();
				
				while (attributs != null && attributs.hasMore()) {
					Attribute attr = (Attribute)attributs.next();
					String attrId = attr.getID();
					if (attrId.equals("uid")) {
						Enumeration values = attr.getAll();
						while (values != null && values.hasMoreElements()) {
							System.out.println(values.nextElement());
						}
					}
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		//TODO à terminer
		return null;
	}

	public static Collection getUsers() {
		return getUsers(null);
	}
	
	public static Collection getAdministrators() {
		return getAdministrators(null);
	}
	
	public static Collection getStudents() {
		return getStudents(null);
	}

	public static Collection getTeachers() {
		return getTeachers(null);
	}
	
	public static Collection getFormations(FormationFilter filter) {
		try {
			Attributes matchAttrs = new BasicAttributes(true);
			NamingEnumeration items = search(BASE_GROUPS,matchAttrs);
			
			while (items != null && items.hasMore()) {
				SearchResult sr = (SearchResult)items.next();
				
				Attributes attrs = sr.getAttributes();
				Attribute tmp = attrs.get("description");
				if (tmp != null)
					System.out.println(tmp.get());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		//TODO à terminer
		return null;
	}

	public static Collection getFormations() {
		return getFormations(null);
	}
}
