/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.actions.forms.ButtonPlusListener;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;



/**
 * @author BeOnTime
 */
public class AddModifyUserWindow {

	
	private static String TITRE = "Ajouter une secrétaire ou un enseignant";

	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel courrielMailLabel;

	private JTextField nameJtf;
	private JTextField surnameJtf;
	private JTextField courrielMailJtf;
	
	private JButton ok;
	private JButton annuler;
	
	private JDialog AMUWFrame;
	private GridBagLayout AMUWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	public AddModifyUserWindow(int type) {
		
		
		switch(type) {
		case 1: TITRE = "Ajouter une secrétaire"; break;
		case 2: TITRE = "Ajouter un enseignant";break;
		}
		
		AMUWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		AMUWFrame.getContentPane().setLayout(AMUWLayout);
        
		initAddModifyUserWindow(type);  
	}
	
	private void initAddModifyUserWindow(int type) {
    	
		
		nameLabel = new JLabel("Nom :");
		addComponent(AMUWLayout,layoutConstraints,nameLabel,1,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(nameLabel);
		
		nameJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,nameJtf,3,1,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(nameJtf);
		
		
		
		surnameLabel = new JLabel("Prénom :");
		addComponent(AMUWLayout,layoutConstraints,surnameLabel,1,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(surnameLabel);
		
		surnameJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,surnameJtf,3,2,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(surnameJtf);
		
		
		switch(type) {
		case 1: initSecretaryParts(); break;
		case 2: initTeacherParts();break;
		}
		
		
	}
	
	
	
	private void initSecretaryParts() {
		
		
		courrielMailLabel = new JLabel("Courriel :");
		addComponent(AMUWLayout,layoutConstraints,courrielMailLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailLabel);
		
		courrielMailJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,courrielMailJtf,3,3,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailJtf);
		
		
		
		JLabel formationsLabel = new JLabel("Formations dont elle à la charge :");
		addComponent(AMUWLayout,layoutConstraints,formationsLabel,1,4,5,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(formationsLabel);
		

		
		JComboBox formationsJcb = new JComboBox();
		JPanel formationsPanel = new JPanel();
		formationsPanel.setLayout(new BoxLayout(formationsPanel, BoxLayout.Y_AXIS));
		formationsPanel.add(formationsJcb);
		formationsPanel.add(Box.createVerticalStrut(5));
    	
		addComponent(AMUWLayout,layoutConstraints,formationsPanel,1,5,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(formationsPanel);
    	
		
    	JButton formationPlusButton = new JButton("+");
    	JPanel formationsPlusPanel = new JPanel();
    	formationPlusButton.addActionListener(new ButtonPlusListener(formationsPlusPanel, formationsPanel, AMUWFrame));
    	
    	
    	formationsPlusPanel.setLayout(new BoxLayout(formationsPlusPanel, BoxLayout.Y_AXIS));
    	formationsPlusPanel.add(formationPlusButton);
    	formationsPlusPanel.add(Box.createVerticalStrut(5));
    	
    	addComponent(AMUWLayout,layoutConstraints,formationsPlusPanel,4,5,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,15,10));
    	AMUWFrame.getContentPane().add(formationsPlusPanel);
		
		
		
		ok = new JButton("OK");
		addComponent(AMUWLayout,layoutConstraints,ok,3,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMUWLayout,layoutConstraints,annuler,4,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(annuler);
		
		
		
		
	}
	
	private void initTeacherParts() {
	
		
		courrielMailLabel = new JLabel("E-mail :");
		addComponent(AMUWLayout,layoutConstraints,courrielMailLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailLabel);
		
		courrielMailJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,courrielMailJtf,3,3,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailJtf);
		
		
		
		JLabel officeLabel = new JLabel("Bureau :");
		addComponent(AMUWLayout,layoutConstraints,officeLabel,1,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(officeLabel);
		
		
		
		JLabel buildingLabel = new JLabel("Batiment :");
		addComponent(AMUWLayout,layoutConstraints,buildingLabel,2,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(buildingLabel);
		
		JComboBox buildingJcb = new JComboBox();
		addComponent(AMUWLayout,layoutConstraints,buildingJcb,3,5,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(buildingJcb);
    	
		
		
		JLabel localLabel = new JLabel("Local :");
		addComponent(AMUWLayout,layoutConstraints,localLabel,2,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(localLabel);
		
		JTextField localJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,localJtf,3,6,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(localJtf);
		
		
		
		JLabel phoneLabel = new JLabel("Téléphone :");
		addComponent(AMUWLayout,layoutConstraints,phoneLabel,1,7,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(phoneLabel);
		
		JTextField phoneJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,phoneJtf,3,7,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(phoneJtf);
		
		
    	
		
		
		
		ok = new JButton("OK");
		addComponent(AMUWLayout,layoutConstraints,ok,3,8,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMUWLayout,layoutConstraints,annuler,4,8,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(annuler);
		
		
	}
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	AMUWFrame.pack();
    	AMUWFrame.setResizable(false);
    	AMUWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	AMUWFrame.setVisible(true);
    }
    
    private static void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
	     
	constraints. gridx= gridx;
	constraints. gridy = gridy;
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
    	
        	MainFrame frame = MainFrame.getInstance();
         	frame.open();
         	
         	AddModifyUserWindow form = new AddModifyUserWindow(1);
         	form.show();
    			
        } 
    

}
