package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * @author BeOnTime
 */
public class UserFilter extends Person implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdPersonne", "id_personne");
        corres.put("Nom", "nom");
        corres.put("Prenom", "prenom");
        corres.put("TypePersonne", "type_personne");
        corres.put("Telephone", "telephone");
        corres.put("EMail", "e_mail");
        corres.put("NomBureau", "nom_bureau");
        corres.put("NomBatimentBureau", "nom_batiment_bureau");
    }


    public UserFilter() {
        super();
    }
    
    public UserFilter(Person person) {
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
