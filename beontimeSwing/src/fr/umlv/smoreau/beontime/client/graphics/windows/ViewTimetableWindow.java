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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;



/**
 * @author BeOnTime
 */
public class ViewTimetableWindow {

	private static final String TITRE = "Visualiser un emploi du temps";
	
	private JLabel visuEDTLabel;
	private JLabel choiceEDTLabel;
	private JLabel modeViewEDTLabel;
	private JLabel periodViewEDTLabel;
	
	private JComboBox visuEDTJcb;
	private JComboBox choiceEDTJcb;
	private JComboBox periodViewEDTJcb;
	
	private JRadioButton semestriel;
	private JRadioButton hebdomadaire;
	
	private JDateChooser periodViewEDTDate;
	
	private JButton ok;
	private JButton annuler;
	
	
	private JPanel periodViewEDTPanel = new JPanel();
	
	private JDialog VTWFrame;
	private GridBagLayout VTWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
    
    
	public ViewTimetableWindow() {
		
		VTWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		VTWFrame.getContentPane().setLayout(VTWLayout);
	    
	    initViewTimetableWindow();  
	    
	}
	
	
	private void initViewTimetableWindow() {
		
		
		visuEDTLabel = new JLabel("Visualisation d'un emploi du temps d'un(e)");
		addComponent(VTWLayout,layoutConstraints,visuEDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(visuEDTLabel);
		
		
		visuEDTJcb = new JComboBox();
		visuEDTJcb.addItem("Formation");
		visuEDTJcb.addItem("Enseignant");
		visuEDTJcb.addItem("Groupe");
		visuEDTJcb.addItem("Local");
		visuEDTJcb.addItem("Materiel");
		addComponent(VTWLayout,layoutConstraints,visuEDTJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(visuEDTJcb);
		
		
		choiceEDTLabel = new JLabel("Veuillez choisir <l'emploi du temps>");
		addComponent(VTWLayout,layoutConstraints,choiceEDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(choiceEDTLabel);
		
		choiceEDTJcb = new JComboBox();
		addComponent(VTWLayout,layoutConstraints,choiceEDTJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(choiceEDTJcb);
		
		
		modeViewEDTLabel = new JLabel("Affichage de l'emploi du temps :");
		addComponent(VTWLayout,layoutConstraints,modeViewEDTLabel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,5,10));
		VTWFrame.getContentPane().add(modeViewEDTLabel);

		
		semestriel = new JRadioButton("semestriel",true);
		semestriel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				periodViewEDTPanel.remove(periodViewEDTDate);
				periodViewEDTPanel.add(periodViewEDTJcb);
				VTWFrame.pack();
				
			}
			
		});
		addComponent(VTWLayout,layoutConstraints,semestriel,1,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		VTWFrame.getContentPane().add(semestriel);		
		
		hebdomadaire = new JRadioButton("hebdomadaire");
		hebdomadaire.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				periodViewEDTPanel.remove(periodViewEDTJcb);
				periodViewEDTPanel.add(periodViewEDTDate);
				VTWFrame.pack();
			}
			
		});
		addComponent(VTWLayout,layoutConstraints,hebdomadaire,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		VTWFrame.getContentPane().add(hebdomadaire);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(semestriel);
		buttonGroup.add(hebdomadaire);
		
		periodViewEDTLabel = new JLabel("Veuillez choisir le semestre à visualiser");
		addComponent(VTWLayout,layoutConstraints,periodViewEDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(periodViewEDTLabel);
		
		periodViewEDTDate = new JDateChooser();
		
		periodViewEDTJcb = new JComboBox();
		periodViewEDTJcb.addItem("1er semestre");
		periodViewEDTJcb.addItem("2ème semestre");
		
		periodViewEDTPanel.add(periodViewEDTJcb);
		addComponent(VTWLayout,layoutConstraints,periodViewEDTPanel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(periodViewEDTPanel);
		
		
		ok = new JButton("OK");
		addComponent(VTWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(VTWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(annuler);
		
	}
	
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
       
    	VTWFrame.pack();
    	VTWFrame.setResizable(false);
    	VTWFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	VTWFrame.setVisible(true);
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
     	
		ViewTimetableWindow form = new ViewTimetableWindow();
		form.show();
		
	}
	

}
