package fr.umlv.smoreau.beontime.test;
/*
 * Created on 9 févr. 2005
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

/**
 * Test de dateChooser
 *   avec le jcalendar.jar
 *   
 * @author abrunete (BeOnTime team)
 */
public class TestDate {

	public static void main(String[] args) {
		final JFrame win = new JFrame("test : date chooser");
		//win.setLayout(new BorderLayout());
		win.getContentPane().add(new JLabel("Choisissez une date : "), BorderLayout.NORTH);
		
		final JDateChooser myDateChooser = new JDateChooser();
		win.getContentPane().add(myDateChooser);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("date : "+myDateChooser.getDate());
				win.dispose();
			}
		});
		win.getContentPane().add(btnOk, BorderLayout.SOUTH);
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.pack();
		win.setVisible(true);
	}
}
