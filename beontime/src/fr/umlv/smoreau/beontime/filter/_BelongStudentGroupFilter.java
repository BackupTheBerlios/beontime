package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.association.BelongStudentGroup;

/**
 * @author BeOnTime
 */
public class _BelongStudentGroupFilter extends BelongStudentGroup implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdGroup.IdGroup", "id_groupe");
        corres.put("IdStudent", "id_etudiant");
    }


    public _BelongStudentGroupFilter() {
        super();
    }
    
    public _BelongStudentGroupFilter(BelongStudentGroup belong) {
        super();
        try {
            FilterUtils.fillFilterClass(this, belong, corres.keySet());
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