package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.GenerateGroupsWindow;

/**
 * @author BeOnTime
 */
public class GenerateGroups extends Action {
    private static final String NAME = "Générer des groupes automatiquement";
    private static final String ICON = "Print24.gif";


    public GenerateGroups(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public GenerateGroups(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public GenerateGroups(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public GenerateGroups(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        GenerateGroupsWindow window = new GenerateGroupsWindow();
        window.show();
    }
}
