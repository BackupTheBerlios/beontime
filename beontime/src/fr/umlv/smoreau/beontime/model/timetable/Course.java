package fr.umlv.smoreau.beontime.model.timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.umlv.smoreau.beontime.model.base.BaseCourse;

/**
 * This is the object class that relates to the Cours table.
 * Any customizations belong here.
 */
public class Course extends BaseCourse {
    private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Calendar beginPeriod;
    private Calendar endPeriod;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Course () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Course (java.lang.Long _idCourse) {
		super(_idCourse);
	}

	/**
	 * Constructor for required fields
	 */
	public Course (
		java.lang.Long _idCourse,
		CourseType _idCourseType,
		java.lang.Long _idFormation) {

		super (
			_idCourse,
			_idCourseType,
			_idFormation);
	}

/*[CONSTRUCTOR MARKER END]*/
	
	private Subject _subject;
	
	public Subject getSubject() {
	    return _subject;
	}
	
	public void setSubject(Subject subject) {
	    this._subject = subject;
	}
	
    public String getBeginPeriod() {
        if (beginPeriod == null)
            return null;
        return FORMAT_DATE.format(beginPeriod.getTime());
    }

    public void setBeginPeriod(Calendar beginPeriod) {
        this.beginPeriod = beginPeriod;
    }
    
    public void setBeginPeriod(String beginPeriod) {
        if (beginPeriod == null)
            return;
        this.beginPeriod = Calendar.getInstance();
        try {
            this.beginPeriod.setTime(FORMAT_DATE.parse(beginPeriod));
        } catch (ParseException e) {
        }
    }

    public String getEndPeriod() {
        if (endPeriod == null)
            return null;
        return FORMAT_DATE.format(endPeriod.getTime());
    }

    public void setEndPeriod(Calendar endPeriod) {
        this.endPeriod = endPeriod;
    }
    
    public void setEndPeriod(String endPeriod) {
        if (endPeriod == null)
            return;
        this.endPeriod = Calendar.getInstance();
        try {
            this.endPeriod.setTime(FORMAT_DATE.parse(endPeriod));
        } catch (ParseException e) {
        }
    }
}