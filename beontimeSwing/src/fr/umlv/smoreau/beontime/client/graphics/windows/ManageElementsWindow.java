/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.availability.AddUnavailability;
import fr.umlv.smoreau.beontime.client.actions.availability.ModifyUnavailability;
import fr.umlv.smoreau.beontime.client.actions.availability.RemoveUnavailability;
import fr.umlv.smoreau.beontime.client.actions.availability.SearchAvailability;
import fr.umlv.smoreau.beontime.client.actions.element.AddMaterial;
import fr.umlv.smoreau.beontime.client.actions.element.AddRoom;
import fr.umlv.smoreau.beontime.client.actions.element.ModifyMaterial;
import fr.umlv.smoreau.beontime.client.actions.element.ModifyRoom;
import fr.umlv.smoreau.beontime.client.actions.element.RemoveMaterial;
import fr.umlv.smoreau.beontime.client.actions.element.RemoveRoom;
import fr.umlv.smoreau.beontime.client.actions.group.AddGroup;
import fr.umlv.smoreau.beontime.client.actions.group.GenerateGroups;
import fr.umlv.smoreau.beontime.client.actions.group.ManageIdentitiesToGroups;
import fr.umlv.smoreau.beontime.client.actions.group.ModifyGroup;
import fr.umlv.smoreau.beontime.client.actions.group.RemoveGroup;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.ModifySubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.RemoveSubject;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ManageElementsWindow {
	
	private static final String TITRE_SUBJECTS = "Gérer les matières";
	private static final String TITRE_GROUPS = "Gérer les groupes";
	private static final String TITRE_UNAVAILABILITIES = "Gérer les indisponibilités";
	private static final String TITRE_USERS = "Gérer les utilisateurs";
	private static final String TITRE_ROOMS = "Gérer les locaux";
	private static final String TITRE_MATERIALS = "Gérer les matériels";
	
	private String type;
	
	private JButton newButton;
	private JButton modifyButton;
	private JButton removeButton;
	private JButton searchUnavailabilitiesButton;
	private JButton manageIdentityButton;
	private JButton generateButton;
	private JButton ok;
	private JButton annuler;
	
	private JPanel panel = new JPanel();
	private JPanel manageButtonPanel = new JPanel();
	private JPanel validateButtonPanel = new JPanel();
	
	private BoTModel model = new BoTModel();
	
	private JDialog MEWFrame;
	JFrame frame;
	
	
	public ManageElementsWindow(String type) {
		
		this.type = type;
		
		String titre = new String();
		if (type.equals("TYPE_SUBJECTS"))
			titre = TITRE_SUBJECTS;
		else if (type.equals("TYPE_GROUPS"))
			titre = TITRE_GROUPS;
		else if (type.equals("TYPE_UNAVAILABILITIES"))
			titre = TITRE_UNAVAILABILITIES;
		else if (type.equals("TYPE_USERS"))
			titre = TITRE_USERS;
		else if (type.equals("TYPE_ROOMS"))
			titre = TITRE_ROOMS;
		else if (type.equals("TYPE_MATERIALS"))
			titre = TITRE_MATERIALS;
		
		/*frame = new JFrame();
		frame.setSize(600,600);
		frame.setVisible(true);
		MEWFrame = new JDialog(frame, titre, true);*/   
		MEWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);   
		initManageElementsWindow(type);  
	}
	
	private void initManageElementsWindow(String type) {
		
		model = new BoTModel();
		
		initManageButtonPanel(type);
		MEWFrame.getContentPane().add(manageButtonPanel, BorderLayout.EAST);
		
		initPanel(type);
		MEWFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		initValidateButtonPanel();
		MEWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
		
	}
	
	
	private void initPanel(String type) {
		
		
		if (type.equals("TYPE_SUBJECTS"))
			panel = new ManageSubjectsTree(model, modifyButton, removeButton).getPanel();
		else if (type.equals("TYPE_GROUPS"))
			panel = new ManageGroupsTree(model, modifyButton, removeButton, manageIdentityButton, generateButton).getPanel();
		else if (type.equals("TYPE_UNAVAILABILITIES"))
			panel = new ManageUnavailabilitiesTree(model, modifyButton, removeButton, searchUnavailabilitiesButton).getPanel();
		else if (type.equals("TYPE_USERS"))
			panel = new ManageUsersTable(model, modifyButton, removeButton).getPanel();
		else if (type.equals("TYPE_ROOMS"))
			panel = new ManageRoomsTable(model, modifyButton, removeButton).getPanel();
		else if (type.equals("TYPE_MATERIALS"))
			panel = new ManageMaterialsTable(model, modifyButton, removeButton).getPanel();
		
		
	}
	
	private void initManageButtonPanel(String type) {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		manageButtonPanel.setLayout(layout);
		
		
		newButton = new JButton(getActionButton("newButton", type));
		addComponent(layout,layoutConstraints,newButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		manageButtonPanel.add(newButton);
		
		modifyButton = new JButton(getActionButton("modifyButton", type));
		addComponent(layout,layoutConstraints,modifyButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		modifyButton.setEnabled(false);
		manageButtonPanel.add(modifyButton);
		
		removeButton = new JButton(getActionButton("removeButton", type));
		addComponent(layout,layoutConstraints,removeButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		removeButton.setEnabled(false);
		manageButtonPanel.add(removeButton);
		
		
		searchUnavailabilitiesButton = new JButton(getActionButton("searchUnavailabilitiesButton", type));
		addComponent(layout,layoutConstraints,searchUnavailabilitiesButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		searchUnavailabilitiesButton.setVisible(false);
		manageButtonPanel.add(searchUnavailabilitiesButton);
		
		
		manageIdentityButton = new JButton(getActionButton("manageIdentityButton", type));
		addComponent(layout,layoutConstraints,manageIdentityButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		manageIdentityButton.setVisible(false);
		manageButtonPanel.add(manageIdentityButton);
		
		generateButton = new JButton(getActionButton("generateButton", type));
		addComponent(layout,layoutConstraints,generateButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		generateButton.setVisible(false);
		manageButtonPanel.add(generateButton);
		
	}
	
	private void initValidateButtonPanel() {
		
		validateButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		ok = new JButton("OK");
		validateButtonPanel.add(ok);
		
		annuler = new JButton("annuler");
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MEWFrame.dispose();
			}
		});
		validateButtonPanel.add(annuler);
		
	}	
	
	private Action getActionButton(String nameButton, String type) {
		
		if (type.compareTo("TYPE_SUBJECTS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return new AddSubject(null);
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return new ModifySubject(null);
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return new RemoveSubject(null);
			}
			
		}
		
		else if (type.compareTo("TYPE_GROUPS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return new AddGroup(null);
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return  new ModifyGroup(null);
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return new RemoveGroup(null);
			}
			
			else if(nameButton.compareTo("manageIdentityButton") == 0) {
				return new GenerateGroups(null);
			}
			
			else if(nameButton.compareTo("generateButton") == 0) {
				return new  ManageIdentitiesToGroups(null);
			}
			
			
			
		}
		
		else if (type.compareTo("TYPE_UNAVAILABILITIES") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return new AddUnavailability(null);
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return new ModifyUnavailability(null);
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return new RemoveUnavailability(null);
			}
			
			
			else if(nameButton.compareTo("searchUnavailabilitiesButton") == 0) {
				return new SearchAvailability(null);
			}
			
			
		}
		
		
		else if (type.compareTo("TYPE_USERS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return null;
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return null;
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return null;
			}
			
		}
		
		else if (type.compareTo("TYPE_ROOMS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return new AddRoom(null);
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return new ModifyRoom(null);
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return new RemoveRoom(null);
			}
			
		}
		
		
		else if (type.compareTo("TYPE_MATERIALS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return new AddMaterial(null);
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return new ModifyMaterial(null);
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return new RemoveMaterial(null);
			}
			
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
	 */
	public void show() {
		
		MEWFrame.setSize(600,450);
		MEWFrame.setResizable(false);
		//MEWFrame.setLocationRelativeTo(frame);
		MEWFrame.setLocationRelativeTo(MainFrame.getInstance().getMainFrame());
		MEWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		MEWFrame.setVisible(true);
	}
	
	private void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
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
		
		//MainFrame frame = MainFrame.getInstance();
		//frame.open();
		
		
		
		ManageElementsWindow form = new ManageElementsWindow("TYPE_UNAVAILABILITIES");
		form.show();
		
	}    
}
