package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class GroupFilter extends Group implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdGroup", new FilterObject("id_groupe"));
        corres.put("IdFormation", new FilterObject("id_formation"));
        corres.put("Heading", new FilterObject("intitule"));
    }


    public GroupFilter() {
        super();
    }
    
    public GroupFilter(Group group) {
        super();
        try {
            FilterUtils.fillFilterClass(this, group, corres.keySet());
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
