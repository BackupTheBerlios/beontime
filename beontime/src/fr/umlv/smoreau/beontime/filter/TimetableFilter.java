package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class TimetableFilter extends Timetable implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("Group.IdGroup", "id_groupe");
        corres.put("Formation.IdFormation", "id_formation");
    }


    public TimetableFilter() {
        super();
    }
    
    public TimetableFilter(Timetable timetable) {
        super();
        try {
            FilterUtils.fillFilterClass(this, timetable, corres.keySet());
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
