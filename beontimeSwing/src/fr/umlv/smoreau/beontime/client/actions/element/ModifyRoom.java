package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;
import fr.umlv.smoreau.beontime.dao.ElementDao;

/**
 * @author BeOnTime
 */
public class ModifyRoom extends Action {
    private static final String NAME = "Modifier le local";
    private static final String ICON = "Edit24.gif";


    public ModifyRoom(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyRoom(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyRoom(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyRoom(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow window = new AddModifyElementWindow(ElementDao.TYPE_ROOM);
        window.show();
    }
}
