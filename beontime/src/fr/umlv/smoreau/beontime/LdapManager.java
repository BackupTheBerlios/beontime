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
import fr.umlv.smoreau.beontime.model.user.User;

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

	public Collection getUsers(UserFilter filter) throws NamingException {
	    ArrayList result = new ArrayList();

		Attributes matchAttrs = new BasicAttributes(true);
		if (filter != null) {
			if (UserDao.TYPE_ADMIN.equals(filter.getUserType()))
				matchAttrs.put(new BasicAttribute("gidNumber","150"));
			else if (UserDao.TYPE_TEACHER.equals(filter.getUserType()))
				matchAttrs.put(new BasicAttribute("gidNumber","801"));
			else if (UserDao.TYPE_STUDENT.equals(filter.getUserType()) &&
			        filter.getIdFormation() != null)
			    matchAttrs.put(new BasicAttribute("gidNumber",filter.getIdFormation()));
		    if (filter.getName() != null)
		        matchAttrs.put(new BasicAttribute("sn",filter.getName()));
		    if (filter.getFirstName() != null)
		        matchAttrs.put(new BasicAttribute("givenName",filter.getFirstName()));
		    if (filter.getEMail() != null)
		        matchAttrs.put(new BasicAttribute("mail",filter.getEMail()));
		    if (filter.getIdUser() != null)
		        matchAttrs.put(new BasicAttribute("uidNumber",filter.getIdUser()));
		    if (filter.getLogin() != null)
		        matchAttrs.put(new BasicAttribute("uid",filter.getLogin()));
		}
		NamingEnumeration items = search(DatabasesProperties.getLdapDNBase("users"),matchAttrs);
		
		while (items != null && items.hasMore()) {
			SearchResult sr = (SearchResult)items.next();
			User person = new User();
			
			Attributes attrs = sr.getAttributes();
			Attribute tmp = attrs.get("gidNumber");
			if (filter != null && UserDao.TYPE_STUDENT.equals(filter.getUserType()) &&
					(((String)tmp.get()).equals("150") || ((String)tmp.get()).equals("801")))
				continue;
			tmp = attrs.get("sn");
			if (tmp != null)
			    person.setName((String) tmp.get());
			tmp = attrs.get("givenName");
			if (tmp != null)
				person.setFirstName((String) tmp.get());
			tmp = attrs.get("mail");
			if (tmp != null)
				person.setEMail((String) tmp.get());
			tmp = attrs.get("uidNumber");
			if (tmp != null)
				person.setIdUser(new Long((String) tmp.get()));
			if (filter != null)
				person.setUserType(filter.getUserType());

			result.add(person);
		}

		return result;
	}
	
	public Collection getAdministrators(UserFilter filter) throws NamingException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(UserDao.TYPE_ADMIN);
		return getUsers(f);
	}
	
	public Collection getStudents(UserFilter filter) throws NamingException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(UserDao.TYPE_STUDENT);
		return getUsers(f);
	}
		
	public Collection getTeachers(UserFilter filter) throws NamingException {
	    UserFilter f = new UserFilter(filter);
	    f.setUserType(UserDao.TYPE_TEACHER);
		return getUsers(f);
	}

	public Collection getUsers() throws NamingException {
		return getUsers(null);
	}
	
	public Collection getAdministrators() throws NamingException {
		return getAdministrators(null);
	}
	
	public Collection getStudents() throws NamingException {
		return getStudents(null);
	}

	public Collection getTeachers() throws NamingException {
		return getTeachers(null);
	}
	
	public Collection getFormations(FormationFilter filter) throws NamingException {
	    ArrayList result = new ArrayList();

	    Attributes matchAttrs = new BasicAttributes(true);
	    if (filter != null) {
	        if (filter.getIdFormation() != null)
	            matchAttrs.put(new BasicAttribute("gidNumber",filter.getIdFormation()));
	        if (filter.getHeading() != null)
	            matchAttrs.put(new BasicAttribute("cn",filter.getHeading()));
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
	            formation.setHeading((String) tmp.get());
	        
	        result.add(formation);
	    }

		return result;
	}

	public Collection getFormations() throws NamingException {
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
