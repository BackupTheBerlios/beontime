package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * @author BeOnTime
 */
public class UserFilter extends Person implements Filter {
    
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

	public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdPersonne() != null) {
	        query.append("id_personne").append("='").append(getIdPersonne()).append("'");
	    }
		if (getNom() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("nom").append("='").append(getNom()).append("'");
		}
		if (getPrenom() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("prenom").append("='").append(getPrenom()).append("'");
		}
		if (getTypePersonne() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("type_personne").append("='").append(getTypePersonne()).append("'");
		}
		if (getTelephone() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("telephone").append("='").append(getTelephone()).append("'");
		}
		if (getEMail() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("e_mail").append("='").append(getEMail()).append("'");
		}
		if (getNomBureau() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("nom_bureau").append("='").append(getNomBureau()).append("'");
		}
		if (getNomBatimentBureau() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append("nom_batiment_bureau").append("='").append(getNomBatimentBureau()).append("'");
		}
		    
		return query.toString();
	}

}
