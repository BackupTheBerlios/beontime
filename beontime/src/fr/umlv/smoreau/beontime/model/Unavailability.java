package fr.umlv.smoreau.beontime.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.umlv.smoreau.beontime.model.base.BaseUnavailability;

/**
 * This is the object class that relates to the Indisponibilite table.
 * Any customizations belong here.
 */
public class Unavailability extends BaseUnavailability {
    private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Unavailability () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Unavailability (java.lang.Long _idUnavailability) {
		super(_idUnavailability);
	}

	/**
	 * Constructor for required fields
	 */
	public Unavailability (
		java.lang.Long _idUnavailability,
		UnavailabilityType _idUnavailabilityType,
		java.lang.Long _idUnavailabilitySubject) {

		super (
			_idUnavailability,
			_idUnavailabilityType,
			_idUnavailabilitySubject);
	}

/*[CONSTRUCTOR MARKER END]*/
	
    private Calendar beginPeriod;
    private Calendar endPeriod;
    private String notDescription;

	
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
    
    public void setNotDescription(String notDescription) {
        this.notDescription = notDescription;
    }
    
    public String getNotDescription() {
        return notDescription;
    }
}