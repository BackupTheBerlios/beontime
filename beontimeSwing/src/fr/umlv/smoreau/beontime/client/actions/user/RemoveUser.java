package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
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


    public RemoveUser(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveUser(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveUser(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveUser(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
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
                    Collection formations = DaoManager.getFormationDao().getFormationsInCharge(user);
                    if (formations.size() > 0) {
                        select = JOptionPane.showConfirmDialog(null, "Cette secr�taire est en charge d'une ou plusieurs formations.\nSuite � cette suppression, des formations ne seront plus pris en charge.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (select == JOptionPane.NO_OPTION)
                            return;
                        for (Iterator i = formations.iterator(); i.hasNext(); ) {
                            Formation formation = (Formation) i.next();
                            formation.setIdSecretary(null);
                            DaoManager.getFormationDao().modifyFormation(formation);
                        }
                    }
                } else if (UserDao.TYPE_TEACHER.equals(user.getUserType())) {
                    Collection formationsResponsible = DaoManager.getFormationDao().getFormationsResponsible(user);
                    Collection subjectsResponsible = DaoManager.getTimetableDao().getSubjectsResponsible(user);
                    Collection coursesDirected = DaoManager.getTimetableDao().getCoursesDirected(user);
                    
                    StringBuffer message = new StringBuffer();
                    if (formationsResponsible.size() > 0) {
                        message.append("Cet enseignant est responsable d'une ou plusieurs formations.\n");
                    }
                    if (subjectsResponsible.size() > 0) {
                        message.append("Cet enseignant est responsable d'une ou plusieurs mati�res.\n");
                    }
                    if (coursesDirected.size() > 0) {
                        message.append("Cet enseignant dirige un ou plusieurs cours.\n");
                    }
                    if (message.length() > 0) {
                        message.append("Des formations, des mati�res et/ou des cours n'auront plus de responsable ...\n");
                        message.append("Continuer ?");
                        select = JOptionPane.showConfirmDialog(null, message.toString(), "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (select == JOptionPane.NO_OPTION)
                            return;
                        for (Iterator i = formationsResponsible.iterator(); i.hasNext(); ) {
                            Formation formation = (Formation) i.next();
                            formation.setIdSecretary(null);
                            DaoManager.getFormationDao().modifyFormation(formation);
                        }
                        for (Iterator i = subjectsResponsible.iterator(); i.hasNext(); ) {
                            Subject subject = (Subject) i.next();
                            subject.setIdTeacher(null);
                            DaoManager.getTimetableDao().modifySubject(subject);
                            
                            mainFrame.getModel().fireRefreshSubject(subject, BoTModel.TYPE_MODIFY);
                        }
                        for (Iterator i = coursesDirected.iterator(); i.hasNext(); ) {
                            Course course = (Course) i.next();
                            IsDirectedByCourseTeacher isDirected = new IsDirectedByCourseTeacher();
                            isDirected.setIdCourse(course);
                            isDirected.setIdTeacher(user.getIdUser());
                            DaoManager.getTimetableDao().removeLinkBetweenCourseAndTeacher(isDirected);
                            
                            //TODO ajouter un fire ...
                        }
                    }
                }

                DaoManager.getUserDao().removeUser(user);

                mainFrame.getModel().fireRefreshUser(user, BoTModel.TYPE_REMOVE);
                
                JOptionPane.showMessageDialog(null, "Suppression effectu�e avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
