package fr.umlv.smoreau.beontime.model.timetable;

import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class Timetable {
    private Group group;
    private Subject[] fields;
    private Course[] courses;

    public Timetable() {
    }

    /**
     * @return Renvoie courses.
     */
    public Course[] getCourses() {
        return courses;
    }

    /**
     * @param courses courses à définir.
     */
    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    /**
     * @return Renvoie fields.
     */
    public Subject[] getFields() {
        return fields;
    }

    /**
     * @param fields fields à définir.
     */
    public void setFields(Subject[] fields) {
        this.fields = fields;
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
