/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * @author BeOnTime
 */
public class TitleBar extends JPanel {
    
	private JLabel responsibleLabel;
	private JLabel intitleLabel;
	
	private JButton periodButton;
	private JButton previousButton;
	private JButton nextButton;
	
	private JPanel periodPanel = new JPanel();
	private JPanel titleBarPanel = new JPanel();
	protected TitleBar titleBar=this;
	 
	
	
	public TitleBar(BoTModel model) {
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
	
	public void setPeriod(String period) {
		periodButton.setText(period);
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
	
	public String getPeriod() {
		return periodButton.getText();
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
		
		final JDateChooser myDateChooser = new JDateChooser();
		Date date=myDateChooser.getDate();
		final SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		periodButton = new JButton(df.format(date));
		periodPanel.add(periodButton, BorderLayout.CENTER);
		periodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new fr.umlv.smoreau.beontime.client.graphics.windows.CalendarDialog(titleBar);
			}
		});
		
		previousButton = new JButton(Action.getImage("Back24.gif"));
		periodPanel.add(previousButton, BorderLayout.WEST);
		
		nextButton = new JButton(Action.getImage("Forward24.gif"));
		periodPanel.add(nextButton, BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String per=getPeriod();
				Calendar c=df.getCalendar();
				try {
					c.setTime(df.parse(per));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int d=c.get(Calendar.DAY_OF_MONTH);
				c.set(Calendar.DAY_OF_MONTH,d-1);
				setPeriod(df.format(c.getTime()));
			}
		});
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String per=getPeriod();
				Calendar c=df.getCalendar();
				try {
					c.setTime(df.parse(per));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int d=c.get(Calendar.DAY_OF_MONTH);
				c.set(Calendar.DAY_OF_MONTH,d+1);
				setPeriod(df.format(c.getTime()));
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
		    intitleLabel.setText(timetable.getFormation().getHeading());
		    responsibleLabel.setText(timetable.getPersonInCharge().getFirstName() + " " + timetable.getPersonInCharge().getName());
		    panel.validate();
		}
	}
}