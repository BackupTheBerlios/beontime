package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;

/**
 * @author BeOnTime
 */
public class _TakePartGroupSubjectCourseFilter extends TakePartGroupSubjectCourse implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdGroup", "id_groupe");
        corres.put("IdCourse", "id_cours");
        corres.put("IdSubject", "id_matiere");
    }


    public _TakePartGroupSubjectCourseFilter() {
        super();
    }
    
    public _TakePartGroupSubjectCourseFilter(TakePartGroupSubjectCourse takePart) {
        super();
        try {
            FilterUtils.fillFilterClass(this, takePart, corres.keySet());
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