package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class AddMaterial extends Action {
    private static final String NAME = "Ajouter un matériel";
    private static final String ICON = "ajouter_materiel.png";
    private static final String SMALL_ICON = "ajouter_materiel_small.png";
    private static final String KEY_STROKE = "ctrl F";


    public AddMaterial(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_MATERIAL, AddModifyElementWindow.TYPE_ADD);
        window.show();
        
        if (window.isOk()) {
            Material material = new Material();
            material.setName(window.getEquipmentName());
            material.setDescription(window.getDescriptionEquipment());
            
            try {
                material = DaoManager.getElementDao().addMaterial(material);
                
                JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
