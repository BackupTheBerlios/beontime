package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyGroupWindow;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class AddGroup extends Action {
    private static final String NAME = "Créer un groupe d'étudiants";
    private static final String ICON = "creer_groupe.png";
    private static final String SMALL_ICON = "creer_groupe_small.png";


    public AddGroup(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyGroupWindow window = new AddModifyGroupWindow(AddModifyGroupWindow.TYPE_ADD);
        window.show();
        
        if (window.isOk()) {
            Group group = new Group();
            group.setHeading(window.getIntitule());
            
            group.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
            
            try {
                group = DaoManager.getGroupDao().addGroup(group);
                
                mainFrame.getModel().getTimetable().addGroup(group);
                mainFrame.getModel().fireRefreshGroup(group, BoTModel.TYPE_ADD);

                JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
