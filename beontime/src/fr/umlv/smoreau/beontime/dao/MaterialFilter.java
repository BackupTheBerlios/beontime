package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class MaterialFilter extends Material implements Filter {
    private static final String DB_ID_MATERIAL = "id_materiel";
    private static final String DB_NOM = "nom";
    private static final String DB_DESCRIPTION = "description";
    
    public MaterialFilter() {
        super();
    }
    
    public MaterialFilter(Material material) {
        this();
        if (material != null) {
            setIdMateriel(material.getIdMateriel());
            setNom(material.getNom());
            setDescription(material.getDescription());
        }
    }

    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontime.dao.Filter#getHQLQuery()
     */
    public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdMateriel() != null) {
	        query.append(DB_ID_MATERIAL).append("='").append(getIdMateriel()).append("'");
	    }
		if (getNom() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NOM).append("='").append(getNom()).append("'");
		}
		if (getDescription() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_DESCRIPTION).append("='").append(getDescription()).append("'");
		}
		    
		return query.toString();
    }

}
