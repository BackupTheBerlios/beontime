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
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class AddModifyGroupWindow {

	
	private static final String TITRE = "Nouveau groupe";

	private JLabel formationGroupLabel;
	private JLabel intitleGroupLabel;
	
	private JComboBox formationGroupJcb;
	
	private JTextField intitleGroupJtf;
	
	private JButton ok;
	private JButton annuler;
	
	private JDialog AMGWFrame;
	private GridBagLayout AMGWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public AddModifyGroupWindow() {
		AMGWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		AMGWFrame.getContentPane().setLayout(AMGWLayout);
        
    	initAddModifyGroupWindow();  
	}
	
	private void initAddModifyGroupWindow() {
    	
		
		formationGroupLabel = new JLabel("Formation correspondante :");
		addComponent(AMGWLayout,layoutConstraints,formationGroupLabel,1,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMGWFrame.getContentPane().add(formationGroupLabel);
		
		formationGroupJcb = new JComboBox();
		addComponent(AMGWLayout,layoutConstraints,formationGroupJcb,2,1,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMGWFrame.getContentPane().add(formationGroupJcb);
		
		
		
		intitleGroupLabel = new JLabel("Intilé du groupe :");
		addComponent(AMGWLayout,layoutConstraints,intitleGroupLabel,1,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMGWFrame.getContentPane().add(intitleGroupLabel);
		
		intitleGroupJtf = new JTextField();
		addComponent(AMGWLayout,layoutConstraints,intitleGroupJtf,2,2,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMGWFrame.getContentPane().add(intitleGroupJtf);
		
		
		
		ok = new JButton("OK");
		addComponent(AMGWLayout,layoutConstraints,ok,2,3,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMGWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMGWLayout,layoutConstraints,annuler,3,3,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMGWFrame.getContentPane().add(annuler);
    	
	}
	
    
	/* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	
    	AMGWFrame.pack();
    	AMGWFrame.setResizable(false);
    	AMGWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	AMGWFrame.setVisible(true);
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
     	
     	AddModifyGroupWindow form = new AddModifyGroupWindow();
     	form.show();
			
    }    
    
}
