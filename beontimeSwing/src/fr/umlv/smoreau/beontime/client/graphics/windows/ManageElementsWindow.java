/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ManageElementsWindow {

	
	private static final String TITRE = "Gérer les éléments";

	private JButton newButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JButton ok;
	private JButton annuler;
	
	private JPanel panel = new JPanel();
	private JPanel manageButtonPanel = new JPanel();
	private JPanel validateButtonPanel = new JPanel();
	
	private BoTModel model = new BoTModel();
	
	private JDialog MEWFrame;

	
	
	public ManageElementsWindow() {
		MEWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);   
    	initManageElementsWindow();  
	}
	
	private void initManageElementsWindow() {
    	
		model = new BoTModel();
		
		initPanel();
		MEWFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		initManageButtonPanel();
		MEWFrame.getContentPane().add(manageButtonPanel, BorderLayout.EAST);
		
		initValidateButtonPanel();
		MEWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
    	
	}
	
	
	private void initPanel() {
		
		//panel = new ManageSubjectsTree(model).getPanel();
		//panel = new ManageGroupsTree(model).getPanel();
		panel = new ManageUsersTable(model).getPanel();
	}
	
	private void initManageButtonPanel() {
		
		manageButtonPanel.setLayout(new BoxLayout(manageButtonPanel, BoxLayout.Y_AXIS));
		//manageButtonPanel.setLayout(new GridLayout(0,1, 10, 10));
		
		newButton = new JButton("Nouveau");
		newButton.setPreferredSize(new Dimension(94,26));
		manageButtonPanel.add(newButton);
	
		modifyButton = new JButton("Modifier");
		modifyButton.setPreferredSize(new Dimension(94,26));
		modifyButton.setSize(94,26);
		manageButtonPanel.add(modifyButton);
		
		deleteButton = new JButton("Supprimer");
		manageButtonPanel.add(deleteButton);
	
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
    	
    	//MEWFrame.setSize(151,148);
    	MEWFrame.pack();
    	//MEWFrame.setResizable(false);
	    MEWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    MEWFrame.setVisible(true);
	    
	    System.out.println(MEWFrame.getSize());
	    System.out.println(deleteButton.getSize());
	    System.out.println(newButton.getSize());
	    System.out.println(modifyButton.getSize());
    }

   
    
    
    public static void main(String[] args){
	
    	MainFrame frame = MainFrame.getInstance();
     	frame.open();
     	
     	ManageElementsWindow form = new ManageElementsWindow();
     	form.show();
			
    }    
}
