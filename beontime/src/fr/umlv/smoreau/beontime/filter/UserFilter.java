package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class UserFilter extends User implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdUser", new FilterObject("id_personne"));
        corres.put("Name", new FilterObject("nom"));
        corres.put("FirstName", new FilterObject("prenom"));
        corres.put("UserType", new FilterObject("type_personne"));
        corres.put("Telephone", new FilterObject("telephone"));
        corres.put("EMail", new FilterObject("e_mail"));
        corres.put("OfficeName", new FilterObject("nom_bureau"));
        corres.put("BuildingNameForOffice", new FilterObject("nom_batiment_bureau"));
        corres.put("Login", new FilterObject("login"));
        corres.put("Password", new FilterObject("password"));
        corres.put("IdFormation", null);
    }


    public UserFilter() {
        super();
    }
    
    public UserFilter(User person) {
        super();
        try {
            FilterUtils.fillFilterClass(this, person, corres.keySet());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'introspection", e);
        }
    }

	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontime.filter.Filter#getQueryHQL()
	 */
	public String getHQLQuery() {
	    try {
            return FilterUtils.createHQLQuery(this, corres);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'introspection", e);
        }
	}

}
