package fr.umlv.smoreau.beontime.dao;

import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class GroupFilter extends Group implements Filter {
    private static final String DB_ID_GROUPE = "id_groupe";
    private static final String DB_ID_FORMATION = "id_formation";
    private static final String DB_INTITULE = "intitule";
    
    public GroupFilter() {
        super();
    }
    
    public GroupFilter(Group group) {
        this();
        if (group != null) {
            setIdFormation(group.getIdFormation());
            setIdGroupe(group.getIdGroupe());
            setIntitule(group.getIntitule());
        }
    }

	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontime.filter.Filter#getQueryHQL()
	 */
	public String getHQLQuery() {
	    StringBuffer query = new StringBuffer();

	    if (getIdGroupe() != null) {
	        query.append(DB_ID_GROUPE).append("='").append(getIdGroupe()).append("'");
	    }
		if (getIdFormation() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_ID_FORMATION).append("='").append(getIdFormation()).append("'");
		}
		if (getIntitule() != null) {
		    if (query.length() != 0)
		        query.append(" AND ");
		    query.append(DB_INTITULE).append("='").append(getIntitule()).append("'");
		}
		    
		return query.toString();
	}

}
