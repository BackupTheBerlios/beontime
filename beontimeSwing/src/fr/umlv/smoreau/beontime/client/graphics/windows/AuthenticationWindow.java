package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class AuthenticationWindow {
	private static final String TITRE = "Connexion";
	
	private JTextField loginJtf;
	private JPasswordField passwordJtf;
	
	private JDialog IWFrame;
    private GridBagLayout IWLayout = new GridBagLayout();
	
	private boolean isOk;

	
	public AuthenticationWindow() {
		IWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		IWFrame.getContentPane().setLayout(IWLayout);
        
    	initIdentificationWindow();  
	}
	
	private void initIdentificationWindow() {
	    GridBagConstraints layoutConstraints = new GridBagConstraints();

	    JLabel loginLabel = new JLabel("Login :");
		addComponent(IWLayout,layoutConstraints,loginLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(loginLabel);
		
		loginJtf = new JTextField();
		addComponent(IWLayout,layoutConstraints,loginJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(loginJtf);
		loginJtf.setText("mduprat"); //TODO un coup de main pour les tests à virer


		JLabel passwordLabel = new JLabel("Password :");
		addComponent(IWLayout,layoutConstraints,passwordLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(passwordLabel);
		
		passwordJtf = new JPasswordField();
		addComponent(IWLayout,layoutConstraints,passwordJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(passwordJtf);
		passwordJtf.setText("mduprat"); //TODO un coup de main pour les tests à virer

		this.isOk = false;

		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                isOk = true;
                IWFrame.dispose();
            }
		});
		addComponent(IWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IWFrame.dispose();
            }
		});
		addComponent(IWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		IWFrame.getContentPane().add(annuler);
	}
	
    	
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	IWFrame.pack();
        IWFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	IWFrame.setResizable(false);
    	IWFrame.setLocationRelativeTo(null);
    	IWFrame.setVisible(true);
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    public String getLogin() {
        return loginJtf.getText();
    }
    
    public String getPassword() {
        return new String(passwordJtf.getPassword());
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
