/*
 * 
 */
package fr.umlv.smoreau.beontime.dao;

import java.util.Collection;

import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class GroupDao {
    private static final GroupDao INSTANCE = new GroupDao();
    
    private GroupDao() {
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }


	public Collection getGroups(GroupFilter filter) {
		//TODO � impl�menter
		return null;
	}
	
	public Collection getGroups() {
		return getGroups(null);
	}
	
	public void addGroup(Group group) {
		//TODO � impl�menter
	}
	
	public void modifyGroup(Group group) {
		//TODO � impl�menter
	}
	
	public void removeGroup(Group group) {
		//TODO � impl�menter
	}
}
