package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.model.Formation;



/**
 * @author BeOnTime
 */
public class AddModifyUserWindow {
	private static final String TITRE_SECRETAIRE = "Ajouter une secrétaire";
	private static final String TITRE_ENSEIGNANT = "Ajouter un enseignant";
	private static final String TITRE_ADMINISTRATEUR = "Ajouter un administrateur";
	
	private String type;

	private JTextField nameJtf;
	private JTextField surnameJtf;
	private JTextField courrielMailJtf;
	private JComboBox buildingJcb;
	private JTextField localJtf;
	private JTextField phoneJtf;
	private JComboBox formationsJcb;
	private String[] formationsName;
	private Long[] formationsId;
	
	private JPanel formationsPanel;
	
	private boolean isOk;

	private JDialog AMUWFrame;
	private GridBagLayout AMUWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	public AddModifyUserWindow(String type) {
	    this.type = type;
	    this.isOk = false;
	    
	    String titre = new String();
	    if (type.equals(UserDao.TYPE_SECRETARY))
	        titre = TITRE_SECRETAIRE;
	    else if (type.equals(UserDao.TYPE_TEACHER))
	        titre = TITRE_ENSEIGNANT;
	    else if (type.equals(UserDao.TYPE_ADMIN))
	        titre = TITRE_ADMINISTRATEUR;
	    
		AMUWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);
		AMUWFrame.getContentPane().setLayout(AMUWLayout);
        
