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
        corres.put("IdSubject", new FilterObject("id_matiere"));
        corres.put("IdFormation", new FilterObject("id_formation"));
        corres.put("IdTeacher", new FilterObject("id_enseignant"));
        corres.put("Heading", new FilterObject("intitule"));
        corres.put("NbMagHours", new FilterObject("nb_heure_mag"));
        corres.put("NbTdHours", new FilterObject("nb_heure_td"));
        corres.put("NbTpHours", new FilterObject("nb_heure_tp"));
        corres.put("NbMagGroups", new FilterObject("nb_groupe_mag"));
        corres.put("NbTdGroups", new FilterObject("nb_groupe_td"));
        corres.put("NbTpGroups", new FilterObject("nb_groupe_tp"));
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
