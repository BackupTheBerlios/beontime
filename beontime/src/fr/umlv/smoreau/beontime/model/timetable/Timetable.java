package fr.umlv.smoreau.beontime.model.timetable;

import java.io.Serializable;
import java.util.Collection;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class Timetable implements Serializable {
    private Formation formation;
    private Group group;
    private Collection groups;
    private Collection subjects;
    private Collection courses;
    
    public Timetable() {
    }
    
    public Timetable(Formation formation) {
        this.formation = formation;
    }
    
    public Timetable(Formation formation, Group group) {
        this.formation = formation;
        this.group = group;
    }

    /**
     * @return Renvoie formation.
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * @param formation formation à définir.
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     * @return Renvoie courses.
     */
    public Collection getCourses() {
        return courses;
    }
    
    public void setCourses(Collection courses) {
        this.courses = courses;
    }

    /**
     * @return Renvoie subjects.
     */
    public Collection getSubjects() {
        return subjects;
    }
    
    public void setSubjects(Collection subjects) {
        this.subjects = subjects;
    }
    
    public Collection getGroups() {
        return groups;
    }
    
    public void setGroups(Collection groups) {
        this.groups = groups;
    }

    /**
     * @return Renvoie group.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group group à définir.
     */
    public void setGroup(Group group) {
        this.group = group;
    }
}
