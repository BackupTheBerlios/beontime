package fr.umlv.smoreau.beontime.client.actions.element;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyElementWindow;

/**
 * @author BeOnTime
 */
public class ModifyMaterial extends Action {
    private static final String NAME = "Modifier le matériel";
    private static final String ICON = "Edit24.gif";


    public ModifyMaterial(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public ModifyMaterial(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public ModifyMaterial(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public ModifyMaterial(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyElementWindow window = new AddModifyElementWindow(1);
        window.show();
    }
}
