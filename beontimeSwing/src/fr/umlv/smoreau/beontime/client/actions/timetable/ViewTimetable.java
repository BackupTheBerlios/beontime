package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.TimeTableViewPanelBar;
import fr.umlv.smoreau.beontime.client.graphics.windows.ViewTimetableWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ViewTimetable extends Action{
    private static final String NAME = "Visualiser un emploi du temps";
    private static final String ICON = "visualiser_emploi.png";
    private static final String SMALL_ICON = "visualiser_emploi_small.png";


    public ViewTimetable(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ViewTimetableWindow window = new ViewTimetableWindow();
		window.show();
		
		if (window.isOk()) {
		    TimetableFilter filter = new TimetableFilter();

		    String type = window.getVisuEDTJcb();
		    if (TimeTableViewPanelBar.TYPE_FORMATION.equals(type))
		        filter.setFormation(new Formation(window.getChoiceEDTJcb()));
		    else if (TimeTableViewPanelBar.TYPE_ENSEIGNANT.equals(type))
		        filter.setTeacher(new User(window.getChoiceEDTJcb()));
		    else if (TimeTableViewPanelBar.TYPE_LOCAL.equals(type))
		        filter.setRoom(new Room(window.getChoiceEDTJcb()));
		    else if (TimeTableViewPanelBar.TYPE_MATERIEL.equals(type))
		        filter.setMaterial(new Material(window.getChoiceEDTJcb()));
		    else if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type)) {
		        filter.setFormation(new Formation(window.getChoiceEDTJcb()));
		        filter.setGroup(new Group(window.getChoice2EDTJcb()));
		    }
		    
		    Calendar begin = Calendar.getInstance();
			begin.setTime(window.getPeriodViewEDTDate());
			begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			begin.set(Calendar.HOUR_OF_DAY, 0);
			begin.set(Calendar.MINUTE, 0);
			begin.set(Calendar.SECOND, 0);
			filter.setBeginPeriod(begin);
			
			Calendar end = Calendar.getInstance();
			end.setTime(window.getPeriodViewEDTDate());
			end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			end.set(Calendar.HOUR_OF_DAY, 23);
			end.set(Calendar.MINUTE, 59);
			end.set(Calendar.SECOND, 59);
			filter.setEndPeriod(end);
		    
		    try {
			    Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
				mainFrame.getModel().fireShowTimetable(timetable);
			} catch (Exception e) {
			    e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
    }
}
