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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * @author BeOnTime
 */


public class GenerateGroupsWindow {


	private static final String TITRE = "Générer automatiquement des groupes";

	private JLabel formationLabel;
	private JLabel nbGroupsLabel;
	private JLabel nameGroup1Label;

	private JComboBox formationLabelJcb;
	private JComboBox nbGroupsLabelJcb;

	private JTextField nameGroup1Jtf;
	
	private JButton ok;
	private JButton annuler;
	
	private JFrame GGWFrame;
	private GridBagLayout GGWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public GenerateGroupsWindow (){
		GGWFrame = new JFrame(TITRE);
    	GGWFrame.getContentPane().setLayout(GGWLayout);
        
    	initGenerateGroupsWindow();  
	}
	
	public void initGenerateGroupsWindow() {
    	
		
		formationLabel = new JLabel("Formation correspondante :");
		addComponent(GGWLayout,layoutConstraints,formationLabel,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(formationLabel);
		
		formationLabelJcb = new JComboBox();
		addComponent(GGWLayout,layoutConstraints,formationLabelJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(formationLabelJcb);
		
		
		
		nbGroupsLabel = new JLabel("Nombre de groupes :");
		addComponent(GGWLayout,layoutConstraints,nbGroupsLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(nbGroupsLabel);
		
		nbGroupsLabelJcb = new JComboBox();
		addComponent(GGWLayout,layoutConstraints,nbGroupsLabelJcb,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(nbGroupsLabelJcb);
		
		
		
		nameGroup1Label = new JLabel("Nom du groupes 1 :");
		addComponent(GGWLayout,layoutConstraints,nameGroup1Label,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,20,10));
		GGWFrame.getContentPane().add(nameGroup1Label);
		
		nameGroup1Jtf = new JTextField();
		addComponent(GGWLayout,layoutConstraints,nameGroup1Jtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,20,10));
		GGWFrame.getContentPane().add(nameGroup1Jtf);
		
		
		
		ok = new JButton("OK");
		addComponent(GGWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(GGWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(annuler);
    	
	}
    	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	GGWFrame.pack();
	    GGWFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    GGWFrame.setVisible(true);
    }

    public void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
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
			
    GenerateGroupsWindow form = new GenerateGroupsWindow();
	form.show();
			
    }    
}
