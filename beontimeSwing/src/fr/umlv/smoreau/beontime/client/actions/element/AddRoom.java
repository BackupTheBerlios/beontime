package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;

/**
 * @author BeOnTime
 */
public class AddRoom extends Action {
    private static final String NAME = "Ajouter un local";
    private static final String ICON = "New24.gif";


    public AddRoom(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddRoom(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddRoom(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddRoom(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow amew=new AddModifyElementWindow(2);
		amew.show();
    }
}
