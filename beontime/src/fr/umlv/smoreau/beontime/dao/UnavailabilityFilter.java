package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class UnavailabilityFilter extends Unavailability implements Filter {
    private static final String DB_ID_INDISPONIBILITE = "id_indisponibilite";
    private static final String DB_DATE_DEBUT = "date_debut";
    private static final String DB_DATE_FIN = "date_fin";
    private static final String DB_ID_TYPE_INDISPONIBILITE = "id_type_indisponibilite";
    private static final String DB_ID_SUJET_INDISPONIBILITE = "id_sujet_indisponibilite";
    private static final String DB_DESCRIPTION = "description";
    
    public UnavailabilityFilter() {
        super();
    }
    
    public UnavailabilityFilter(Unavailability unavailability) {
        this();
        if (unavailability != null) {
            setIdIndisponibilite(unavailability.getIdIndisponibilite());
            setDateDebut(unavailability.getDateDebut());
            setDateFin(unavailability.getDateFin());
            setIdTypeUnavailability(unavailability.getIdTypeUnavailability());
            setIdSujetIndisponibilite(unavailability.getIdSujetIndisponibilite());
            setDescription(unavailability.getDescription());
        }
    }

	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontime.filter.Filter#getQueryHQL()
	 */
	public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdIndisponibilite() != null) {
	        query.append(DB_ID_INDISPONIBILITE).append("='").append(getIdIndisponibilite()).append("'");
	    }
		if (getDateDebut() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_DATE_DEBUT).append("='").append(getDateDebut()).append("'");
		}
		if (getDateFin() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_DATE_FIN).append("='").append(getDateFin()).append("'");
		}
		if (getIdTypeUnavailability() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_TYPE_INDISPONIBILITE).append("='").append(getIdTypeUnavailability()).append("'");
		}
		if (getIdSujetIndisponibilite() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_SUJET_INDISPONIBILITE).append("='").append(getIdSujetIndisponibilite()).append("'");
		}
		if (getDescription() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_DESCRIPTION).append("='").append(getDescription()).append("'");
		}
		    
		return query.toString();
	}

}
