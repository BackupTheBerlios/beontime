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
        corres.put("IdCourse", new FilterObject("id_cours"));
        corres.put("IdCourseType.IdCourseType", new FilterObject("id_type_cours"));
        corres.put("BeginPeriod", new FilterObject("date_debut", FilterObject.GREAT_EQUAL));
        corres.put("EndPeriod", new FilterObject("date_fin", FilterObject.LESS_EQUAL));
        corres.put("IdFormation", new FilterObject("id_formation"));
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
