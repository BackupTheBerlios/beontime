/*
 * Created on 14 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.actions;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class Action {
	public static Icon getImage(String urli){
		URL url=Action.class.getResource(urli);
		if (url==null) return null;
		return new ImageIcon(url);
	}
	public javax.swing.Action getAction(){
		return null;		
	}
}
