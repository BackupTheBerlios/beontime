package fr.umlv.smoreau.beontime;

import java.util.ArrayList;
import java.util.Collection;
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

import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.filter.FormationFilter;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.user.Person;

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

    
    public boolean testLoginPwd(String login, String password) {
    	if ("".equals(password))
    		return false;

        Hashtable env = new Hashtable(common);
        StringBuffer tmp = new StringBuffer("ldap://");
        tmp.append(DatabasesProperties.getLdapHost()).append(":");
        tmp.append(DatabasesProperties.getLdapPort());
        env.put(Context.PROVIDER_URL, tmp.toString());
        tmp = new StringBuffer("uid=");
        tmp.append(login).append(",");
        tmp.append(DatabasesProperties.getLdapDNBase("users"));
        env.put(Context.SECURITY_PRINCIPAL, tmp.toString());
        env.put(Context.SECURITY_CREDENTIALS, password);
       
        try {
			DirContext ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			// mot de passe invalide
			return false;
		}

        return true;
    }

	public Collection getUsers(UserFilter filter) {
	    ArrayList result = new ArrayList();

		try {
			Attributes matchAttrs = new BasicAttributes(true);
			if (filter != null) {
				if (filter.getTypePersonne().equals(UserDao.TYPE_ADMIN))
					matchAttrs.put(new BasicAttribute("gidNumber","150"));
				else if (filter.getTypePersonne().equals(UserDao.TYPE_TEACHER))
					matchAttrs.put(new BasicAttribute("gidNumber","801"));
			    if (filter.getNom() != null)
			        matchAttrs.put(new BasicAttribute("sn",filter.getNom()));
			    if (filter.getPrenom() != null)
			        matchAttrs.put(new BasicAttribute("givenName",filter.getPrenom()));
			    if (filter.getEMail() != null)
			        matchAttrs.put(new BasicAttribute("mail",filter.getEMail()));
			    if (filter.getIdPersonne() != null)
			        matchAttrs.put(new BasicAttribute("uidNumber",filter.getIdPersonne()));
			}
			NamingEnumeration items = search(DatabasesProperties.getLdapDNBase("users"),matchAttrs);
			
			while (items != null && items.hasMore()) {
				SearchResult sr = (SearchResult)items.next();
				Person person = new Person();
				
				Attributes attrs = sr.getAttributes();
				Attribute tmp = attrs.get("gidNumber");
				if (filter.getTypePersonne().equals(UserDao.TYPE_STUDENT) &&
						(((String)tmp.get()).equals("150") || ((String)tmp.get()).equals("801")))
					continue;
				tmp = attrs.get("sn");
				if (tmp != null)
				    person.setNom((String) tmp.get());
				tmp = attrs.get("givenName");
				if (tmp != null)
					person.setPrenom((String) tmp.get());
				tmp = attrs.get("mail");
				if (tmp != null)
					person.setEMail((String) tmp.get());
				tmp = attrs.get("uidNumber");
				if (tmp != null)
					person.setIdPersonne(new Long((String) tmp.get()));
				//TODO ajouter sans doute login et password !
				person.setTypePersonne(UserDao.TYPE_TEACHER);
				
				result.add(person);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public Collection getAdministrators(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    f.setTypePersonne(UserDao.TYPE_ADMIN);
		return getUsers(f);
	}
	
	public Collection getStudents(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    f.setTypePersonne(UserDao.TYPE_STUDENT);
		return getUsers(f);
	}
		
	public Collection getTeachers(UserFilter filter) {
	    UserFilter f = new UserFilter(filter);
	    f.setTypePersonne(UserDao.TYPE_TEACHER);
		return getUsers(f);
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
				    formation.setIdFormation(new Long((String) tmp.get()));
				tmp = attrs.get("cn");
				if (tmp != null)
				    formation.setIntitule((String) tmp.get());
				
				result.add(formation);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

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
			System.err.println("L'hôte ou le port est invalide : " + e.getMessage());
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
