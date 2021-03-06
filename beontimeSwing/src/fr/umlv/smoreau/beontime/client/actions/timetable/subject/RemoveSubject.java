package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class RemoveSubject extends Action {
    private static final String NAME = "Supprimer la mati�re";
    private static final String ICON = "supprimer_matiere.png";
    private static final String SMALL_ICON = "supprimer_matiere_small.png";


    public RemoveSubject(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Subject subject = mainFrame.getSubjectSelected();
        if (subject == null)
            return;
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer la mati�re '"+subject.getHeading()+"'", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            Timetable timetable = mainFrame.getModel().getTimetable();
            Collection courses = timetable.getCourses(subject);
            try {
                if (courses.size() > 0) {
                    select = JOptionPane.showConfirmDialog(null, "Des cours sont li�s � cette mati�re.\nLa suppression de la mati�re supprima aussi les cours li�s.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.NO_OPTION)
                        return;
                    for (Iterator i = courses.iterator(); i.hasNext(); ) {
                        Course course = (Course) i.next();
                        course = DaoManager.getTimetableDao().getCourse(course, new String[] {TimetableDao.JOIN_GROUPS_SUBJECTS, TimetableDao.JOIN_TEACHERS_DIRECTING});

                        // suppression des indisponibilit�s qui en d�coulent
                        UnavailabilityFilter filter = new UnavailabilityFilter();
                        filter.setIdCourse(course.getIdCourse());
                        DaoManager.getAvailabilityDao().removeUnavailability(filter);
                        
                        DaoManager.getTimetableDao().removeCourse(course);
                    }
                }
   
                DaoManager.getTimetableDao().removeSubject(subject);
                
                JOptionPane.showMessageDialog(null, "Suppression effectu�e avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
