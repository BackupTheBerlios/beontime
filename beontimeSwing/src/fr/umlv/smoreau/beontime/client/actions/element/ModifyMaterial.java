package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class ModifyMaterial extends Action {
    private static final String NAME = "Modifier le matériel";
    private static final String ICON = "";


    public ModifyMaterial(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyMaterial(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyMaterial(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyMaterial(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Material material = mainFrame.getMaterialSelected();
        if (material == null)
            return;

        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_MATERIAL);
        window.setEquipmentName(material.getName());
        window.setDescriptionEquipment(material.getDescription());
        window.show();
        
        if (window.isOk()) {
            material.setName(window.getEquipmentName());
            material.setDescription(window.getDescriptionEquipment());

            try {
                DaoManager.getElementDao().modifyMaterial(material);
                
                mainFrame.getModel().fireRefreshMaterial(material, BoTModel.TYPE_MODIFY);
                
                JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
