package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 */
public class AddRoom extends Action {
    private static final String NAME = "Ajouter un local";
    private static final String ICON = "ajouter_local.png";
    private static final String SMALL_ICON = "ajouter_local_small.png";


    public AddRoom(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_ROOM, AddModifyElementWindow.TYPE_ADD);
        window.show();
        
        if (window.isOk()) {
            Room room = new Room();
            room.setName(window.getEquipmentName());
            room.setDescription(window.getDescriptionEquipment());
            room.setBuildingName(window.getBuildingName());
            
            try {
                room = DaoManager.getElementDao().addRoom(room);
                
                mainFrame.getModel().fireRefreshRoom(room, BoTModel.TYPE_ADD);
                
                JOptionPane.showMessageDialog(null, "Ajout effectu� avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
