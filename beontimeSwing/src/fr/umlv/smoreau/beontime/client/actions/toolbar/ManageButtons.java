package fr.umlv.smoreau.beontime.client.actions.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.ButtonBar;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageButtonWindow;

/**
 * @author BeOnTime
 */
public class ManageButtons extends Action {
    private static final String NAME = "Configurer la barre d'outils";
    private static final String ICON = "gerer_icone.png";
    private static final String SMALL_ICON = "gerer_icone_small.png";


    public ManageButtons(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
		final ManageButtonWindow dialog = new ManageButtonWindow(mainFrame, mainFrame.getToolBar().getDefToolBar());

		dialog.setListenerDone(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (dialog.isOk()){
					ArrayList buttons = dialog.getButtons();
					ButtonBar buttonBar = mainFrame.getToolBar();
					buttonBar.changeAll(buttons);
					/*buttonBar.addButton(null);
					buttonBar.addButton(new ManageButtons(mainFrame));*/
					buttonBar.saveToolBar();
					mainFrame.refresh();
				}
			}
		});
    }
}