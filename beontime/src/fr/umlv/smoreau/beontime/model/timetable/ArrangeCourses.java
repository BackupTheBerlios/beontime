package fr.umlv.smoreau.beontime.model.timetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Courses;

/**
 * @author BeOnTime
 */
public class ArrangeCourses implements Serializable {
    public static final String MONDAY = "Lundi";
    public static final String TUESDAY = "Mardi";
    public static final String WEDNESDAY = "Mercredi";
    public static final String THURSDAY = "Jeudi";
    public static final String FRIDAY = "Vendredi";
    public static final String SATURDAY = "Samedi";
    public static final String SUNDAY = "Dimanche";

    public static final String[] ALL_DAYS = { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY };
    
    private static final int DEFAULT_HOUR_EARLIEST = 8;
    private static final int DEFAULT_HOUR_LATEST = 18;
    
    private HashMap coursesArranged;
    private Collection coursesNotArranged;
    private int hourEarliest;
    private int hourLatest;
    private boolean isArranged;
    
    protected ArrangeCourses() {
        this(null);
    }

    protected ArrangeCourses(Collection courses) {
        coursesNotArranged = courses;
        isArranged = false;
    }
    
    private void arrange(Collection courses) {
        ArrayList coursesMonday = new ArrayList();
        ArrayList coursesTuesday = new ArrayList();
        ArrayList coursesWednesday = new ArrayList();
        ArrayList coursesThursday = new ArrayList();
        ArrayList coursesFriday = new ArrayList();
        ArrayList coursesSaturday = new ArrayList();
        ArrayList coursesSunday = new ArrayList();
        
        hourEarliest = DEFAULT_HOUR_EARLIEST;
        hourLatest = DEFAULT_HOUR_LATEST;
        
        if (courses != null) {
	        for (Iterator i = courses.iterator(); i.hasNext(); ) {
	            Course course = (Course) i.next();
	            
	            int h = course.getBeginDate().get(Calendar.HOUR_OF_DAY);
	            if (h < hourEarliest)
	                hourEarliest = h;
	            h = course.getEndDate().get(Calendar.HOUR_OF_DAY);
	            if (course.getEndDate().get(Calendar.MINUTE) > 0)
	                ++h;
	            if (h > hourLatest)
	                hourLatest = h;
	            
	            switch (course.getBeginDate().get(Calendar.DAY_OF_WEEK)) {
	                case Calendar.MONDAY:
	                    coursesMonday.add(course);
	                	break;
	                case Calendar.TUESDAY:
	                    coursesTuesday.add(course);
	                	break;
	                case Calendar.WEDNESDAY:
	                    coursesWednesday.add(course);
	                	break;
	                case Calendar.THURSDAY:
	                    coursesThursday.add(course);
	                	break;
	                case Calendar.FRIDAY:
	                    coursesFriday.add(course);
	                	break;
	                case Calendar.SATURDAY:
	                    coursesSaturday.add(course);
	                	break;
	                case Calendar.SUNDAY:
	                    coursesSunday.add(course);
	                	break;
	            }
	        }
        }

        CourseComparator comparator = new CourseComparator();
        Collections.sort(coursesMonday, comparator);
        Collections.sort(coursesTuesday, comparator);
        Collections.sort(coursesWednesday, comparator);
        Collections.sort(coursesThursday, comparator);
        Collections.sort(coursesFriday, comparator);
        Collections.sort(coursesSaturday, comparator);
        Collections.sort(coursesSunday, comparator);
        
        coursesMonday = (ArrayList) merge(coursesMonday);
        coursesTuesday = (ArrayList) merge(coursesTuesday);
        coursesWednesday = (ArrayList) merge(coursesWednesday);
        coursesThursday = (ArrayList) merge(coursesThursday);
        coursesFriday = (ArrayList) merge(coursesFriday);
        coursesSaturday = (ArrayList) merge(coursesSaturday);
        coursesSunday = (ArrayList) merge(coursesSunday);
        
        this.coursesArranged = new HashMap();
        this.coursesArranged.put(MONDAY, coursesMonday);
        this.coursesArranged.put(TUESDAY, coursesTuesday);
        this.coursesArranged.put(WEDNESDAY, coursesWednesday);
        this.coursesArranged.put(THURSDAY, coursesThursday);
        this.coursesArranged.put(FRIDAY, coursesFriday);
        this.coursesArranged.put(SATURDAY, coursesSaturday);
        this.coursesArranged.put(SUNDAY, coursesSunday);
    }
    
    private Collection merge(Collection courses) {
        ArrayList tmp = new ArrayList();
        Iterator i = courses.iterator();
        if (i.hasNext()) {
	        Course c1 = (Course) i.next();
	        Courses cs = new Courses();
	        while (i.hasNext()) {
	            Course c2 = (Course) i.next();
	            
	            if (c1.getEndDate().getTimeInMillis() > c2.getBeginDate().getTimeInMillis()) {
	                if (cs.getCourses().size() == 0)
	                    cs.addCourse(c1);
	                cs.addCourse(c2);
	            } else {
	                if (cs.getCourses().size() != 0) {
	                    tmp.add(cs);
	                    cs = new Courses();
	                }
	                tmp.add(c1);
	            }
	            c1 = c2;
	        }
	        if (cs.getCourses().size() != 0)
                tmp.add(cs);
	        else
	            tmp.add(c1);
        }
        return tmp;
    }
    
    public Collection getCoursesNotArranged() {
        return coursesNotArranged;
    }
    
    public Collection getCoursesArranged(String day) {
        if (!isArranged)
            arrange(coursesNotArranged);
        return (Collection) coursesArranged.get(day);
    }
    
    public Collection getCoursesArranged() {
        if (!isArranged)
            arrange(coursesNotArranged);
        
        Collection courses = new ArrayList();
        courses.addAll((Collection) coursesArranged.get(MONDAY));
        courses.addAll((Collection) coursesArranged.get(TUESDAY));
        courses.addAll((Collection) coursesArranged.get(WEDNESDAY));
        courses.addAll((Collection) coursesArranged.get(THURSDAY));
        courses.addAll((Collection) coursesArranged.get(FRIDAY));
        courses.addAll((Collection) coursesArranged.get(SATURDAY));
        courses.addAll((Collection) coursesArranged.get(SUNDAY));
        
        return courses;
    }
    
    public void setCourses(Collection courses) {
        coursesNotArranged = courses;
        isArranged = false;
    }
    
    public void addCourse(Course course) {
        coursesNotArranged.add(course);
        isArranged = false;
    }
    
    public boolean removeCourse(Course course) {
        if (coursesNotArranged.remove(course)) {
            isArranged = false;
            return true;
        }
        return false;
    }
    
    public int getHourEarliest() {
        return hourEarliest;
    }
    
    public int getHourLatest() {
        return hourLatest;
    }
    
    
    private class CourseComparator implements Comparator {
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            Course course1 = (Course) o1;
            Course course2 = (Course) o2;

            return course1.getBeginDate().compareTo(course2.getBeginDate());
        }
    }
}
