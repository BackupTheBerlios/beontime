package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class RemoveUnavailability extends Action {
    private static final String NAME = "Supprimer l'indisponibilit�";
    private static final String ICON = "supprimer_indisponibilite.png";
    private static final String SMALL_ICON = "supprimer_indisponibilite_small.png";
    
    public RemoveUnavailability(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Unavailability unavailability = mainFrame.getUnavailabilitySelected();
        if (unavailability == null)
            return;
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer l'indisponibilit�", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            try {
                DaoManager.getAvailabilityDao().removeUnavailability(unavailability);
                
                JOptionPane.showMessageDialog(null, "Suppression effectu�e avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
