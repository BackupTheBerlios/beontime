/*
 * Created on 21 f�vr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.actions.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintTimetable extends Action {
	
	public static javax.swing.Action getAction(String name,final MainFrame mainFrame){
		AbstractAction va=new AbstractAction(name,getImage("images/Print24.gif")) {
		public void actionPerformed(ActionEvent e) {
			}
		};
		
		return va;
	}

}
