package fr.umlv.smoreau.beontime.client.actions.timetable.subject;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifySubjectWindow;
import fr.umlv.smoreau.beontime.model.timetable.Subject;

/**
 * @author BeOnTime
 */
public class AddSubject extends Action {
    private static final String NAME = "Ajouter une mati�re";
    private static final String ICON = "ajouter_matiere.png";


    public AddSubject(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddSubject(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddSubject(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddSubject(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifySubjectWindow window = new AddModifySubjectWindow(AddModifySubjectWindow.TYPE_ADD);
        window.show();
        
        if (window.isOk()) {
            Subject subject = new Subject();
            subject.setHeading(window.getIntitule());
            subject.setIdTeacher(window.getTeacher().getIdUser());
            subject.setNbMagHours(window.getNbMagHours());
            subject.setNbTdHours(window.getNbTdHours());
            subject.setNbTpHours(window.getNbTpHours());
            subject.setNbMagGroups(window.getNbMagGroups());
            subject.setNbTdGroups(window.getNbTdGroups());
            subject.setNbTpGroups(window.getNbTpGroups());
            
            subject.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
            
            try {
                subject = DaoManager.getTimetableDao().addSubject(subject);
                mainFrame.getModel().getTimetable().addSubject(subject);
                mainFrame.getModel().fireRefreshSubject(subject, BoTModel.TYPE_ADD);
                
                JOptionPane.showMessageDialog(null, "Ajout effectu� avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
