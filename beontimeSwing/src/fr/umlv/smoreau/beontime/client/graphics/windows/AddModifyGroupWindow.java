package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.utils.TextFieldBoT;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.filter.GroupFilter;

/**
 * @author BeOnTime
 */
public class AddModifyGroupWindow {
    private static final String TITRE_ADD = "Nouveau groupe";
    private static final String TITRE_MODIFY = "Modifier le groupe";
    
    public static final int TYPE_ADD = 0;
	public static final int TYPE_MODIFY = 1;
    
    private JComboBox formationGroupJcb;
    
    private JTextField intituleGroupJtf;
    
    private boolean isOk;
    private int type;
    
    private JDialog AMGWFrame;
    private GridBagLayout AMGWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
    
    
    public AddModifyGroupWindow(int type) {
        this.type = type;

        String titre = new String();
        switch(type) {
        	case TYPE_ADD: titre = TITRE_ADD; break;
        	case TYPE_MODIFY: titre = TITRE_MODIFY; break;
        }
        AMGWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);
        AMGWFrame.getContentPane().setLayout(AMGWLayout);
        
        this.isOk = false;
        
        initAddModifyGroupWindow();  
    }
    
    private void initAddModifyGroupWindow() {
        JLabel formationGroupLabel = new JLabel("Formation correspondante :");
        addComponent(AMGWLayout,layoutConstraints,formationGroupLabel,1,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMGWFrame.getContentPane().add(formationGroupLabel);
        
        /*formationGroupJcb = new JComboBox();
         addComponent(AMGWLayout,layoutConstraints,formationGroupJcb,2,1,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
         AMGWFrame.getContentPane().add(formationGroupJcb);*/
        
        JLabel group = new JLabel(MainFrame.getInstance().getFormationSelected().getHeading());
        addComponent(AMGWLayout,layoutConstraints,group,2,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMGWFrame.getContentPane().add(group);
        
        
        JLabel intitleGroupLabel = new JLabel("Intilé du groupe :");
        addComponent(AMGWLayout,layoutConstraints,intitleGroupLabel,1,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMGWFrame.getContentPane().add(intitleGroupLabel);
        
        intituleGroupJtf = new TextFieldBoT(20);
        addComponent(AMGWLayout,layoutConstraints,intituleGroupJtf,2,2,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
        AMGWFrame.getContentPane().add(intituleGroupJtf);
        
        
        
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionOk());
        addComponent(AMGWLayout,layoutConstraints,ok,2,3,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMGWFrame.getContentPane().add(ok);
        
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMGWFrame.dispose();
            }
        });
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
        AMGWFrame.setLocationRelativeTo(null);
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
    
    public String getIntitule() {
        return intituleGroupJtf.getText().trim();
    }
    
    public void setIntitule(String intitule) {
        intituleGroupJtf.setText(intitule);
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        String intitule = getIntitule();
        if (intitule == null || "".equals(intitule))
            return 1;
        if (type == TYPE_ADD) {
	        try {
	            GroupFilter filter = new GroupFilter();
	            filter.setHeading(intitule);
	            if (DaoManager.getGroupDao().getGroups(filter).size() > 0)
	                return 2;
	        } catch (Exception e) {
	            return 3;
	        }
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
                AMGWFrame.dispose();
                return;
            case 1:
                errorMessage = "L'intitulé est obligatoire";
                break;
            case 2:
                errorMessage = "Le nom est déjà utilisé par un groupe existant";
                break;
            case 3:
                errorMessage = "Erreur lors de la vérification de l'intitulé ...";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
