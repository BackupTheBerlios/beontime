/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;


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
	
	private JPanel nameGroupLabelPanel = new JPanel();
	private JPanel nameGroupJtfPanel = new JPanel();
	
	private JDialog GGWFrame;
	private GridBagLayout GGWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();

	
	
	public GenerateGroupsWindow (){
		GGWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
    	GGWFrame.getContentPane().setLayout(GGWLayout);
        
    	initGenerateGroupsWindow();  
	}
	
	private void initGenerateGroupsWindow() {
    	
		
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
		initNumberJcb(nbGroupsLabelJcb, 1, 10);
		nbGroupsLabelJcb.addItemListener(new ItemListener() {
			
    		public void itemStateChanged(ItemEvent e) {
    			
    			int nbGroupsChoose = ((JComboBox)e.getSource()).getSelectedIndex()+1;
    			int nbGroupsVisible = nameGroupLabelPanel.getComponentCount()/2;
    			
    			if (nbGroupsChoose > nbGroupsVisible) {
    				
    				int nbSup = nbGroupsChoose-nbGroupsVisible;
    				
    				for(int i=nbSup-1;i>=0;i--) {
    					nameGroupLabelPanel.add(new JLabel("Nom du groupe "+(nbGroupsChoose-i)+" :"));
    					nameGroupLabelPanel.add(Box.createVerticalStrut(9));
    				
    					nameGroupJtfPanel.add(new JTextField());
    					nameGroupJtfPanel.add(Box.createVerticalStrut(5));
    				
    				}
    				GGWFrame.pack();
    			}
    			
    			if (nbGroupsChoose < nbGroupsVisible) {
    				
    				int nbInf = nbGroupsVisible - nbGroupsChoose;
        			int position = nbGroupsVisible*2;
        			
    				for(int i=1;i<=nbInf;i++) {
    				
    					nameGroupLabelPanel.remove(position-i);
    					nameGroupLabelPanel.remove(position-(i+1));
    				
    					nameGroupJtfPanel.remove(position-i);
    					nameGroupJtfPanel.remove(position-(i+1));
    					
    					position-=1;
    				}
    					
    				GGWFrame.pack();
    			}
    			
    		}
		});

		
		addComponent(GGWLayout,layoutConstraints,nbGroupsLabelJcb,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(nbGroupsLabelJcb);
		
		
		
		nameGroup1Label = new JLabel("Nom du groupe 1 :");
		
		nameGroupLabelPanel.setLayout(new BoxLayout(nameGroupLabelPanel, BoxLayout.Y_AXIS));
		nameGroupLabelPanel.add(nameGroup1Label);
		nameGroupLabelPanel.add(Box.createVerticalStrut(9));
		
		addComponent(GGWLayout,layoutConstraints,nameGroupLabelPanel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,20,10));
		GGWFrame.getContentPane().add(nameGroupLabelPanel);
		
		nameGroup1Jtf = new JTextField();
		
		nameGroupJtfPanel.setLayout(new BoxLayout(nameGroupJtfPanel, BoxLayout.Y_AXIS));
		nameGroupJtfPanel.add(nameGroup1Jtf);
		nameGroupJtfPanel.add(Box.createVerticalStrut(5));
		
		addComponent(GGWLayout,layoutConstraints,nameGroupJtfPanel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,20,10));
		GGWFrame.getContentPane().add(nameGroupJtfPanel);
		
		
		
		ok = new JButton("OK");
		addComponent(GGWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(GGWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		GGWFrame.getContentPane().add(annuler);
    	
	}
	
	 private void initNumberJcb(JComboBox jcb, int start, int end) {

    	for(int i=start;i<=end;i++) {
    		jcb.addItem(""+i);
    	}
    }
    	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	GGWFrame.pack();
    	GGWFrame.setResizable(false);
	    GGWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    GGWFrame.setVisible(true);
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
     	
     	GenerateGroupsWindow form = new GenerateGroupsWindow();
     	form.show();
			
    }    
}
