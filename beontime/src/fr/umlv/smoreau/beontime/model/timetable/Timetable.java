package fr.umlv.smoreau.beontime.model.timetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class Timetable implements Serializable {
    private Formation formation;
    private Group group;
    private Collection groups;
    private Collection subjects;
    private Collection courses;
    private User personInCharge;
    
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
    
    public Collection getCourses(Subject subject) {
        ArrayList list = new ArrayList();
        for (Iterator i = courses.iterator(); i.hasNext(); ) {
            Course course = (Course) i.next();
            if (course.getSubject().equals(subject))
                list.add(course);
        }
        return list;
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public void removeCourse(Course course) {
        courses.remove(course);
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
    
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
    
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
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
    
    public void setPersonInCharge(User personInCharge) {
        this.personInCharge = personInCharge;
    }
    
    public User getPersonInCharge() {
        return personInCharge;
    }
}
