package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 */
public class RemoveRoom extends Action {
    private static final String NAME = "Supprimer le local";
    private static final String ICON = "supprimer_local.png";
    private static final String SMALL_ICON = "supprimer_local_small.png";


    public RemoveRoom(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Room room = mainFrame.getRoomSelected();
        if (room == null) {
            room = mainFrame.getModel().getTimetable().getRoom();
            if (room == null)
                return;
        }
        
        int select = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le local '"+room.getName()+"'", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (select == JOptionPane.YES_OPTION) {
            try {
                room = DaoManager.getElementDao().getRoom(room, new String[] {ElementDao.JOIN_COURSES});
                if (room.getCourses().size() > 0) {
                    select = JOptionPane.showConfirmDialog(null, "Des cours ont lieu dans ce local.\nSuite à cette suppression, ces cours n'auront peut-être plus de local défini.\nContinuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.NO_OPTION)
                        return;
                }
                DaoManager.getElementDao().removeRoom(room);

                mainFrame.getModel().fireRefreshRoom(room, BoTModel.TYPE_REMOVE);
                if (mainFrame.getRoomSelected() == null)
                    mainFrame.getModel().fireCloseTimetable();
                
                JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
