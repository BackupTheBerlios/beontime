/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ModifyDBParametersWindow {
	private static final String TITRE = "Paramétrer la connexion aux bases de données";

	private JLabel connectionOracleLabel;
	private JLabel nameBaseOracleLabel;
	private JLabel hostOracleLabel;
	private JLabel portOracleLabel;
	private JLabel loginOracleLabel;
	private JLabel passwordOracleLabel;
	private JLabel connectionLDAPLabel;
	private JLabel baseDNLDAPLabel;
	private JLabel hostLDAPLabel;
	private JLabel portLDAPLabel;
	
	private JTextField connectionOracleJtf;
	private JTextField nameBaseOracleJtf;
	private JTextField hostOracleJtf;
	private JTextField portOracleJtf;
	private JTextField loginOracleJtf;
	private JTextField passwordOracleJtf;
	private JTextField connectionLDAPJtf;
	private JTextField baseDNLDAPJtf;
	private JTextField hostLDAPJtf;
	private JTextField portLDAPJtf;
	
	private JButton ok;
	private JButton annuler;
	
	private JDialog MDBPWFrame;
	private GridBagLayout MDBPWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public ModifyDBParametersWindow() {
		MDBPWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
    	MDBPWFrame.getContentPane().setLayout(MDBPWLayout);
        
    	initModifyDBParametersWindow();  
	}
	
	private void initModifyDBParametersWindow() {
		
		connectionOracleLabel = new JLabel("Connexion Oracle :");
		addComponent(MDBPWLayout,layoutConstraints,connectionOracleLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(connectionOracleLabel);
		
		
		
		nameBaseOracleLabel = new JLabel("Nom de la base :");
		addComponent(MDBPWLayout,layoutConstraints,nameBaseOracleLabel,1,2,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(nameBaseOracleLabel);
		
		nameBaseOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,nameBaseOracleJtf,3,2,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(nameBaseOracleJtf);
		
		
		
		hostOracleLabel = new JLabel("Hote :");
		addComponent(MDBPWLayout,layoutConstraints,hostOracleLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(hostOracleLabel);
		
		hostOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,hostOracleJtf,2,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(hostOracleJtf);
		
		portOracleLabel = new JLabel("Port :");
		addComponent(MDBPWLayout,layoutConstraints,portOracleLabel,5,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(portOracleLabel);
		
		portOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,portOracleJtf,6,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(portOracleJtf);
		
		
		
		loginOracleLabel = new JLabel("Login :");
		addComponent(MDBPWLayout,layoutConstraints,loginOracleLabel,1,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(loginOracleLabel);
		
		loginOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,loginOracleJtf,2,4,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(loginOracleJtf);
		
		passwordOracleLabel = new JLabel("Password :");
		addComponent(MDBPWLayout,layoutConstraints,passwordOracleLabel,4,4,2,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(passwordOracleLabel);
		
		passwordOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,passwordOracleJtf,6,4,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(passwordOracleJtf);
		
		
		
		
		
	
		connectionLDAPLabel = new JLabel("Connexion LDAP :");
		addComponent(MDBPWLayout,layoutConstraints,connectionLDAPLabel,1,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(connectionLDAPLabel);
		
		
		
		baseDNLDAPLabel = new JLabel("Base DN :");
		addComponent(MDBPWLayout,layoutConstraints,baseDNLDAPLabel,1,6,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(baseDNLDAPLabel );
		
		baseDNLDAPJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,baseDNLDAPJtf,3,6,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(baseDNLDAPJtf);
		
		
		
		hostLDAPLabel = new JLabel("Hote :");
		addComponent(MDBPWLayout,layoutConstraints,hostLDAPLabel,1,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(hostLDAPLabel);
		
		baseDNLDAPJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,baseDNLDAPJtf,2,7,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(baseDNLDAPJtf);
		
		portLDAPLabel = new JLabel("Port :");
		addComponent(MDBPWLayout,layoutConstraints,portLDAPLabel,5,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(portLDAPLabel);
		
		portLDAPJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,portLDAPJtf,6,7,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(portLDAPJtf);
		
		
		
		ok = new JButton("OK");
		addComponent(MDBPWLayout,layoutConstraints,ok,5,8,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	MDBPWFrame.dispose();
            }
		});
		addComponent(MDBPWLayout,layoutConstraints,annuler,6,8,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(annuler);
	
		
	}
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	
    	MDBPWFrame.setSize(493,442);
    	MDBPWFrame.setSize(493,402);
    	MDBPWFrame.setResizable(false);
    	MDBPWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	MDBPWFrame.setVisible(true);
    	
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
     	
     	ModifyDBParametersWindow form = new ModifyDBParametersWindow();
     	form.show();
			
    }    

}
