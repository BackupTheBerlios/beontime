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
import fr.umlv.smoreau.beontime.client.graphics.windows.ManageButtonWindow;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionManageButtonWindow extends Action{



	public static javax.swing.Action getAction(String name,final MainFrame mainFrame){
		AbstractAction va=new AbstractAction(name,getImage("images/New24.gif")) {
		public void actionPerformed(ActionEvent e) {
			final ManageButtonWindow dialog = new ManageButtonWindow(mainFrame,mainFrame.getToolBar().getDefToolBar());

			dialog.setListenerDone(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if (dialog.isOk()){
							ArrayList buttons = dialog.getButtons();
							mainFrame.getToolBar().changeAll(buttons);
							mainFrame.getToolBar().getDefToolBar().add("----------");
							mainFrame.getToolBar().getToolBar().addSeparator();
							mainFrame.getToolBar().addButton("ActionManageButtonWindow","Configurer la toolbar");
							mainFrame.getToolBar().saveToolBar();
							mainFrame.refresh();
						}
					}
			});
			}
		};
		
		return va;
	}


}
