package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.ClipboardManager;
import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * @author BeOnTime
 */
public class CopyCourse extends Action {
    private static final String NAME = "Copier le cours";
    private static final String ICON = "copier.png";
    private static final String SMALL_ICON = "copier_small.png";


    public CopyCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
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
	        
	        ActionsList.getAction("PasteCourse").setEnabled(true);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
    }
}
