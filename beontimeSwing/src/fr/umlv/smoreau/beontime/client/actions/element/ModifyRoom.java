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
public class ModifyRoom extends Action {
    private static final String NAME = "Modifier le local";
    private static final String ICON = "modifier_local.png";


    public ModifyRoom(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyRoom(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyRoom(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyRoom(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Room room = mainFrame.getRoomSelected();
        if (room == null)
            return;

        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_ROOM);
        window.setEquipmentName(room.getName());
        window.setDescriptionEquipment(room.getDescription());
        window.setBuildingName(room.getBuildingName());
        window.show();
        
        if (window.isOk()) {
            room.setName(window.getEquipmentName());
            room.setDescription(window.getDescriptionEquipment());
            room.setBuildingName(window.getBuildingName());

            try {
                DaoManager.getElementDao().modifyRoom(room);
                
                mainFrame.getModel().fireRefreshRoom(room, BoTModel.TYPE_MODIFY);
                
                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
