/*
 * Created on 23 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.actions.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.parts.TitleBar;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionPeriodTitleBar extends Action{
	final JDateChooser myDateChooser = new JDateChooser();
	public static javax.swing.Action getAction(String name,TitleBar titleBar){
		AbstractAction va=new AbstractAction(name) {
		public void actionPerformed(ActionEvent e) {
			
			}
		};
		return va;
	}

}
