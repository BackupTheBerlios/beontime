package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * @author BeOnTime
 */
public class CourseFilter extends Course implements Filter {
    private static final String DB_ID_COURS = "id_cours";
    private static final String DB_ID_TYPE_COURS = "id_type_cours";
    private static final String DB_DATE_DEBUT = "date_debut";
    private static final String DB_DATE_FIN = "date_fin";
    private static final String DB_NB_SEMAINE = "nb_semaine";
    private static final String DB_ID_FORMATION = "id_formation";
    
    public CourseFilter() {
        super();
    }
    
    public CourseFilter(Course course) {
        this();
        if (course != null) {
            setIdCours(course.getIdCours());
            setIdTypeCourse(course.getIdTypeCourse());
            setDateDebut(course.getDateDebut());
            setDateFin(course.getDateFin());
            setNbSemaine(course.getNbSemaine());
            setIdFormation(course.getIdFormation());
        }
    }

	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontime.filter.Filter#getQueryHQL()
	 */
	public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdCours() != null) {
	        query.append(DB_ID_COURS).append("='").append(getIdCours()).append("'");
	    }
		if (getIdTypeCourse() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_TYPE_COURS).append("='").append(getIdTypeCourse()).append("'");
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
		if (getNbSemaine() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_SEMAINE).append("='").append(getNbSemaine()).append("'");
		}
		if (getIdFormation() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_FORMATION).append("='").append(getIdFormation()).append("'");
		}
		    
		return query.toString();
	}

}
