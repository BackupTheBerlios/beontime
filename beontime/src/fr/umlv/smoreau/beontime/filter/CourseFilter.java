package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * @author BeOnTime
 */
public class CourseFilter extends Course implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdCourse", "id_cours");
        corres.put("IdCourseType.IdCourseType", "id_type_cours");
        //corres.put("BeginDate", "date_debut");
        //corres.put("EndDate", "date_fin");
        corres.put("NbWeeks", "nb_semaine");
        corres.put("IdFormation", "id_formation");
    }


    public CourseFilter() {
        super();
    }
    
    public CourseFilter(Course course) {
        super();
        try {
            FilterUtils.fillFilterClass(this, course, corres.keySet());
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
