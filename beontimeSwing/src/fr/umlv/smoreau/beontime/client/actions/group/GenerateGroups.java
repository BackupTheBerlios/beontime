package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.GenerateGroupsWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class GenerateGroups extends Action {
    private static final String NAME = "Générer des groupes automatiquement";
    private static final String ICON = "groupe_auto.png";
    private static final String SMALL_ICON = "groupe_auto_small.png";


    public GenerateGroups(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        try {
            UserFilter filter = new UserFilter();
            filter.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
            Collection students = DaoManager.getUserDao().getStudents(filter);

            if (students.size() == 0) {
                JOptionPane.showMessageDialog(null, "Aucun étudiant n'a été trouvé pour cette formation", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            GenerateGroupsWindow window = new GenerateGroupsWindow(students.size());
            window.show();
            
            if (window.isOk()) {
                Collection groups = window.getNameGroups();
                User[] stud = (User[]) students.toArray(new User[students.size()]);
                int j = 0;
                int nbStudentsByGroup = students.size() / groups.size();
                int surplus = students.size() - nbStudentsByGroup * groups.size();
                for (Iterator i = groups.iterator(); i.hasNext(); ) {
                    Group group = new Group();
                    group.setHeading((String) i.next());
                    group.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
                    int k;
                    for (k = 0; k < nbStudentsByGroup; ++k)
                        group.addStudent(stud[k+j]);
                    if (surplus > 0) {
                        group.addStudent(stud[k+j]);
                        --surplus;
                        ++j;
                    }
                    j += nbStudentsByGroup;
                    
                    group = DaoManager.getGroupDao().addGroup(group);
                    
                    mainFrame.getModel().getTimetable().addGroup(group);
                    mainFrame.getModel().fireRefreshGroup(group, BoTModel.TYPE_ADD);
                }
                
                JOptionPane.showMessageDialog(null, "Ajouts effectués avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
