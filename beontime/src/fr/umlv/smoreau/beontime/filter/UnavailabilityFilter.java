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
        corres.put("BeginPeriod", new FilterObject("date_debut", FilterObject.GREAT_EQUAL));
        corres.put("EndPeriod", new FilterObject("date_fin", FilterObject.LESS_EQUAL));
        corres.put("IdUnavailabilityType.IdUnavailabilityType", new FilterObject("id_type_indisponibilite"));
        corres.put("IdUnavailabilitySubject", new FilterObject("id_sujet_indisponibilite"));
        corres.put("Description", new FilterObject("description"));
        corres.put("IdCourse", new FilterObject("id_cours"));
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
