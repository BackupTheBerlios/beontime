/*
 * 
 */
package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.umlv.smoreau.beontime.client.actions.Action;

/**
 * @author BeOnTime
 */
public class ViewTimetable extends Action{

    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.actions.Action#exec()
     */
    public void exec() {
        // TODO Raccord de méthode auto-généré

    }
	public static javax.swing.Action getViewTimetableAction(){
		AbstractAction vtta=new AbstractAction("View TimeTable",getImage("resources/.gif")) {
			public void actionPerformed(ActionEvent e) {

			}
		};
		
		return vtta;
		
	}

}