		initAddModifyUserWindow();  
	}
	
	private void initAddModifyUserWindow() {
		JLabel nameLabel = new JLabel("Nom :");
		addComponent(AMUWLayout,layoutConstraints,nameLabel,1,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(nameLabel);
		
		nameJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,nameJtf,3,1,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(nameJtf);
		
		JLabel surnameLabel = new JLabel("Prénom :");
		addComponent(AMUWLayout,layoutConstraints,surnameLabel,1,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(surnameLabel);
		
		surnameJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,surnameJtf,3,2,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(surnameJtf);
		
		
		if (type.equals(UserDao.TYPE_SECRETARY))
		    initSecretaryParts();
		else if (type.equals(UserDao.TYPE_TEACHER))
			initTeacherParts();
		else if (type.equals(UserDao.TYPE_ADMIN))
			initAdminParts();
	}


	private void initSecretaryParts() {
	    JLabel courrielMailLabel = new JLabel("Courriel :");
		addComponent(AMUWLayout,layoutConstraints,courrielMailLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailLabel);
		
		courrielMailJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,courrielMailJtf,3,3,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailJtf);


		JLabel formationsLabel = new JLabel("Formations dont elle à la charge :");
		addComponent(AMUWLayout,layoutConstraints,formationsLabel,1,4,5,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(formationsLabel);
		
		formationsPanel = new JPanel();
		formationsPanel.setLayout(new BoxLayout(formationsPanel, BoxLayout.Y_AXIS));

    	JPanel formationsPlusPanel = new JPanel();
		JButton formationPlusButton = new JButton("+");

		try {
            Collection formations = DaoManager.getFormationDao().getNotAllottedFormations();
            formationsJcb = new JComboBox();
            formationsName = new String[formations.size()+1];
            formationsId = new Long[formations.size()+1];
            formationsName[0] = "";
            int j = 1;
            for (Iterator i = formations.iterator(); i.hasNext(); ++j) {
                Formation formation = (Formation) i.next();
                formationsId[j] = formation.getIdFormation();
                formationsName[j] = formation.getHeading();
            }
            formationsJcb = new JComboBox(formationsName);
            formationsPanel.add(formationsJcb);
    		formationsPanel.add(Box.createVerticalStrut(5));
            formationPlusButton.addActionListener(new ButtonPlusListener(formationsPlusPanel, formationsPanel, AMUWFrame, formationsName));
        } catch (Exception e) {
            e.printStackTrace();
            JLabel label = new JLabel("Erreur lors de la récupération des formations non attribuées");
            label.setForeground(Color.RED);
            addComponent(AMUWLayout,layoutConstraints,label,1,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
            AMUWFrame.getContentPane().add(label);
            formationsJcb = new JComboBox();
            formationsPanel.add(formationsJcb);
    		formationsPanel.add(Box.createVerticalStrut(5));
    		formationsJcb.setEnabled(false);
            formationPlusButton.setEnabled(false);
        }
    	
		addComponent(AMUWLayout,layoutConstraints,formationsPanel,1,6,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(formationsPanel);
    	
    	
    	formationsPlusPanel.setLayout(new BoxLayout(formationsPlusPanel, BoxLayout.Y_AXIS));
    	formationsPlusPanel.add(formationPlusButton);
    	formationsPlusPanel.add(Box.createVerticalStrut(5));
    	
    	addComponent(AMUWLayout,layoutConstraints,formationsPlusPanel,4,6,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,15,10));
    	AMUWFrame.getContentPane().add(formationsPlusPanel);


		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(AMUWLayout,layoutConstraints,ok,3,7,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMUWFrame.dispose();
            }
		});
		addComponent(AMUWLayout,layoutConstraints,annuler,4,7,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(annuler);
	}
	
	private void initTeacherParts() {
	    JLabel courrielMailLabel = new JLabel("E-mail :");
		addComponent(AMUWLayout,layoutConstraints,courrielMailLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailLabel);
		
		courrielMailJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,courrielMailJtf,3,3,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailJtf);
		
		
		JLabel officeLabel = new JLabel("Bureau :");
		addComponent(AMUWLayout,layoutConstraints,officeLabel,1,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(officeLabel);

		
		try {
            Collection buildings = DaoManager.getElementDao().getBuildings();
            String[] b = new String[buildings.size()+1];
            b[0] = "";
            int j = 1;
            for (Iterator i = buildings.iterator(); i.hasNext(); ++j)
                b[j] = (String) i.next();
            buildingJcb = new JComboBox(b);
        } catch (Exception e) {
            JLabel label = new JLabel("Erreur lors de la récupération des batiments existants");
            label.setForeground(Color.RED);
            addComponent(AMUWLayout,layoutConstraints,label,2,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
            AMUWFrame.getContentPane().add(label);
            buildingJcb = new JComboBox();
        }

        JLabel buildingLabel = new JLabel("Batiment :");
		addComponent(AMUWLayout,layoutConstraints,buildingLabel,2,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(buildingLabel);

		buildingJcb.setEditable(true);
		addComponent(AMUWLayout,layoutConstraints,buildingJcb,3,6,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(buildingJcb);
    	

		
		JLabel localLabel = new JLabel("Local :");
		addComponent(AMUWLayout,layoutConstraints,localLabel,2,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(localLabel);
		
		localJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,localJtf,3,7,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(localJtf);
		
		
		
		JLabel phoneLabel = new JLabel("Téléphone :");
		addComponent(AMUWLayout,layoutConstraints,phoneLabel,1,8,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(phoneLabel);
		
		phoneJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,phoneJtf,3,8,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(phoneJtf);

		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(AMUWLayout,layoutConstraints,ok,3,9,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMUWFrame.dispose();
            }
		});
		addComponent(AMUWLayout,layoutConstraints,annuler,4,9,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(annuler);
	}
	
	private void initAdminParts() {
		JLabel courrielMailLabel = new JLabel("E-mail :");
		addComponent(AMUWLayout,layoutConstraints,courrielMailLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailLabel);
		
		courrielMailJtf = new JTextField();
		addComponent(AMUWLayout,layoutConstraints,courrielMailJtf,3,3,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(courrielMailJtf);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(AMUWLayout,layoutConstraints,ok,3,4,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMUWFrame.dispose();
            }
		});
		addComponent(AMUWLayout,layoutConstraints,annuler,4,4,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(annuler);
	}
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	AMUWFrame.pack();
    	AMUWFrame.setResizable(false);
    	AMUWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	AMUWFrame.setLocationRelativeTo(null);
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
    
    public String getName() {
        return nameJtf.getText().trim();
    }
    
    public void setName(String name) {
        nameJtf.setText(name);
    }
    
    public String getSurname() {
        return surnameJtf.getText().trim();
    }
    
    public void setSurname(String surname) {
        surnameJtf.setText(surname);
    }
    
    public String getCourrielMail() {
        String tmp = courrielMailJtf.getText().trim();
        if ("".equals(tmp))
            return null;
        return tmp;
    }
    
    public void setCourrielMail(String courriel) {
        courrielMailJtf.setText(courriel);
    }
    
    public String getBuilding() {
        String string = null;
        if (buildingJcb != null) {
            string = (String) buildingJcb.getSelectedItem();
	        if (string != null)
	            string = string.trim();
        }
        return string;
    }
    
    public void setBuilding(String building) {
        if (buildingJcb != null)
            buildingJcb.setSelectedItem(building);
    }
    
    public String getLocal() {
        String tmp = null;
        if (localJtf != null) {
            localJtf.getText().trim();
            if ("".equals(tmp))
                return null;
        }
        return tmp;
    }
    
    public void setLocal(String local) {
        if (localJtf != null)
            localJtf.setText(local);
    }
    
    public String getPhone() {
        String tmp = null;
        if (phoneJtf != null) {
            phoneJtf.getText().trim();
            if ("".equals(tmp))
                return null;
        }
        return tmp;
    }
    
    public void setPhone(String phone) {
        if (phoneJtf != null)
            phoneJtf.setText(phone);
    }
    
    public Set getFormations() {
		HashSet list = new HashSet();
		Component[] components = formationsPanel.getComponents();
		
		for(int i = 0; i < components.length; ++i) {
		    if (components[i] instanceof JComboBox) {
		        int index = ((JComboBox)components[i]).getSelectedIndex();
		        if (index > 0) {
			        Formation formation = new Formation();
			        formation.setHeading(formationsName[index]);
			        formation.setIdFormation(formationsId[index]);
			        list.add(formation);
		        }
		    }
		}
		
		return list;
    }
    
    public void setFormations(Collection formations) {
		//TODO Sandrine: ajouter les formations en créant des JComboBox avec des boutons "x" ...
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        String name = getName();
        if (name == null || "".equals(name))
            return 1;
        String surname = getSurname();
        if (surname == null || "".equals(surname))
            return 2;
        String email = getCourrielMail();
        if (email != null && !"".equals(email) && !email.matches(".*@.*\\..*")) {
            return 3;
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
                AMUWFrame.dispose();
                return;
            case 1:
                errorMessage = "Le nom est obligatoire";
                break;
            case 2:
                errorMessage = "Le prénom est obligatoire";
                break;
            case 3:
                errorMessage = "L'adresse email est invalide";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
