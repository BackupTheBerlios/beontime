package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class RemoveMaterial extends Action {
    private static final String NAME = "Supprimer le matériel";
    private static final String ICON = "supprimer_materiel.png";
    private static final String SMALL_ICON = "supprimer_materiel_small.png";


    public RemoveMaterial(MainFrame mainFrame) {
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
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le matériel '"+material.getName()+"'", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            try {
                material = DaoManager.getElementDao().getMaterial(material, new String[] {ElementDao.JOIN_COURSES});
                if (material.getCourses().size() > 0) {
                    select = JOptionPane.showConfirmDialog(null, "Des cours utilisent ce matériel.\nSuite à cette suppression, ces cours n'auront peut-être plus de matériel défini.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.NO_OPTION)
                        return;
                }
                DaoManager.getElementDao().removeMaterial(material);

                mainFrame.getModel().fireRefreshMaterial(material, BoTModel.TYPE_REMOVE);
                if (mainFrame.getMaterialSelected() == null)
                    mainFrame.getModel().fireCloseTimetable();
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
