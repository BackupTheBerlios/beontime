package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 */
public class RoomFilter extends Room implements Filter {
    private static final String DB_ID_LOCAL = "id_local";
    private static final String DB_NOM = "nom";
    private static final String DB_NOM_BATIMENT = "nom_batiment";
    private static final String DB_DESCRIPTION = "description";	
    
    public RoomFilter() {
        super();
    }
    
    public RoomFilter(Room room) {
        this();
        if (room != null) {
            setIdLocal(room.getIdLocal());
            setNom(room.getNom());
            setNomBatiment(room.getNomBatiment());
            setDescription(room.getDescription());
        }
    }

    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontime.dao.Filter#getHQLQuery()
     */
    public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdLocal() != null) {
	        query.append(DB_ID_LOCAL).append("='").append(getIdLocal()).append("'");
	    }
		if (getNom() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NOM).append("='").append(getNom()).append("'");
		}
		if (getNomBatiment() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_NOM_BATIMENT).append("='").append(getNomBatiment()).append("'");
		}
		if (getDescription() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_DESCRIPTION).append("='").append(getDescription()).append("'");
		}
		    
		return query.toString();
    }

}
