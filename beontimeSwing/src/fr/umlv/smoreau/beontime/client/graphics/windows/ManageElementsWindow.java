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
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
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
				return ActionsList.getAction("AddSubject");
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return ActionsList.getAction("ModifySubject");
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return ActionsList.getAction("RemoveSubject");
			}
			
		}
		
		else if (type.compareTo("TYPE_GROUPS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return ActionsList.getAction("AddGroup");
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return ActionsList.getAction("ModifyGroup");
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return ActionsList.getAction("RemoveGroup");
			}
			
			else if(nameButton.compareTo("manageIdentityButton") == 0) {
				return ActionsList.getAction("GenerateGroups");
			}
			
			else if(nameButton.compareTo("generateButton") == 0) {
				return ActionsList.getAction("ManageIdentitiesToGroups");
			}
			
			
			
		}
		
		else if (type.compareTo("TYPE_UNAVAILABILITIES") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return ActionsList.getAction("AddUnavailability");
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return ActionsList.getAction("ModifyUnavailability");
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return ActionsList.getAction("RemoveUnavailability");
			}
			
			
			else if(nameButton.compareTo("searchUnavailabilitiesButton") == 0) {
				return ActionsList.getAction("SearchAvailability");
			}
			
			
		}
		
		
		else if (type.compareTo("TYPE_USERS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return ActionsList.getAction("AddUser");
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return ActionsList.getAction("ModifyUser");
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return ActionsList.getAction("RemoveUser");
			}
			
		}
		
		else if (type.compareTo("TYPE_ROOMS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return ActionsList.getAction("AddRoom");
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return ActionsList.getAction("ModifyRoom");
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return ActionsList.getAction("RemoveRoom");
			}
			
		}
		
		
		else if (type.compareTo("TYPE_MATERIALS") ==0) {
			
			if(nameButton.compareTo("newButton") == 0) {
				return ActionsList.getAction("AddMaterial");
			}
			
			else if(nameButton.compareTo("modifyButton") == 0) {
				return ActionsList.getAction("ModifyMaterial");
			}
			
			else if(nameButton.compareTo("removeButton") == 0) {
				return ActionsList.getAction("RemoveMaterial");
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
