package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ManageElementsWindow {
	private static final String TITRE_SUBJECTS = "G�rer les mati�res";
	private static final String TITRE_GROUPS = "G�rer les groupes";
	private static final String TITRE_UNAVAILABILITIES = "G�rer les indisponibilit�s";
	private static final String TITRE_USERS = "G�rer les utilisateurs";
	private static final String TITRE_ROOMS = "G�rer les locaux";
	private static final String TITRE_MATERIALS = "G�rer les mat�riels";
	
	public static final int TYPE_SUBJECTS = 0;
	public static final int TYPE_GROUPS = 1;
	public static final int TYPE_UNAVAILABILITIES = 2;
	public static final int TYPE_USERS_BY_ADMIN = 3;
	public static final int TYPE_USERS_BY_SECRETARY = 4;
	public static final int TYPE_ROOMS = 5;
	public static final int TYPE_MATERIALS = 6;
	
	private int type;
	
	private JButton newButton;
	private JButton modifyButton;
	private JButton removeButton;
	private JButton searchUnavailabilitiesButton;
	private JButton manageIdentityButton;
	private JButton generateButton;
	
	private JPanel panel = new JPanel();
	private JPanel manageButtonPanel = new JPanel();
	private JPanel validateButtonPanel = new JPanel();
	
	private BoTModel model = new BoTModel();
	
	private JDialog MEWFrame;
	JFrame frame;
	
	
	public ManageElementsWindow(int type) {
		this.type = type;
		
		String titre = new String();
		if (type == TYPE_SUBJECTS)
			titre = TITRE_SUBJECTS;
		else if (type == TYPE_GROUPS)
			titre = TITRE_GROUPS;
		else if (type == TYPE_UNAVAILABILITIES)
			titre = TITRE_UNAVAILABILITIES;
		else if (type == TYPE_USERS_BY_ADMIN || type == TYPE_USERS_BY_SECRETARY)
			titre = TITRE_USERS;
		else if (type == TYPE_ROOMS)
			titre = TITRE_ROOMS;
		else if (type == TYPE_MATERIALS)
			titre = TITRE_MATERIALS;
  
		MEWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);   
		initManageElementsWindow(type);  
	}
	
	private void initManageElementsWindow(int type) {
		model = new BoTModel();
		
		initManageButtonPanel(type);
		MEWFrame.getContentPane().add(manageButtonPanel, BorderLayout.EAST);
		
		initPanel(type);
		MEWFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		initValidateButtonPanel();
		MEWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
	}
	
	
	private void initPanel(int type) {
		if (type == TYPE_SUBJECTS)
			panel = new ManageSubjectsTree(model, modifyButton, removeButton).getPanel();
		else if (type == TYPE_GROUPS)
			panel = new ManageGroupsTree(model, modifyButton, removeButton, manageIdentityButton, generateButton).getPanel();
		else if (type == TYPE_UNAVAILABILITIES)
			panel = new ManageUnavailabilitiesTree(model, modifyButton, removeButton, searchUnavailabilitiesButton).getPanel();
		else if (type == TYPE_USERS_BY_ADMIN) {
		    try {
                Collection users = DaoManager.getUserDao().getUsers(false);
                panel = new ManageUsersTable(model, users).getPanel();
            } catch (Exception e) {
                //TODO Sandrine: afficher un message d'erreur sur la fen�tre ...
                //Je verrais bien ce message disant "erreur lors de la r�cup�ration des utilisateurs" en rouge au dessus des boutons � droite du tableau
            }
		} else if (type == TYPE_USERS_BY_SECRETARY) {
		    try {
                Collection users = DaoManager.getUserDao().getTeachers(false);
                panel = new ManageUsersTable(model, users).getPanel();
            } catch (Exception e) {
                //TODO Sandrine: afficher un message d'erreur sur la fen�tre ...
                //Je verrais bien ce message disant "erreur lors de la r�cup�ration des utilisateurs" en rouge au dessus des boutons � droite du tableau
            }
		} else if (type == TYPE_ROOMS)
			panel = new ManageRoomsTable(model, modifyButton, removeButton).getPanel();
		else if (type == TYPE_MATERIALS)
			panel = new ManageMaterialsTable(model, modifyButton, removeButton).getPanel();
	}
	
	private void initManageButtonPanel(int type) {
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
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MEWFrame.dispose();
			}
		});
		validateButtonPanel.add(ok);
		
		JButton annuler = new JButton("annuler");
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MEWFrame.dispose();
			}
		});
		validateButtonPanel.add(annuler);
	}	
	
	private Action getActionButton(String nameButton, int type) {
		if (type == TYPE_SUBJECTS) {
			if(nameButton.compareTo("newButton") == 0)
				return ActionsList.getAction("AddSubject");
			else if(nameButton.compareTo("modifyButton") == 0)
				return ActionsList.getAction("ModifySubject");
			else if(nameButton.compareTo("removeButton") == 0)
				return ActionsList.getAction("RemoveSubject");
		}
		
		else if (type == TYPE_GROUPS) {
			if(nameButton.compareTo("newButton") == 0)
				return ActionsList.getAction("AddGroup");
			else if(nameButton.compareTo("modifyButton") == 0)
				return ActionsList.getAction("ModifyGroup");
			else if(nameButton.compareTo("removeButton") == 0)
				return ActionsList.getAction("RemoveGroup");
			else if(nameButton.compareTo("manageIdentityButton") == 0)
				return ActionsList.getAction("GenerateGroups");
			else if(nameButton.compareTo("generateButton") == 0)
				return ActionsList.getAction("ManageIdentitiesToGroups");
		}
		
		else if (type == TYPE_UNAVAILABILITIES) {
			if(nameButton.compareTo("newButton") == 0)
				return ActionsList.getAction("AddUnavailability");
			else if(nameButton.compareTo("modifyButton") == 0)
				return ActionsList.getAction("ModifyUnavailability");
			else if(nameButton.compareTo("removeButton") == 0)
				return ActionsList.getAction("RemoveUnavailability");
			else if(nameButton.compareTo("searchUnavailabilitiesButton") == 0)
				return ActionsList.getAction("SearchAvailability");
		}
		
		else if (type == TYPE_USERS_BY_ADMIN || type == TYPE_USERS_BY_SECRETARY) {
			if(nameButton.compareTo("newButton") == 0)
				return ActionsList.getAction("AddTeacher");
			else if(nameButton.compareTo("modifyButton") == 0)
				return ActionsList.getAction("ModifyUser");
			else if(nameButton.compareTo("removeButton") == 0)
				return ActionsList.getAction("RemoveUser");
		}
		
		else if (type == TYPE_ROOMS) {
			if(nameButton.compareTo("newButton") == 0)
				return ActionsList.getAction("AddRoom");
			else if(nameButton.compareTo("modifyButton") == 0)
				return ActionsList.getAction("ModifyRoom");
			else if(nameButton.compareTo("removeButton") == 0)
				return ActionsList.getAction("RemoveRoom");
		}
		
		else if (type == TYPE_MATERIALS) {
			if(nameButton.compareTo("newButton") == 0)
				return ActionsList.getAction("AddMaterial");
			else if(nameButton.compareTo("modifyButton") == 0)
				return ActionsList.getAction("ModifyMaterial");
			else if(nameButton.compareTo("removeButton") == 0)
				return ActionsList.getAction("RemoveMaterial");
		}

		return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
	 */
	public void show() {
		MEWFrame.setSize(600,450);
		MEWFrame.setResizable(false);
		MEWFrame.setLocationRelativeTo(MainFrame.getInstance().getMainFrame());
		MEWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		MEWFrame.setLocationRelativeTo(null);
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
}
