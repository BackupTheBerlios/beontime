package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.ClipboardManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * @author BeOnTime
 */
public class CutCourse extends Action {
    private static final String NAME = "Couper le cours";
    private static final String ICON = "couper.png";
    private static final String SMALL_ICON = "couper_small.png";
    private static final String KEY_STROKE = "shift X";


    public CutCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Course course = mainFrame.getCourseSelected();
        if (course == null)
            return;

        try {
	        Course courseTmp = DaoManager.getTimetableDao().getCourse(course, new String[] {TimetableDao.JOIN_GROUPS_SUBJECTS, TimetableDao.JOIN_TEACHERS_DIRECTING, TimetableDao.JOIN_ROOMS, TimetableDao.JOIN_MATERIALS});
	        courseTmp.setSubject(course.getSubject());
	        course = courseTmp;

	        ClipboardManager.putCourse(course);
	        
            // suppression des indisponibilités qui en découlent
            UnavailabilityFilter filter = new UnavailabilityFilter();
            filter.setIdCourse(course.getIdCourse());
            DaoManager.getAvailabilityDao().removeUnavailability(filter);
	        
	        DaoManager.getTimetableDao().removeCourse(course);
	        
	        ActionsList.getAction("PasteCourse").setEnabled(true);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
    }
}
