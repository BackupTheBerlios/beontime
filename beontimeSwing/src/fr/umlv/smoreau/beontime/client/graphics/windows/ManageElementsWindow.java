package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.TreePath;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.edit.Edit;

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
	
	public static final int TYPE_SUBJECTS = 0;
	public static final int TYPE_GROUPS = 1;
	public static final int TYPE_UNAVAILABILITIES = 2;
	public static final int TYPE_USERS_BY_ADMIN = 3;
	public static final int TYPE_USERS_BY_SECRETARY = 4;
	public static final int TYPE_ROOMS = 5;
	public static final int TYPE_MATERIALS = 6;
	
	private int type;
	
	private JButton newButton;
	private JButton newButton2;
	private JButton newButton3;
	private JButton modifyButton;
	private JButton removeButton;
	private JButton searchUnavailabilitiesButton;
	private JButton manageIdentityButton;
	private JButton generateButton;
	
	private JPanel panel = new JPanel();
	private JPanel manageButtonPanel = new JPanel();
	private JPanel validateButtonPanel = new JPanel();
	
	private BoTModel model;
	
	private JDialog MEWFrame;
	JFrame frame;
	
	
	public ManageElementsWindow(int type) {
		this.model = MainFrame.getInstance().getModel();
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
		initManageButtonPanel(type);
		MEWFrame.getContentPane().add(manageButtonPanel, BorderLayout.EAST);
		
		initPanel(type);
		MEWFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		initValidateButtonPanel();
		MEWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
	}
	
	
	private void initPanel(int type) {
	    JLabel label = null;

		if (type == TYPE_SUBJECTS)
			panel = new ManageSubjectsTree(model).getPanel();
		else if (type == TYPE_GROUPS) {
			panel = new ManageGroupsTree(model).getPanel();
			manageIdentityButton.setVisible(true);
			generateButton.setVisible(true);
		} else if (type == TYPE_UNAVAILABILITIES) {
			panel = new ManageUnavailabilitiesTree(model).getPanel();
			searchUnavailabilitiesButton.setVisible(true);
		} else if (type == TYPE_USERS_BY_ADMIN) {
		    try {
                Collection users = DaoManager.getUserDao().getUsers(false);
                users.remove(MainFrame.getInstance().getUserConnected());
                panel = new ManageUsersTable(model, users).getPanel();
            } catch (Exception e) {
            	panel.setLayout(new BorderLayout());
            	label = new JLabel("Erreur lors de la récupération des utilisateurs");
            }
		} else if (type == TYPE_USERS_BY_SECRETARY) {
		    try {
                Collection users = DaoManager.getUserDao().getTeachers(false);
                panel = new ManageUsersTable(model, users).getPanel();
            } catch (Exception e) {
            	panel.setLayout(new BorderLayout());
            	label = new JLabel("Erreur lors de la récupération des utilisateurs");
            }
		} else if (type == TYPE_ROOMS) {
		    try {
                Collection rooms = DaoManager.getElementDao().getRooms();
                panel = new ManageRoomsTable(model, rooms).getPanel();
            } catch (Exception e) {
            	panel.setLayout(new BorderLayout());
            	label = new JLabel("Erreur lors de la récupération des locaux");
            }
		} else if (type == TYPE_MATERIALS) {
		    try {
                Collection materials = DaoManager.getElementDao().getMaterials();
                panel = new ManageMaterialsTable(model, materials).getPanel();
            } catch (Exception e) {
            	panel.setLayout(new BorderLayout());
            	label = new JLabel("Erreur lors de la récupération des matériels");
            }
		}
		
		if (label != null) {
		    Font font = new Font("Arial", Font.BOLD, 15);
        	label.setFont(font);
        	label.setForeground(Color.RED);
        	label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        	panel.add(label, BorderLayout.CENTER);
		}
	}
	
	private void initManageButtonPanel(int type) {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		manageButtonPanel.setLayout(layout);
		
		
		newButton = new JButton(getActionButton("newButton", type));
		addComponent(layout,layoutConstraints,newButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		manageButtonPanel.add(newButton);
		
		if (type == TYPE_USERS_BY_ADMIN) {
			newButton2 = new JButton(getActionButton("newButton2", type));
			addComponent(layout,layoutConstraints,newButton2,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
			manageButtonPanel.add(newButton2);
	
			newButton3 = new JButton(getActionButton("newButton3", type));
			addComponent(layout,layoutConstraints,newButton3,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
			manageButtonPanel.add(newButton3);
		}
		
		modifyButton = new JButton(getActionButton("modifyButton", type));
		modifyButton.getAction().setEnabled(false);
		addComponent(layout,layoutConstraints,modifyButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		manageButtonPanel.add(modifyButton);
		
		removeButton = new JButton(getActionButton("removeButton", type));
		removeButton.getAction().setEnabled(false);
		addComponent(layout,layoutConstraints,removeButton,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
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
		ok.addActionListener(new ActionButton());
		validateButtonPanel.add(ok);
		
		/*JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionButton());
		validateButtonPanel.add(annuler);*/
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
				return ActionsList.getAction("ManageIdentitiesToGroups");
			else if(nameButton.compareTo("generateButton") == 0)
				return ActionsList.getAction("GenerateGroups");
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
			else if(nameButton.compareTo("newButton2") == 0)
				return ActionsList.getAction("AddSecretary");
			else if(nameButton.compareTo("newButton3") == 0)
				return ActionsList.getAction("AddAdministrator");
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
		MEWFrame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent arg0) {
            }

            public void windowClosing(WindowEvent arg0) {
                (new ActionButton()).actionPerformed(null);
            }

            public void windowClosed(WindowEvent arg0) {
            }

            public void windowIconified(WindowEvent arg0) {
            }

            public void windowDeiconified(WindowEvent arg0) {
            }

            public void windowActivated(WindowEvent arg0) {
            }

            public void windowDeactivated(WindowEvent arg0) {
            }
		});
		MEWFrame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
	
	
	private class ActionButton implements ActionListener {
	    /* (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
	    public void actionPerformed(ActionEvent arg0) {
	        if (TYPE_USERS_BY_ADMIN == type || TYPE_USERS_BY_SECRETARY == type)
	            MainFrame.getInstance().setUserSelected(null);
	        if (TYPE_ROOMS == type)
	            MainFrame.getInstance().setRoomSelected(null);
	        if (TYPE_MATERIALS == type)
	            MainFrame.getInstance().setMaterialSelected(null);
	        if (TYPE_GROUPS == type)
	            MainFrame.getInstance().setGroupSelected(null);
	        if (TYPE_SUBJECTS == type) {
	            Edit edit = MainFrame.getInstance().getEdit();
	            edit.addSelectionPath(new TreePath(edit.getModel().getRoot()));
	        }
	        MEWFrame.dispose();
	    }
	}
}
