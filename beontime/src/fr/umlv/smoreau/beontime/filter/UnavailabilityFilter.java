package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class UnavailabilityFilter extends Unavailability implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdUnavailability", new FilterObject("id_indisponibilite"));
        //corres.put("BeginDate", "date_debut");
        //corres.put("EndDate", "date_fin");
        corres.put("IdUnavailabilityType.IdUnavailabilityType", new FilterObject("id_type_indisponibilite"));
        corres.put("IdUnavailabilitySubject", new FilterObject("id_sujet_indisponibilite"));
        corres.put("Description", new FilterObject("description"));
    }


    public UnavailabilityFilter() {
        super();
    }
    
    public UnavailabilityFilter(Unavailability unavailability) {
        super();
        try {
            FilterUtils.fillFilterClass(this, unavailability, corres.keySet());
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
