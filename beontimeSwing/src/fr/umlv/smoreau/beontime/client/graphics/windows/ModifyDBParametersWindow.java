package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Database;

/**
 * @author BeOnTime
 */
public class ModifyDBParametersWindow {
	private static final String TITRE = "Paramétrer la connexion aux bases de données";
	
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
	
    private boolean isOk;
	
	private JDialog MDBPWFrame;
	private GridBagLayout MDBPWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public ModifyDBParametersWindow() {
		MDBPWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
    	MDBPWFrame.getContentPane().setLayout(MDBPWLayout);
        
    	initModifyDBParametersWindow();  
	}
	
	private void initModifyDBParametersWindow() {
	    JLabel connectionOracleLabel = new JLabel("Connexion Oracle :");
		addComponent(MDBPWLayout,layoutConstraints,connectionOracleLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(connectionOracleLabel);
		
		
		
		JLabel nameBaseOracleLabel = new JLabel("Nom de la base :");
		addComponent(MDBPWLayout,layoutConstraints,nameBaseOracleLabel,1,2,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(nameBaseOracleLabel);
		
		nameBaseOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,nameBaseOracleJtf,3,2,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(nameBaseOracleJtf);
		
		
		
		JLabel hostOracleLabel = new JLabel("Hôte :");
		addComponent(MDBPWLayout,layoutConstraints,hostOracleLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(hostOracleLabel);
		
		hostOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,hostOracleJtf,2,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(hostOracleJtf);
		
		JLabel portOracleLabel = new JLabel("Port :");
		addComponent(MDBPWLayout,layoutConstraints,portOracleLabel,5,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(portOracleLabel);
		
		portOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,portOracleJtf,6,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(portOracleJtf);
		
		
		
		JLabel loginOracleLabel = new JLabel("Login :");
		addComponent(MDBPWLayout,layoutConstraints,loginOracleLabel,1,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(loginOracleLabel);
		
		loginOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,loginOracleJtf,2,4,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(loginOracleJtf);
		
		JLabel passwordOracleLabel = new JLabel("Password :");
		addComponent(MDBPWLayout,layoutConstraints,passwordOracleLabel,4,4,2,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(passwordOracleLabel);
		
		passwordOracleJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,passwordOracleJtf,6,4,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(passwordOracleJtf);

	
		JLabel connectionLDAPLabel = new JLabel("Connexion LDAP :");
		addComponent(MDBPWLayout,layoutConstraints,connectionLDAPLabel,1,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(connectionLDAPLabel);
		
		
		
		JLabel baseDNLDAPLabel = new JLabel("Base DN :");
		addComponent(MDBPWLayout,layoutConstraints,baseDNLDAPLabel,1,6,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(baseDNLDAPLabel );
		
		baseDNLDAPJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,baseDNLDAPJtf,3,6,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
		MDBPWFrame.getContentPane().add(baseDNLDAPJtf);
		
		
		
		JLabel hostLDAPLabel = new JLabel("Hôte :");
		addComponent(MDBPWLayout,layoutConstraints,hostLDAPLabel,1,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(hostLDAPLabel);
		
		hostLDAPJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,hostLDAPJtf,2,7,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(hostLDAPJtf);
		
		JLabel portLDAPLabel = new JLabel("Port :");
		addComponent(MDBPWLayout,layoutConstraints,portLDAPLabel,5,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(portLDAPLabel);
		
		portLDAPJtf = new JTextField();
		addComponent(MDBPWLayout,layoutConstraints,portLDAPJtf,6,7,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		MDBPWFrame.getContentPane().add(portLDAPJtf);
		
		
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(MDBPWLayout,layoutConstraints,ok,5,8,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MDBPWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
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
    	MDBPWFrame.setLocationRelativeTo(null);
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
    
    public void setSqlBaseName(String baseName) {
        nameBaseOracleJtf.setText(baseName);
    }
    
    public String getSqlBaseName() {
        return nameBaseOracleJtf.getText();
    }
    
    public void setSqlHost(String host) {
        hostOracleJtf.setText(host);
    }
    
    public String getSqlHost() {
        return hostOracleJtf.getText();
    }
    
    public void setSqlPort(String port) {
        portOracleJtf.setText(port);
    }
    
    public String getSqlPort() {
        return portOracleJtf.getText();
    }
    
    public void setSqlLogin(String login) {
        loginOracleJtf.setText(login);
    }
    
    public String getSqlLogin() {
        return loginOracleJtf.getText();
    }
    
    public void setSqlPassword(String password) {
        passwordOracleJtf.setText(password);
    }
    
    public String getSqlPassword() {
        return passwordOracleJtf.getText();
    }
    
    public void setLdapBaseDN(String baseDN) {
        baseDNLDAPJtf.setText(baseDN);
    }
    
    public String getLdapBaseDN() {
        return baseDNLDAPJtf.getText();
    }
    
    public void setLdapHost(String host) {
        hostLDAPJtf.setText(host);
    }
    
    public String getLdapHost() {
        return hostLDAPJtf.getText();
    }
    
    public void setLdapPort(String port) {
        portLDAPJtf.setText(port);
    }
    
    public String getLdapPort() {
        return portLDAPJtf.getText();
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        Database sql = new Database(getSqlBaseName(), getSqlHost(), getSqlPort(), getSqlLogin(), getSqlPassword());
        try {
            if (!DaoManager.getDatabaseDao().testDatabase(sql))
                return 1;
        } catch (RemoteException e) {
            return 2;
        }
        Database ldap = new Database(getLdapBaseDN(), getLdapHost(), getLdapPort());
        try {
            if (!DaoManager.getDatabaseDao().testDatabase(ldap))
                return 3;
        } catch (RemoteException e) {
            return 4;
        }
        return 0;
    }
    
    
    private class ActionOk implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent arg0) {
            String errorMessage = null;
            switch (checking()) {
            case 0:
                isOk = true;
                MDBPWFrame.dispose();
                return;
            case 1:
                errorMessage = "Les paramètres pour la base Oracle sont invalides";
                break;
            case 2:
                errorMessage = "Erreur lors de la vérification des paramètres pour la base Oracle";
                break;
            case 3:
                errorMessage = "Les paramètres pour la base Ldap sont invalides";
                break;
            case 4:
                errorMessage = "Erreur lors de la vérification des paramètres pour la base Ldap";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
