package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class RemoveCourse extends Action {
    private static final String NAME = "Supprimer le cours";
    private static final String ICON = "supprimer_cours.png";
    private static final String SMALL_ICON = "supprimer_cours_small.png";
    private static final String KEY_STROKE = "DELETE";


    public RemoveCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Course course = mainFrame.getCourseSelected();
        if (course == null)
            return;
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce cours", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            Timetable timetable = mainFrame.getModel().getTimetable();
            try {
                course = DaoManager.getTimetableDao().getCourse(course, new String[] {TimetableDao.JOIN_GROUPS_SUBJECTS, TimetableDao.JOIN_TEACHERS_DIRECTING});

                // suppression des indisponibilités qui en découlent
                UnavailabilityFilter filter = new UnavailabilityFilter();
                filter.setIdCourse(course.getIdCourse());
                DaoManager.getAvailabilityDao().removeUnavailability(filter);

                DaoManager.getTimetableDao().removeCourse(course);
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
