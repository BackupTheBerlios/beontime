package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifySubjectWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.timetable.Subject;

/**
 * @author BeOnTime
 */
public class ModifySubject extends Action {
    private static final String NAME = "Modifier la matière";
    private static final String ICON = "modifier_matiere.png";
    private static final String SMALL_ICON = "modifier_matiere_small.png";


    public ModifySubject(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Subject subject = mainFrame.getSubjectSelected();
        if (subject == null)
            return;

        AddModifySubjectWindow window = new AddModifySubjectWindow(AddModifySubjectWindow.TYPE_MODIFY);
        window.setIntitule(subject.getHeading());
        window.setIdTeacher(subject.getIdTeacher());
        window.setIdFormation(subject.getIdFormation());
        window.setNbMagHours(subject.getNbMagHours());
        window.setNbTdHours(subject.getNbTdHours());
        window.setNbTpHours(subject.getNbTpHours());
        window.setNbMagGroups(subject.getNbMagGroups());
        window.setNbTdGroups(subject.getNbTdGroups());
        window.setNbTpGroups(subject.getNbTpGroups());
        window.show();
        
        if (window.isOk()) {
            subject.setHeading(window.getIntitule());
            subject.setIdTeacher(window.getTeacher().getIdUser());
            subject.setNbMagHours(window.getNbMagHours());
            subject.setNbTdHours(window.getNbTdHours());
            subject.setNbTpHours(window.getNbTpHours());
            subject.setNbMagGroups(window.getNbMagGroups());
            subject.setNbTdGroups(window.getNbTdGroups());
            subject.setNbTpGroups(window.getNbTpGroups());

            try {
                DaoManager.getTimetableDao().modifySubject(subject);
                
                mainFrame.getModel().fireRefreshSubject(subject, BoTModel.TYPE_MODIFY);
                
                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
