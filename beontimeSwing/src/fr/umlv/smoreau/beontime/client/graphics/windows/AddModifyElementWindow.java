/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class AddModifyElementWindow {

	private static String TITRE = "Ajouter une secrétaire ou un enseignant";

	private JLabel equipmentNameLabel;
	private JLabel descriptionEquipmentLabel;

	private JTextField equipmentNameJtf;
	
	private JComboBox buildingNameJcB;
	
	private JTextArea descriptionEquipmentJta;
	
	private JButton ok;
	private JButton annuler;
	
	private JPanel secretaryPanel = new JPanel();
	private JPanel teacherPanel = new JPanel();
	
	private JDialog AMEWFrame;
	private GridBagLayout AMEWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public AddModifyElementWindow(int type) {
		
		
		switch(type) {
		case 1: TITRE = "Créer un matériel"; break;
		case 2: TITRE = "Créer un local";break;
		}
		
		AMEWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		AMEWFrame.getContentPane().setLayout(AMEWLayout);
        
		initAddModifyUserWindow(type);  
	}
	
	private void initAddModifyUserWindow(int type) {
		
		switch(type) {
		case 1: initEquipmentParts(); break;
		case 2: initLocalParts();break;
		}
		
		
	}
	
	
	
	private void initEquipmentParts() {
		
		
		equipmentNameLabel = new JLabel("Nom du matériel :");
		addComponent(AMEWLayout,layoutConstraints,equipmentNameLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(equipmentNameLabel);
		
		equipmentNameJtf = new JTextField();
		addComponent(AMEWLayout,layoutConstraints,equipmentNameJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(equipmentNameJtf);
		
		
		
		descriptionEquipmentLabel = new JLabel("Description");
		addComponent(AMEWLayout,layoutConstraints,descriptionEquipmentLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(descriptionEquipmentLabel);
		
		descriptionEquipmentJta = new JTextArea();
		descriptionEquipmentJta.setLineWrap(true);
		descriptionEquipmentJta.setRows(4);
    	JScrollPane pane= new JScrollPane(descriptionEquipmentJta);
    	addComponent(AMEWLayout,layoutConstraints,pane,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,20,10));
    	AMEWFrame.getContentPane().add(pane);
		
		
		
		ok = new JButton("OK");
		addComponent(AMEWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMEWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(annuler);
		
		
		
		
	}
	
	private void initLocalParts() {
		
	}
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	AMEWFrame.pack();
    	AMEWFrame.setResizable(false);
    	AMEWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	AMEWFrame.setVisible(true);
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
    	
        	MainFrame frame = MainFrame.getInstance();
         	frame.open();
         	
         	AddModifyElementWindow form = new AddModifyElementWindow(1);
         	form.show();
    			
        } 
}
