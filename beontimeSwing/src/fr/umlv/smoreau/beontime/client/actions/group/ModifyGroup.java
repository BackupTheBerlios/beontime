package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyGroupWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class ModifyGroup extends Action {
    private static final String NAME = "Modifier le groupe";
    private static final String ICON = "modifier_groupe.png";
    private static final String SMALL_ICON = "modifier_groupe_small.png";


    public ModifyGroup(MainFrame mainFrame) {
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

        AddModifyGroupWindow window = new AddModifyGroupWindow(AddModifyGroupWindow.TYPE_MODIFY);
        window.setIntitule(group.getHeading());
        window.show();
        
        if (window.isOk()) {
            group.setHeading(window.getIntitule());

            try {
                DaoManager.getGroupDao().modifyGroup(group);
                
                mainFrame.getModel().fireRefreshGroup(group, BoTModel.TYPE_MODIFY);

                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
