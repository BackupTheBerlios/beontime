package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class RemoveUser extends Action {
    private static final String NAME = "Supprimer l'utilisateur";
    private static final String ICON = "supprimer_user.png";
    private static final String SMALL_ICON = "supprimer_user_small.png";


    public RemoveUser(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        User user = mainFrame.getUserSelected();
        if (user == null)
            return;
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer l'utilisateur '"+user.getName()+"'", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            try {
                if (UserDao.TYPE_SECRETARY.equals(user.getUserType())) {
                    user = DaoManager.getUserDao().getUser(user, new String[] {UserDao.JOIN_FORMATIONS_IN_CHARGE});
                    if (user.getFormationsInCharge().size() > 0) {
                        select = JOptionPane.showConfirmDialog(null, "Cette secrétaire est en charge d'une ou plusieurs formations.\nSuite à cette suppression, des formations ne seront plus pris en charge.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (select == JOptionPane.NO_OPTION)
                            return;
                        for (Iterator i = user.getFormationsInCharge().iterator(); i.hasNext(); ) {
                            Formation formation = (Formation) i.next();
                            formation.setIdSecretary(null);
                            DaoManager.getFormationDao().modifyFormation(formation);
                        }
                    }
                } else if (UserDao.TYPE_TEACHER.equals(user.getUserType())) {
                    user = DaoManager.getUserDao().getUser(user, new String[] {UserDao.JOIN_FORMATIONS_RESPONSIBLE,
                                                                               UserDao.JOIN_SUBJECTS_RESPONSIBLE,
                                                                               UserDao.JOIN_COURSES_DIRECTED});
                    StringBuffer message = new StringBuffer();
                    if (user.getFormationsResponsible().size() > 0) {
                        message.append("Cet enseignant est responsable d'une ou plusieurs formations.\n");
                    }
                    if (user.getSubjectsResponsible().size() > 0) {
                        message.append("Cet enseignant est responsable d'une ou plusieurs matières.\n");
                    }
                    if (user.getCoursesDirected().size() > 0) {
                        message.append("Cet enseignant dirige un ou plusieurs cours.\n");
                    }
                    if (message.length() > 0) {
                        message.append("Des formations, des matières et/ou des cours n'auront plus de responsable ...\n");
                        message.append("Continuer ?");
                        select = JOptionPane.showConfirmDialog(null, message.toString(), "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (select == JOptionPane.NO_OPTION)
                            return;
                        for (Iterator i = user.getFormationsResponsible().iterator(); i.hasNext(); ) {
                            Formation formation = (Formation) i.next();
                            formation.setIdTeacher(null);
                            DaoManager.getFormationDao().modifyFormation(formation);
                        }
                        for (Iterator i = user.getSubjectsResponsible().iterator(); i.hasNext(); ) {
                            Subject subject = (Subject) i.next();
                            subject.setIdTeacher(null);
                            DaoManager.getTimetableDao().modifySubject(subject);
                        }
                        for (Iterator i = user.getCoursesDirected().iterator(); i.hasNext(); ) {
                            Course course = (Course) i.next();
                            IsDirectedByCourseTeacher isDirected = new IsDirectedByCourseTeacher();
                            isDirected.setIdCourse(course);
                            isDirected.setIdTeacher(user.getIdUser());
                            DaoManager.getTimetableDao().removeLinkBetweenCourseAndTeacher(isDirected);
                        }
                    }
                }

                DaoManager.getUserDao().removeUser(user);

                //mainFrame.getModel().fireRefreshUser(user, BoTModel.TYPE_REMOVE);
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
