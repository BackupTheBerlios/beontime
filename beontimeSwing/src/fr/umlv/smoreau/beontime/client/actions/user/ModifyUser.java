package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUserWindow;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ModifyUser extends Action {
    private static final String NAME = "Modifier l'utilisateur";
    private static final String ICON = "modifier_user.png";


    public ModifyUser(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyUser(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyUser(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyUser(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        User user = mainFrame.getUserSelected();
        if (user == null)
            return;

        AddModifyUserWindow window = new AddModifyUserWindow(user.getUserType());
        window.setName(user.getName());
        window.setSurname(user.getFirstName());
        window.setCourrielMail(user.getEMail());
        window.setBuilding(user.getBuildingNameForOffice());
        window.setLocal(user.getOfficeName());
        window.setPhone(user.getTelephone());
        window.setFormations(user.getFormationsInCharge());
        window.show();
        
        if (window.isOk()) {
            user.setName(window.getName());
            user.setFirstName(window.getSurname());
            user.setEMail(window.getCourrielMail());
            user.setBuildingNameForOffice(window.getBuilding());
            user.setOfficeName(window.getLocal());
            user.setTelephone(window.getPhone());
            user.setFormationsInCharge(window.getFormations());

            try {
                DaoManager.getUserDao().modifyUser(user);
                
                mainFrame.getModel().fireRefreshUser(user, BoTModel.TYPE_MODIFY);
                
                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
