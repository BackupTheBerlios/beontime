package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class RemoveSubject extends Action {
    private static final String NAME = "Supprimer la matière";
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
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer la matière '"+subject.getHeading()+"'", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            Timetable timetable = mainFrame.getModel().getTimetable();
            Collection courses = timetable.getCourses(subject);
            try {
                if (courses.size() > 0) {
                    select = JOptionPane.showConfirmDialog(null, "Des cours sont liés à cette matière.\nLa suppression de la matière supprima aussi les cours liés.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.NO_OPTION)
                        return;
                    for (Iterator i = courses.iterator(); i.hasNext(); ) {
                        Course course = (Course) i.next();
                        DaoManager.getTimetableDao().removeCourse(course);
                        timetable.removeCourse(course);
                        mainFrame.getModel().fireRefreshCourse(course, BoTModel.TYPE_REMOVE);
                    }
                }
   
                DaoManager.getTimetableDao().removeSubject(subject);
                timetable.removeSubject(subject);
                mainFrame.getModel().fireRefreshSubject(subject, BoTModel.TYPE_REMOVE);
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
