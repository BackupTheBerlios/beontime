package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.association.TakePlaceCourseRoom;
import fr.umlv.smoreau.beontime.model.association.UseCourseMaterial;

/**
 * @author BeOnTime
 */
public class _UseCourseMaterialFilter extends UseCourseMaterial implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdMaterial.IdMaterial", new FilterObject("id_materiel"));
        corres.put("IdCourse.IdCourse", new FilterObject("id_cours"));
    }


    public _UseCourseMaterialFilter() {
        super();
    }
    
    public _UseCourseMaterialFilter(TakePlaceCourseRoom takePlace) {
        super();
        try {
            FilterUtils.fillFilterClass(this, takePlace, corres.keySet());
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