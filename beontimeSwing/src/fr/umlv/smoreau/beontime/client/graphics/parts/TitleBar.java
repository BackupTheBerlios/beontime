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
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;


/**
 * Manages the title bar of the swing application including title labels and date chooser
 * @author BeOnTime
 */
public class TitleBar extends JPanel {
	private TimeTableViewPanelBar timetableViewPanel;
	
	private JDateChooser periodChooser;
	private JButton previousButton;
	private JButton nextButton;
	
	private JPanel periodPanel = new JPanel();
	private JPanel titleBarPanel = new JPanel();
	protected TitleBar titleBar=this;
	 
	private BoTModel model;
	
	/** time in millis for a week*/
	private static final long aWeek = new GregorianCalendar(2005, 02, 14).getTimeInMillis() - (new GregorianCalendar(2005, 02, 07).getTimeInMillis());
	/** time in millis for half a year*/
	private static final long anHalfYear = new GregorianCalendar(2005, 07, 01).getTimeInMillis() - (new GregorianCalendar(2005, 01, 01).getTimeInMillis());

	
	public TitleBar(BoTModel model, MainFrame mainFrame) {
		this.model = model;
		titleBarPanel.setLayout(new BorderLayout());
		
		timetableViewPanel = new TimeTableViewPanelBar(mainFrame);

		initPeriodPanel();
		periodPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		titleBarPanel.add(timetableViewPanel, BorderLayout.CENTER);
		titleBarPanel.add(periodPanel, BorderLayout.EAST);
    
		model.addBoTListener(new TitleBarListener(this));
	}
	
	public void setPeriod(Calendar period) {
		periodChooser.setDate(period.getTime());
	}
	
	public void setPeriod(Date period) {
		periodChooser.setDate(period);
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
                MainFrame mainFrame = MainFrame.getInstance();
                if (mainFrame.getFormationSelected() == null)
                    return;
				TimetableFilter filter = new TimetableFilter();
				filter.setFormation(mainFrame.getFormationSelected());
				Calendar begin = Calendar.getInstance();
				begin.setTime(getPeriod());
				begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				filter.setBeginPeriod(begin);
				Calendar end = Calendar.getInstance();
				end.setTime(getPeriod());
				end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				filter.setEndPeriod(end);
				try {
					Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
					mainFrame.getModel().fireShowTimetable(timetable);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            }
		});

		periodPanel.add(periodChooser, BorderLayout.CENTER);
		previousButton = new JButton(Action.getImage("gauche_small.png"));
		periodPanel.add(previousButton, BorderLayout.WEST);
		
		nextButton = new JButton(Action.getImage("droite_small.png"));
		periodPanel.add(nextButton, BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (MainFrame.getInstance().getViewType()==MainFrame.VIEW_WEEK)
					periodChooser.setDate(new Date(periodChooser.getDate().getTime()-aWeek));
				else if (MainFrame.getInstance().getViewType()==MainFrame.VIEW_HALF_YEAR) 
					periodChooser.setDate(new Date(periodChooser.getDate().getTime()-anHalfYear));
			}
		});
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (MainFrame.getInstance().getViewType()==MainFrame.VIEW_WEEK) 
					periodChooser.setDate(new Date(periodChooser.getDate().getTime()+aWeek));
				else if (MainFrame.getInstance().getViewType()==MainFrame.VIEW_HALF_YEAR) 
					periodChooser.setDate(new Date(periodChooser.getDate().getTime()+anHalfYear));	
			}
		});
		
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
		    
		    //TODO à voir quand le formulaire 'Visualiser un emploi du temps' sera fonctionnel
		}
		
		public void closeTimetable(BoTEvent e) {
		    if (e.isInitTimetableViewPanel())
		        timetableViewPanel.init();
			initPeriodPanel();
		}
	}
}