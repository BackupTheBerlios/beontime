package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.ElementDao;
import fr.umlv.smoreau.beontime.filter.MaterialFilter;
import fr.umlv.smoreau.beontime.filter.RoomFilter;

/**
 * @author BeOnTime
 */
public class AddModifyElementWindow {
    private static final String TITRE_LOCAL    = "Ajouter un local";
	private static final String TITRE_MATERIEL = "Ajouter un matériel";

	private JTextField equipmentNameJtf;
	
	private JComboBox buildingNameJcB;
	
	private JTextArea descriptionEquipmentJta;
	
	private JPanel secretaryPanel = new JPanel();
	private JPanel teacherPanel = new JPanel();
	
	private JDialog AMEWFrame;
	private GridBagLayout AMEWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
	private int type;
	private boolean isOk;
	
	
	public AddModifyElementWindow(int type) {
	    this.type = type;

	    String titre = new String();
		switch(type) {
			case ElementDao.TYPE_MATERIAL: titre = TITRE_MATERIEL; break;
			case ElementDao.TYPE_ROOM: titre = TITRE_LOCAL;break;
		}
		
		AMEWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);
		AMEWFrame.getContentPane().setLayout(AMEWLayout);
        
		initAddModifyUserWindow();  
	}
	
	private void initAddModifyUserWindow() {	
		switch(type) {
			case ElementDao.TYPE_MATERIAL: initEquipmentParts(); break;
			case ElementDao.TYPE_ROOM: initLocalParts();break;
		}
		
		JLabel descriptionEquipmentLabel = new JLabel("Description");
		addComponent(AMEWLayout,layoutConstraints,descriptionEquipmentLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(descriptionEquipmentLabel);
		
		descriptionEquipmentJta = new JTextArea();
		descriptionEquipmentJta.setLineWrap(true);
		descriptionEquipmentJta.setRows(4);
    	JScrollPane pane= new JScrollPane(descriptionEquipmentJta);
    	addComponent(AMEWLayout,layoutConstraints,pane,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,20,10));
    	AMEWFrame.getContentPane().add(pane);
		

		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(AMEWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMEWFrame.dispose();
            }
		});
		addComponent(AMEWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(annuler);
	}
	
	
	
	private void initEquipmentParts() {
	    JLabel equipmentNameLabel = new JLabel("Nom du matériel :");
		addComponent(AMEWLayout,layoutConstraints,equipmentNameLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(equipmentNameLabel);
		
		equipmentNameJtf = new JTextField();
		addComponent(AMEWLayout,layoutConstraints,equipmentNameJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(equipmentNameJtf);
	}
	
	private void initLocalParts() {
	    JLabel equipmentNameLabel = new JLabel("Nom du local :");
		addComponent(AMEWLayout,layoutConstraints,equipmentNameLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(equipmentNameLabel);
		
		equipmentNameJtf = new JTextField();
		addComponent(AMEWLayout,layoutConstraints,equipmentNameJtf,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(equipmentNameJtf);
		
		try {
            Collection buildings = DaoManager.getElementDao().getBuildings();
            buildingNameJcB = new JComboBox(buildings.toArray(new String[buildings.size()]));
        } catch (Exception e) {
            JLabel label = new JLabel("Erreur lors de la récupération des batiments existants");
            label.setForeground(Color.RED);
            addComponent(AMEWLayout,layoutConstraints,label,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
            AMEWFrame.getContentPane().add(label);
            buildingNameJcB = new JComboBox();
        }
        
        JLabel buildingNameLabel = new JLabel("Nom du batiment:");
		addComponent(AMEWLayout,layoutConstraints,buildingNameLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(buildingNameLabel);

		buildingNameJcB.setEditable(true);
		addComponent(AMEWLayout,layoutConstraints,buildingNameJcB,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		AMEWFrame.getContentPane().add(buildingNameJcB);
	}
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	AMEWFrame.pack();
    	AMEWFrame.setResizable(false);
    	AMEWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	AMEWFrame.setLocationRelativeTo(null);
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

    
    public String getEquipmentName() {
        return equipmentNameJtf.getText().trim();
    }
    
    public void setEquipmentName(String name) {
        equipmentNameJtf.setText(name);
        equipmentNameJtf.setEnabled(false);
    }
    
    public String getDescriptionEquipment() {
        return descriptionEquipmentJta.getText().trim();
    }
    
    public void setDescriptionEquipment(String description) {
        descriptionEquipmentJta.setText(description);
    }
    
    public String getBuildingName() {
        String string = (String) buildingNameJcB.getSelectedItem();
        if (string != null)
            string = string.trim();
        return string;
    }
    
    public void setBuildingName(String buildingName) {
        buildingNameJcB.setSelectedItem(buildingName);
    }

    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        String equipmentName = getEquipmentName();
        if (equipmentName == null || "".equals(equipmentName))
            return 1;
        try {
	        if (type == ElementDao.TYPE_MATERIAL) {
	            MaterialFilter filter = new MaterialFilter();
	            filter.setName(equipmentName);
	            if (DaoManager.getElementDao().getMaterials(filter).size() > 0)
	                return 2;
	        } else if (type == ElementDao.TYPE_ROOM) {
	            RoomFilter filter = new RoomFilter();
	            filter.setName(equipmentName);
	            if (DaoManager.getElementDao().getRooms(filter).size() > 0)
	                return 3;
	        }
        } catch (Exception e) {
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
                AMEWFrame.dispose();
                return;
            case 1:
                errorMessage = "Le nom est obligatoire";
                break;
            case 2:
                errorMessage = "Le nom est déjà utilisé par un matériel existant";
                break;
            case 3:
                errorMessage = "Le nom est déjà utilisé par un local existant";
                break;
            case 4:
                errorMessage = "Erreur lors de la vérification du nom ...";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
