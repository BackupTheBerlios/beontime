package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.BoTConfig;
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
    private static final String KEY_STROKE = "ctrl O";


    public ViewTimetable(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
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
		        filter.setFormation((Formation) window.getChoiceEDTJcb());
		    else if (TimeTableViewPanelBar.TYPE_ENSEIGNANT.equals(type))
		        filter.setTeacher((User) window.getChoiceEDTJcb());
		    else if (TimeTableViewPanelBar.TYPE_LOCAL.equals(type))
		        filter.setRoom((Room) window.getChoiceEDTJcb());
		    else if (TimeTableViewPanelBar.TYPE_MATERIEL.equals(type))
		        filter.setMaterial((Material) window.getChoiceEDTJcb());
		    else if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type)) {
		        filter.setFormation((Formation) window.getChoiceEDTJcb());
		        filter.setGroup((Group) window.getChoice2EDTJcb());
		    }
		    
		    Calendar begin = Calendar.getInstance();
		    Calendar end = Calendar.getInstance();

		    if (window.getModeViewEDT() == MainFrame.VIEW_WEEK) {
				begin.setTime(window.getPeriodViewEDTDate());
				begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				begin.set(Calendar.HOUR_OF_DAY, 0);
				begin.set(Calendar.MINUTE, 0);
				begin.set(Calendar.SECOND, 0);
				
				end.setTime(window.getPeriodViewEDTDate());
				end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				end.set(Calendar.HOUR_OF_DAY, 23);
				end.set(Calendar.MINUTE, 59);
				end.set(Calendar.SECOND, 59);
		    } else {
		        if (window.getPeriodViewEDTJcb() == 1) {
			        if (filter.getFormation() != null) {
			            if (filter.getFormation().getBeginSecondHalfYear() == null)
			                begin.setTime(BoTConfig.getBeginSecondHalfYear());
			            else
			                begin.setTime(filter.getFormation().getBeginSecondHalfYear().getTime());
			        } else {
			            begin.setTime(BoTConfig.getBeginSecondHalfYear());
			        }
			        
			        if (filter.getFormation() != null) {
			            if (filter.getFormation().getEndSecondHalfYear() == null)
			                end.setTime(BoTConfig.getEndSecondHalfYear());
			            else
			                end.setTime(filter.getFormation().getEndSecondHalfYear().getTime());
			        } else {
			            end.setTime(BoTConfig.getEndSecondHalfYear());
			        }
			        
			        begin.set(Calendar.YEAR, window.getYear());
			        end.set(Calendar.YEAR, window.getYear());
			        if (begin.getTimeInMillis() > end.getTimeInMillis())
			            begin.set(Calendar.YEAR, begin.get(Calendar.YEAR) - 1);
		        } else {
		            if (filter.getFormation() != null) {
			            if (filter.getFormation().getBeginFirstHalfYear() == null)
			                begin.setTime(BoTConfig.getBeginFirstHalfYear());
			            else
			                begin.setTime(filter.getFormation().getBeginFirstHalfYear().getTime());
			        } else {
			            begin.setTime(BoTConfig.getBeginFirstHalfYear());
			        }
			        
			        if (filter.getFormation() != null) {
			            if (filter.getFormation().getEndFirstHalfYear() == null)
			                end.setTime(BoTConfig.getEndFirstHalfYear());
			            else
			                end.setTime(filter.getFormation().getEndFirstHalfYear().getTime());
			        } else {
			            end.setTime(BoTConfig.getEndFirstHalfYear());
			        }
			        
			        begin.set(Calendar.YEAR, window.getYear());
			        end.set(Calendar.YEAR, window.getYear());
			        if (begin.getTimeInMillis() > end.getTimeInMillis())
			            end.set(Calendar.YEAR, end.get(Calendar.YEAR) + 1);
		        }
		    }

			filter.setBeginPeriod(begin);
			filter.setEndPeriod(end);
		    
		    try {
			    Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);

			    mainFrame.setViewType(window.getModeViewEDT());

				mainFrame.getModel().fireShowTimetable(timetable);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
    }
}
