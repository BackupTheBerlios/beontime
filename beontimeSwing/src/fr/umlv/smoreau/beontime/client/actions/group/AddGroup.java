package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyGroupWindow;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class AddGroup extends Action {
    private static final String NAME = "Cr�er un groupe d'�tudiants";
    private static final String ICON = "creer_groupe.png";
    private static final String SMALL_ICON = "creer_groupee_small.png";


    public AddGroup(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyGroupWindow window = new AddModifyGroupWindow();
        window.show();
        
        if (window.isOk()) {
            Group group = new Group();
            group.setHeading(window.getIntitule());
            
            group.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
            
            try {
                DaoManager.getGroupDao().addGroup(group);

                JOptionPane.showMessageDialog(null, "Ajout effectu� avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
