package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.Formation;

/**
 * @author BeOnTime
 */
public class FormationFilter extends Formation implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdFormation", "id_formation");
        corres.put("Intitule", "intitule");
        corres.put("IdTeacher", "id_secretaire");
        corres.put("IdEnseignant", "id_enseignant");
    }


    public FormationFilter() {
        super();
    }
    
    public FormationFilter(Formation formation) {
        super();
        try {
            FilterUtils.fillFilterClass(this, formation, corres.keySet());
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
