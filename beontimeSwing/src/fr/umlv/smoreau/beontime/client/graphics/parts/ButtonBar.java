/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author BeOnTime
 */
public class ButtonBar {
	
	private JLabel visuEDTLabel;
	private JComboBox jcbTypeEDT;
	private JComboBox jcbSubjectEDT;
	
	private ArrayList listButtonDefault = new ArrayList();
	private ArrayList listButtonAdd = new ArrayList();
	
	private JPanel buttonPanel = new JPanel();
	private JPanel visuEDTPanel = new JPanel();
	private JPanel buttonBarPanel = new JPanel();
	
	public ButtonBar() {
		
		initVisuEDTPanel();
		initButtonPanel();
		
		BorderLayout buttonBarPanelLayout = new BorderLayout();
		buttonBarPanel.setLayout(buttonBarPanelLayout);
		
		buttonBarPanel.add(buttonPanel, BorderLayout.CENTER);
		buttonBarPanel.add(visuEDTPanel, BorderLayout.EAST);
		
	}
	
	public void setButtonBarPanel(JPanel panel) {
		buttonBarPanel = panel;
	}
	
	public JPanel getButtonBarPanel() {
		return buttonBarPanel;
	}
	
	private void initVisuEDTPanel() {
		
		GridBagLayout visuEDTPanelLayout = new GridBagLayout();
	    GridBagConstraints layoutConstraints = new GridBagConstraints();

	    visuEDTPanel.setLayout(visuEDTPanelLayout);
		
		visuEDTLabel = new JLabel("Visualiser un emploi du temps");
		addComponent(visuEDTPanelLayout,layoutConstraints,visuEDTLabel,1,GridBagConstraints.REMAINDER,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		visuEDTPanel.add(visuEDTLabel);
		
		
		jcbTypeEDT = new JComboBox();
		jcbTypeEDT.addItem("Formation");
		jcbTypeEDT.addItem("Enseignant");
		jcbTypeEDT.addItem("Groupe");
		jcbTypeEDT.addItem("Local");
		jcbTypeEDT.addItem("Materiel");
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbTypeEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,5,10));
		visuEDTPanel.add(jcbTypeEDT);
		
		jcbSubjectEDT = new JComboBox();
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbSubjectEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,10,10));
		visuEDTPanel.add(jcbSubjectEDT);

	}
	
	private void initButtonPanel() {
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
		
		ButtonBar bar = new ButtonBar();
		
		JFrame mafenetre = new JFrame();
		mafenetre.getContentPane().add(bar.getButtonBarPanel());
		mafenetre.setTitle("Essai td");
		mafenetre.pack();
		mafenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mafenetre.setVisible(true);
	}
	
}
