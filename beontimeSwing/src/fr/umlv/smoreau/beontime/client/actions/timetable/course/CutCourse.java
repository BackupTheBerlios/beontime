package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.ClipboardManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class CutCourse extends Action {
    private static final String NAME = "Couper le cours";
    private static final String ICON = "couper.png";
    private static final String SMALL_ICON = "couper_small.png";


    public CutCourse(MainFrame mainFrame) {
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
	        
	        Timetable timetable = mainFrame.getModel().getTimetable();
	        
	        DaoManager.getTimetableDao().removeCourse(course);
            timetable.removeCourse(course);
            mainFrame.setCourseSelected(null);
            mainFrame.getModel().fireRefreshCourse(course, BoTModel.TYPE_REMOVE);
	        
	        ActionsList.getAction("PasteCourse").setEnabled(true);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
    }
}
