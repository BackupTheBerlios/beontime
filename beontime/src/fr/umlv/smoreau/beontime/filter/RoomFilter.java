package fr.umlv.smoreau.beontime.filter;

import java.util.HashMap;

import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 */
public class RoomFilter extends Room implements Filter {
    private static final HashMap corres;
    
    static {
        corres = new HashMap();
        corres.put("IdLocal", "id_local");
        corres.put("Nom", "nom");
        corres.put("NomBatiment", "nom_batiment");
        corres.put("Description", "description");
    }


    public RoomFilter() {
        super();
    }
    
    public RoomFilter(Room room) {
        super();
        try {
            FilterUtils.fillFilterClass(this, room, corres.keySet());
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
