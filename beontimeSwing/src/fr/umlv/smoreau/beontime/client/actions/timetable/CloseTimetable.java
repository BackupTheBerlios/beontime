package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class CloseTimetable extends Action {
    private static final String NAME = "Fermer l'emploi du temps";
    private static final String ICON = "fermer_emploi.png";
    private static final String SMALL_ICON = "fermer_emploi_small.png";


    public CloseTimetable(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        BoTModel model = mainFrame.getModel();
        model.setTimetable(null);
        try {
            model.fireCloseTimetable();
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
