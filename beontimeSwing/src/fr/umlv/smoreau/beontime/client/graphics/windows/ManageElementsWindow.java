/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private static final String TITRE_SUBJECTS = "Gérer les matières";
	private static final String TITRE_GROUPS = "Gérer les groupes";
	private static final String TITRE_UNAVAILABILITIES = "Gérer les indisponibilités";
	private static final String TITRE_USERS = "Gérer les utilisateurs";
	private static final String TITRE_ROOMS = "Gérer les locaux";
	private static final String TITRE_MATERIALS = "Gérer les matériels";
	
	private String type;

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
	    
	    
		MEWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);   
    	initManageElementsWindow(type);  
	}
	
	private void initManageElementsWindow(String type) {
    	
		model = new BoTModel();
		
		initManageButtonPanel();
		MEWFrame.getContentPane().add(manageButtonPanel, BorderLayout.EAST);
		
		initPanel(type);
		MEWFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		initValidateButtonPanel();
		MEWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
    	
	}
	
	
	private void initPanel(String type) {
		
		
		if (type.equals("TYPE_SUBJECTS"))
			panel = new ManageSubjectsTree(model).getPanel();
	    else if (type.equals("TYPE_GROUPS"))
	    	panel = new ManageGroupsTree(model).getPanel();
	    else if (type.equals("TYPE_UNAVAILABILITIES"))
	    {}
	    else if (type.equals("TYPE_USERS"))
	    	panel = new ManageUsersTable(model, modifyButton, deleteButton).getPanel();
	    else if (type.equals("TYPE_ROOMS"))
	    	panel = new ManageRoomsTable(model, modifyButton, deleteButton).getPanel();
	    else if (type.equals("TYPE_MATERIALS"))
	    	panel = new ManageMaterialsTable(model, modifyButton, deleteButton).getPanel();
		
		
		
		//panel = new ManageSubjectsTree(model, modifyButton, deleteButton).getPanel();
		//panel = new ManageGroupsTree(model, modifyButton, deleteButton).getPanel();
		//panel = new ManageUsersTable(model, modifyButton, deleteButton).getPanel();
		//panel = new ManageRoomsTable(model, modifyButton, deleteButton).getPanel();
		//panel = new ManageMaterialsTable(model, modifyButton, deleteButton).getPanel();
	}
	
	private void initManageButtonPanel() {
		
		manageButtonPanel.setLayout(new BoxLayout(manageButtonPanel, BoxLayout.Y_AXIS));
		//manageButtonPanel.setLayout(new GridLayout(0,1, 10, 10));
		
		newButton = new JButton("Nouveau");
		newButton.setPreferredSize(new Dimension(94,26));
		manageButtonPanel.add(newButton);
	
		modifyButton = new JButton("Modifier");
		modifyButton.setEnabled(false);
		modifyButton.setPreferredSize(new Dimension(94,26));
		modifyButton.setSize(94,26);
		manageButtonPanel.add(modifyButton);
		
		deleteButton = new JButton("Supprimer");
		deleteButton.setEnabled(false);
		manageButtonPanel.add(deleteButton);
	
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
         	
         	/*ManageElementsWindow form = new ManageElementsWindow();
         	form.show();*/
    			
        }    
}
