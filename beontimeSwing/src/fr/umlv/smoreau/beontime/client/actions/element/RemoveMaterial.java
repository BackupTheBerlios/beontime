package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class RemoveMaterial extends Action {
    private static final String NAME = "Supprimer le matériel";
    private static final String ICON = "supprimer_materiel.png";


    public RemoveMaterial(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public RemoveMaterial(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public RemoveMaterial(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public RemoveMaterial(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Material material = mainFrame.getMaterialSelected();
        if (material == null)
            return;
        
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
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
