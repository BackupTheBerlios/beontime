package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.timetable.Subject;

/**
 * @author BeOnTime
 */
public class SubjectFilter extends Subject implements Filter {
    private static final String DB_ID_MATIERE = "id_matiere";
    private static final String DB_ID_FORMATION = "id_formation";
    private static final String DB_ID_ENSEIGNANT = "id_enseignant";
    private static final String DB_INTITULE = "intitule";
    private static final String DB_NB_HEURE_MAG = "nb_heure_mag";
    private static final String DB_NB_HEURE_TD = "nb_heure_td";
    private static final String DB_NB_HEURE_TP = "nb_heure_tp";
    private static final String DB_NB_GROUPE_MAG = "nb_groupe_mag";
    private static final String DB_NB_GROUPE_TD = "nb_groupe_td";
    private static final String DB_NB_GROUPE_TP = "nb_groupe_tp";
    
    
    public SubjectFilter() {
        super();
    }
    
    public SubjectFilter(Subject subject) {
        this();
        if (subject != null) {
            
        }
    }

	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontime.filter.Filter#getQueryHQL()
	 */
	public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdMatiere() != null) {
	        query.append(DB_ID_MATIERE).append("='").append(getIdMatiere()).append("'");
	    }
		if (getIdFormation() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_FORMATION).append("='").append(getIdFormation()).append("'");
		}
		if (getIdTeacher() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_ENSEIGNANT).append("='").append(getIdTeacher()).append("'");
		}
		if (getIntitule() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_INTITULE).append("='").append(getIntitule()).append("'");
		}
		if (getNbHeureMag() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_HEURE_MAG).append("='").append(getNbHeureMag()).append("'");
		}
		if (getNbHeureTd() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_HEURE_TD).append("='").append(getNbHeureTd()).append("'");
		}
		if (getNbHeureTp() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_HEURE_TP).append("='").append(getNbHeureTp()).append("'");
		}
		if (getNbGroupeMag() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_GROUPE_MAG).append("='").append(getNbGroupeMag()).append("'");
		}
		if (getNbGroupeTd() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_GROUPE_TD).append("='").append(getNbGroupeTd()).append("'");
		}
		if (getNbGroupeTp() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NB_GROUPE_TP).append("='").append(getNbGroupeTp()).append("'");
		}

		return query.toString();
	}

}
