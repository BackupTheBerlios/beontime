package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUnavailabilityWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class ModifyUnavailability extends Action {
    private static final String NAME = "Modifier l'indisponibilité";
    private static final String ICON = "modifier_indisponibilite.png";
    private static final String SMALL_ICON = "modifier_indisponibilite_small.png";
    
    public ModifyUnavailability(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Unavailability unavailability = mainFrame.getUnavailabilitySelected();
        if (unavailability == null)
            return;

        AddModifyUnavailabilityWindow window = new AddModifyUnavailabilityWindow(AddModifyUnavailabilityWindow.TYPE_MODIFY);
        window.setTypeUnavailability(unavailability.getIdUnavailabilityType().getNameUnavailabilityType());
        window.setSubjectUnavailability(unavailability.getIdUnavailabilitySubject());
        window.setDescription(unavailability.getDescription());
        window.setBeginDate(unavailability.getBeginDate());
        window.setEndDate(unavailability.getEndDate());
        window.show();
        
        if (window.isOk()) {
            try {
                for (int i = 0; i < window.getRepeatUnavailability(); ++i) {
		            Calendar beginDate = window.getBeginDate();
	        	    beginDate.set(Calendar.HOUR_OF_DAY, beginDate.get(Calendar.HOUR_OF_DAY) + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
		            unavailability.setBeginDate(beginDate);
		            Calendar endDate = window.getEndDate();
		            endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY) + (endDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
		            unavailability.setEndDate(endDate);
		            unavailability.setDescription(window.getDescription());
		            
		            DaoManager.getAvailabilityDao().modifyUnavailability(unavailability);
		            
		            unavailability.getBeginDate().set(Calendar.HOUR_OF_DAY, window.getBeginDate().get(Calendar.HOUR_OF_DAY));
		            unavailability.getEndDate().set(Calendar.HOUR_OF_DAY, window.getEndDate().get(Calendar.HOUR_OF_DAY));
		            mainFrame.getModel().fireRefreshUnavailability(unavailability, BoTModel.TYPE_MODIFY);
                }
	            
	            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
