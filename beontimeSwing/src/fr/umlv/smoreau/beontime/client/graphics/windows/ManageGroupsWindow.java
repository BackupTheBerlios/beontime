/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class ManageGroupsWindow {
	
	private static final String TITRE = "Gérer les entités des groupes";
	
	private JButton ok;
	private JButton annuler;
	
	private JPanel panelTable = new JPanel();
	private JPanel validateButtonPanel = new JPanel();
	
	private BoTModel model = new BoTModel();
	
	private JDialog MGWFrame;
	
	
	
	public ManageGroupsWindow() {
		MGWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);   
		initManageGroupsWindow();  
	}
	
	private void initManageGroupsWindow() {
		
		model = new BoTModel();
		
		initPanel();
		MGWFrame.getContentPane().add(panelTable, BorderLayout.CENTER);
		
		initValidateButtonPanel();
		MGWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
		
	}
	
	
	private void initPanel() {
	
		//TODO pour tester en local
		
		//Définition des groupes qui contiennent des étudiants
		//ces groupes sont ceux sélectionnés dans le formulaire "Gérer les groupes"
		Group group1 = new Group(new Long(1));
		group1.setHeading("Groupe1");
		HashSet set1 = new HashSet();
		set1.add(new Long (3));
		set1.add(new Long (5));
		set1.add(new Long (7));
		group1.setStudents(set1);
		
		Group group2 = new Group(new Long(2));
		group2.setHeading("Groupe2");
		HashSet set2 = new HashSet();
		set2.add(new Long (1));
		set2.add(new Long (3));
		group2.setStudents(set2);
		
		Group group3 = new Group(new Long(3));
		group3.setHeading("Groupe3");
		HashSet set3 = new HashSet();
		set3.add(new Long (1));
		set3.add(new Long (3));
		set3.add(new Long (6));
		group3.setStudents(set3);
		
		Group group4 = new Group(new Long(4));
		group4.setHeading("Groupe4");
		HashSet set4 = new HashSet();
		set4.add(new Long (4));
		set4.add(new Long (5));
		set4.add(new Long (7));
		group4.setStudents(set4);
		
		ArrayList groups = new ArrayList();
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);
		groups.add(group4);
		
		
		//Définition d'une formation contenant plusieurs groupes
		//Elle correspond à la formation sur laquelle la secrétaire est en train de travailler
		Formation formation = new Formation();
		Group group0 = new Group(new Long(0));
		group0.setHeading("Groupe0");
		HashSet set0 = new HashSet();
		set0.add(new Long (1));
		set0.add(new Long (2));
		set0.add(new Long (3));
		set0.add(new Long (4));
		set0.add(new Long (5));
		set0.add(new Long (6));
		set0.add(new Long (7));
		group0.setStudents(set0);
		
		HashSet setGroups = new HashSet();
		setGroups.add(group0);
		setGroups.add(group1);
		setGroups.add(group2);
		setGroups.add(group3);
		setGroups.add(group4);
		formation.setGroups(setGroups);
	
		
		panelTable = new ManageIdentityGroupsTable(model, formation, groups).getPanel(); 
	
	}
	
	
	private void initValidateButtonPanel() {
		
		validateButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		ok = new JButton("OK");
		validateButtonPanel.add(ok);
		
		annuler = new JButton("annuler");
		validateButtonPanel.add(annuler);
		
	}	
	
	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
	 */
	public void show() {
		
		//MGWFrame.setSize(151,148);
		MGWFrame.pack();
		//MGWFrame.setResizable(false);
		MGWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		MGWFrame.setVisible(true);
		
		System.out.println(MGWFrame.getSize());
	}
	
	
	
	
	public static void main(String[] args){
		
		MainFrame frame = MainFrame.getInstance();
		frame.open();
		
		ManageGroupsWindow form = new ManageGroupsWindow();
		form.show();
		
	}    
	
}
