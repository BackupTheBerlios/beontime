package fr.umlv.smoreau.beontime.model.timetable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 * @author BeOnTime
 */
public class Courses {
    private Collection courses;
    private Calendar beginDate;
    private Calendar endDate;
    private int red;
    private int green;
    private int blue;
    
    public Courses() {
        courses = new ArrayList();
        beginDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        red = 0;
        green = 0;
        blue = 0;
    }
    
    public Collection getCourses() {
        return courses;
    }
    
    public void addCourse(Course course) {
        courses.add(course);
        if (courses.size() == 1) {
            beginDate.setTime(course.getBeginDate().getTime());
            endDate.setTime(course.getEndDate().getTime());
        } else if (course.getBeginDate().getTimeInMillis() < beginDate.getTimeInMillis()) {
            beginDate.setTime(course.getBeginDate().getTime());
        } else if (course.getEndDate().getTimeInMillis() > endDate.getTimeInMillis()) {
            endDate.setTime(course.getEndDate().getTime());
        }
        if (course.getSubject() != null) {
	        Color color = course.getSubject().getColor();
	        red += color.getRed();
	        green += color.getGreen();
	        blue += color.getBlue();
        }
    }
    
    public Calendar getBeginDate() {
        return beginDate;
    }
    
    public Calendar getEndDate() {
        return endDate;
    }
    
    public Color getColor() {
        int n = courses.size();
        return new Color(red / n, green / n, blue / n);
    }
}
