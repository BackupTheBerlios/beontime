package fr.umlv.smoreau.beontime.client.actions.availability;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUnavailabilityWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Unavailability;

/**
 * @author BeOnTime
 */
public class AddUnavailability extends Action {
    private static final String NAME = "Ajouter une indisponibilité";
    private static final String ICON = "ajouter_indisponibilite.png";
    private static final String SMALL_ICON = "ajouter_indisponibilite_small.png";
    
    public AddUnavailability(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyUnavailabilityWindow window = new AddModifyUnavailabilityWindow(AddModifyUnavailabilityWindow.TYPE_ADD);
        window.show();
        
        if (window.isOk()) {
            try {
                for (int i = 0; i < window.getRepeatUnavailability(); ++i) {
		            Unavailability unavailability = new Unavailability();
		            Calendar beginDate = window.getBeginDate();
	        	    beginDate.set(Calendar.DAY_OF_YEAR, beginDate.get(Calendar.DAY_OF_YEAR) + 7*i);
	        	    beginDate.set(Calendar.HOUR_OF_DAY, beginDate.get(Calendar.HOUR_OF_DAY) + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
		            unavailability.setBeginDate(beginDate);
		            Calendar endDate = window.getEndDate();
		            endDate.set(Calendar.DAY_OF_YEAR, endDate.get(Calendar.DAY_OF_YEAR) + 7*i);
		            endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY) + (endDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
		            unavailability.setEndDate(endDate);
		            unavailability.setIdUnavailabilityType(DaoManager.getAvailabilityDao().getTypeUnavailability(window.getTypeUnavailability()));
		            unavailability.setIdUnavailabilitySubject(window.getSubjectUnavailability());
		            unavailability.setDescription(window.getDescription());
		            
		            unavailability = DaoManager.getAvailabilityDao().addUnavailability(unavailability);
                }
	            
	            JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
