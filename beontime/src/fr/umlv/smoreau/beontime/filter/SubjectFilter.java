package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.timetable.Subject;

/**
 * @author BeOnTime
 */
public class SubjectFilter extends Subject implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdSubject", "id_matiere");
        corres.put("IdFormation", "id_formation");
        corres.put("IdTeacher", "id_enseignant");
        corres.put("Heading", "intitule");
        corres.put("NbMagHours", "nb_heure_mag");
        corres.put("NbTdHours", "nb_heure_td");
        corres.put("NbTpHours", "nb_heure_tp");
        corres.put("NbMagGroups", "nb_groupe_mag");
        corres.put("NbTdGroups", "nb_groupe_td");
        corres.put("NbTpGroups", "nb_groupe_tp");
    }


    public SubjectFilter() {
        super();
    }
    
    public SubjectFilter(Subject subject) {
        super();
        try {
            FilterUtils.fillFilterClass(this, subject, corres.keySet());
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
