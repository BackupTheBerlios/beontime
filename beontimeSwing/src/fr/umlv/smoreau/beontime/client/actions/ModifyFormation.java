package fr.umlv.smoreau.beontime.client.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.ModifyFormationWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Formation;

/**
 * @author BeOnTime
 */
public class ModifyFormation extends Action{
    private static final String NAME = "Modifier la formation";
    private static final String ICON = "formation.png";
    private static final String SMALL_ICON = "formation_small.png";


    public ModifyFormation(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Formation formation = mainFrame.getFormationSelected();
        if (formation == null)
            return;

        ModifyFormationWindow window = new ModifyFormationWindow();
        window.setIntituleFormation(formation.getHeading());
        window.setNameSecretaryLabel(formation.getIdSecretary().getName() + " " + formation.getIdSecretary().getFirstName());
        window.setIdTeacher(formation.getIdTeacher());
        window.setStartHalfYear1(formation.getBeginFirstHalfYear());
        window.setEndHalfYear1(formation.getEndFirstHalfYear());
        window.setStartHalfYear2(formation.getBeginSecondHalfYear());
        window.setEndHalfYear2(formation.getEndSecondHalfYear());
        window.show();
        
        if (window.isOk()) {
            formation.setHeading(window.getIntituleFormation());
            formation.setIdTeacher(window.getTeacherJcb().getIdUser());
            formation.setBeginFirstHalfYear(window.getStartHalfYear1());
            formation.setEndFirstHalfYear(window.getEndHalfYear1());
            formation.setBeginSecondHalfYear(window.getStartHalfYear2());
            formation.setEndSecondHalfYear(window.getEndHalfYear2());
            
            try {
                DaoManager.getFormationDao().modifyFormation(formation);
                
                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
