package fr.umlv.smoreau.beontime.model.timetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class Timetable implements Serializable {
    private Formation formation;
    private User teacher;
    private Group group;
    private Room room;
    private Material material;
    private Collection groups;
    private Collection subjects;
    private Collection courses;
    private User personInCharge;
    private Calendar beginPeriod;
    private Calendar endPeriod;
    
    public Timetable() {
    }
    
    public Timetable(Formation formation) {
        this.formation = formation;
    }
    
    public Timetable(Formation formation, Group group) {
        this.formation = formation;
        this.group = group;
    }
    
    public Timetable(User teacher) {
        this.teacher = teacher;
    }
    
    public Timetable(Room room) {
        this.room = room;
    }
    
    public Timetable(Material material) {
        this.material = material;
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
    
    public User getTeacher() {
        return teacher;
    }
    
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public void setMaterial(Material material) {
        this.material = material;
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
        if (courses == null)
            courses = new ArrayList();
        courses.add(course);
    }
    
    public boolean removeCourse(Course course) {
        return courses.remove(course);
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
        if (subjects == null)
            subjects = new ArrayList();
        subjects.add(subject);
    }
    
    public boolean removeSubject(Subject subject) {
        return subjects.remove(subject);
    }
    
    public Collection getGroups() {
        return groups;
    }
    
    public void setGroups(Collection groups) {
        this.groups = groups;
    }
    
    public void addGroup(Group group) {
        if (groups == null)
            groups = new ArrayList();
        groups.add(group);
    }
    
    public boolean removeGroup(Group group) {
        return groups.remove(group);
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
    
    public Calendar getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(Calendar beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public Calendar getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Calendar endPeriod) {
        this.endPeriod = endPeriod;
    }
}
