package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class ModifyMaterial extends Action {
    private static final String NAME = "Modifier le mat�riel";
    private static final String ICON = "modifier_materiel.png";
    private static final String SMALL_ICON = "modifier_materiel_small.png";


    public ModifyMaterial(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Material material = mainFrame.getMaterialSelected();
        if (material == null) {
            material = mainFrame.getModel().getTimetable().getMaterial();
            if (material == null)
                return;
        }

        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_MATERIAL, AddModifyElementWindow.TYPE_MODIFY);
        window.setEquipmentName(material.getName());
        window.setDescriptionEquipment(material.getDescription());
        window.show();
        
        if (window.isOk()) {
            material.setName(window.getEquipmentName());
            material.setDescription(window.getDescriptionEquipment());

            try {
                DaoManager.getElementDao().modifyMaterial(material);
                
                JOptionPane.showMessageDialog(null, "Modification effectu�e avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
