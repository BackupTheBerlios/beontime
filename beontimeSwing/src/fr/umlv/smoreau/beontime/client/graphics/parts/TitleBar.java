package fr.umlv.smoreau.beontime.client.graphics.parts;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;


/**
 * Manages the title bar of the swing application including title labels and date chooser
 * @author BeOnTime
 */
public class TitleBar extends JPanel {
	private JDateChooser periodChooser;
	private JLabel semesterChooser;
	private JButton previousButton;
	private JButton nextButton;
	
	private JPanel periodPanel = new JPanel();
	private JPanel titleBarPanel = new JPanel();
	protected TitleBar titleBar=this;
	 
	private BoTModel model;
	private MainFrame mainFrame;
	
	private boolean noLoadTimetable;

	
	public TitleBar(BoTModel model, MainFrame mainFrame) {
		this.model = model;
		this.mainFrame = mainFrame;
		this.noLoadTimetable = false;
		titleBarPanel.setLayout(new BorderLayout());
		
		TimeTableViewPanelBar timetableViewPanel = new TimeTableViewPanelBar(mainFrame);

		initPeriodPanel();
		periodPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		titleBarPanel.add(timetableViewPanel, BorderLayout.CENTER);
		titleBarPanel.add(periodPanel, BorderLayout.EAST);
    
		model.addBoTListener(new TitleBarListener(this));
	}
	
	public void setTitleBarPanel(JPanel panel) {
		titleBarPanel = panel;
	}
	
	public Date getPeriod() {
		return periodChooser.getDate();
	}
	
	public JPanel getTitleBarPanel() {
		return titleBarPanel;
	}
	
	private void initPeriodPanel() {
		periodPanel.setLayout(new BorderLayout());
		
		periodChooser = new JDateChooser();
		periodChooser.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if (mainFrame.getViewType() == MainFrame.VIEW_WEEK) {
                    Calendar date = Calendar.getInstance();
                    date.setTime(periodChooser.getDate());
                    date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    periodChooser.setDate(date.getTime());
                    periodChooser.revalidate();
                }
                if (noLoadTimetable || mainFrame.getModel().getTimetable() == null)
                    return;
				TimetableFilter filter = new TimetableFilter(mainFrame.getModel().getTimetable());
				filter.setBeginPeriod(mainFrame.getBeginPeriod());
				filter.setEndPeriod(mainFrame.getEndPeriod());
				try {
					Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
					mainFrame.getModel().fireShowTimetable(timetable);
				} catch (Exception e) {
				    e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            }
		});
		periodChooser.setDateFormatString("dd MMMMM yyyy");
		periodChooser.setDate(Calendar.getInstance().getTime());
		periodPanel.add(periodChooser, BorderLayout.CENTER);

		previousButton = new JButton(Action.getImage("gauche_small.png"));
		periodPanel.add(previousButton, BorderLayout.WEST);
		
		nextButton = new JButton(Action.getImage("droite_small.png"));
		periodPanel.add(nextButton, BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (mainFrame.getViewType() == MainFrame.VIEW_WEEK) {
				    Calendar date = Calendar.getInstance();
				    date.setTime(periodChooser.getDate());
				    date.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR) - 1);
				    periodChooser.setDate(date.getTime());
				} else {
				    Calendar date = Calendar.getInstance();
				    date.setTime(periodChooser.getDate());
				    date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 6);
				    periodChooser.setDate(date.getTime());
				    initSemesterChooser();
				}
			}
		});
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (mainFrame.getViewType() == MainFrame.VIEW_WEEK) {
				    Calendar date = Calendar.getInstance();
				    date.setTime(periodChooser.getDate());
				    date.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR) + 1);
				    periodChooser.setDate(date.getTime());
				} else {
				    Calendar date = Calendar.getInstance();
				    date.setTime(periodChooser.getDate());
				    date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 6);
				    periodChooser.setDate(date.getTime());
				    initSemesterChooser();
				}
			}
		});
	}

	public void setViewType(int type) {
	    initSemesterChooser();
	    if (MainFrame.VIEW_HALF_YEAR == type) {
		    periodPanel.remove(periodChooser);
		    periodPanel.add(semesterChooser, BorderLayout.CENTER);
	    } else {
	        periodPanel.remove(semesterChooser);
		    periodPanel.add(periodChooser, BorderLayout.CENTER);
	    }
	    periodPanel.revalidate();
	}
	
	private void initSemesterChooser() {
		StringBuffer semester = new StringBuffer();
		Calendar date = Calendar.getInstance();
		date.setTime(periodChooser.getDate());
		semester.append("<html>");
		if (date.get(Calendar.MONTH) <= 6)
		    semester.append("1<sup>er</sup>");
		else
		    semester.append("2<sup>ème</sup>");
		semester.append(" semestre ");
		semester.append(date.get(Calendar.YEAR));
		semester.append("</html>");
		if (semesterChooser == null)
		    semesterChooser = new JLabel(semester.toString());
		else
		    semesterChooser.setText(semester.toString());
	}
	
	public void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
        constraints. gridwidth= gridwidth;
        constraints. gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.anchor = anchor;
        constraints.fill = fill;
        constraints.insets = insets;
 
        gbLayout.setConstraints(comp,constraints);
    }
	
	
	private class TitleBarListener extends DefaultBoTListener {
	    private JPanel panel;

	    public TitleBarListener(JPanel panel) {
	        this.panel = panel;
	    }

		public void refreshAll(BoTEvent e) {
		    Timetable timetable = e.getTimetable();
		    
		    noLoadTimetable = true;

		    Calendar date = Calendar.getInstance();
		    date.setTime(timetable.getBeginPeriod().getTime());
		    if (mainFrame.getViewType() == MainFrame.VIEW_HALF_YEAR)
		        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 3);
		    periodChooser.setDate(date.getTime());

		    noLoadTimetable = false;
		}
		
		public void closeTimetable(BoTEvent e) {
			initPeriodPanel();
		}
	}
}