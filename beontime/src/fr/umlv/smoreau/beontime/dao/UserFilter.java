package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * @author BeOnTime
 */
public class UserFilter extends Person implements Filter {
    private static final String DB_ID_PERSONNE = "id_personne";
    private static final String DB_NOM = "nom";
    private static final String DB_PRENOM = "prenom";
    private static final String DB_TYPE_PERSONNE = "type_personne";
    private static final String DB_TELEPHONE = "telephone";
    private static final String DB_EMAIL = "e_mail";
    private static final String DB_NOM_BUREAU = "nom_bureau";
    private static final String DB_NOM_BATIMENT_BUREAU = "nom_batiment_bureau";

    public UserFilter() {
        super();
    }
    
    public UserFilter(Person person) {
        this();
        if (person != null) {
	        setIdPersonne(person.getIdPersonne());
	        setNom(person.getNom());
	        setPrenom(person.getPrenom());
	        setTypePersonne(person.getTypePersonne());
	        setTelephone(person.getTelephone());
	        setEMail(person.getEMail());
	        setNomBureau(person.getNomBureau());
	        setNomBatimentBureau(person.getNomBatimentBureau());
        }
    }

	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontime.filter.Filter#getQueryHQL()
	 */
	public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdPersonne() != null) {
	        query.append(DB_ID_PERSONNE).append("='").append(getIdPersonne()).append("'");
	    }
		if (getNom() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NOM).append("='").append(getNom()).append("'");
		}
		if (getPrenom() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_PRENOM).append("='").append(getPrenom()).append("'");
		}
		if (getTypePersonne() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_TYPE_PERSONNE).append("='").append(getTypePersonne()).append("'");
		}
		if (getTelephone() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_TELEPHONE).append("='").append(getTelephone()).append("'");
		}
		if (getEMail() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_EMAIL).append("='").append(getEMail()).append("'");
		}
		if (getNomBureau() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NOM_BUREAU).append("='").append(getNomBureau()).append("'");
		}
		if (getNomBatimentBureau() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NOM_BATIMENT_BUREAU).append("='").append(getNomBatimentBureau()).append("'");
		}
		    
		return query.toString();
	}

}
