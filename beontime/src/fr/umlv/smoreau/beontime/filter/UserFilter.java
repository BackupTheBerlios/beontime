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
        corres.put("IdUser", "id_personne");
        corres.put("Name", "nom");
        corres.put("FirstName", "prenom");
        corres.put("UserType", "type_personne");
        corres.put("Telephone", "telephone");
        corres.put("EMail", "e_mail");
        corres.put("OfficeName", "nom_bureau");
        corres.put("BuildingNameForOffice", "nom_batiment_bureau");
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
