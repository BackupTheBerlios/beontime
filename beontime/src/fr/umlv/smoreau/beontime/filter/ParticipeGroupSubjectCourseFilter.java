package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.association.ParticipeGroupSubjectCourse;

/**
 * @author BeOnTime
 */
public class ParticipeGroupSubjectCourseFilter extends ParticipeGroupSubjectCourse implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdGroupe.IdGroupe", "id_groupe");
        corres.put("IdCourse.IdCours", "id_cours");
        corres.put("IdSubject.IdMatiere", "id_matiere");
    }


    public ParticipeGroupSubjectCourseFilter() {
        super();
    }
    
    public ParticipeGroupSubjectCourseFilter(ParticipeGroupSubjectCourse participe) {
        super();
        try {
            FilterUtils.fillFilterClass(this, participe, corres.keySet());
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