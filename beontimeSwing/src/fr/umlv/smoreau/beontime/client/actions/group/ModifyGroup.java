package fr.umlv.smoreau.beontime.client.actions.group;

import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyGroupWindow;

/**
 * @author BeOnTime
 */
public class ModifyGroup extends Action {
    private static final String NAME = "Modifier le groupe";
    private static final String ICON = "modifier_groupe.png";
    private static final String SMALL_ICON = "modifier_groupe_small.png";


    public ModifyGroup(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyGroupWindow window = new AddModifyGroupWindow();
        window.show();
    }
}
