package fr.umlv.smoreau.beontime.client.graphics.parts;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;


/**
 * Manages the title bar of the swing application including title labels and date chooser
 * @author BeOnTime
 */
public class TitleBar extends JPanel {
	/** This class must be serializable */
	private static final long serialVersionUID = 1L;
	
	private JLabel responsibleLabel;
	private JLabel intitleLabel;
	
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
	private static final long anHalfYear = new GregorianCalendar(2005, 06, 01).getTimeInMillis() - (new GregorianCalendar(2005, 01, 01).getTimeInMillis());

	
	public TitleBar(BoTModel model) {
		this.model = model;
		titleBarPanel.setLayout(new BorderLayout());
		
		initTitleBarPanel();
		responsibleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		periodPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		titleBarPanel.add(responsibleLabel, BorderLayout.WEST);
		titleBarPanel.add(intitleLabel, BorderLayout.CENTER);
		titleBarPanel.add(periodPanel, BorderLayout.EAST);
    
		model.addBoTListener(new TitleBarListener(this));
	}
	
	
	public void setResponsible(String name) {
		responsibleLabel.setName(name);
	}
	
	public void setIntitle(String intitle) {
		intitleLabel.setName(intitle);
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
	
	public String getResponsible() {
		return responsibleLabel.getName();
	}
	
	public String getIntitleLabel() {
		return intitleLabel.getName();
	}
	
	public Date getPeriod() {
		return periodChooser.getDate();
	}
	
	public JPanel getTitleBarPanel() {
		return titleBarPanel;
	}
	
	
	private void initTitleBarPanel() {
		
		responsibleLabel = new JLabel("");
		intitleLabel = new JLabel("");
		initPeriodPanel();
		
	}
	
	private void initPeriodPanel() {
		
		periodPanel.setLayout(new BorderLayout());
		
		periodChooser = new JDateChooser();
		//TODO fire ?
		periodPanel.add(periodChooser, BorderLayout.CENTER);
		previousButton = new JButton(Action.getImage("gauche.png"));
		periodPanel.add(previousButton, BorderLayout.WEST);
		
		nextButton = new JButton(Action.getImage("droite.png"));
		periodPanel.add(nextButton, BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//TODO prendre en considération le type d'affichage : semaine / semestre
				//if week
				periodChooser.setDate(new Date(periodChooser.getDate().getTime()-aWeek));
				//if semestre
		//		periodChooser.setDate(new Date(periodChooser.getDate().getTime()-anHalfYear));	
				try {			
					model.fireShowTimetable(model.getTimetable()); //TODO modifier
				} catch (InterruptedException e) {
					System.err.println("calendar -- fire exception");
					e.printStackTrace();
				}
			
			}
		});
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//TODO prendre en considération le type d'affichage : semaine / semestre
				//if week
				periodChooser.setDate(new Date(periodChooser.getDate().getTime()+aWeek));
				//if semestre
		//		periodChooser.setDate(new Date(periodChooser.getDate().getTime()+anHalfYear));	
				try {			
					model.fireShowTimetable(model.getTimetable()); //TODO modifier
				} catch (InterruptedException e) {
					System.err.println("calendar ++ fire exception");
					e.printStackTrace();
				}
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
		    intitleLabel.setText("Formation : " + timetable.getFormation().getHeading());
		    if (timetable.getPersonInCharge()==null) {
		    	responsibleLabel.setEnabled(false);
		    	intitleLabel.setEnabled(false);
		    }
		    else {
		    	responsibleLabel.setEnabled(true);
		    	intitleLabel.setEnabled(true);
		    	responsibleLabel.setText("Responsable : " + timetable.getPersonInCharge().getFirstName() + " " + timetable.getPersonInCharge().getName());
		    }
		    //TODO : changer la date ?
		    panel.validate();
		}
	}
}