package fr.umlv.smoreau.beontime.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * @author BeOnTime
 */
public class ArrangeCourses {
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
    
    private HashMap courses;
    private int hourEarliest;
    private int hourLatest;

    public ArrangeCourses(Collection courses) {
        ArrayList coursesMonday = new ArrayList();
        ArrayList coursesTuesday = new ArrayList();
        ArrayList coursesWednesday = new ArrayList();
        ArrayList coursesThursday = new ArrayList();
        ArrayList coursesFriday = new ArrayList();
        ArrayList coursesSaturday = new ArrayList();
        ArrayList coursesSunday = new ArrayList();
        
        hourEarliest = DEFAULT_HOUR_EARLIEST;
        hourLatest = DEFAULT_HOUR_LATEST;

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

        CourseComparator comparator = new CourseComparator();
        Collections.sort(coursesMonday, comparator);
        Collections.sort(coursesTuesday, comparator);
        Collections.sort(coursesWednesday, comparator);
        Collections.sort(coursesThursday, comparator);
        Collections.sort(coursesFriday, comparator);
        Collections.sort(coursesSaturday, comparator);
        Collections.sort(coursesSunday, comparator);
        
        this.courses = new HashMap();
        this.courses.put(MONDAY, coursesMonday);
        this.courses.put(TUESDAY, coursesTuesday);
        this.courses.put(WEDNESDAY, coursesWednesday);
        this.courses.put(THURSDAY, coursesThursday);
        this.courses.put(FRIDAY, coursesFriday);
        this.courses.put(SATURDAY, coursesSaturday);
        this.courses.put(SUNDAY, coursesSunday);
    }
    
    public Collection getCourses(String day) {
        return (Collection) courses.get(day);
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
