package fr.umlv.smoreau.beontime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
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

import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * @author BeOnTime
 */
public class LdapManager {
	private static HashMap common;
	
	static {
		common = new HashMap();
		common.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		common.put("java.naming.ldap.version", "3");
	}
	
    private static final LdapManager INSTANCE = new LdapManager();

    private LdapManager() {
    }

    public static LdapManager getInstance() {
        return INSTANCE;
    }


    private NamingEnumeration search(String base, Attributes matchAttrs) throws NamingException {
        Hashtable env = new Hashtable(common);
        StringBuffer url = new StringBuffer("ldap://");
        url.append(DatabasesProperties.getLdapHost()).append(":");
        url.append(DatabasesProperties.getLdapPort());
        env.put(Context.PROVIDER_URL, url.toString());
       
        DirContext ctx = new InitialDirContext(env);

        return ctx.search(base, matchAttrs);
    }


	public Collection getUsers(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getAdministrators(UserFilter filter) {
	    // gidNumber=150
		//TODO � impl�menter
		return null;
	}
	
	public Collection getStudents(UserFilter filter) {
		//TODO � impl�menter
		return null;
	}
		
	public Collection getTeachers(UserFilter filter) {
		try {
			Attributes matchAttrs = new BasicAttributes(true);
			matchAttrs.put(new BasicAttribute("gidNumber","801"));
			NamingEnumeration items = search(DatabasesProperties.getLdapDNBase("users"),matchAttrs);
			
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
		//TODO � terminer
		return null;
	}

	public Collection getUsers() {
		return getUsers(null);
	}
	
	public Collection getAdministrators() {
		return getAdministrators(null);
	}
	
	public Collection getStudents() {
		return getStudents(null);
	}

	public Collection getTeachers() {
		return getTeachers(null);
	}
	
	public Collection getFormations(FormationFilter filter) {
	    ArrayList result = new ArrayList();

		try {
			Attributes matchAttrs = new BasicAttributes(true);
			if (filter != null) {
			    if (filter.getIdFormation() != null)
			        matchAttrs.put(new BasicAttribute("gidNumber",filter.getIdFormation()));
			    if (filter.getIntitule() != null)
			        matchAttrs.put(new BasicAttribute("cn",filter.getIntitule()));
			}
			NamingEnumeration items = search(DatabasesProperties.getLdapDNBase("groups"),matchAttrs);
			
			while (items != null && items.hasMore()) {
				SearchResult sr = (SearchResult)items.next();
				Formation formation = new Formation();
				
				Attributes attrs = sr.getAttributes();
				Attribute tmp = attrs.get("gidNumber");
				if (tmp != null)
				    formation.setIdFormation((Long) tmp.get());
				tmp = attrs.get("cn");
				if (tmp != null)
				    formation.setIntitule((String) tmp.get());
				
				result.add(formation);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		//TODO � tester � la fac
		return result;
	}

	public Collection getFormations() {
		return getFormations(null);
	}
	
	
	public static boolean testConnection(String host, String port, String dnBase) {
		Hashtable env = new Hashtable(common);
		StringBuffer url = new StringBuffer("ldap://");
		url.append(host).append(":");
		url.append(port);
		env.put(Context.PROVIDER_URL, url.toString());
		
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			System.err.println("L'h�te ou le port est invalide : " + e.getMessage());
			return false;
		}
		
		try {
			ctx.search(dnBase, null);
		} catch (NamingException e) {
			System.err.println("La base '"+dnBase+"' est invalide : " + e.getMessage());
			return false;
		}

		return true;
	}
}
