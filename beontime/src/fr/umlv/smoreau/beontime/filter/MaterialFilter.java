package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class MaterialFilter extends Material implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdMateriel", "id_materiel");
        corres.put("Nom", "nom");
        corres.put("Description", "description");
    }


    public MaterialFilter() {
        super();
    }
    
    public MaterialFilter(Material material) {
        super();
        try {
            FilterUtils.fillFilterClass(this, material, corres.keySet());
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
