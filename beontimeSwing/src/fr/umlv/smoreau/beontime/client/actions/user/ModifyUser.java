package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUserWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ModifyUser extends Action {
    private static final String NAME = "Modifier l'utilisateur";
    private static final String ICON = "modifier_user.png";
    private static final String SMALL_ICON = "modifier_user_small.png";


    public ModifyUser(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        User user = mainFrame.getUserSelected();
        if (user == null)
            return;

        try {
	        if (UserDao.TYPE_SECRETARY.equals(user.getUserType()))
	            user = DaoManager.getUserDao().getUser(user, new String[] {UserDao.JOIN_FORMATIONS_IN_CHARGE});
	
	        AddModifyUserWindow window = new AddModifyUserWindow(user.getUserType(), AddModifyUserWindow.TYPE_MODIFY);
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

                DaoManager.getUserDao().modifyUser(user);
                
                //mainFrame.getModel().fireRefreshUser(user, BoTModel.TYPE_MODIFY);
                
                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
	        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
