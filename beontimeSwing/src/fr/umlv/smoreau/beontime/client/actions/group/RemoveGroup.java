package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.GroupDao;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class RemoveGroup extends Action {
    private static final String NAME = "Supprimer le groupe";
    private static final String ICON = "supprimer_groupe.png";
    private static final String SMALL_ICON = "supprimer_groupe_small.png";


    public RemoveGroup(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Group group = mainFrame.getGroupSelected();
        if (group == null) {
            group = mainFrame.getModel().getTimetable().getGroup();
            if (group == null)
                return;
        }
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le groupe '"+group.getHeading()+"'", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            Timetable timetable = mainFrame.getModel().getTimetable();
            try {
                group = DaoManager.getGroupDao().getGroup(group, new String[] {GroupDao.JOIN_STUDENTS, GroupDao.JOIN_SUBJECTS_COURSES_TAKEPART});
                if (group.getSubjectsCoursesTakePart().size() > 0) {
                    select = JOptionPane.showConfirmDialog(null, "Des cours sont liés à ce groupe.\nLa suppression du groupe supprima aussi les cours liés.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.NO_OPTION)
                        return;
                    for (Iterator i = group.getSubjectsCoursesTakePart().iterator(); i.hasNext(); ) {
                        Course course = new Course(((TakePartGroupSubjectCourse) i.next()).getIdCourse());
                        course = DaoManager.getTimetableDao().getCourse(course, new String[] {TimetableDao.JOIN_TEACHERS_DIRECTING, TimetableDao.JOIN_GROUPS_SUBJECTS});
                        DaoManager.getTimetableDao().removeCourse(course);
                    }
                }

                DaoManager.getGroupDao().removeGroup(group);
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
