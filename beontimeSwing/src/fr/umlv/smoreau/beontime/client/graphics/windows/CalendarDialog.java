/*
 * Created on 23 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.parts.TitleBar;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CalendarDialog extends JDialog{
	private TitleBar titleBar;
	private final static int X_LOCATION = 380;
	private final static int Y_LOCATION = 150;
	public CalendarDialog(TitleBar tb){
		titleBar=tb;
		init();
	}

	/**
	 * 
	 */
	private void init() {
		final JPanel jpanel = new JPanel(new BorderLayout());
		jpanel.add(new JLabel("Choisissez une date : "), BorderLayout.NORTH);
		
		final JDateChooser myDateChooser = new JDateChooser();
		jpanel.add(myDateChooser);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Date date=myDateChooser.getDate();
				SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
				titleBar.setPeriod(df.format(date));
				dispose();
			}
		});
		jpanel.add(btnOk, BorderLayout.SOUTH);
		getContentPane().add(jpanel);
		setTitle("Calendrier");
		setModal(true);
		setResizable(true);
		setLocation(X_LOCATION,Y_LOCATION);
		setSize(150,100);
		setVisible(true);
		
	}

	/**
	 * @return Returns the jDialog.
	 */

}
