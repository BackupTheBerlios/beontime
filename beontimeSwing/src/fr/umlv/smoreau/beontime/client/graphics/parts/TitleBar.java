/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	
	
	public TitleBar() {
		
		titleBarPanel.setLayout(new BorderLayout());
		
		initTitleBarPanel();
		responsibleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		periodPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		titleBarPanel.add(responsibleLabel, BorderLayout.WEST);
		titleBarPanel.add(intitleLabel, BorderLayout.CENTER);
		titleBarPanel.add(periodPanel, BorderLayout.EAST);
    
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
		
		responsibleLabel = new JLabel("Responsable");
		intitleLabel = new JLabel("Intitule");
		initPeriodPanel();
		
	}
	

	private void initPeriodPanel() {
		
		periodPanel.setLayout(new BorderLayout());
		
		periodButton = new JButton("14 janvier 2005");
		periodPanel.add(periodButton, BorderLayout.CENTER);
		
		previousButton = new JButton();
		periodPanel.add(previousButton, BorderLayout.WEST);
		
		nextButton = new JButton();
		periodPanel.add(nextButton, BorderLayout.EAST);
		
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
	
	
	public static void main(String[] args){
		
		TitleBar maBar = new TitleBar();
		
		JFrame mafenetre = new JFrame();
		mafenetre.getContentPane().add(maBar.getTitleBarPanel());
		mafenetre.setTitle("Essai td");
		mafenetre.pack();
		mafenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mafenetre.setVisible(true);
		}
	
}