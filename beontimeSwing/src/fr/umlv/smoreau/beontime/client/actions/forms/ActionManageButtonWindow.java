/*
 * Created on 23 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.actions.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.ButtonBar;
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageButtonWindow;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionManageButtonWindow extends Action{



	public static javax.swing.Action getAction(String name,final MainFrame mainFrame){
		final ButtonBar toolBar=mainFrame.getToolBar();
		AbstractAction va=new AbstractAction(name,getImage("images/New24.gif")) {
		public void actionPerformed(ActionEvent e) {
			mainFrame.getToolBar();

			final ManageButtonWindow dialog = new ManageButtonWindow(mainFrame,toolBar.getDefToolBar());

			dialog.setListenerDone(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if (dialog.isOk()){
							ArrayList buttons = dialog.getButtons();
							toolBar.changeAll(buttons);
							toolBar.saveToolBar();
							mainFrame.refresh();
							toolBar.addButton("ActionManageButtonWindow","Configurer la toolbar");
						}
					}
			});
			}
		};
		
		return va;
	}


}
