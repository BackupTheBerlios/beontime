package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;

/**
 * @author BeOnTime
 */
public class AddMaterial extends Action {
    private static final String NAME = "Ajouter un matériel";
    private static final String ICON = "New24.gif";
    
    public AddMaterial(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddMaterial(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddMaterial(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddMaterial(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow amew=new AddModifyElementWindow(1);
		amew.show();
    }
}
