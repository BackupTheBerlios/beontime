package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;

/**
 * @author BeOnTime
 */
public class _IsDirectedByCourseTeacherFilter extends IsDirectedByCourseTeacher implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdCourse.IdCourse", new FilterObject("id_cours"));
        corres.put("IdTeacher", new FilterObject("id_enseignant"));
    }


    public _IsDirectedByCourseTeacherFilter() {
        super();
    }
    
    public _IsDirectedByCourseTeacherFilter(IsDirectedByCourseTeacher isDirected) {
        super();
        try {
            FilterUtils.fillFilterClass(this, isDirected, corres.keySet());
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