package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class AddMaterial extends Action {
    private static final String NAME = "Ajouter un matériel";
    private static final String ICON = "ajouter_materiel.png";
    
    public AddMaterial(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddMaterial(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddMaterial(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddMaterial(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_MATERIAL);
        window.show();
        
        if (window.isOk()) {
            Material material = new Material();
            material.setName(window.getEquipmentName());
            material.setDescription(window.getDescriptionEquipment());
            
            try {
                DaoManager.getElementDao().addMaterial(material);
                
                JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
