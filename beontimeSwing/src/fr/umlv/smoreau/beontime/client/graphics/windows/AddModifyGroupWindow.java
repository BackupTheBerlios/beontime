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

/**
 * @author BeOnTime
 */
public class AddModifyGroupWindow {
    private static final String TITRE = "Nouveau groupe";
    
    private JComboBox formationGroupJcb;
    
    private JTextField intitleGroupJtf;
    
    private boolean isOk;
    
    private JDialog AMGWFrame;
    private GridBagLayout AMGWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
    
    
    public AddModifyGroupWindow() {
        AMGWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
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
        
        intitleGroupJtf = new JTextField();
        addComponent(AMGWLayout,layoutConstraints,intitleGroupJtf,2,2,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
        AMGWFrame.getContentPane().add(intitleGroupJtf);
        
        
        
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
        return intitleGroupJtf.getText();
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        String intitule = getIntitule();
        if (intitule == null || "".equals(intitule))
            return 1;
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
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
