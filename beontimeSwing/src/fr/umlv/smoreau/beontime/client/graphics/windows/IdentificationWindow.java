/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class IdentificationWindow {

	private static final String TITRE = "Connexion";

	private JLabel loginLabel;
	private JLabel passwordLabel;
	
	private JTextField loginJtf;
	private JTextField passwordJtf;
	
	private JButton ok;
	private JButton annuler;
	
	private JDialog IWFrame;
	private GridBagLayout IWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public IdentificationWindow() {
		IWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		IWFrame.getContentPane().setLayout(IWLayout);
        
    	initIdentificationWindow();  
	}
	
	private void initIdentificationWindow() {
    	
		
		loginLabel = new JLabel("Login :");
		addComponent(IWLayout,layoutConstraints,loginLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(loginLabel);
		
		loginJtf = new JTextField();
		addComponent(IWLayout,layoutConstraints,loginJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(loginJtf);
		
		
		
		passwordLabel = new JLabel("Password :");
		addComponent(IWLayout,layoutConstraints,passwordLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(passwordLabel);
		
		passwordJtf = new JTextField();
		addComponent(IWLayout,layoutConstraints,passwordJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(passwordJtf);
		
		
		
		ok = new JButton("OK");
		addComponent(IWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(IWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(annuler);
    	
	}
	
    	
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	IWFrame.pack();
    	IWFrame.setResizable(false);
    	IWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	IWFrame.setVisible(true);
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
     	
     	IdentificationWindow form = new IdentificationWindow();
     	form.show();
			
    }    
    
}
